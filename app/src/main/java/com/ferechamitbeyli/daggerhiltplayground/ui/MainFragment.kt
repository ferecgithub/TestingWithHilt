package com.ferechamitbeyli.daggerhiltplayground.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ferechamitbeyli.daggerhiltplayground.R
import com.ferechamitbeyli.daggerhiltplayground.databinding.FragmentMainBinding
import com.ferechamitbeyli.daggerhiltplayground.model.Blog
import com.ferechamitbeyli.daggerhiltplayground.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment
    constructor(
        private val someString: String
    ): Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    private val viewModel: MainViewModel by viewModels()

    private val TAG : String = "DEBUG"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Here is some string: $someString")

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogsEvent)


    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?){
        if(message != null) binding.text.text = message else binding.text.text = "Unknown error."
    }

    private fun appendBlogTitles(blogs: List<Blog>){
        val sb = StringBuilder()
        for(blog in blogs){
            sb.append(blog.title + "\n")
        }
        binding.text.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        binding.progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}