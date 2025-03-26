package model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class Hero(
    @StringRes val superHeroName: Int,
    @StringRes val superHeroDescription: Int,
    @DrawableRes val superHeroImage: Int

)