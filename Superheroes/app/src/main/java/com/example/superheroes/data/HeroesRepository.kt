package com.example.superheroes.data

import model.Hero
import com.example.superheroes.R

object HeroesRepository {
    val heroes = listOf(
        Hero(
            superHeroName = R.string.hero1,
            superHeroDescription = R.string.description1,
            superHeroImage = R.drawable.android_superhero1
        ),
        Hero(
            superHeroName = R.string.hero2,
            superHeroDescription = R.string.description2,
            superHeroImage = R.drawable.android_superhero2
        ),
        Hero(
            superHeroName = R.string.hero3,
            superHeroDescription = R.string.description3,
            superHeroImage = R.drawable.android_superhero3
        ),
        Hero(
            superHeroName = R.string.hero4,
            superHeroDescription = R.string.description4,
            superHeroImage = R.drawable.android_superhero4
        ),
        Hero(
            superHeroName = R.string.hero5,
            superHeroDescription = R.string.description5,
            superHeroImage = R.drawable.android_superhero5
        ),
        Hero(
            superHeroName = R.string.hero6,
            superHeroDescription = R.string.description6,
            superHeroImage = R.drawable.android_superhero6
        )
    )
}