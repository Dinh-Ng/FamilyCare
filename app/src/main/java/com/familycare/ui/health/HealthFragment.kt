package com.familycare.ui.health

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.familycare.R

class HealthFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_placeholder, container, false).also {
        it.findViewById<TextView>(R.id.tvTitle).text = "Sức khỏe"
    }
}
