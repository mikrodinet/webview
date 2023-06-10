package eu.org.mikrodinet,

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import com.google.android.gms.ads.AdRequest; //Import class AdRequest
import com.google.android.gms.ads.AdView;    //import class Adview
// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyAIh7bPMbWZXPbTZIggAZ_ntbE4kYDAvgw",
  authDomain: "just-resolver-373013.firebaseapp.com",
  projectId: "just-resolver-373013",
  storageBucket: "just-resolver-373013.appspot.com",
  messagingSenderId: "506021927297",
  appId: "1:506021927297:web:57e7249204f7f332d47080",
  measurementId: "G-L87MVX9K91"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

public class MainActivity extends Activity {

    private final int STORAGE_PERMISSION_CODE = 1;
    private WebView mWebView;

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to download files")
                    .setPositiveButton("ok", (dialog, which) -> ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE))
                    .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        requestStoragePermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.activity_main_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new HelloWebViewClient());
        mWebView.setDownloadListener((url, userAgent, contentDisposition, mimeType, contentLength) -> {
            Uri source = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(source);
            String cookies = CookieManager.getInstance().getCookie(url);
            request.addRequestHeader("cookie", cookies);
            request.addRequestHeader("User-Agent", userAgent);
            request.setDescription("Downloading File...");
            request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
            DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            dm.enqueue(request);
            Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
        });
        mWebView.loadUrl("https://www.mikrodinet.eu.or"); //Replace The Link Here
    }
    private static class HelloWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url)
        {
            view.loadUrl(url);
            return true;
        }
    }
  
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }


//initialize the goodies
function initAd(){
        if ( window.plugins && window.plugins.AdMob ) {
            var ad_units = {
                ios : {
                    banner: 'ca-app-pub-6827901881006590/7982089965',		//PUT ADMOB ADCODE HERE
                    interstitial: 'ca-app-pub-6827901881006590/6698817209'	//PUT ADMOB ADCODE HERE
                },
                android : {
                    banner: 'ca-app-pub-6827901881006590/7982089965',		//PUT ADMOB ADCODE HERE
                    interstitial: 'ca-app-pub-6827901881006590/6698817209'	//PUT ADMOB ADCODE HERE
                }
            };
            var admobid = ( /(android)/i.test(navigator.userAgent) ) ? ad_units.android : ad_units.ios;

            window.plugins.AdMob.setOptions( {
                publisherId: admobid.banner,
                interstitialAdId: admobid.interstitial,
                bannerAtTop: false, // set to true, to put banner at top
                overlap: true, // set to true, to allow banner overlap webview
                offsetTopBar: false, // set to true to avoid ios7 status bar overlap
                isTesting: false, // receiving test ad
                autoShow: false // auto show interstitial ad when loaded
            });

            registerAdEvents();
            window.plugins.AdMob.createInterstitialView();	//get the interstitials ready to be shown
            window.plugins.AdMob.requestInterstitialAd();

        } else {
            //alert( 'admob plugin not ready' );
        }
}
//functions to allow you to know when ads are shown, etc.
function registerAdEvents() {
        document.addEventListener('onReceiveAd', function(){});
        document.addEventListener('onFailedToReceiveAd', function(data){});
        document.addEventListener('onPresentAd', function(){});
        document.addEventListener('onDismissAd', function(){ });
        document.addEventListener('onLeaveToAd', function(){ });
        document.addEventListener('onReceiveInterstitialAd', function(){ });
        document.addEventListener('onPresentInterstitialAd', function(){ });
        document.addEventListener('onDismissInterstitialAd', function(){
        	window.plugins.AdMob.createInterstitialView();			//REMOVE THESE 2 LINES IF USING AUTOSHOW
            window.plugins.AdMob.requestInterstitialAd();			//get the next one ready only after the current one is closed
        });
    }
    
    //display the banner
    function showBannerFunc(){
    window.plugins.AdMob.createBannerView();
    }
    //display the interstitial
    function showInterstitialFunc(){
    window.plugins.AdMob.createInterstitialView();	//get the interstitials ready to be shown and show when it's loaded.
    window.plugins.AdMob.requestInterstitialAd();
    }
    
    window.plugins.AdMob.destroyBannerView();
    }

}
Tappx.setCollectLocationEnabled(context,true);
-keepattributes *Annotation*
-keepclassmembers class com.google.**.R$* {
    public static ;
}
-keep public class com.google.ads.** {*;}
-keep public class com.google.android.gms.** {*;}
-keep public class com.tappx.** { *; }
Tappx.setCoppaCompliance(context, true);
