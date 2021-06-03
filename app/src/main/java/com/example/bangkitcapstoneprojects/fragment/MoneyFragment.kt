package com.example.bangkitcapstoneprojects.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bangkitcapstoneprojects.R
import com.example.bangkitcapstoneprojects.databinding.FragmentMoneyBinding
import com.example.bangkitcapstoneprojects.model.DonateMoney
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class MoneyFragment : Fragment() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: FragmentMoneyBinding
    private lateinit var auth: FirebaseAuth
    var selectedPhotoUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoneyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getIDEvent()

        binding.btnImageTransfer.setOnClickListener {
            val intentMoney = Intent(Intent.ACTION_PICK)
            intentMoney.type = "image/*"
            startActivityForResult(intentMoney, 0)
        }

        binding.btnDonateMoney.setOnClickListener {
            saveData()
            binding.btnImageTransfer.setBackgroundResource(R.drawable.poster_image)
            binding.edtMoneyEvent.setText(null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            val bitmapMoney = MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedPhotoUri)
            val bitmapDrawableMoney = BitmapDrawable(bitmapMoney)
            binding.btnImageTransfer.setBackgroundDrawable(bitmapDrawableMoney)
        }
    }

    private fun getIDEvent() {
        val extras = activity?.intent
        if (extras != null) {
            var eventId = extras.getStringExtra(FoodFragment.EXTRA_ID)

            if (eventId != null) {
                binding.tvIdEvent.text = eventId.toString()
            }
        }
    }

    private fun saveData() {
        if (selectedPhotoUri == null) return
        var fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("images/transfer/$fileName")
        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    Log.d("TAG", "Successfully Uploaded Image: ${it.metadata?.path}")
                    ref.downloadUrl.addOnSuccessListener {
                        Log.d("TAG", "File Location: $it")
                        saveDataFood(it.toString())
                    }
                }
                .addOnFailureListener {
                    Log.d("TAG", "Failed to Upload Image")
                }
    }

    private fun saveDataFood(imageMoney: String) {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val email = currentUser?.email
        val id = FirebaseAuth.getInstance().uid ?: ""

        if (binding.edtMoneyEvent.text.toString().isEmpty()) {
            binding.edtMoneyEvent.error = "Field Can't Blank"
        }else {
            val ref = FirebaseDatabase.getInstance().getReference("donate")
            val donateId = ref.push().key
            val donate = DonateMoney(
                    id,
                    binding.tvIdEvent.text.toString(),
                    email,
                    binding.edtMoneyEvent.text.toString(),
                    imageMoney
            )

            if (donateId != null) {
                ref.child(donateId).setValue(donate)
                        .addOnSuccessListener {
                            Log.d("TAG", "Donate Success")
                            Toast.makeText(activity?.applicationContext, "Donate Success", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                        finish()
                        }
            }
        }
    }
}