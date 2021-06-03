package com.example.bangkitcapstoneprojects.activity

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.bangkitcapstoneprojects.R
import com.example.bangkitcapstoneprojects.adapter.SectionPagerAdapter
import com.example.bangkitcapstoneprojects.databinding.ActivityEventDetailBinding
import com.example.bangkitcapstoneprojects.fragment.MoneyFragment
import com.example.bangkitcapstoneprojects.model.DonateFood
import com.example.bangkitcapstoneprojects.model.DonateMoney
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class EventDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_EVENT = "extra_event"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_USER = "extra_user"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_FOOD = "extra_food"
        const val EXTRA_MONEY = "extra_money"
        const val EXTRA_IMAGE = "extra_image"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.donate_food,
            R.string.donate_money
        )
    }

    private lateinit var binding: ActivityEventDetailBinding
    private lateinit var auth: FirebaseAuth
    var selectedPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getEventData()
        viewPagerConfig()

//        binding.btnImageTransfer.setOnClickListener {
//            val intentTransfer = Intent(Intent.ACTION_PICK)
//            intentTransfer.type = "image/*"
//            startActivityForResult(intentTransfer, 0)
//        }
//
//        binding.btnImageFood.setOnClickListener {
//            val intentFood = Intent(Intent.ACTION_PICK)
//            intentFood.type = "image/*"
//            startActivityForResult(intentFood, 0)
//        }
//
//        binding.btnDonateMoney.setOnClickListener {
//            saveDonateMoney()
//        }
//
//        binding.btnDonateFood.setOnClickListener {
//            saveDonateFood()
//        }

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
//            selectedPhotoUri = data.data
//            val bitmapTransfer = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
//            val bitmapDrawableTransfer = BitmapDrawable(bitmapTransfer)
////            binding.btnImageTransfer.setBackgroundDrawable(bitmapDrawableTransfer)
////
////            val bitmapFood = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
////            val bitmapDrawableFood = BitmapDrawable(bitmapFood)
////            binding.btnImageFood.setBackgroundDrawable(bitmapDrawableFood)
//        }
//    }

    private fun viewPagerConfig() {
        val sectionPagerAdapter = SectionPagerAdapter(this, this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun getEventData() {
        val extras = intent
        if (extras != null) {

            var eventTitle = extras.getStringExtra(EXTRA_EVENT)
            var eventID = extras.getStringExtra(EXTRA_ID)
            var eventEmail = extras.getStringExtra(EXTRA_EMAIL)
            var eventUser = extras.getStringExtra(EXTRA_USER)
            var eventDesc = extras.getStringExtra(EXTRA_DESC)
            var eventDate = extras.getStringExtra(EXTRA_DATE)
            var eventFood = extras.getStringExtra(EXTRA_FOOD)
            var eventMoney = extras.getStringExtra(EXTRA_MONEY)
            var eventImage = extras.getStringExtra(EXTRA_IMAGE)

            if (eventTitle != null) {
                binding.tvTitle.text = eventTitle.toString()
                binding.tvIdEvent.text = eventID.toString()
                binding.tvEmail.text = eventEmail.toString()
                binding.tvUser.text = eventUser.toString()
                binding.tvDesc.text = eventDesc.toString()
                binding.tvDate.text = eventDate.toString()
                binding.tvFood.text = eventFood.toString()
                binding.tvMoney.text = eventMoney.toString()
                Glide.with(this)
                        .load(eventImage)
                        .into(binding.imgPoster)
            }
        }
    }

    private fun saveDonateMoney() {
        if (selectedPhotoUri == null) return
        var fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("images/transfer/$fileName")
        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    Log.d("TAG", "Successfully Uploaded Image: ${it.metadata?.path}")
                    ref.downloadUrl.addOnSuccessListener {
                        Log.d("TAG", "File Location: $it")
                        saveDataMoney(it.toString())
                    }
                }
                .addOnFailureListener {  }
    }

    private fun saveDataMoney(imageTransfer: String) {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val email = currentUser?.email
        val id = FirebaseAuth.getInstance().uid ?: ""

//        if (binding.edtMoneyEvent.text.toString().isEmpty()) {
////            binding.edtMoneyEvent.error = "Field Can't Blank"
//        }

        val ref = FirebaseDatabase.getInstance().getReference("donate")
        val donateId = ref.push().key
//        val donate = DonateMoney(
//                id,
//                binding.tvIdEvent.text.toString(),
//                email,
//                binding.tvFood.text.toString(),
//                binding.tvMoney.text.toString(),
//                binding.edtMoneyEvent.text.toString(),
//                imageTransfer
//        )

//        if (donateId != null) {
//            ref.child(donateId).setValue(donate)
//                    .addOnSuccessListener {
//                        Log.d("TAG", "Success to Save Event")
//                        Toast.makeText(applicationContext, "Success to Donate", Toast.LENGTH_SHORT).show()
////                        val intent = Intent(this, MainActivity::class.java)
////                        startActivity(intent)
////                        finish()
//                    }
//        }
    }

    private fun saveDonateFood() {
        if (selectedPhotoUri == null) return
        var fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("images/food/$fileName")
        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    Log.d("TAG", "Successfully Uploaded Image: ${it.metadata?.path}")
                    ref.downloadUrl.addOnSuccessListener {
                        Log.d("TAG", "File Location: $it")
                        saveDataFood(it.toString())
                    }
                }
                .addOnFailureListener {  }
    }

    private fun saveDataFood(imageFood: String) {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val email = currentUser?.email
        val id = FirebaseAuth.getInstance().uid ?: ""
//
//        if (binding.edtFoodEvent.text.toString().isEmpty()) {
//            binding.edtFoodEvent.error = "Field Can't Blank"
//        }
//
//        val ref = FirebaseDatabase.getInstance().getReference("donate")
//        val donateId = ref.push().key
//        val donate = DonateFood(
//                id,
//                binding.tvIdEvent.text.toString(),
//                email,
//                binding.tvFood.text.toString(),
//                binding.tvMoney.text.toString(),
//                binding.edtFoodEvent.text.toString(),
//                imageFood
//        )

//        if (donateId != null) {
//            ref.child(donateId).setValue(donate)
//                    .addOnSuccessListener {
//                        Log.d("TAG", "Success to Save Event")
//                        Toast.makeText(applicationContext, "Success to Donate", Toast.LENGTH_SHORT).show()
////                        val intent = Intent(this, MainActivity::class.java)
////                        startActivity(intent)
////                        finish()
//                    }
//        }
    }
}