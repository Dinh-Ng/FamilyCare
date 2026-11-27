package com.familycare

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private enum class Screen {
        LOGIN,
        REGISTER,
        ROLE_SELECTION,
        CREATE_FAMILY,
        JOIN_FAMILY,
        HOME
    }

    private enum class FamilyRole {
        PARENT,
        MEMBER
    }

    private val screenHistory = mutableListOf<Screen>()
    private var currentScreen = Screen.LOGIN
    private var selectedRole = FamilyRole.PARENT
    private var selectedMemberRange = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                goBack()
            }
        })

        showScreen(Screen.LOGIN, addToHistory = false)
    }

    private fun showScreen(screen: Screen, addToHistory: Boolean = true) {
        if (addToHistory && screen != currentScreen) {
            screenHistory.add(currentScreen)
        }
        currentScreen = screen

        val layout = when (screen) {
            Screen.LOGIN -> R.layout.login
            Screen.REGISTER -> R.layout.activity_register
            Screen.ROLE_SELECTION -> R.layout.activity_role_selection
            Screen.CREATE_FAMILY -> R.layout.activity_create_family
            Screen.JOIN_FAMILY -> R.layout.activity_join_family
            Screen.HOME -> R.layout.activity_main
        }
        setContentView(layout)
        applySystemInsets()
        bindCurrentScreen()
    }

    private fun applySystemInsets() {
        val root = findViewById<ViewGroup>(android.R.id.content).getChildAt(0) ?: return
        val initialLeft = root.paddingLeft
        val initialTop = root.paddingTop
        val initialRight = root.paddingRight
        val initialBottom = root.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                initialLeft + systemBars.left,
                initialTop + systemBars.top,
                initialRight + systemBars.right,
                initialBottom + systemBars.bottom
            )
            insets
        }
        ViewCompat.requestApplyInsets(root)
    }

    private fun bindCurrentScreen() {
        when (currentScreen) {
            Screen.LOGIN -> bindLogin()
            Screen.REGISTER -> bindRegister()
            Screen.ROLE_SELECTION -> bindRoleSelection()
            Screen.CREATE_FAMILY -> bindCreateFamily()
            Screen.JOIN_FAMILY -> bindJoinFamily()
            Screen.HOME -> Unit
        }
    }

    private fun bindLogin() {
        findViewById<View>(R.id.btnLogin).setOnClickListener {
            showScreen(Screen.ROLE_SELECTION)
        }
        findViewById<View>(R.id.btnGoogle).setOnClickListener {
            showScreen(Screen.ROLE_SELECTION)
        }
        findViewById<View>(R.id.tvRegister).setOnClickListener {
            showScreen(Screen.REGISTER)
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

    private fun bindRegister() {
        findViewById<View>(R.id.btnRegisterBack).setOnClickListener { goBack() }
        findViewById<View>(R.id.tvRegisterLogin).setOnClickListener {
            screenHistory.clear()
            showScreen(Screen.LOGIN, addToHistory = false)
        }
        findViewById<View>(R.id.btnRegisterGoogle).setOnClickListener {
            showScreen(Screen.ROLE_SELECTION)
        }

        findViewById<View>(R.id.btnRegister).setOnClickListener {
            val name = findViewById<EditText>(R.id.edtRegisterName)
            val email = findViewById<EditText>(R.id.edtRegisterEmail)
            val password = findViewById<EditText>(R.id.edtRegisterPassword)
            val confirmation = findViewById<EditText>(R.id.edtRegisterPasswordConfirm)

            when {
                name.text.isNullOrBlank() -> name.showInputError("Nhập họ và tên")
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString().trim()).matches() ->
                    email.showInputError("Nhập địa chỉ email hợp lệ")
                password.text.length < 8 -> password.showInputError("Mật khẩu cần ít nhất 8 ký tự")
                confirmation.text.toString() != password.text.toString() ->
                    confirmation.showInputError("Mật khẩu xác nhận chưa khớp")
                else -> showScreen(Screen.ROLE_SELECTION)
            }
        }
    }

    private fun bindRoleSelection() {
        findViewById<View>(R.id.btnRoleBack).setOnClickListener { goBack() }
        findViewById<View>(R.id.cardParentRole).setOnClickListener {
            selectedRole = FamilyRole.PARENT
            updateRoleSelection()
        }
        findViewById<View>(R.id.cardMemberRole).setOnClickListener {
            selectedRole = FamilyRole.MEMBER
            updateRoleSelection()
        }
        findViewById<View>(R.id.btnContinueRole).setOnClickListener {
            showScreen(Screen.CREATE_FAMILY)
        }
        updateRoleSelection()
    }

    private fun updateRoleSelection() {
        val parentSelected = selectedRole == FamilyRole.PARENT
        val parentIndicator = findViewById<TextView>(R.id.indicatorParent)
        val memberIndicator = findViewById<TextView>(R.id.indicatorMember)
        val continueButton = findViewById<TextView>(R.id.btnContinueRole)

        parentIndicator.text = if (parentSelected) "✓" else "○"
        parentIndicator.setBackgroundResource(
            if (parentSelected) R.drawable.bg_primary_button else R.drawable.bg_input
        )
        parentIndicator.setTextColor(
            if (parentSelected) android.graphics.Color.WHITE else ROLE_UNSELECTED_COLOR
        )

        memberIndicator.text = if (parentSelected) "○" else "✓"
        memberIndicator.setBackgroundResource(
            if (parentSelected) R.drawable.bg_input else R.drawable.bg_primary_button
        )
        memberIndicator.setTextColor(
            if (parentSelected) ROLE_UNSELECTED_COLOR else android.graphics.Color.WHITE
        )

        continueButton.text = if (parentSelected) {
            "Tiếp tục với vai trò Bố / Mẹ  →"
        } else {
            "Tiếp tục với vai trò Ông / Bà  →"
        }
    }

    private fun bindCreateFamily() {
        findViewById<View>(R.id.btnCreateFamilyBack).setOnClickListener { goBack() }

        val smallRange = findViewById<TextView>(R.id.chipMember3to5)
        val largeRange = findViewById<TextView>(R.id.chipMember6to10)
        smallRange.setOnClickListener {
            selectedMemberRange = 3
            updateMemberRangeSelection()
        }
        largeRange.setOnClickListener {
            selectedMemberRange = 6
            updateMemberRangeSelection()
        }
        updateMemberRangeSelection()

        findViewById<View>(R.id.btnCreateFamilyContinue).setOnClickListener {
            showScreen(Screen.JOIN_FAMILY)
        }
    }

    private fun updateMemberRangeSelection() {
        val smallRange = findViewById<TextView>(R.id.chipMember3to5)
        val largeRange = findViewById<TextView>(R.id.chipMember6to10)
        val smallSelected = selectedMemberRange == 3

        smallRange.text = if (smallSelected) "✓  3 - 5 người" else "3 - 5 người"
        smallRange.setBackgroundResource(
            if (smallSelected) R.drawable.bg_primary_button else R.drawable.bg_input
        )
        smallRange.setTextColor(
            if (smallSelected) android.graphics.Color.WHITE else ROLE_TEXT_COLOR
        )

        largeRange.text = if (smallSelected) "6 - 10 người" else "✓  6 - 10 người"
        largeRange.setBackgroundResource(
            if (smallSelected) R.drawable.bg_input else R.drawable.bg_primary_button
        )
        largeRange.setTextColor(
            if (smallSelected) ROLE_TEXT_COLOR else android.graphics.Color.WHITE
        )
    }

    private fun bindJoinFamily() {
        findViewById<View>(R.id.btnJoinFamilyBack).setOnClickListener { goBack() }

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
            val code = codeInputs.joinToString(separator = "") { it.text.toString().trim() }
                .uppercase()
            if (!code.matches(Regex("[A-Z0-9]{6}"))) {
                codeInputs.firstOrNull { it.text.isNullOrBlank() }?.apply {
                    error = "Nhập đủ 6 ký tự"
                    requestFocus()
                }
            } else {
                showScreen(Screen.HOME)
            }
        }
    }

    private fun EditText.showInputError(message: String) {
        error = message
        requestFocus()
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun goBack() {
        hideKeyboard()
        if (screenHistory.isNotEmpty()) {
            showScreen(screenHistory.removeAt(screenHistory.lastIndex), addToHistory = false)
        } else if (currentScreen != Screen.LOGIN) {
            showScreen(Screen.LOGIN, addToHistory = false)
        } else {
            finish()
        }
    }

    private fun hideKeyboard() {
        val focused = currentFocus ?: return
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(focused.windowToken, 0)
        focused.clearFocus()
    }

    companion object {
        private const val ROLE_UNSELECTED_COLOR = 0xFF9CAFA8.toInt()
        private const val ROLE_TEXT_COLOR = 0xFF526C63.toInt()
    }
}
