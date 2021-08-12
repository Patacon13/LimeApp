package app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.limeapp.R;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    WifiManager wifiManager;
    ConnectivityManager connectivityManager;
    URLConnection connection = null;

    public MainActivity(WifiManager wifiManager, URLConnection urlConnection) {
        this.wifiManager = wifiManager;
        this.connection = urlConnection;
    }

    public boolean httpGetToLibreMesh() throws InterruptedException {
        boolean[] success = {false};

        Thread connectionThread = new Thread(new Runnable() {
            public void run() {
                try {
                    connection.getInputStream();
                    success[0] = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });

        connectionThread.start();
        connectionThread.join();

        return success[0];


    }

    private void showError() {
        TextView tips = (TextView) findViewById(R.id.tips);
        TextView errorTitle = (TextView) findViewById(R.id.errorTitle);
        Button retry = (Button) findViewById(R.id.retryButton);
        tips.setText(Html.fromHtml(getResources().getString(R.string.tips)));
        tips.setVisibility(View.VISIBLE);
        retry.setVisibility(View.VISIBLE);
        errorTitle.setVisibility(View.VISIBLE);
    }

    private void hideLibreMesh() {
        TextView libreMesh = (TextView) findViewById(R.id.LibreMeshText);
        libreMesh.setVisibility(View.INVISIBLE);
    }

    private void hideError() {
        TextView tips = (TextView) findViewById(R.id.tips);
        TextView errorTitle = (TextView) findViewById(R.id.errorTitle);
        Button retry = (Button) findViewById(R.id.retryButton);
        tips.setText(Html.fromHtml(getResources().getString(R.string.tips)));
        tips.setVisibility(View.INVISIBLE);
        retry.setVisibility(View.INVISIBLE);
        errorTitle.setVisibility(View.INVISIBLE);
    }

    private void initializeMain() {
        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        try {
            connection = new URL("http://thisnode.info/cgi-bin/hostname").openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        connection.setConnectTimeout(5000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeMain();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) NetworkAccessManager.requestWifi(connectivityManager);
        if (!accessToLibreMesh()) {
            hideLibreMesh();
            showError();
        }
    }

    public boolean verifyLibreMeshConnection() {
        try {
            return httpGetToLibreMesh();
        } catch (InterruptedException e) {
            System.out.println("Error de interrupcion al intentar acceder a la IP de LibreMesh.");
        }
        return false;

    }
    public boolean accessToLibreMesh() {
        Intent myIntent = new Intent(this, LibreMesh.class);
        if (NetworkAccessManager.verifyWifiConnection(wifiManager)) {
            if (verifyLibreMeshConnection()) {
                startActivity(myIntent);
                finish();
                return true;
            } else {
                System.out.println("No está en una red LibreMesh");
            }
        }
        else {
            System.out.println("No está conectado a la Wi-Fi");
        }
        return false;
    }

    public void retry(View view) {
        hideError();
        if (!accessToLibreMesh()) (new Handler()).postDelayed(this::showError, 200);
    }
}