package app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.limeapp.R;
import com.example.limeapp.databinding.ActivityLibreMeshBinding;

public class LibreMesh extends AppCompatActivity {

    private ConnectivityManager connectivityManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        com.example.limeapp.databinding.ActivityLibreMeshBinding binding = ActivityLibreMeshBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        iniciarNavegador();
    }

    private void iniciarNavegador() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) requestWifi();

        WebView navegador;
        navegador = (WebView) findViewById(R.id.navegadorLibreMesh);
        navegador.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        navegador.loadUrl("192.168.0.2");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void requestWifi() {
        final NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build();

        connectivityManager.requestNetwork(networkRequest, new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    connectivityManager.bindProcessToNetwork(network);
                else
                    ConnectivityManager.setProcessDefaultNetwork(network);
            }

            @Override
            public void onLost(Network network) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    connectivityManager.bindProcessToNetwork(null);
                else
                    ConnectivityManager.setProcessDefaultNetwork(null);
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();
            }
        });
    }

    private void requestMobile() {
        //TODO: testear
        final NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();

        connectivityManager.requestNetwork(networkRequest, new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    connectivityManager.bindProcessToNetwork(network);
                else
                    ConnectivityManager.setProcessDefaultNetwork(network);
            }

            @Override
            public void onLost(Network network) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    connectivityManager.bindProcessToNetwork(null);
                else
                    ConnectivityManager.setProcessDefaultNetwork(null);
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();
            }
        });
    }

}

