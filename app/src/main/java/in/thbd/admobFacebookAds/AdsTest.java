package in.thbd.admobFacebookAds;

import android.app.Activity;
import android.widget.RelativeLayout;

import in.thbd.AdmobFacebook.AdsAdapter.AdsGDPR;
import in.thbd.AdmobFacebook.AdsAdapter.BannerAdsAdapter;
import in.thbd.AdmobFacebook.AdsAdapter.InitAds;
import in.thbd.AdmobFacebook.AdsAdapter.InterstitialAdsAdapter;
import in.thbd.AdmobFacebook.AdsAdapter.NativeAdsAdapter;
import in.thbd.AdmobFacebook.AdsAdapter.RewardsAdsAdapter;
import in.thbd.AdmobFacebook.Utils.Constant;

public class AdsTest {

    Activity act = null;

    public AdsTest(Activity act) {
        this.act = act;
        AdsGDPR adsGDPR = new AdsGDPR(act);
        InitAds.selectAds(act);

        RelativeLayout adsLayout = new RelativeLayout(act);

        BannerAdsAdapter.bannerAdmob(act, adsLayout, Constant.ADS_SELECT_FACEBOOK, Constant.ADMOB_TEST_BANNER_ID, Constant.FB_TEST_BANNER_FACEBOOK_ID);
        BannerAdsAdapter.bannerFacebook(act, adsLayout, Constant.ADS_SELECT_ADMOB, Constant.FB_TEST_BANNER_FACEBOOK_ID, Constant.ADMOB_TEST_BANNER_ID);

        InterstitialAdsAdapter.loadInterstitialAdmob(act, Constant.ADS_SELECT_FACEBOOK, Constant.ADMOB_TEST_INTERSTITIAL_ID, Constant.FB_TEST_INTERSTITIAL_ID);
        InterstitialAdsAdapter.loadInterstitialFacebook(act, Constant.ADS_SELECT_ADMOB, Constant.FB_TEST_INTERSTITIAL_ID, Constant.ADMOB_TEST_INTERSTITIAL_ID);

        if(InterstitialAdsAdapter.isAdsLoaded()){
            InterstitialAdsAdapter.showInterstitial(act);
        }


        RewardsAdsAdapter.loadRewardsAdmob(act, Constant.ADS_SELECT_FACEBOOK, Constant.ADMOB_TEST_REWARDS_ID, Constant.FB_TEST_REWARDS_ID);
        RewardsAdsAdapter.loadRewardsFacebook(act, Constant.ADS_SELECT_ADMOB, Constant.FB_TEST_REWARDS_ID, Constant.ADMOB_TEST_REWARDS_ID);

        if (RewardsAdsAdapter.isAdsAvailable()){
            RewardsAdsAdapter.showAds(act);

        }

        NativeAdsAdapter.loadNativeAdmob(act, adsLayout, Constant.ADS_SELECT_FACEBOOK, Constant.ADMOB_TEST_NATIVE_ID, Constant.FB_TEST_NATIVE_ADS_ID,Constant.BIG_NATIVE_ADS);

        NativeAdsAdapter.loadBigFacebookNative(act, adsLayout, Constant.ADS_SELECT_ADMOB, Constant.FB_TEST_NATIVE_ADS_ID, Constant.ADMOB_TEST_NATIVE_ID);

        NativeAdsAdapter.loadSmallFacebookNative(act, adsLayout, Constant.ADS_SELECT_ADMOB, Constant.FB_TEST_NATIVE_BANNER_ID, Constant.ADMOB_TEST_NATIVE_ID);

    }
}
