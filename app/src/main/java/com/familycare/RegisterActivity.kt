package com.familycare

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge

class RegisterActivity : BaseFlowActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        applySystemInsets()

        bindBack(R.id.btnRegisterBack)
        findViewById<View>(R.id.tvRegisterLogin).setOnClickListener { finish() }
        findViewById<View>(R.id.btnRegisterGoogle).setOnClickListener {
            openScreen(RoleSelectionActivity::class.java)
        }
        findViewById<View>(R.id.btnRegister).setOnClickListener {
            val name = findViewById<EditText>(R.id.edtRegisterName)
            val email = findViewById<EditText>(R.id.edtRegisterEmail)
            val password = findViewById<EditText>(R.id.edtRegisterPassword)
            val confirmation = findViewById<EditText>(R.id.edtRegisterPasswordConfirm)

            when {
                name.text.isNullOrBlank() -> name.showError("Nhập họ và tên")
                !Patterns.EMAIL_ADDRESS.matcher(email.text.toString().trim()).matches() ->
                    email.showError("Nhập địa chỉ email hợp lệ")
                password.text.length < 8 -> password.showError("Mật khẩu cần ít nhất 8 ký tự")
                confirmation.text.toString() != password.text.toString() ->
                    confirmation.showError("Mật khẩu xác nhận chưa khớp")
                else -> openScreen(RoleSelectionActivity::class.java)
            }
        }
    }

    private fun EditText.showError(message: String) {
        error = message
        requestFocus()
    }
}
