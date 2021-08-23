---
title: Network Access Manager
parent: Application
has_children: false
nav_order: 1
---
# Network Access Manager

The application has a class that manages every message requiring network verifications or selections.

## Requesting WiFi

The method that requests the WiFi interface over the mobile one works with this algorithm:

```
1. Create a NetworkRequest with the WiFi as a transport type.
2. Request a network with the NetworkRequewst generated in the Connectivity Manager.
2.1 In the message add a NetworkCallback with the onAvailable and onLost overridden methods that set the process to the WiFi network
```

On resume, the first step is just as simple as to create the object:

```Java
final NetworkRequest networkRequest = new NetworkRequest.Builder()
    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
    .build();
```

And the second one can be achived by sending the message with the NetworkCallback overridden:

```Java
connectivityManager.requestNetwork(networkRequest, new ConnectivityManager.NetworkCallback() {
    @Override
    void onAvailable(Network network) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) connectivityManager.bindProcessToNetwork(network);
        else ConnectivityManager.setProcessDefaultNetwork(network);
    }

    @Override
    public void onLost(Network network) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) connectivityManager.bindProcessToNetwork(null);
        else ConnectivityManager.setProcessDefaultNetwork(null);
    }

});
```


