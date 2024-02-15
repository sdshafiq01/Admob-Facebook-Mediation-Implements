# Admob Facebook Mediation Implements With Auto GDPR

This library creating for easy to use Admob & Facebook ads easily. You can load & show Admob or Facebook ads once code easily. You can't control ads click listener here. This library just shows your ads perfectly in an easy way.


Recent Updated Version: [![](https://jitpack.io/v/sdshafiq01/Admob-Facebook-Mediation-Implements.svg)](https://jitpack.io/#sdshafiq01/Admob-Facebook-Mediation-Implements)


1> Add into settings.gradle

  	dependencyResolutionManagement {
  		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  		repositories {
  			
  			maven { url 'https://jitpack.io' }
  		}
  	}
 
2> Add into build.gradle (Module :app)

	dependencies {
	        implementation 'com.github.sdshafiq01:Admob-Facebook-Mediation-Implements:1.1.01'
	}

3> Intit Google Admob GDPR
		
  	AdsGDPR adsGDPR = new AdsGDPR(act);

4> Add Banner layout into XML

	// add this layout into the activity.xml or fragment.xml file. you can set 2 types of ads here => banner ads / native ads
    <RelativeLayout
          android:id="@+id/adsLayout"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          app:layout_constraintBottom_toBottomOf="parent" />

5> Add into java

       // init ads 
         InitAds.selectAds(act);
	 
        // init banner ads layout
        RelativeLayout adsLayout = findViewById(R.id.adsLayout);

# Show Admob banner ads in the layout
	
 	//if Admob ads are not loaded then automatically load Facebook banner ads
        BannerAdsAdapter.bannerAdmob(act, adsLayout, Constant.ADS_SELECT_FACEBOOK, Constant.ADMOB_TEST_BANNER_ID, Constant.FB_TEST_BANNER_FACEBOOK_ID);

# Show Facebook banner ads in the layout

	//if Facebook ads are not loaded then automatically load Admob banner ads
        BannerAdsAdapter.bannerFacebook(act, adsLayout, Constant.ADS_SELECT_ADMOB, Constant.ADMOB_TEST_BANNER_ID, Constant.FB_TEST_BANNER_FACEBOOK_ID);



# Load Admob interstitial ads
		
  	//If Admob ads are not loaded then automatically load Facebook interstitial ads
 	 InterstitialAdsAdapter.loadInterstitialAdmob(act, Constant.ADS_SELECT_FACEBOOK, Constant.ADMOB_TEST_INTERSTITIAL_ID, Constant.FB_TEST_INTERSTITIAL_ID);
        
# Load Facebook interstitial ads

	//If Facebook ads are not loaded then automatically load Admob interstitial ads
	InterstitialAdsAdapter.loadInterstitialFacebook(act, Constant.ADS_SELECT_ADMOB, Constant.FB_TEST_INTERSTITIAL_ID, Constant.ADMOB_TEST_INTERSTITIAL_ID);
       
# Show Admob/Facebook interstitial ads

	// Check whether Interstitial Ads is loaded or not. if any one ad loaded then it shows automatically
        if(InterstitialAdsAdapter.isAdsLoaded()){
            InterstitialAdsAdapter.showInterstitial(act);
	    
        }
	

# Load Admob Rewards ads
		
  	//If Admob ads are not loaded then automatically load Facebook Rewards ads
 	  RewardsAdsAdapter.loadRewardsAdmob(act, Constant.ADS_SELECT_FACEBOOK, Constant.ADMOB_TEST_REWARDS_ID, Constant.FB_TEST_REWARDS_ID);
       

        
# Load Facebook Rewards ads

	//If Facebook ads are not loaded then automatically load Admob Rewards ads
	 RewardsAdsAdapter.loadRewardsFacebook(act, Constant.ADS_SELECT_ADMOB, Constant.FB_TEST_REWARDS_ID, Constant.ADMOB_TEST_REWARDS_ID);
       
# Show Admob/Facebook Rewards ads

	// Check whether Rewards Ads is loaded or not. if any one ad loaded then it shows automatically
       
       if (RewardsAdsAdapter.isAdsAvailable()){
            RewardsAdsAdapter.showAds(act);

        }

////////////////////////////////////



# Load Admob/Facebook Native Ads With NativeAdsAdapter

## Admob Big Native Ads (Constant.BIG_NATIVE_ADS)<br>
![alt text](https://github.com/sdshafiq01/Admob-Facebook-Mediation-Implements/blob/main/images/admob-big.png?raw=true)

## Admob Small Native Ads (Constant.SMALL_NATIVE_ADS)<br>
![alt text](https://github.com/sdshafiq01/Admob-Facebook-Mediation-Implements/blob/main/images/admob-smal.png?raw=true)
    	
     	// load Admob big  or small Size Native Ads, if not loaded Admob ads then auto load Facebook big or small size native ads.
        NativeAdsAdapter.loadNativeAdmob(act, adsLayout, Constant.ADS_SELECT_FACEBOOK, Constant.ADMOB_TEST_NATIVE_ID, Constant.FB_TEST_NATIVE_ADS_ID, Constant.BIG_NATIVE_ADS);



## Admob Big Native Ads<br>
![alt text](https://github.com/sdshafiq01/Admob-Facebook-Mediation-Implements/blob/main/images/fb-big.png?raw=true)

## Admob Small Native Ads<br>
![alt text](https://github.com/sdshafiq01/Admob-Facebook-Mediation-Implements/blob/main/images/fb-small.png?raw=true)

	// load Facebook Big Size Native Ads, if not loaded Admob ads then auto load Admob big-size native ads.
        NativeAdsAdapter.loadBigFacebookNative(act, adsLayout, Constant.ADS_SELECT_ADMOB, Constant.FB_TEST_NATIVE_ADS_ID, Constant.ADMOB_TEST_NATIVE_ID);

	// load Facebook Small Size Native Ads, if not loaded Admob ads then auto load Admob small-size native ads.
        NativeAdsAdapter.loadSmallFacebookNative(act, adsLayout, Constant.ADS_SELECT_ADMOB, Constant.FB_TEST_NATIVE_BANNER_ID, Constant.ADMOB_TEST_NATIVE_ID);

# Support:
For feature requests, improvements, questions, or any other integration issues using Admob Facebook Mediation Library, 
then contact us via email: 

	techharvestbd@gmail.com
        
