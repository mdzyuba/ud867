# Overview
This is the final project for the **Gradle for Android and Java** class 
(ud867) on [Udacity](http://www.udacity.com/).

## Purpose of the Project

The main purpose of this project is to learn Gradle and use it for 
building both Android and Google Cloud Endpoint application.

## Outcome of the Project

This project demonstrates development free and paid flavors of the Android app.
The project is split into several modules:

* A Java library that provides jokes
* A Google Cloud Endpoints (GCE) project that serves those jokes
* An Android Library containing an activity for displaying jokes
* An Android app that fetches jokes from the GCE module and passes them 
to the Android Library for display

The project build.gradle file demonstrates run of an end to end test 
suite both with a local and remove GCE instances.

## Credits

The jokes in this project are inspired by jokes I've heard before from 
various people.

## Usage Details

To use the project, please open it in Android Studio and build by
following general Android Application build instructions.

### Project Structure

* **jokes_java_lib** - a Java library that provides jokes.
* **jokes_android_lib** - an Android Library that provide an activity 
that displays a joke.
* **backend** - a web app that provides an endpoint that tells a joke.

* **app** - an Android app with following flavors:
  * **paid** - a version with no ads
  * **free** - a version with ads
  * **localAppEng** - provides a version of paid and free app versions 
  that point to a local App Engine dev. server.

### End to End Tests

In order to run the Android app tests configured to access the local
GCE server, please start an Android Emulator first and then run masterTest
gradle task:
```
./gradlew masterTest
```

## Contribution

This is a learning project. It is open for comments and suggestions.
This project is not intended for code contributions.

## License

This code is distributed under [MIT license](https://opensource.org/licenses/MIT).

Copyright (c) [2019] [Mykola Dzyuba]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.