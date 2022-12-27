package com.furkancosgun.example.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.furkancosgun.example.R
import com.furkancosgun.example.databinding.FragmentSearchBinding


class fragment_search : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.txtGreeting.text = getString(R.string.hello_from_s, "Search")
        return binding.root
    }

}