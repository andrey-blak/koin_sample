package com.example.pdp.presentation.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pdp.databinding.MessagesFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessagesFragment : Fragment() {
	private lateinit var binding: MessagesFragmentBinding
	private val args: MessagesFragmentArgs by navArgs()
	private val viewModel by viewModel<MessagesViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		args.userName
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = MessagesFragmentBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}
}
