package com.franco.moviesdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.franco.moviesdb.R
import com.franco.moviesdb.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val binding = FragmentHomeBinding.inflate(layoutInflater)
//        val textView: TextView = root.findViewById(R.id.text_home)
        binding.lifecycleOwner = this

        binding.viewModel = homeViewModel

        homeViewModel.response.observe(viewLifecycleOwner, Observer {
            val moviesActionList = it.movieActionList
        })
        return binding.root
    }
}