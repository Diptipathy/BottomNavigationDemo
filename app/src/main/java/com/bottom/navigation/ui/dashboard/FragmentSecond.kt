package com.bottom.navigation.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bottom.navigation.databinding.FragmentSecondBinding

class FragmentSecond : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val factory = SecondViewModelFactory(requireContext())
        val secondViewModel = ViewModelProvider(this, factory = factory)[SecondViewModel::class.java]

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val root: View = binding.root

        secondViewModel.text1.observe(viewLifecycleOwner) {
            binding.textAlphabet.text = it
        }

        secondViewModel.text2.observe(viewLifecycleOwner) {
            binding.textNumber.text = it
        }

        secondViewModel.text3.observe(viewLifecycleOwner) {
            binding.textCharacter.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}