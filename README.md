![banner](./promo/banner.png)

[![BINTRAY][BINTRAY-SVG]][BINTRAY]
[![MAVEN-CENTRAL][MAVEN-CENTRAL-SVG]][MAVEN-CENTRAL]
[![TRAVIS-CI][TRAVIS-CI-SVG]][TRAVIS-CI]
[![ANDROID-ARSENAL][ANDROID-ARSENAL-SVG]][ANDROID-ARSENAL]


## What is *RXASSETMANAGER*?
An RxJava2 implementation of the Android AssetManager.

_Any help or contribution is welcome._


## How do you *GET* it?
Use the `jcenter()` or `mavenCentral()` repository.
```groovy
  repositories {
    jcenter()
    mavenCentral()
  }
```

Then pick a module.

**Core**
```groovy
  dependencies {
    implementation "com.github.jonathanmerritt.rxassetmanager:core:x.y.z"
  }
```

**Core-ext** - includes `:core` automatically.
```groovy
  dependencies {
    implementation "com.github.jonathanmerritt.rxassetmanager:core-ext:x.y.z"
  }
```


## How do you *USE* it?
Create an instance.
```kotlin
val manager = RxAssetManager(context)
```

Then open the asset as an InputStream for the given file name/path.

**Core**
```kotlin
manager.open("file.txt").subscribe {  }
```

Or open as InputStreams for the given folder name/path.

**Core-ext**
```kotlin
manager.listOpen("folder").subscribe {  }
```

*Please check the sample app for more examples.*


## What does it *DO*?
Miminally wraps the original android assetmanager into rxjava2 types.

**Core** - Intended to be a 1:1 copy, it's only diverted from that slighty.

Function|Parameters|Description|Returns
---|---|---|---
getLocales()|none|gets locales.|Flowable\<String>

**Core-ext** - Extends core while adding more functions, mainly listing chains.

Function|Parameters|Description|Returns
---|---|---|---
openString(String)|name = asset file name/path|opens asset file as a string.|Maybe\<String>


## TODO
- Add javadoc.
- Add tests.
- ...


## Credits
- [RxRecipes][RX-RECIPES] by [Scott Meschke][SCOTT-MESCHKE].
- [RxJava][RXJAVA] by [Reactive-io][RXJAVA]
- [Android][ANDROID] by [Google][GOOGLE]


## [License][LICENSE]
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
[RXJAVA]: https://github.com/ReactiveX/RxJava
[REACTIVEIO]: http://reactivex.io/
[ANDROID]: https://source.android.com/
[GOOGLE]: https://android-developers.googleblog.com/
