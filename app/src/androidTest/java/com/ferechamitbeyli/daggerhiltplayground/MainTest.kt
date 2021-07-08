 package com.ferechamitbeyli.daggerhiltplayground

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.launchActivity
import com.ferechamitbeyli.daggerhiltplayground.di.AppModule
import com.ferechamitbeyli.daggerhiltplayground.ui.MainActivity
import com.ferechamitbeyli.daggerhiltplayground.ui.MainFragment
import com.ferechamitbeyli.daggerhiltplayground.ui.MainFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@UninstallModules(AppModule::class)
@HiltAndroidTest
class MainTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

     @Inject
     lateinit var someString: String

     @Inject
     lateinit var fragmentFactory: MainFragmentFactory

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun someTest() {
        assertThat(someString, containsString("TESTING"))
    }

    // For activity test
    @Test
    fun mainActivityTest() {
        val scenario = launchActivity<MainActivity>()
    }

    // For fragment test
    @Test
    fun mainFragmentTest() {
        val scenario = launchFragmentInHiltContainer<MainFragment> (
            factory = fragmentFactory
        ) { }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object TestAppModule {
        @Singleton
        @Provides
        fun provideSomeString(): String {
            return "It's some TESTING String!"
        }
    }



}