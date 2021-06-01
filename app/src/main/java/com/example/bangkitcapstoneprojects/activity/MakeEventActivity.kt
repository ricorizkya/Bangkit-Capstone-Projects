package com.example.bangkitcapstoneprojects.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.bangkitcapstoneprojects.databinding.ActivityMakeEventBinding
import com.example.bangkitcapstoneprojects.model.Eventt
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class MakeEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityMakeEventBinding
    private lateinit var auth: FirebaseAuth

    var day = 0
    var month = 0
    var year = 0

    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0

    var selectedPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pickDate()

        binding.btnChoseImage.setOnClickListener {
            Log.d("TAG", "Choose Image")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        binding.btnMakeEvent.setOnClickListener {
            uploadImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("TAG", "Photo was selected")
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitmapDrawable = BitmapDrawable(bitmap)
            binding.btnChoseImage.setBackgroundDrawable(bitmapDrawable)
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        saveDay = dayOfMonth
        saveMonth = month
        saveYear = year

        getDateTimeCalendar()
        binding.tvDateStart.text = "$saveDay $saveMonth $saveYear"
    }

    private fun pickDate() {
        binding.btnDateStart.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun uploadImage() {
        if (selectedPhotoUri == null) return
        var fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$fileName")
        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    Log.d("TAG", "Succesfully Uploaded Image: ${it.metadata?.path}")
                    ref.downloadUrl.addOnSuccessListener {
//                        it.toString()
                        Log.d("TAG", "File Location: $it")
                        saveEvent(it.toString())
                    }
                }
                .addOnFailureListener {

                }
    }

    private fun saveEvent(image: String) {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val username = currentUser?.displayName
        val email = currentUser?.email
        val id = FirebaseAuth.getInstance().uid ?: ""


        if (binding.edtTitleEvent.text.toString().isEmpty() && binding.edtDescEvent.text.toString().isEmpty() && binding.tvDateStart.text.toString().isEmpty() && image.isEmpty()) {
            binding.edtTitleEvent.error = "Field Can't Blank"
            binding.edtDescEvent.error = "Field Can't Blank"
            return Toast.makeText(applicationContext, "Field Can't Blank", Toast.LENGTH_SHORT).show()
        }

        val ref = FirebaseDatabase.getInstance().getReference("event")
        val eventId = ref.push().key
        val event = Eventt(
                id,
                username,
                email,
                binding.edtTitleEvent.text.toString(),
                binding.edtDescEvent.text.toString(),
                binding.edtFoodEvent.text.toString(),
                binding.edtCashEvent.text.toString(),
                binding.edtPeopleEvent.text.toString(),
                binding.tvDateStart.text.toString(),
                image
        )

        if (eventId != null) {
            ref.child(eventId).setValue(event)
                    .addOnSuccessListener {
                        Log.d("TAG", "Success to Save Event")
                        Toast.makeText(applicationContext, "Your Event Added Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
        }
    }
}