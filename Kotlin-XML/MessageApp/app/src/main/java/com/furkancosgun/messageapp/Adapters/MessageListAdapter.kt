package com.furkancosgun.messageapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.furkancosgun.messageapp.Model.TextMessage
import com.furkancosgun.messageapp.R

class MessageListAdapter(
    val context: Context,
    var messageList: MutableList<TextMessage>?
) :
    RecyclerView.Adapter<MessageListAdapter.MessageViewHolder>() {
    inner class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtUname: TextView
        val txtMessage: TextView
        val txtTime: TextView
        val card: CardView

        init {
            txtUname = view.findViewById(R.id.txtUname)
            txtMessage = view.findViewById(R.id.txtMessage)
            card = view.findViewById(R.id.card)
            txtTime = view.findViewById(R.id.txtTime)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val design = LayoutInflater.from(context).inflate(R.layout.message_row, parent, false)
        return MessageViewHolder(design)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.txtMessage.text = messageList?.get(position)!!.text
        holder.txtUname.text = messageList!![position].from
        holder.txtTime.text = messageList!![position].time
    }

    internal fun setItems(items: MutableList<TextMessage>?) {
        this.messageList = items
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return messageList!!.size
    }
}