package com.ferechamitbeyli.daggerhiltplayground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ferechamitbeyli.daggerhiltplayground.R
import com.ferechamitbeyli.daggerhiltplayground.databinding.ActivityMainBinding
import com.ferechamitbeyli.daggerhiltplayground.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }

}



















