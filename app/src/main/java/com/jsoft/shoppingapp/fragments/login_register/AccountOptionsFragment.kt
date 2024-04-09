package com.jsoft.shoppingapp.fragments.login_register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jsoft.shoppingapp.R
import com.jsoft.shoppingapp.databinding.FragmentAccountOptionsBinding
import com.jsoft.shoppingapp.databinding.FragmentIntroductionBinding

class AccountOptionsFragment : Fragment(R.layout.fragment_account_options) {

    private lateinit var binding: FragmentAccountOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountOptionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonRegisterAccountOptions.setOnClickListener {
                findNavController().navigate(R.id.action_accountOptionsFragment_to_registerFragment)
            }

            loginAccountOptions.setOnClickListener {
                findNavController().navigate(R.id.action_accountOptionsFragment_to_loginFragment)
            }
        }

    }


}
