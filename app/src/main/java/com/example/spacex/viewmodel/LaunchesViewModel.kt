package com.example.spacex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacex.model.LaunchesEntity
import com.example.spacex.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//A ViewModel holds data to be displayed in a fragment or activity associated with the ViewModel

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val getAllLaunchesUseCase: GetAllLaunchesUseCase
) : ViewModel() {

    //LiveData is an observable data holder class
    private val _launchesList: MutableLiveData<List<LaunchesEntity>> = MutableLiveData()
    val launchesList: LiveData<List<LaunchesEntity>> get() = _launchesList

    private val _singleLaunch: MutableLiveData<LaunchesEntity> = MutableLiveData()
    val singleLaunch: LiveData<LaunchesEntity> get() = _singleLaunch

    fun getLaunchesFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            getAllLaunchesUseCase.invoke()?.let { allLaunches ->
                _launchesList.postValue(allLaunches)
            }
        }
    }

    private fun getDetailsOf(n: Int): LaunchesEntity {

        return _launchesList.value!!.find { singleEntity ->
            singleEntity.flightNumber == n

        } ?: throw Throwable("Entity is empty")

    }

    fun launchWasSelected(flightNumber: Int) {
        _singleLaunch.value = getDetailsOf(flightNumber)
    }
}