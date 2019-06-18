package com.example.pdp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.pdp.databinding.LoginFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
	private lateinit var binding: LoginFragmentBinding
	private val viewModel by viewModel<LoginViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel.getUser().observe(this, Observer { user ->
			// todo implement
			Toast.makeText(context, "Logged as " + user.name, Toast.LENGTH_SHORT).show()
		})
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
	}
}
