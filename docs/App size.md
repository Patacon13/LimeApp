---
title: App size
parent: Application
has_children: false
---
# App size

The idea of the app was to incorporate it into the LibreMesh OS (inside the router), so the app size needed to be light. The final size is approximately 730,3 KB. This was achieved with some tricks:

1. Add scripts to build.gradle

```groovy
```groovy
buildTypes {
    release {
        minifyEnabled true
        shrinkResources true
        proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
    }
}  
```

2. Add resConfig for the Spanish language in build.gradle

```groovy
resConfigs "es"
```

3. Convert the icon to WebP

4. Delete unused dependencies in the build.gradle

5. Delete these dependencies (added automatically) and all use of them

```groovy
implementation 'androidx.appcompat:appcompat:1.2.0'
implementation 'com.google.android.material:material:1.2.1'
```
