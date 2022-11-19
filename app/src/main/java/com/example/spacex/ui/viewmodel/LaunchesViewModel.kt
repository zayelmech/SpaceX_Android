package com.example.spacex.ui.viewmodel

import androidx.lifecycle.*
import com.example.spacex.usecases.GetAllLaunchesUseCase
import com.example.spacex.database.LaunchRoomEntity
import com.example.spacex.usecases.UpdateAllLaunchesUseCase
import com.example.spacex.ui.UpdateUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

//A ViewModel holds data to be displayed in a fragment or activity associated with the ViewModel

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val getAllLaunchesUseCase: GetAllLaunchesUseCase,
    private val updateAllLaunchesUseCase: UpdateAllLaunchesUseCase,
) : ViewModel() {

    //LiveData is an observable data holder class
    //private val _launchesList: MutableLiveData<List<LaunchRoomEntity>?> = MutableLiveData()
    val launchesList: LiveData<List<LaunchRoomEntity>> = getAllLaunchesUseCase().asLiveData()

    private val _singleLaunch: MutableLiveData<LaunchRoomEntity?> = MutableLiveData()
    val singleLaunch: LiveData<LaunchRoomEntity?> get() = _singleLaunch

    // Backing property to avoid state updates from other classes
    private val _uiState: MutableStateFlow<UpdateUiState?> =
        MutableStateFlow(UpdateUiState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<UpdateUiState?> = _uiState

    fun getLaunchesFromApi() {

        _uiState.value = UpdateUiState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            updateAllLaunchesUseCase().let { throwable ->

                if (throwable == null) {
                    _uiState.value = UpdateUiState.Success("Data was updated")
                } else {
                    _uiState.value =
                        UpdateUiState.Error(Throwable(throwable.message))
                }
            }
        }
    }

    private fun getDetailsOf(n: Int): LaunchRoomEntity? {

        return launchesList.value?.find { singleEntity ->
            singleEntity.launchNumber == n

        }
    }

    fun launchWasSelected(flightNumber: Int) {
        _singleLaunch.value = getDetailsOf(flightNumber)
    }
}