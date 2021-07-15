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
        SpeechInterface speechInterface = new SpeechInterface();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        webView.addJavascriptInterface(speechInterface, "speechSynthesis");
    }

    private class SpeechInterface {
        //Voice reading
        @JavascriptInterface
        public void getVoices() {

        }
    }

    private void runNavigator() {

        WebView navegador;
        navegador = (WebView) findViewById(R.id.navegadorLibreMesh);
        configureNavigator(navegador);

        System.out.println(NetworkAccessManager.getGateway(wifiManager));
        navegador.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            navegador.setWebContentsDebuggingEnabled(true);
        }
        navegador.loadUrl(NetworkAccessManager.getGateway(wifiManager));
    }

}

