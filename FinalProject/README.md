# Overview
This is the Final Project for the Gradle for Android and Java class (ud867) on udacity.com.

## Credits

The jokes in this project are inspired by jokes I've heard before from various people.

## Project Structure

* **jokes_java_lib** - a Java library that provides jokes.
* **jokes_android_lib** - an Android Library that provide an activity that displays a joke.
* **backend** - a web app that provides an endpoint that tells a joke.

* **app** - an Android app with following flavors:
  * **paid** - a version with no ads
  * **free** - a version with ads
  * **localAppEng** - provides a version of paid and free app versions that point to a local App Engine dev. server.

## End to End Test

In order to run the Android app tests configured to access the local
GCE server, please start an Android Emulator first and then run masterTest
gradle task:
```
./gradlew masterTest
```
