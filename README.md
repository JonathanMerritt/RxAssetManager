![logo](./app/src/main/res/mipmap-xxxhdpi/ic_launcher.png)

[![BINTRAY][BINTRAY-SVG]][BINTRAY]
[![MAVEN-CENTRAL][MAVEN-CENTRAL-SVG]][MAVEN-CENTRAL]
[![TRAVIS-CI][TRAVIS-CI-SVG]][TRAVIS-CI]
[![ANDROID-ARSENAL][ANDROID-ARSENAL-SVG]][ANDROID-ARSENAL]

## What is RxAssetManager?

**An RxJava2 implementation of the Android AssetManager.**

Inspired by the blog post [RxRecipes: Wrap your way to Rx][RX-RECIPES] by [Scott Meschke][SCOTT-MESCHKE].

_Any help or contribution is welcome._

## Download
```groovy
  repositories {
    jcenter()
    //OR
    mavenCentral()
  }

  dependencies {
    implementation "com.github.jonathanmerritt.rxassetmanager:core OR* core-ext:x.y.z"
  }
```

*`core-ext` will include `core` automatically.


## Usage
This example will either open or list and open any files as InputStreams.

_Check the sample app for more detailed examples._

#### Core
```kotlin
RxAssetManager(context).open("assets/file.txt").subscribe {  }
```

#### Core-Ext
```kotlin
RxAssetManager(context).listOpen("assets/folder").subscribe {  }
```

## TODO
- Add javadoc.
- Add tests.
- ...

## [Apache-2.0][LICENSE]

    Copyright 2018 Jonathan Merritt 11R00TT00R11@GMAIL.COM

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[BINTRAY-SVG]:https://img.shields.io/bintray/v/jonathanmerritt/RxAssetManager/core.svg?style=flat-square&colorB=067EC4&label=Bintray
[BINTRAY]:https://bintray.com/jonathanmerritt/RxAssetManager/core/_latestVersion
[MAVEN-CENTRAL-SVG]: https://img.shields.io/maven-central/v/com.github.jonathanmerritt.rxassetmanager/core.svg?style=flat-square&colorB=067EC4&label=MavenCentral
[MAVEN-CENTRAL]: http://repo1.maven.org/maven2/com/github/jonathanmerritt/rxassetmanager/core
[TRAVIS-CI-SVG]: https://img.shields.io/travis/JonathanMerritt/RxAssetManager.svg?style=flat-square&colorB=067EC4&label=TravisCI
[TRAVIS-CI]: https://travis-ci.org/JonathanMerritt/RxAssetManager
[ANDROID-ARSENAL-SVG]: https://img.shields.io/badge/AndroidArsenal-RxAssetManager-blue.svg?style=flat-square
[ANDROID-ARSENAL]: https://android-arsenal.com/details/1/6855

[RELEASES]: https://github.com/JonathanMerritt/RxAssetManager/releases
[LICENSE]: https://github.com/JonathanMerritt/RxAssetManager/blob/master/LICENSE.txt
[RX-RECIPES]: https://hackernoon.com/rxrecipes-wrap-your-way-to-rx-fd40eb5254b6
[SCOTT-MESCHKE]: https://github.com/scottmeschke