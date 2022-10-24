package com.example.spacex.model


import com.google.gson.annotations.SerializedName

data class FirstStage(
    @SerializedName("cores")
    val cores: List<Core>
)