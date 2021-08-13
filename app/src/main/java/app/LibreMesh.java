package app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.LimeApp.R;
import com.example.LimeApp.databinding.ActivityLibreMeshBinding;


public class LibreMesh extends AppCompatActivity {

    private ConnectivityManager connectivityManager;
    private WifiManager wifiManager;
    private WebView navigator;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (navigator.canGoBack() && !navigator.getUrl().equals(("http://" + NetworkAccessManager.getGateway(wifiManager) + "/app/#/rx"))) {
                        navigator.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        com.example.LimeApp.databinding.ActivityLibreMeshBinding binding = ActivityLibreMeshBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) NetworkAccessManager.requestWifi(connectivityManager);

        runNavigator();
    }

    private void configureNavigator() {
        WebSettings settings = navigator.getSettings();
        MockSpeechInterface mockSpeechInterface = new MockSpeechInterface();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        navigator.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        //FIXME: poner comentario problema y link al issue
        navigator.addJavascriptInterface(mockSpeechInterface, "speechSynthesis");
    }

    private class MockSpeechInterface {
        //Voice reading
        @JavascriptInterface
        public void getVoices() {

        }
    }

    private void runNavigator() {

        navigator = (WebView) findViewById(R.id.navegadorLibreMesh);
        configureNavigator();

        System.out.println(NetworkAccessManager.getGateway(wifiManager));
        navigator.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            navigator.setWebContentsDebuggingEnabled(true);
        }
        navigator.loadUrl(NetworkAccessManager.getGateway(wifiManager));
    }

}

