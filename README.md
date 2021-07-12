# LibreMesh-app

## Description

This is a network capability application that helps the LibreMesh user to connect and configure the node.
The application is created for Freifunk - Altermundi as a part of the Google Summer of Code 2021 program.

### Why a network capability app

Android only send data to internet through one way, mobile data or WiFi. To help the user connecting to Internet, Android detects if the WiFi provide an internet connection, and automatically change between Data or WiFi unless the user explicitly asks for a WiFi connection.

On the general case, the user doesn't know that there's a possibility to use the WiFi without Internet, or doesn't want to because it implies losing the Internet Access. With this application, the user can configure the node *without* disconnecting Mobile Data.

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


# Doc

https://blog.freifunk.net/2021/06/11/android-native-app-for-network-selection-capability-in-libremesh-routers-overview/

