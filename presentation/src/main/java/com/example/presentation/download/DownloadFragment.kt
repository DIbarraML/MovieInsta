package com.example.presentation.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.presentation.databinding.FragmentDownloadBinding

class DownloadFragment : Fragment() {

    lateinit var binding: FragmentDownloadBinding

    companion object {
        fun newInstance() = DownloadFragment()
    }

    private lateinit var viewModel: DownloadViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDownloadBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DownloadViewModel::class.java)

    }

}