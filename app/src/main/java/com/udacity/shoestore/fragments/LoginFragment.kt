package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false)
        val binding : FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.btnLogIn.setOnClickListener {
            if(binding.txtEmail.text.equals("") || binding.txtPass.equals("")){
                Toast.makeText(requireContext(), "Email/Password can't be empty", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "You Logged Successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }

        binding.btnSignUp.setOnClickListener {
            if(binding.txtEmail.text.equals("") || binding.txtPass.equals("")){
                Toast.makeText(requireContext(), "Email/Password can't be empty", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "You SignedUp Successfully", Toast.LENGTH_SHORT).show()
            }


        }
        return binding.root
    }

}