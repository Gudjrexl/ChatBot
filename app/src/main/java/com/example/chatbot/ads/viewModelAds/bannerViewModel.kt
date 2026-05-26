package com.example.chatbot.ads.viewModelAds


import androidx.lifecycle.ViewModel
import com.example.chatbot.ads.model.AdState
import com.example.chatbot.ads.repoAds.BannerRepository
import com.example.chatbot.ads.repoAds.BannerRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BannerViewModel : ViewModel() {

    private val repository: BannerRepository = BannerRepositoryImpl()

    private val _adState =

        MutableStateFlow<AdState>(
            AdState.Idle
        )

    val adState =
        _adState.asStateFlow()

    fun loadBanner() {

        _adState.value =
            AdState.Loading
    }

    fun onBannerLoaded() {

        _adState.value =
            AdState.Loaded
    }

    fun onBannerFailed() {

        _adState.value =
            AdState.Failed
    }

    fun getBannerAdId():
            String {

        return repository
            .getBannerAdId()
    }
}