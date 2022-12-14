package com.imecatro.data.network

import com.imecatro.data.network.model.LaunchesEntity
import retrofit2.Response
import retrofit2.http.GET

interface SpacexService {
    @GET(LAUNCHES_LIST)
    suspend fun getLaunches(): Response<List<LaunchesEntity>>

    companion object {
        const val BASE_URL = "https://api.spacexdata.com/"
        const val LAUNCHES_LIST = "v3/launches"
    }
}