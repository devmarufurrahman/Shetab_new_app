package com.shetab.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shetab.main.databinding.MsgItemForTeamMemberBinding
import com.shetab.main.model.ChatMessage

class ChatAdapter(private val messages: MutableList<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MsgItemForTeamMemberBinding.inflate(inflater, parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

    fun addMessage(message: ChatMessage) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

    inner class ChatViewHolder(private val binding: MsgItemForTeamMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: ChatMessage) {
            if (message.isMe) {
                binding.ItemViewLay.visibility = View.GONE
                binding.messageTextViewLay.visibility = View.VISIBLE
                binding.messageTextView.text = message.message
            } else {
                binding.ItemViewLay.visibility = View.VISIBLE
                binding.messageTextViewLay.visibility = View.GONE
                binding.messageTextView.text = message.message
            }
        }
    }
}
