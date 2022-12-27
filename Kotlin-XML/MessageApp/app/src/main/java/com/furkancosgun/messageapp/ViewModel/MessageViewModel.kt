package com.furkancosgun.messageapp.ViewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkancosgun.messageapp.Model.TextMessage
import com.google.firebase.database.*

class MessageViewModel(val roomNo: String) : ViewModel() {

    private var db: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref: DatabaseReference = db.getReference("messages")
    var liveMessages = MutableLiveData<MutableList<TextMessage>>(null)


    init {
        liveMessages = MutableLiveData(
            mutableListOf<TextMessage>(
                TextMessage(
                    from = "XFC",
                    to = "0000",
                    text = "HELLO WORLD",
                    time = "2022-12-26T21:27:46.387872"
                )
            )
        )
        getAllMessages()
    }

    private fun getAllMessages() {
        val query = ref.orderByChild("to").equalTo(this.roomNo)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                liveMessages.value?.clear()
                for (snap in snapshot.children) {
                    val message = snap.getValue(TextMessage::class.java)
                    message?.let {
                        liveMessages.value?.add(message)
                        Log.d("XFC", "onDataChange: ")
                    }
                }
                liveMessages.value = liveMessages.value
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(ContentValues.TAG, error.message.toString())
            }
        })
    }

    fun saveMessage(message: TextMessage) {
        ref.push().setValue(message)
    }
}