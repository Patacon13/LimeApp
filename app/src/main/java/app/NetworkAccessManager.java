package app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class NetworkAccessManager extends AppCompatActivity {

    public static void requestMobile(ConnectivityManager connectivityManager) {
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void requestWifi(ConnectivityManager connectivityManager) {
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

    private static String intToIp(int addr) {
        return  ((addr & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF));
    }

    public static String getPrivateIp(WifiManager wm) {
        int ip = wm.getConnectionInfo().getIpAddress();
        return intToIp(ip);
    }

    public static boolean verifyWifiConnection(WifiManager wm) {
        if (wm.isWifiEnabled()) {
            return wm.getConnectionInfo().getNetworkId() != -1;
        }
        return false;
    }

    public static String getGateway(WifiManager wm) {
        return intToIp(wm.getDhcpInfo().gateway);
    }

}
