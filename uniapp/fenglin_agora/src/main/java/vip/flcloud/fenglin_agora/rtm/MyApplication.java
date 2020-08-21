package vip.flcloud.fenglin_agora.rtm;

import android.content.Context;
import android.webkit.WebView;

import io.dcloud.application.DCloudApplication;
import vip.flcloud.fenglin_agora.R;

//public class MyApplication extends DCloudApplication {
public class MyApplication {

//    private static MyApplication myApplication;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        myApplication = this;
//    }

    public static Context getApplication() {

        return io.dcloud.application.a.b().a();
//        return myApplication;
    }


    public static WebView getWebView() {
        WebView webView = new WebView(MyApplication.getApplication());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/apps/__UNI__CCCDFF0/www/__uniappview.html");
        return webView;
    }
}
