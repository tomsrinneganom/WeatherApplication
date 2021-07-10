package com.example.weatherapplication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs

class ErrorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.error_fragment, container, false)
        val message = requireArguments().getString("message")
        view.findViewById<TextView>(R.id.errorMessageTextView).text = message
        return view
    }


}