package com.ferechamitbeyli.daggerhiltplayground.ui


import androidx.lifecycle.*
import com.ferechamitbeyli.daggerhiltplayground.model.Blog
import com.ferechamitbeyli.daggerhiltplayground.util.DataState
import com.ferechamitbeyli.daggerhiltplayground.repository.MainRepository
import com.ferechamitbeyli.daggerhiltplayground.ui.MainStateEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is GetBlogsEvent -> {
                    mainRepository.getBlogs()
                        .onEach {dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                None -> {
                    // who cares
                }
            }
        }
    }

}


sealed class MainStateEvent{

    object GetBlogsEvent: MainStateEvent()

    object None: MainStateEvent()
}


















