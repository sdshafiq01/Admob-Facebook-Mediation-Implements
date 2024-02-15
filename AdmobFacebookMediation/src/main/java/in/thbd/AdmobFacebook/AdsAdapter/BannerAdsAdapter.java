package in.thbd.AdmobFacebook.AdsAdapter;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import in.thbd.AdmobFacebook.Utils.Constant;

public class BannerAdsAdapter {


    public static void bannerAdmob(Activity activity, RelativeLayout layAds, String selectAdsBackup, String adsId, String fbAdsId) {

        AdRequest request = new AdRequest.Builder()
                .build();
        AdView adView;
        adView = new AdView(activity);
        adView.setAdUnitId(adsId);
        layAds.removeAllViews();
        layAds.addView(adView);
        AdSize adSize = getAdSize(activity);
        adView.setAdSize(adSize);
        adView.loadAd(request);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                 if (Constant.ADS_SELECT_FACEBOOK.equals(selectAdsBackup)){
                        com.facebook.ads.AdView adViews = new com.facebook.ads.AdView(activity,
                                fbAdsId, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        adViews.loadAd();
                        layAds.removeAllViews();
                        layAds.addView(adViews);
                        adViews.loadAd();

                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });


    }

    public static void bannerFacebook(Activity activity, RelativeLayout layAds, String selectAdsBackup, String fbAdsId, String admobAdsId) {

        com.facebook.ads.AdView adViews = new com.facebook.ads.AdView(activity,
                fbAdsId, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        adViews.loadAd();
        layAds.removeAllViews();
        layAds.addView(adViews);
        adViews.loadAd();


        if (adViews.isActivated()){
            if (Constant.ADS_SELECT_ADMOB.equals(selectAdsBackup)){

                AdRequest request = new AdRequest.Builder()
                        .build();
                AdView adView;
                adView = new AdView(activity);
                adView.setAdUnitId(admobAdsId);
                layAds.removeAllViews();
                layAds.addView(adView);
                AdSize adSize = getAdSize(activity);
                adView.setAdSize(adSize);
                adView.loadAd(request);

            }
        }




    }


    private static AdSize getAdSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

}
