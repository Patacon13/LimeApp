# LibreMesh-app

## Description

This is a network capability application that helps the LibreMesh user to connect and configure the node.
The application is created for Freifunk - Altermundi as a part of the Google Summer of Code 2021 program.

### Why a network capability app

Android only sends data to internet through one way, mobile data or WiFi. To help the user connecting to Internet, Android detects if the WiFi provides an internet connection, and automatically changes between Data or WiFi unless the user explicitly asks for a WiFi connection.
In the general case, the user doesn't know that there's a possibility to use the WiFi without Internet or doesn't want to because it implies losing Internet Access. With this application, the user can configure the node without disconnecting Mobile Data.

### Status of the app

As we're starting the second part of the GSoC, the app isn't finished yet, there's only a preview version that has the principal functions, without a good graphical interface. But can show 

## How to compile it

#### How to compile it on Ubuntu 20.04

0. Install git and Android Studio
```bash=
sudo apt install git
sudo apt install openjdk-11-jdk
sudo add-apt-repository ppa:maarten-fonville/android-studio
sudo apt update
sudo apt install android-studio
```

1. Clone the repo

```bash=
git clone https://github.com/Patacon13/LibreMesh-app
```

2. Open it on Android Studio

```
Android Studio -> Open an existing project -> [Folder of cloned project]
```
## To-Do

* A better graphical interface with the integrations of all the planned functions of the app.
* Support other services in addition to Lime-App.
* Add the app to the LibreMesh operating system, giving the posibility to the user to obtain the app directly from the router.

# Doc

https://blog.freifunk.net/2021/06/11/android-native-app-for-network-selection-capability-in-libremesh-routers-overview/

