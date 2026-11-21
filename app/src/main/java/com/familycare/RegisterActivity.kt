package com.familycare

import android.os.Bundle
import android.text.Html
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        findViewById<FrameLayout>(R.id.btn_back)?.setOnClickListener {
            finish()
        }

        findViewById<TextView>(R.id.tv_go_to_login)?.setOnClickListener {
            finish()
        }

        val tvTerms = findViewById<TextView>(R.id.tv_terms_text)
        val termsHtml = "Tôi đồng ý với <b><u><font color='#0E6C56'>Điều khoản dịch vụ</font></u></b> và <b><u><font color='#0E6C56'>Chính sách bảo mật dữ liệu y tế</font></u></b> của gia đình."
        tvTerms?.text = Html.fromHtml(termsHtml, Html.FROM_HTML_MODE_LEGACY)
    }
}
