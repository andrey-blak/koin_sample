package com.example.pdp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.navigation.fragment.findNavController
import com.example.pdp.R
import com.example.pdp.databinding.LoginFragmentBinding
import com.example.pdp.utils.observe

class LoginFragment : Fragment() {
	private lateinit var binding: LoginFragmentBinding
	private val viewModel by viewModels<LoginViewModel> { SavedStateVMFactory(this) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.getNavigationToMessages().observe(this, Observer { name ->
			val action = LoginFragmentDirections.actionLoginFragmentToMessagesFragment(name)
			findNavController().navigate(action)
		})
		observe(viewModel.getNavigationToRegister()) {
			findNavController().navigate(R.id.action_global_registerDialog)
		}
		viewModel.getError().observe(this, Observer { error ->
			Toast.makeText(context, error.messageResId, Toast.LENGTH_SHORT).show()
		})
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = LoginFragmentBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.loginButton.setOnClickListener {
			val login = binding.loginInput.text.toString()
			val password = binding.passwordInput.text.toString()
			viewModel.login(login, password)
		}
		binding.registerButton.setOnClickListener {
			viewModel.register()
		}
	}
}
