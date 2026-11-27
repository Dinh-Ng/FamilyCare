package com.familycare

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge

class JoinFamilyActivity : BaseFlowActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_join_family)
        applySystemInsets()
        bindBack(R.id.btnJoinFamilyBack)

        val codeInputs = listOf(
            findViewById<EditText>(R.id.edtFamilyCode1),
            findViewById<EditText>(R.id.edtFamilyCode2),
            findViewById<EditText>(R.id.edtFamilyCode3),
            findViewById<EditText>(R.id.edtFamilyCode4),
            findViewById<EditText>(R.id.edtFamilyCode5),
            findViewById<EditText>(R.id.edtFamilyCode6)
        )
        codeInputs.forEachIndexed { index, input ->
            input.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
                override fun afterTextChanged(s: Editable?) {
                    if (!s.isNullOrEmpty() && index < codeInputs.lastIndex) {
                        codeInputs[index + 1].requestFocus()
                    } else if (s.isNullOrEmpty() && index > 0) {
                        codeInputs[index - 1].requestFocus()
                    }
                }
            })
        }
        findViewById<View>(R.id.btnJoinStart).setOnClickListener {
            val code = codeInputs.joinToString("") { it.text.toString().trim() }.uppercase()
            if (code.matches(Regex("[A-Z0-9]{6}"))) {
                openScreen(MainActivity::class.java)
            } else {
                codeInputs.firstOrNull { it.text.isNullOrBlank() }?.apply {
                    error = "Nhập đủ 6 ký tự"
                    requestFocus()
                }
            }
        }
    }
}
