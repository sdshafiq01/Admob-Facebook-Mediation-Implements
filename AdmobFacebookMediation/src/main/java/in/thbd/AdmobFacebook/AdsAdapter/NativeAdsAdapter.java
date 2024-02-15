package in.thbd.AdmobFacebook.AdsAdapter;

import static android.content.ContentValues.TAG;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.List;

import in.thbd.AdmobFacebook.R;
import in.thbd.AdmobFacebook.Utils.Constant;
import in.thbd.AdmobFacebook.Utils.NativeTemplateStyle;
import in.thbd.AdmobFacebook.Utils.TemplateView;

public class NativeAdsAdapter {

    private static com.facebook.ads.NativeAd nativeAdFb;
    private static NativeBannerAd nativeBannerAd;
    private static AdLoader adLoader;
    private static NativeAdLayout nativeAdLayout;
    private static LinearLayout smallAdView;
    private static NativeAdLayout adView;
    public static int counter = 0;

    // ads size = big, small
    public static void loadNativeAdmob(Activity activity, RelativeLayout adLayout, String selectAdsBackup, String idNative,
                                       String fbNativeId, String size) {

//                LayoutInflater layoutInflater = (LayoutInflater)
//                        activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                adLayout.addView(layoutInflater.inflate(R.layout.ads_native_ads_layout, null, false), 1);


        ColorDrawable background = new ColorDrawable();
        background.setColor(activity.getResources().getColor(android.R.color.white));

         adLoader = new AdLoader.Builder(activity, idNative)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {

                        TemplateView view = null;
                        if (size.contains("big")){
                            view = (TemplateView) LayoutInflater.from(activity)
                                    .inflate(R.layout.ads_native_med_ads_layout, adLayout, false);
                        }else{
                            view = (TemplateView) LayoutInflater.from(activity)
                                    .inflate(R.layout.ads_native_ads_layout, adLayout, false);
                        }


//                                view.setVisibility(View.VISIBLE);
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().withMainBackgroundColor(background).build();
//                            TemplateView template = findViewById(R.id.my_template);
                        view.setStyles(styles);
                        view.setNativeAd(nativeAd);
                        adLayout.setVisibility(View.VISIBLE);
                        adLayout.removeAllViews();
                        adLayout.addView(view);
                    }
                }
                ).withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);

                        if (Constant.ADS_SELECT_FACEBOOK.equals(selectAdsBackup)) {

                            if (size.contains("big")){
                                loadBigFacebookNative(activity,fbNativeId,adLayout);
                            }else{
                                loadSmallFacebookNative(activity,adLayout,fbNativeId);
                            }


                        }

                    }
                }).build();


        adLoader.loadAd(new AdRequest.Builder().build());

//        adLoader.loadAd(new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2).addKeyword(Hpk3).addKeyword(Hpk4).build());


    }

    private static void loadBigFacebookNative(Activity activity,String idNativeBackup,RelativeLayout adLayout) {
        nativeAdFb = new com.facebook.ads.NativeAd(activity, idNativeBackup);

        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e(TAG, "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                adLayout.setVisibility(View.VISIBLE);
                inflateAd(activity,nativeAdFb,adLayout);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d(TAG, "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d(TAG, "Native ad impression logged!");
            }
        };

        // Request an ad
        nativeAdFb.loadAd(
                nativeAdFb.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());
    }

    public static void loadSmallFacebookNative(Activity activity, RelativeLayout adLayout,String fbAdsID ) {

        nativeBannerAd = new NativeBannerAd(activity, fbAdsID);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                adLayout.setVisibility(View.VISIBLE);
                inflateSmallAd(activity,nativeBannerAd,adLayout);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        // load the ad
        nativeBannerAd.loadAd(
                nativeBannerAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());

    }





    private static void inflateAd(Activity activity, com.facebook.ads.NativeAd nativeAdFb, RelativeLayout adLayout) {

        nativeAdFb.unregisterView();
        // Add the Ad view into the ad container.
//        nativeAdLayout = activity.findViewById(R.id.native_ad_container);
        LayoutInflater inflater = LayoutInflater.from(activity);
        if (adLayout != null){
            adLayout.removeAllViews();
        }

        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView = (NativeAdLayout) inflater.inflate(R.layout.layout_fb_native_ads, adLayout, false);
        assert adLayout != null;
        adLayout.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = activity.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAdFb, nativeAdLayout);

       if (adChoicesContainer != null){
           adChoicesContainer.removeAllViews();
       }

        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAdFb.getAdvertiserName());
        nativeAdBody.setText(nativeAdFb.getAdBodyText());
        nativeAdSocialContext.setText(nativeAdFb.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAdFb.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAdFb.getAdCallToAction());
        sponsoredLabel.setText(nativeAdFb.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAdFb.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);

    }


    private static void inflateSmallAd(Activity activity, NativeBannerAd nativeBannerAd, RelativeLayout adLayout) {
        // Unregister last ad
        nativeBannerAd.unregisterView();

        // Add the Ad view into the ad container.
//        nativeAdLayout = findViewById(R.id.native_banner_ad_container);
        LayoutInflater inflater = LayoutInflater.from(activity);
        if (adLayout != null){
            adLayout.removeAllViews();
        }
        // Inflate the Ad view.  The layout referenced is the one you created in the last step.
        smallAdView = (LinearLayout) inflater.inflate(R.layout.layout_fb_native_small_ads, adLayout, false);
        assert adLayout != null;
        adLayout.addView(smallAdView);

        // Add the AdChoices icon
        RelativeLayout adChoicesContainer = smallAdView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        TextView nativeAdTitle = smallAdView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = smallAdView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = smallAdView.findViewById(R.id.native_ad_sponsored_label);
        MediaView nativeAdIconView = smallAdView.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction = smallAdView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(smallAdView, nativeAdIconView, clickableViews);
    }


}
