package com.example.spacex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacex.GetAllLaunchesUseCase
import com.example.spacex.database.LaunchRoomEntity
import com.example.spacex.ui.LaunchesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//A ViewModel holds data to be displayed in a fragment or activity associated with the ViewModel

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val getAllLaunchesUseCase: GetAllLaunchesUseCase
) : ViewModel() {

    //LiveData is an observable data holder class
    private val _launchesList: MutableLiveData<List<LaunchRoomEntity>?> = MutableLiveData()
    val launchesList: LiveData<List<LaunchRoomEntity>?> get() = _launchesList

    private val _singleLaunch: MutableLiveData<LaunchRoomEntity?> = MutableLiveData()
    val singleLaunch: LiveData<LaunchRoomEntity?> get() = _singleLaunch

    // Backing property to avoid state updates from other classes
    private val _uiState: MutableStateFlow<LaunchesUiState?> =
        MutableStateFlow(LaunchesUiState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<LaunchesUiState?> = _uiState

    fun getLaunchesFromApi() {
        _uiState.value = LaunchesUiState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            getAllLaunchesUseCase()?.let { allLaunches ->
                _launchesList.postValue(allLaunches)

                if (allLaunches.isNotEmpty()) {
                    _uiState.value = LaunchesUiState.Success(allLaunches)
                } else {
                    _uiState.value =
                        LaunchesUiState.Error(Throwable("Local database has no data storage"))
                }
            }


        }
    }

    private fun getDetailsOf(n: Int): LaunchRoomEntity? {

        return _launchesList.value?.find { singleEntity ->
            singleEntity.launchNumber == n

        }

    }

    fun launchWasSelected(flightNumber: Int) {
        _singleLaunch.value = getDetailsOf(flightNumber)
    }
}