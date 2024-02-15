package in.thbd.AdmobFacebook.AdsAdapter;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Objects;

import in.thbd.AdmobFacebook.Utils.Constant;

public class InterstitialAdsAdapter {
    private static InterstitialAd mInterstitialAd;
    private static com.facebook.ads.InterstitialAd interstitialAdFb;
    private static InterstitialAdListener interstitialAdListener;

    public static int counter = 0;


    public static void loadInterstitialAdmob(Activity activity, String selectAdsBackup, String admobAdsID, String fbAdsID) {

        AdRequest request = new AdRequest.Builder()
                .build();

        InterstitialAd.load(activity, admobAdsID, request,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;


                        if (Objects.equals(Constant.ADS_SELECT_FACEBOOK, selectAdsBackup)) {
                            interstitialAdFb = new com.facebook.ads.InterstitialAd(activity, fbAdsID);
                            interstitialAdFb.loadAd();

                        }


                    }
                });


    }

    public static void loadInterstitialFacebook(Activity activity, String selectAdsBackup, String fbAdsID, String admobAdsID) {

        interstitialAdFb = new com.facebook.ads.InterstitialAd(activity, fbAdsID);
        interstitialAdFb.loadAd();

        if (!interstitialAdFb.isAdLoaded()) {

            AdRequest request = new AdRequest.Builder()
                    .build();
            InterstitialAd.load(activity, admobAdsID, request,
                    new InterstitialAdLoadCallback() {

                    });

        }


    }

    public static boolean isAdsLoaded() {

        return mInterstitialAd != null || interstitialAdFb != null && interstitialAdFb.isAdLoaded();

    }

    public static void showInterstitial(Activity activity) {


        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);

        } else {

            if (interstitialAdFb != null && interstitialAdFb.isAdLoaded()) {
                interstitialAdFb.show();
            }

        }


    }


}