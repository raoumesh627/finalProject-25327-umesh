package com.example.umesh_yadav_25327.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.umesh_yadav_25327.R

class InfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_info, container, false)

        // Set the toolbar title
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "About"
        return root
    }
}