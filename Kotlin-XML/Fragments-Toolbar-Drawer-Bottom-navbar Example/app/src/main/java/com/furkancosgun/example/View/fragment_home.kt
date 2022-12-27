package com.furkancosgun.example.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.furkancosgun.example.R
import com.furkancosgun.example.databinding.FragmentHomeBinding


class fragment_home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.txtGreeting.text = getString(R.string.hello_from_s, "Home")
        return binding.root
    }

}