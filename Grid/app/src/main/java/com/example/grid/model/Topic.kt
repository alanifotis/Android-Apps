package com.example.grid.model


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val field: Int,
    val count: Int,
    @DrawableRes val image: Int
)