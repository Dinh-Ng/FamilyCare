package com.familycare

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge

class RoleSelectionActivity : BaseFlowActivity() {
    private var parentSelected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_role_selection)
        applySystemInsets()
        bindBack(R.id.btnRoleBack)

        findViewById<View>(R.id.cardParentRole).setOnClickListener {
            parentSelected = true
            updateSelection()
        }
        findViewById<View>(R.id.cardMemberRole).setOnClickListener {
            parentSelected = false
            updateSelection()
        }
        findViewById<View>(R.id.btnContinueRole).setOnClickListener {
            openScreen(CreateFamilyActivity::class.java)
        }
        updateSelection()
    }

    private fun updateSelection() {
        val parentIndicator = findViewById<TextView>(R.id.indicatorParent)
        val memberIndicator = findViewById<TextView>(R.id.indicatorMember)
        val continueButton = findViewById<TextView>(R.id.btnContinueRole)

        parentIndicator.text = if (parentSelected) "✓" else "○"
        parentIndicator.setBackgroundResource(
            if (parentSelected) R.drawable.bg_primary_button else R.drawable.bg_input
        )
        parentIndicator.setTextColor(
            if (parentSelected) android.graphics.Color.WHITE else UNSELECTED_COLOR
        )
        memberIndicator.text = if (parentSelected) "○" else "✓"
        memberIndicator.setBackgroundResource(
            if (parentSelected) R.drawable.bg_input else R.drawable.bg_primary_button
        )
        memberIndicator.setTextColor(
            if (parentSelected) UNSELECTED_COLOR else android.graphics.Color.WHITE
        )
        continueButton.text = if (parentSelected) {
            "Tiếp tục với vai trò Bố / Mẹ  →"
        } else {
            "Tiếp tục với vai trò Ông / Bà  →"
        }
    }

    private companion object {
        const val UNSELECTED_COLOR = 0xFF9CAFA8.toInt()
    }
}
