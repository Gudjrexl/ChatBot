package com.example.chatbot.ads.repoAds


import com.example.chatbot.ads.config.AdIds

class BannerRepositoryImpl
    : BannerRepository {

    override fun getBannerAdId():
            String {

        return AdIds.BANNER_TEST_ID
    }
}