package app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.limeapp.R;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    WifiManager wifiManager;
    ConnectivityManager connectivityManager;
    public boolean httpGetToLibreMesh() throws InterruptedException, IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        InetAddress addr;
        try {
            addr = InetAddress.getByName("thisnode.info");
            try {
                return addr.isReachable(250);
            }
            catch(IOException e) {
                Toast.makeText(getApplicationContext(),"Error al intentar llegar al host",Toast.LENGTH_LONG).show();
            }
        } catch (UnknownHostException e) {
            Toast.makeText(getApplicationContext(),"Error al encontrar el host",Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) NetworkAccessManager.requestWifi(connectivityManager);
    }

    public boolean verifyLibreMeshConnection() {
        try {
            return httpGetToLibreMesh();
        } catch (InterruptedException e) {
            System.out.println("Error de interrupcion al intentar acceder a la IP de LibreMesh.");
        } catch (IOException e) {
            System.out.println("Error de entrada o salida al intentar acceder a la IP de LibreMesh.");
        }
        return false;

    }

    public void informConnectionToLibreMesh(View view) {
        if(NetworkAccessManager.verifyWifiConnection(wifiManager))
            Toast.makeText(getApplicationContext(), verifyLibreMeshConnection() ? "Está en una red LibreMesh" : "No está en una red LibreMesh",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "No está conectado a la WiFi. Conéctese y vuelva a intentarlo" ,Toast.LENGTH_LONG).show();
    }

    public void informPrivateIp(View view) {
        Toast.makeText(getApplicationContext(), NetworkAccessManager.getPrivateIp(wifiManager),Toast.LENGTH_LONG).show();
    }


    public void accessToLibreMesh(View view) {
        Intent myIntent = new Intent(this, LibreMesh.class);
        startActivity(myIntent);
        if(NetworkAccessManager.verifyWifiConnection(wifiManager))
            if(verifyLibreMeshConnection())
                startActivity(myIntent);
            else
                Toast.makeText(getApplicationContext(), "No está en una red LibreMesh",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "No está conectado a la WiFi. Conéctese y vuelva a intentarlo" ,Toast.LENGTH_LONG).show();
    }
}