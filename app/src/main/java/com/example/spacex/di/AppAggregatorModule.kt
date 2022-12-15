package com.example.spacex.di

import com.imecatro.data.network.di.NetworkModule
import com.imecatro.data.room.di.RoomModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [RoomModule::class, NetworkModule::class])
interface AppAggregatorModule