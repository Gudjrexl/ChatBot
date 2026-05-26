package com.example.chatbot.ads



import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd

class AppOpenManager(
    private val context: Context
) {

    companion object {

        private const val TAG =
            "APP_OPEN_AD"

        private const val APP_OPEN_TEST_ID =
            "ca-app-pub-3940256099942544/9257395921"
    }

    private var appOpenAd:
            AppOpenAd? = null

    private var isLoading =
        false

    private var isShowing =
        false

    private var hasShownAd =
        false

    fun loadAd() {

        if (isLoading || appOpenAd != null) {

            return
        }

        isLoading = true

        val request =
            AdRequest.Builder()
                .build()

        AppOpenAd.load(

            context,

            APP_OPEN_TEST_ID,

            request,

            object :
                AppOpenAd
                .AppOpenAdLoadCallback() {

                override fun onAdLoaded(
                    ad: AppOpenAd
                ) {

                    Log.d(
                        TAG,
                        "APP OPEN LOADED"
                    )

                    appOpenAd = ad

                    isLoading = false

                    /*
                    SHOW ONLY ONCE
                    */

                    if (
                        context is Activity
                        &&
                        !hasShownAd
                    ) {

                        hasShownAd = true

                        showAdIfAvailable(
                            context
                        )
                    }
                }

                override fun onAdFailedToLoad(
                    error: LoadAdError
                ) {

                    Log.d(
                        TAG,
                        "FAILED : ${error.message}"
                    )

                    isLoading = false
                }
            }
        )
    }

    fun showAdIfAvailable(
        activity: Activity
    ) {

        if (isShowing) {

            return
        }

        if (appOpenAd == null) {

            loadAd()

            return
        }

        appOpenAd?.fullScreenContentCallback =

            object :
                FullScreenContentCallback() {

                override fun onAdDismissedFullScreenContent() {

                    appOpenAd = null

                    isShowing = false

                    loadAd()
                }

                override fun onAdShowedFullScreenContent() {

                    isShowing = true
                }
            }

        appOpenAd?.show(activity)
    }
}