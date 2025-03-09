package com.shetab.main.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shetab.main.adapter.ChatAdapter
import com.shetab.main.databinding.ActivityLiveChatPageBinding
import com.shetab.main.model.ChatMessage

class LiveChatPage : AppCompatActivity() {
    private lateinit var binding : ActivityLiveChatPageBinding
    private lateinit var chatAdapter: ChatAdapter
    private val chatMessages = mutableListOf<ChatMessage>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveChatPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupRecyclerView()

        // Send button click event
        binding.sendIconLay.setOnClickListener {
            sendMessage()
        }

    }


    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(chatMessages)
        binding.rvLiveChatList.apply {
            layoutManager = LinearLayoutManager(this@LiveChatPage)
            adapter = chatAdapter
            setHasFixedSize(true)
        }
    }



    private fun sendMessage() {
        val messageText = binding.messageEditText.text.toString().trim()

        if (messageText.isNotEmpty()) {
            val message = ChatMessage(
                sender = "You",
                message = messageText,
                isMe = true
            )

            chatAdapter.addMessage(message)

            chatAdapter.notifyDataSetChanged()

            binding.messageEditText.text.clear()

            binding.rvLiveChatList.scrollToPosition(chatMessages.size - 1)
        } else {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
        }
    }


}