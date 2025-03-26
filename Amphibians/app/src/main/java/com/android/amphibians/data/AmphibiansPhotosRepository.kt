package com.android.amphibians.data

import com.android.amphibians.network.Amphibians
import com.android.amphibians.network.AmphibiansApiService

interface AmphibiansPhotosRepository {
    suspend fun getAmphibiansPhotos(): List<Amphibians>
}

class NetworkAmphibiansPhotosRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansPhotosRepository {
    override suspend fun getAmphibiansPhotos(): List<Amphibians> = amphibiansApiService.getPhotos()
}