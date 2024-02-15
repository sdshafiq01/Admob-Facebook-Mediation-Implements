package in.thbd.admobFacebookAds;

import android.app.Activity;

import in.thbd.AdmobFacebook.AdsAdapter.AdsGDPR;
import in.thbd.AdmobFacebook.AdsAdapter.InitAds;

public class AdsTest {

    Activity act = null;

    public AdsTest(Activity act) {
        this.act = act;
        AdsGDPR adsGDPR = new AdsGDPR(act);
        InitAds.selectAds(act);


    }
}
