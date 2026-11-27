package com.familycare

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

abstract class BaseFlowActivity : AppCompatActivity() {
    protected fun openScreen(activityClass: Class<out AppCompatActivity>) {
        startActivity(Intent(this, activityClass))
    }

    protected fun bindBack(viewId: Int) {
        findViewById<View>(viewId).setOnClickListener { finish() }
    }

    protected fun applySystemInsets() {
        val root = findViewById<ViewGroup>(android.R.id.content).getChildAt(0) ?: return
        val left = root.paddingLeft
        val top = root.paddingTop
        val right = root.paddingRight
        val bottom = root.paddingBottom
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(left + bars.left, top + bars.top, right + bars.right, bottom + bars.bottom)
            insets
        }
        ViewCompat.requestApplyInsets(root)
    }
}
