package com.example.pdp.presentation.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pdp.databinding.MessageItemBinding

class MessagesAdapter : PagedListAdapter<Message, MessagesAdapter.MessageViewHolder>(DIFF_CALLBACK) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
		val binding = MessageItemBinding.inflate(LayoutInflater.from(parent.context))
		return MessageViewHolder(binding)
	}

	override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
		val message = getItem(position)!!
		holder.bind(message)
	}

	class MessageViewHolder : RecyclerView.ViewHolder {
		private val binding: MessageItemBinding

		constructor(binding: MessageItemBinding) : super(binding.root) {
			this.binding = binding
		}

		fun bind(message: Message) {
			binding.text.setText(message.message)
		}
	}

	companion object {
		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Message?>() {
			override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
				return oldItem == newItem
			}

			override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
				return oldItem.message == newItem.message
			}
		}
	}
}
