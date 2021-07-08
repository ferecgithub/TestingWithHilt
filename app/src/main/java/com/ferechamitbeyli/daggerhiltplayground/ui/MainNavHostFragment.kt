package com.ferechamitbeyli.daggerhiltplayground.ui

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Hilt injects dependencies before onCreate() and prevents injecting after onCreate().
 * Using this class to avoid the crash of NavHostFragment because we are using Navigator Component.
 * We basically implemented it ourselves and wrote it to the name attribute in FragmentContainerView.
 */
@AndroidEntryPoint
class MainNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = fragmentFactory
    }
}