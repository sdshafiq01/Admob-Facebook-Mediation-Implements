package in.thbd.AdmobFacebook.AdsAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import in.thbd.AdmobFacebook.Utils.Constant;

public class RewardsAdsAdapter {

    private static final String TAG = "Loading Rewards Ads";
    private static RewardedVideoAd rewardedVideoAd_fb;
    private static RewardedAd  rewardedAd_admob;

    public static void loadRewardsAdmob(Activity activity, String selectAdsBackup, String idAds, String fbAdsId ) {

        AdRequest request = new AdRequest.Builder()
                .build();

        RewardedAd.load(activity, idAds, request, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.toString());
                        rewardedAd_admob = null;
                        if (selectAdsBackup.equalsIgnoreCase(Constant.ADS_SELECT_FACEBOOK)){
                            rewardedVideoAd_fb = new RewardedVideoAd(activity, fbAdsId);

                            rewardedVideoAd_fb.loadAd(
                                    rewardedVideoAd_fb.buildLoadAdConfig()
                                            .withAdListener(null)
                                            .build());

                        }

                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd_admob = ad;
                        Log.d(TAG, "Ad was loaded.");
//                        rewardedAd_admob.setFullScreenContentCallback(new FullScreenContentCallback() {
//                            @Override
//                            public void onAdDismissedFullScreenContent() {
//                                super.onAdDismissedFullScreenContent();
//                                rewardedAd_admob= null;
//                            }
//                        });

                    }

                });



    }

    public static void loadRewardsFacebook(Activity activity, String selectAdsBackup, String idAds, String admobAdsId ) {

        AdRequest request = new AdRequest.Builder()
                .build();

        rewardedVideoAd_fb = new RewardedVideoAd(activity, idAds);
        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {

            @Override
            public void onAdLoaded(Ad ad) {
                // Rewarded video ad is loaded and ready to be displayed
                Log.e(TAG, "Rewarded video ad is loaded and ready to be displayed!");
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Rewarded video ad clicked
                Log.d(TAG, "Rewarded video ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Rewarded Video ad impression - the event will fire when the
                // video starts playing
                Log.d(TAG, "Rewarded video ad impression logged!");
            }

            @Override
            public void onRewardedVideoCompleted() {
                // Rewarded Video View Complete - the video has been played to the end.
                // You can use this event to initialize your reward
                Log.d(TAG, "Rewarded video completed!");
                rewardedVideoAd_fb = null;
                // Call method to give reward
                // giveReward();
            }
            @Override
            public void onError(Ad ad, AdError error) {
                // Rewarded video ad failed to load
                Log.e(TAG, "Rewarded video ad failed to load: " + error.getErrorMessage());


                    if (selectAdsBackup.equalsIgnoreCase(Constant.ADS_SELECT_ADMOB)){
                    RewardedAd.load(activity, admobAdsId,
                            request, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    // Handle the error.
                                    Log.e(TAG, loadAdError.toString());
                                    rewardedAd_admob = null;


                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd ad) {
                                    rewardedAd_admob = ad;
                                    Log.e(TAG, "Ad was loaded.");
                                }
                            });
                }


            }

            @Override
            public void onRewardedVideoClosed() {
                // The Rewarded Video ad was closed - this can occur during the video
                // by closing the app, or closing the end card.
                Log.d(TAG, "Rewarded video ad closed!");
            }
        };

        rewardedVideoAd_fb.loadAd(
                rewardedVideoAd_fb.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());

    }

    public static void showAds(Activity activity){

        if (rewardedVideoAd_fb.isAdLoaded()){
            rewardedVideoAd_fb.show();

            rewardedVideoAd_fb = null;
            rewardedAd_admob = null;

        }else if (rewardedAd_admob != null){
            rewardedAd_admob.show(activity, null);

            rewardedAd_admob = null;
            rewardedVideoAd_fb = null;
        }


    }

    public static boolean isAdsAvailable(){
        Log.e(TAG, "isAdsAvailable: checking!" );
        if (rewardedVideoAd_fb != null && rewardedVideoAd_fb.isAdLoaded())  return true;
        else if (rewardedAd_admob != null) return true;
        else return false;


    }


}
