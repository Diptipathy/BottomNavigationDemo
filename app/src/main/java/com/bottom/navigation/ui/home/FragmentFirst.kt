package com.bottom.navigation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bottom.navigation.databinding.FragmentFirstBinding

class FragmentFirst : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val firstViewModel = ViewModelProvider(this)[FirstViewModel::class.java]
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btnCreate: Button = binding.btnCreate
        btnCreate.setOnClickListener {
            Log.d("Dipti", "Create Button Clicked: ")
            firstViewModel.createFileIfNotExists(requireContext())
            firstViewModel.startBackgroundWork(requireContext())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}