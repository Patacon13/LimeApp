package app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.limeapp.R;
import com.example.limeapp.databinding.ActivityLibreMeshBinding;


public class LibreMesh extends AppCompatActivity {

    private ConnectivityManager connectivityManager;
    private WifiManager wifiManager;

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        com.example.limeapp.databinding.ActivityLibreMeshBinding binding = ActivityLibreMeshBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) NetworkAccessManager.requestWifi(connectivityManager);

        runNavigator();
    }

    private void configureNavigator(WebView webView) {
        WebSettings settings = webView.getSettings();
        MockSpeechInterface mockSpeechInterface = new MockSpeechInterface();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        //FIXME: poner comentario problema y link al issue
        webView.addJavascriptInterface(mockSpeechInterface, "speechSynthesis");
    }

    private class MockSpeechInterface {
        //Voice reading
        @JavascriptInterface
        public void getVoices() {

        }
    }

    private void runNavigator() {

        WebView navigator;
        navigator = (WebView) findViewById(R.id.navegadorLibreMesh);
        configureNavigator(navigator);

        System.out.println(NetworkAccessManager.getGateway(wifiManager));
        navigator.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            navigator.setWebContentsDebuggingEnabled(true);
        }
        navigator.loadUrl(NetworkAccessManager.getGateway(wifiManager));
    }

}

