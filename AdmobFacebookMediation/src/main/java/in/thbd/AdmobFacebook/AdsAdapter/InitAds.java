package in.thbd.AdmobFacebook.AdsAdapter;

import android.app.Activity;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class InitAds {

    public static void selectAds(Activity activity) {

        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
//                Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
//                for (String adapterClass : statusMap.keySet()) {
//                    AdapterStatus status = statusMap.get(adapterClass);
//                    Log.d("MyApp", String.format(
//                            "Adapter name: %s, Description: %s, Latency: %d",
//                            adapterClass, status.getDescription(), status.getLatency()));
//                }
            }
        });
        AudienceNetworkAds.initialize(activity);


    }

}
