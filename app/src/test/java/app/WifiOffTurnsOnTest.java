package app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URLConnection;


@RunWith(MockitoJUnitRunner.class)
public class WifiOffTurnsOnTest {

    @Mock
    WifiManager wm;

    @Mock
    URLConnection urlc;

    @Mock
    WifiInfo wi;

    @Test
    public void notConnectedToNetwork() {
        when(wm.isWifiEnabled()).thenReturn(Boolean.FALSE);
        when(wm.getConnectionInfo()).thenReturn(wi);
        when(wm.getConnectionInfo().getNetworkId()).thenReturn(1);

        try {
            when(urlc.getInputStream()).thenReturn(null);
        } catch (IOException e) {
            fail();
        }

        MainActivity ma = new MainActivity(wm, urlc);

        assertEquals(false, ma.accessToLibreMesh());

        when(wm.isWifiEnabled()).thenReturn(Boolean.TRUE);

        assertEquals(true, ma.accessToLibreMesh());
    }

}
