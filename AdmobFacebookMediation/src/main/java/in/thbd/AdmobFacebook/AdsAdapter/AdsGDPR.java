package in.thbd.AdmobFacebook.AdsAdapter;

import static android.content.ContentValues.TAG;
import android.app.Activity;
import android.provider.Settings;
import android.util.Log;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.UserMessagingPlatform;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AdsGDPR {

    private ConsentInformation consentInformation;
    ConsentDebugSettings debugSettings;
    ConsentForm consentForm;
    Activity activity;

    public AdsGDPR(Activity activity) {
        this.activity = activity;
        updateGDPRConsentStatus();
    }

    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "md5: "+e.getMessage() );
        }
        return "";
    }

    public void updateGDPRConsentStatus() {
//        if (BuildConfig.DEBUG) {
//            String android_id = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
//            String deviceId = md5(android_id).toUpperCase();
//            debugSettings = new ConsentDebugSettings.Builder(activity)
//                    .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
//                    .addTestDeviceHashedId(deviceId)
//                    .build();
//        }

        debugSettings = new ConsentDebugSettings.Builder(activity)
                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
                .build();


        ConsentRequestParameters params = new ConsentRequestParameters.Builder()
                .setTagForUnderAgeOfConsent(false)
                .setConsentDebugSettings(debugSettings)
                .build();
        consentInformation = UserMessagingPlatform.getConsentInformation(activity);
        consentInformation.requestConsentInfoUpdate(activity, params, () -> {
                    if (consentInformation.isConsentFormAvailable()) {
                        loadForm(activity);

                    }
                },
                formError -> {
                });
        Log.d("GDPR", "GDPR Funding choices is selected");

    }

    public void loadForm(Activity activity) {
        UserMessagingPlatform.loadConsentForm(activity, consentForm -> {
                    this.consentForm = consentForm;
                    if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.REQUIRED) {
                        consentForm.show(activity, formError -> {
                            loadForm(activity);

                        });
                    }
                },
                formError -> {
                }
        );
    }
}
