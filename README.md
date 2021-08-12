# Mini Stockbit
This project is a mini-copy of [Stockbit Android Application](https://stockbit.com/). Main features of this application are Login view and Top Tier page which shows high Crypto coin value.

<p align="center">
  <a href="https://drive.google.com/file/d/1Yo05E796uPyj9XzZ_0tSwNsaUyvET1bz/view?usp=sharing"><img src="https://img.shields.io/badge/DOWNLOAD%20APK-v1.0-brightgreen" alt="Mini-Stockbit"/></a> 
  <br/>
  <br/>
  <a href="https://kotlinlang.org/"><img src="https://img.shields.io/badge/kotlin-v1.5.10-blue" alt="kotlin"/></a>
  <a href="https://gradle.org/"><img src="https://img.shields.io/badge/gradle-v6.8.3-green" alt="gradle"/></a>
  <a href="https://gradle.org/"><img src="https://img.shields.io/badge/gradle--plugin-v4.2.1-green" alt="gradle-plugin"/></a>
  <a href="https://developer.android.com/studio/"><img src="https://img.shields.io/badge/android%20studio-v4.2.1-blue" alt="android-studio"></a>
  <a href="https://material.io/develop/android"><img src="https://img.shields.io/badge/material-v1.4.0-lightgrey" alt="material-design"></a>
  <a href="https://developer.android.com/guide/navigation"><img src="https://img.shields.io/badge/navigation%20component-v2.3.5-brightgreen" alt="navigation-component"></a>
  <a href="https://square.github.io/okhttp/4.x/okhttp/okhttp3/"><img src="https://img.shields.io/badge/okhttp3-v4.9.0-green" alt="okhttp3"></a>
  <a href="https://square.github.io/retrofit/"><img src="https://img.shields.io/badge/retrofit2-v2.9.0-brightgreen" alt="retrofit2"></a>
  <a href="https://developer.android.com/kotlin/coroutines"><img src="https://img.shields.io/badge/coroutines-v1.4.3-blue" alt="coroutines"/></a>
  <a href="https://insert-koin.io/"><img src="https://img.shields.io/badge/koin-v3.1.2-yellow" alt="koin"/></a>
  <a href="https://github.com/ajalt/timberkt"><img src="https://img.shields.io/badge/timber-v1.5.1-orange" alt="timber"/></a>
  <a href="https://mockk.io/"><img src="https://img.shields.io/badge/mockk-v1.12.0-blue" alt="mockk"/></a>
  <a href="https://kotest.io/"><img src="https://img.shields.io/badge/kotest-v4.6.1-brightgreen" alt="kotest"/></a>
</p>

### Features
- Support Dark/Night Mode
- Lifecycle Aware
- Login Page
- Top Tier(Watchlist) Page :
  - Pagination + load on scroll
  - Pull to refresh
  - Loading state
  - Error state
  - Continue next page after error
  - Clickable data item
- Search Page (Coming Soon)

### Tech Stack
- Single Activity Architecture (Jetpack Navigation Component)
- Modularization of Android Application
- MVVM and Clean Architecture
- Either class for handling state
- View Binding
- Material Design
- Koin
- Coroutine and Live Data
- MockK and Kotest for Unit Testing
- Kotlin DSL for Gradle

### Unit Testing
By default, Android Studio runs its unit testing using Android JUnit, to be able to run unit testing using [Kotest](https://kotest.io/) you need to install the [Kotest plugin](https://plugins.jetbrains.com/plugin/14080-kotest) first in Android Studio.

<img src="https://github.com/TimurHaryo/MiniStockbit/blob/master/screenshot/unit_testing.png" />

## TAKE A NOTE
If Kotest error during building testing, change Run/Debug Configurations with following steps:
- Open the Run/Debug Configuration menu and choose "Edit Configurations..."

<img src="https://github.com/TimurHaryo/MiniStockbit/blob/master/screenshot/step1.png" />

- Choose your configuration from the left side(make sure it is inside Kotest Configuration) and change the "Shorten command line" (right-below side) into "JAR Manifest" <br/>

<img src="https://github.com/TimurHaryo/MiniStockbit/blob/master/screenshot/step2.png" />

- Click "OK" to save your configuration then run again using green-play-button <br/>

<img src="https://github.com/TimurHaryo/MiniStockbit/blob/master/screenshot/step3.png" />

- If these step still not working, try to change the "Shorten command line" into another else
- Lastly, if still still still not working...let me know. I'd be happy to discuss together
