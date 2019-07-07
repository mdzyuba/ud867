package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import androidx.annotation.NonNull;


/**
 * The Free version fragment with ads. 
 */
public class MainActivityFragment extends MainActivityBaseFragment {

    private static final String TAG = "MainActivityFragment";
    private InterstitialAd interstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        if (root == null) {
            Log.e(TAG, "Unable to create the view.");
            return null;
        }

        initAds(root);

        initInterstitialAd();

        return root;
    }

    private void initAds(View root) {
        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    private void initInterstitialAd() {
        Context context = getContext();
        if (context == null) {
            Log.e(TAG, "Unable to create the the ad. The context is null.");
            return;
        }
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }

    /**
     * This method will display an Interstitial Ad before navigating to an activity
     * that displays a joke.
     *
     * @param joke a joke to be displayed.
     */
    @Override
    protected void showJokeTellingActivity(final String joke) {

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                AdRequest adRequest = new AdRequest.Builder().build();
                interstitialAd.loadAd(adRequest);
                MainActivityFragment.super.showJokeTellingActivity(joke);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                MainActivityFragment.super.showJokeTellingActivity(joke);
            }
        });

        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            MainActivityFragment.super.showJokeTellingActivity(joke);
        }
    }

}
