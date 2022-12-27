package com.furkancosgun.messageapp.View

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkancosgun.messageapp.Adapters.MessageListAdapter
import com.furkancosgun.messageapp.Model.TextMessage
import com.furkancosgun.messageapp.ViewModel.MessageViewModel
import com.furkancosgun.messageapp.databinding.FragmentMessageRoomBinding
import java.time.LocalDateTime.*

class MessageRoomFragment(private val roomId: String, private val nickname: String) : Fragment() {
    private lateinit var binding: FragmentMessageRoomBinding
    private val viewModel: MessageViewModel = MessageViewModel(roomId)
    private lateinit var adapter: MessageListAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageRoomBinding.inflate(inflater, container, false)
        binding.btnSend.setOnClickListener {
            val message = TextMessage(
                from = nickname,
                to = roomId,
                text = binding.edtMessage.text.toString(),
                time = now().toString()
            )
            binding.edtMessage.text?.clear()
            viewModel.saveMessage(message)
        }
        binding.recylerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recylerView.setHasFixedSize(true)

        adapter = MessageListAdapter(requireContext(), viewModel.liveMessages.value)
        binding.recylerView.adapter = adapter
        viewModel.liveMessages.observe(this.viewLifecycleOwner) {
            adapter.setItems(it)
        }

        return binding.root
    }


}