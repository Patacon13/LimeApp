---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: default
nav_order: 1
---

# LimeApp

## Description

This is a network capability application that helps the LibreMesh user to connect and configure the node.
The application is created for Freifunk - Altermundi as a part of the Google Summer of Code 2021 program.

### Why a network capability app

Android only sends data to internet through one way, mobile data or WiFi. To help the user connecting to Internet, Android detects if the WiFi provides an internet connection, and automatically changes between Data or WiFi unless the user explicitly asks for a WiFi connection.
In the general case, the user doesn't know that there's a possibility to use the WiFi without Internet or doesn't want to because it implies losing Internet Access. With this application, the user can configure the node without disconnecting Mobile Data.

### Status of the app

This is the APP published on the GSoC. It has all the features expected.
There's a to-do list below containing a list of things that we want to add in the future on the idea of a future 2.0 version.

## How to compile it

#### How to compile it on Ubuntu 20.04

0. Install git and Android Studio
```
sudo apt install git
sudo apt install openjdk-11-jdk
sudo add-apt-repository ppa:maarten-fonville/android-studio
sudo apt update
sudo apt install android-studio
```

1. Clone the repo

```
git clone https://github.com/Patacon13/LibreMesh-app
```

2. Open it on Android Studio

```
Android Studio -> Open an existing project -> [Folder of cloned project]
```
## To-Do

* Support other services in addition to Lime-App.

# Google website of the project

https://summerofcode.withgoogle.com/projects/5276643104391168

# Posts

https://blog.freifunk.net/2021/06/11/android-native-app-for-network-selection-capability-in-libremesh-routers-overview/
https://blog.freifunk.net/2021/07/12/building-an-app-for-network-capability/
https://blog.freifunk.net/2021/08/23/finishing-an-app-for-network-capability-for-the-libremesh-os/
