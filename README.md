# MIKRODINET

[MIKRODINET](https://www.mikrodinet.eu.org) adalah sebuah blog personal yang membahas tentang pembelajaran mengenai ilmu pengetahuan yang berkaitan dengan ilmu komputer terlebih khusus tutorial dan cara menjalankan software komputer yang mudah dipelajari oleh pemula.

Original site : www.mikrodinet.eu.org

# Android-Webview-App
A Simple To Use App That Shows Web Pages Within The App And Download Content Using Phone's Internal Downloader

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

### Screenshot
<img src="screenshot_demo.jpg" width="416" height="720">

### Demo

Download here : [Demo Apk](https://github.com/mikrodinet/webview/raw/master/app/download/app-release.apk)

# Getting Started

[Download](https://github.com/mikrodinet/webview/raw/master/app/download/app-release.apk) or clone this repository and import it into Android Studio.

## Windows

```
# Create a folder under the drive root
$ mkdir actions-runner; cd actions-runner# Download the latest runner package
$ Invoke-WebRequest -Uri https://github.com/actions/runner/releases/download/v2.304.0/actions-runner-win-x64-2.304.0.zip -OutFile actions-runner-win-x64-2.304.0.zip# Optional: Validate the hash
$ if((Get-FileHash -Path actions-runner-win-x64-2.304.0.zip -Algorithm SHA256).Hash.ToUpper() -ne 'fbbddd2f94b195dde46aa6028acfe873351964c502aa9f29bb64e529b789500b'.ToUpper()){ throw 'Computed checksum did not match' }# Extract the installer
$ Add-Type -AssemblyName System.IO.Compression.FileSystem ; [System.IO.Compression.ZipFile]::ExtractToDirectory("$PWD/actions-runner-win-x64-2.304.0.zip", "$PWD")
```
### Config
```
# Create the runner and start the configuration experience
$ ./config.cmd --url https://github.com/mikrodinet/webview --token A3WZ3XRGVE4PZIRRZMVPMIDEQNE62# Run it!
$ ./run.cmd
```


## Change Website URL 
Open the ```app/src/main/java/com/webview/app/MainActivity.java``` file and replace `https://github.com/mikrodinet` on line **68** with your website
```json
mWebView.loadUrl("https://www.mikrodinet.eu.org");
```
### manifest

```
<manifest ... >
    <uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.INTERNET" />
<!-- Bypass APPLICATION_ID check for WebView API for Ads -->
  <meta-data
      android:name="com.google.android.gms.ads.INTEGRATION_MANAGER"
      android:value="webview"/>
<WebView
    android:id="@+id/webview"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
/>
<script type="text/javascript">
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
dependencies {
  implementation 'com.google.android.gms:play-services-ads:22.0.0'
}
</script>
<script type="text/javascript">
WebView webView = (WebView) findViewById(R.id.webview);
webView.getSettings().setJavaScriptEnabled(true);

webView.setWebViewClient(new MyWebViewClient());
webView.loadUrl("https://www.mikrodinet.eu.org");
</script>
<gap:plugin name="com.google.cordova.admob" source="plugins.cordova.io" />
<gap:plugin name="cordova-plugin-admobpro" source="npm"/>

...
</manifest>
```

# Admob
source Banner and Interstitial
```


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
    
```

## plugin cordova

```
<script src="[https://raw.githubusercontent.com/sunnycupertino/cordova-plugin-admob-simple/master/www/AdMob.js](https://raw.githubusercontent.com/mikrodinet/webview/master/admob/plugin/js/index.js)"></script>
<script type="text/javascript">
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
    
</script>
```

## Service

GoogleService.json


```
{
  "project_info": {
    "project_number": "506021927297",
    "project_id": "just-resolver-373013",
    "storage_bucket": "just-resolver-373013.appspot.com"
  },
  "client": [
    {
      "client_info": {
        "mobilesdk_app_id": "1:506021927297:android:eaef3fb89531f5f5d47080",
        "android_client_info": {
          "package_name": "com.mikrodinet.app"
        }
      },
      "oauth_client": [
        {
          "client_id": "506021927297-cbtqp4m737pnuno6inl561s4hk0o6fh3.apps.googleusercontent.com",
          "client_type": 3
        }
      ],
      "api_key": [
        {
          "current_key": "AIzaSyCsJdQSdfMvUhoPtkRK5DV9fGS52P1KZMc"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": [
            {
              "client_id": "506021927297-cbtqp4m737pnuno6inl561s4hk0o6fh3.apps.googleusercontent.com",
              "client_type": 3
            }
          ]
        }
      }
    },
    {
      "client_info": {
        "mobilesdk_app_id": "1:506021927297:android:e5d0fc5241683a57d47080",
        "android_client_info": {
          "package_name": "eu.org.mikrodinet.app"
        }
      },
      "oauth_client": [
        {
          "client_id": "506021927297-cbtqp4m737pnuno6inl561s4hk0o6fh3.apps.googleusercontent.com",
          "client_type": 3
        }
      ],
      "api_key": [
        {
          "current_key": "AIzaSyCsJdQSdfMvUhoPtkRK5DV9fGS52P1KZMc"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": [
            {
              "client_id": "506021927297-cbtqp4m737pnuno6inl561s4hk0o6fh3.apps.googleusercontent.com",
              "client_type": 3
            }
          ]
        }
      },
      "admob_app_id": "ca-app-pub-6827901881006590~1120387899"
    },
    {
      "client_info": {
        "mobilesdk_app_id": "1:506021927297:android:66f8aa16f94d7a07d47080",
        "android_client_info": {
          "package_name": "eu.org.mikrodinet.webview"
        }
      },
      "oauth_client": [
        {
          "client_id": "506021927297-cbtqp4m737pnuno6inl561s4hk0o6fh3.apps.googleusercontent.com",
          "client_type": 3
        }
      ],
      "api_key": [
        {
          "current_key": "AIzaSyCsJdQSdfMvUhoPtkRK5DV9fGS52P1KZMc"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": [
            {
              "client_id": "506021927297-cbtqp4m737pnuno6inl561s4hk0o6fh3.apps.googleusercontent.com",
              "client_type": 3
            }
          ]
        }
      }
    },
    {
      "client_info": {
        "mobilesdk_app_id": "1:506021927297:android:7f0748f0b0d72eacd47080",
        "android_client_info": {
          "package_name": "io.gonative.ios.wrjomm"
        }
      },
      "oauth_client": [
        {
          "client_id": "506021927297-cbtqp4m737pnuno6inl561s4hk0o6fh3.apps.googleusercontent.com",
          "client_type": 3
        }
      ],
      "api_key": [
        {
          "current_key": "AIzaSyCsJdQSdfMvUhoPtkRK5DV9fGS52P1KZMc"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": [
            {
              "client_id": "506021927297-cbtqp4m737pnuno6inl561s4hk0o6fh3.apps.googleusercontent.com",
              "client_type": 3
            }
          ]
        }
      }
    }
  ],
  "configuration_version": "1"
}
```

## package

```
package eu.org.mikrodinet;

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
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
```


### LICENSE

Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/
                        Website : https://www.mikrodinet.eu.org
                        gh-pages : https://mikrodinet.github.io
                        github : https://github.com/mikrodinet/webview
