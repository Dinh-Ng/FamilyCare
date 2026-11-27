package com.familycare

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog

class LoginActivity : BaseFlowActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)
        applySystemInsets()

        findViewById<View>(R.id.btnLogin).setOnClickListener {
            openScreen(RoleSelectionActivity::class.java)
        }
        findViewById<View>(R.id.btnGoogle).setOnClickListener {
            openScreen(RoleSelectionActivity::class.java)
        }
        findViewById<View>(R.id.tvRegister).setOnClickListener {
            openScreen(RegisterActivity::class.java)
        }
        findViewById<View>(R.id.tvForgotPassword).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Quên mật khẩu?")
                .setMessage("Chức năng đặt lại mật khẩu sẽ được hỗ trợ khi có dịch vụ tài khoản. Bạn có thể đăng ký tài khoản mới hoặc thử lại sau.")
                .setPositiveButton("Đã hiểu", null)
                .show()
        }

        val password = findViewById<EditText>(R.id.edtPassword)
        findViewById<View>(R.id.btnTogglePassword).setOnClickListener {
            val selection = password.selectionStart.coerceAtLeast(0)
            password.transformationMethod =
                if (password.transformationMethod is PasswordTransformationMethod) {
                    HideReturnsTransformationMethod.getInstance()
                } else {
                    PasswordTransformationMethod.getInstance()
                }
            password.setSelection(selection.coerceAtMost(password.text.length))
        }
    }
}
