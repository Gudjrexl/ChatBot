package com.example.chatbot.ads


import android.app.Activity
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatbot.ads.viewModelAds.BannerViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.AdListener

@Composable
fun BannerAdView(

    vm: BannerViewModel = viewModel()
) {

    val context =
        LocalContext.current

    val activity =
        context as Activity

    val configuration =
        LocalConfiguration.current

    val screenWidth =

        configuration
            .screenWidthDp

    val adSize =

        AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(

            context,

            screenWidth
        )

    LaunchedEffect(Unit) {

        vm.loadBanner()
    }

    AndroidView(

        factory = {

            AdView(context).apply {

                setAdSize(adSize)

                adUnitId =
                    vm.getBannerAdId()

                layoutParams =

                    ViewGroup.LayoutParams(

                        ViewGroup
                            .LayoutParams
                            .MATCH_PARENT,

                        ViewGroup
                            .LayoutParams
                            .WRAP_CONTENT
                    )

                adListener =

                    object : AdListener() {

                        override fun onAdLoaded() {

                            vm.onBannerLoaded()
                        }

                        override fun onAdFailedToLoad(
                            error: LoadAdError
                        ) {

                            vm.onBannerFailed()
                        }
                    }

                loadAd(

                    AdRequest.Builder()
                        .build()
                )
            }
        }
    )
}