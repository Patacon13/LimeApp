---
title: Application
has_children: true
nav_order: 3
---
# Application

## General description
The application is designed in the Java language with the Android graphical elements.
The principal function of the app is to choose the interface to send data, but it's also a solution for the users to access the LibreMesh configuration with an installed application. The app works directly with the Lime-App installed on the node executing it in a WebView browser.

## Context
The Google Summer of Code is a google program that helps students to participate in open-source projects with organizations. Freifunk is an organization that supports free networks in Germany and has some "supported organizations" in their GSoC projects. Altermundi is one of them.

## The project and the problem

Altermundi presented to the students an idea of a proposal the idea to make a network capability application for the Android OS. 

This idea surges from the problem that Android usually chooses the network that provides an internet connection, and with that, the users usually have troubles connecting to the Lime-App.
It's common among community networks that the majority of the users only or most commonly uses a mobile phone, also, Android is the most popular operating system in this network.
This problem created a lack of communication between network administrators and the ones who had to report errors due to the inability of the users to stay in the configuration without losing internet access (if the user loses internet access, he can't inform the problem).

## The solution

An Android Application was made with the possibility to decide what interface to use (WiFi or mobile data), this makes the phone have an "Application context" and a "Device context". On the application context the WiFi interface enables the user to connect to the Lime-App, and on the other hand, the "Device Context" lets the user inform the problems through a messaging system (like Signal, Telegram, or Whatsapp).
