package com.example.spacex.ui.viewmodel

import androidx.lifecycle.*
import com.imecatro.domain.launches.usecases.GetAllLaunchesUseCase
import com.imecatro.data.room.model.LaunchRoomEntity
import com.imecatro.domain.launches.usecases.UpdateAllLaunchesUseCase
import com.example.spacex.ui.UpdateUiState
import com.imecatro.domain.launches.model.LaunchDomainModel
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
    private val updateAllLaunchesUseCase:UpdateAllLaunchesUseCase,
) : ViewModel() {

    //LiveData is an observable data holder class
    //private val _launchesList: MutableLiveData<List<LaunchRoomEntity>?> = MutableLiveData()
    val launchesList: LiveData<List<LaunchDomainModel>> = getAllLaunchesUseCase().asLiveData()

    private val _singleLaunch: MutableLiveData<LaunchDomainModel?> = MutableLiveData()
    val singleLaunch: LiveData<LaunchDomainModel?> get() = _singleLaunch

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

    private fun getDetailsOf(n: Int): LaunchDomainModel? {

        return launchesList.value?.find { singleEntity ->
            singleEntity.launchNumber == n

        }
    }

    fun launchWasSelected(flightNumber: Int) {
        _singleLaunch.value = getDetailsOf(flightNumber)
    }
}