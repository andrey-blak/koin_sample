package com.example.pdp.presentation.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pdp.databinding.MessagesFragmentBinding
import com.example.pdp.utils.observe

class MessagesFragment : Fragment() {
	private lateinit var binding: MessagesFragmentBinding
	private val args: MessagesFragmentArgs by navArgs()
	private val viewModel by viewModels<MessagesViewModel>()
	private lateinit var messagesAdapter: MessagesAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		args.userName
		observe(viewModel.messages, { pagedMessages ->
			messagesAdapter.submitList(pagedMessages)
		})
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = MessagesFragmentBinding.inflate(inflater)
		messagesAdapter = MessagesAdapter()
		binding.messagesRecyclerView.adapter = messagesAdapter
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}
}
