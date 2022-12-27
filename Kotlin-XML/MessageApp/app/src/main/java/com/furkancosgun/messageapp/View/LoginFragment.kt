package com.furkancosgun.messageapp.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.furkancosgun.messageapp.R
import com.furkancosgun.messageapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnLogin.setOnClickListener {
            val chatNo = binding.edtChatNo.text.toString()
            val nickName = binding.edtNick.text.toString()
            if (nickName.isEmpty()) {
                binding.edtLytNick.error = getString(R.string.nickError)
                binding.edtLytRoom.error = null
            } else if (chatNo.isEmpty()) {
                binding.edtLytRoom.error = getString(R.string.roomError)
                binding.edtLytNick.error = null
            } else {
                changeFragment(
                    MessageRoomFragment(
                        chatNo,
                        nickName
                    )
                )
            }
        }
        return binding.root
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentRenderer, fragment)
        fragmentTransaction.commit()
    }
}