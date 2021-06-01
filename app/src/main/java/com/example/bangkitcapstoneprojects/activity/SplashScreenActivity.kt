package com.example.bangkitcapstoneprojects.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.bangkitcapstoneprojects.databinding.ActivitySplashScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        binding.splashScreen.alpha = 0f
        binding.splashScreen.animate().setDuration(1500).alpha(1f).withEndAction {
            Handler().postDelayed({
                if (user != null) {
                    val dashboard = Intent(this, MainActivity::class.java)
                    startActivity(dashboard)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }else {
                    val login = Intent(this, LoginActivity::class.java)
                    startActivity(login)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            },2000)
        }
    }
}