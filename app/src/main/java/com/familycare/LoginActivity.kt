package com.familycare

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        findViewById<TextView>(R.id.tv_go_to_register)?.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
