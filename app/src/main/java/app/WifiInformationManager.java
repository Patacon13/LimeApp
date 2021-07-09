package app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WifiInformationManager extends AppCompatActivity {
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
