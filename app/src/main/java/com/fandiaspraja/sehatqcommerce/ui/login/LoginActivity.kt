package com.fandiaspraja.sehatqcommerce.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fandiaspraja.sehatqcommerce.R
import com.fandiaspraja.sehatqcommerce.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_login.*

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            if (email.text.isNullOrEmpty() || password.text.isNullOrEmpty()){
                Toast.makeText(applicationContext, "Email atau Password tidak boleh kosong", Toast.LENGTH_LONG).show()
            }else{
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
        }

        btn_fb.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        btn_google.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }
}