
package com.familycare

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnGoogle: FrameLayout
    private lateinit var btnTogglePassword: ImageButton
    private lateinit var tvForgotPassword: TextView
    private lateinit var tvRegister: TextView

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        setupEvents()
    }

    private fun initViews() {
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnGoogle = findViewById(R.id.btnGoogle)
        btnTogglePassword = findViewById(R.id.btnTogglePassword)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        tvRegister = findViewById(R.id.tvRegister)
    }

    private fun setupEvents() {

        btnTogglePassword.setOnClickListener {
            val cursor = edtPassword.selectionStart

            if (isPasswordVisible) {
                edtPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                edtPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }

            isPasswordVisible = !isPasswordVisible

            edtPassword.setSelection(
                cursor.coerceIn(0, edtPassword.length())
            )
        }

        btnLogin.setOnClickListener {
            login()
        }

        btnGoogle.setOnClickListener {
            Toast.makeText(
                this,
                "Chức năng Google Sign-In đang được tích hợp",
                Toast.LENGTH_SHORT
            ).show()
        }

        tvForgotPassword.setOnClickListener {
            Toast.makeText(
                this,
                "Chức năng quên mật khẩu",
                Toast.LENGTH_SHORT
            ).show()
        }

        tvRegister.setOnClickListener {
            Toast.makeText(
                this,
                "Chức năng đăng ký tài khoản",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun login() {
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString()

        if (email.isEmpty()) {
            edtEmail.error = "Vui lòng nhập email"
            edtEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            edtPassword.error = "Vui lòng nhập mật khẩu"
            edtPassword.requestFocus()
            return
        }

        Toast.makeText(
            this,
            "Thông tin hợp lệ, tiến hành xác thực!",
            Toast.LENGTH_SHORT
        ).show()

        // TODO: Tích hợp Firebase Authentication
    }
}
