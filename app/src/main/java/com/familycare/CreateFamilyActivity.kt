package com.familycare

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge

class CreateFamilyActivity : BaseFlowActivity() {
    private var selectedRange = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_family)
        applySystemInsets()
        bindBack(R.id.btnCreateFamilyBack)

        findViewById<TextView>(R.id.chipMember3to5).setOnClickListener {
            selectedRange = 3
            updateRange()
        }
        findViewById<TextView>(R.id.chipMember6to10).setOnClickListener {
            selectedRange = 6
            updateRange()
        }
        findViewById<View>(R.id.btnCreateFamilyContinue).setOnClickListener {
            openScreen(JoinFamilyActivity::class.java)
        }
        updateRange()
    }

    private fun updateRange() {
        val small = findViewById<TextView>(R.id.chipMember3to5)
        val large = findViewById<TextView>(R.id.chipMember6to10)
        val smallSelected = selectedRange == 3

        small.text = if (smallSelected) "✓  3 - 5 người" else "3 - 5 người"
        small.setBackgroundResource(
            if (smallSelected) R.drawable.bg_primary_button else R.drawable.bg_input
        )
        small.setTextColor(if (smallSelected) android.graphics.Color.WHITE else TEXT_COLOR)

        large.text = if (smallSelected) "6 - 10 người" else "✓  6 - 10 người"
        large.setBackgroundResource(
            if (smallSelected) R.drawable.bg_input else R.drawable.bg_primary_button
        )
        large.setTextColor(if (smallSelected) TEXT_COLOR else android.graphics.Color.WHITE)
    }

    private companion object {
        const val TEXT_COLOR = 0xFF526C63.toInt()
    }
}
