package app;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.limeapp.R;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    /* FIXME: implementar
    public boolean isConecctedToInternet() throws InterruptedException, IOException {

        Process p1 = java.lang.Runtime.getRuntime().exec("sh ping -c 1 www.google.com");
        int returnVal = p1.waitFor();
        return returnVal == 0;

    }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void verificaConexion(View view) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        InetAddress addr;
        try {
            addr = InetAddress.getByName("www.google.com");
            try {
                String text = addr.isReachable(5000) ? "Está en una red de LibreMesh" : "No está en una red de LibreMesh";
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }
            catch(IOException e) {
                Toast.makeText(getApplicationContext(),"Error al intentar llegar al host",Toast.LENGTH_LONG).show();
            }
        } catch (UnknownHostException e) {
            Toast.makeText(getApplicationContext(),"Error al encontrar el host",Toast.LENGTH_LONG).show();
        }
    }

    public void enviaPing(View view) {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        int ip = wm.getConnectionInfo().getIpAddress();
        String ipString = String.format("%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
        Toast.makeText(getApplicationContext(),ipString,Toast.LENGTH_LONG).show();
    }
}