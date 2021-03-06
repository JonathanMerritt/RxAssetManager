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

Function|Parameters|Description|Pair|Returns
---|---|---|---|---
`getLocales()`|none|Gets all locales.|no|Flowable\<String>
`close()`|none|Closes the asset manager.|no|Completable
`open(String, Int)`|**path**: asset file path, **mode**: file access mode|Opens an asset input stream.|yes|Maybe\<InputStream>
`openTypeface(String)`|**path**: asset file path|Opens an asset font.|yes|Maybe\<Typeface>
`openFd(String)`|**path**: asset file path|Opens an asset file descriptor.|yes|Maybe\<AssetFileDescriptor>
`list(String)`|**path**: asset folder path|Lists asset files.|no|Flowable\<String>
`openNonAssetFd(Int, String)`|**cookie**: non asset file cookie, **path**: non asset file path|Opens a non asset file.|yes|Maybe\<AssetFileDescriptor>
`openXmlResourceParser(Int, String)`|**cookie**: xml file cookie, **path**: xml file path|Opens a xml resource parser.|yes|Maybe\<XmlResourceParser>

**Core-ext** - Extends core while adding more functions, mainly listing chains.

Function|Parameters|Description|Pair|Returns
---|---|---|---|---
`openString(String, Int)`|**path**: asset file path, **mode**: file access mode|Opens asset file as string|yes|Maybe\<String>
`openBytes(String, Int)`|**path**: asset file path, **mode**: file access mode|Opens asset file as a byte array|yes|Maybe\<ByteArray>
`openSave(String, Int, String)`|**path**: asset file path, **mode**: file access mode, **to**: save to location|Opens and save asset file.|yes|Maybe\<File>
`openBitmap(String, Int)`|**path**: asset file path, **mode**: file access mode|Opens asset file as a bitmap.|yes|Maybe\<Bitmap>
`listAll(String, Sorting)`|**path**: asset folder path, **sorting**: comparator object|Lists asset files recursively.|no|Flowable\<String>
`listOpen(String, Int, Boolean)`|**path**: asset folder path, **mode**: file access mode, **all**: list all files|Lists and opens asset files as input streams.|yes|Flowable\<InputStream>
`listOpenString(String, Int, Boolean)`|**path**: asset folder path, **mode**: file access mode, **all**: list all files|Lists and opens asset files as strings.|yes|Flowable\<String>
`listOpenBytes(String, Int, Boolean)`|**path**: asset folder path, **mode**: file access mode, **all**: list all files|Lists and opens asset files as byte arrays.|yes|Flowable\<ByteArray>
`listOpenSave(String, Int, String, Boolean)`|**path**: asset folder path, **mode**: file access mode, **to**: save to location, **all**: list all files|Lists, opens and saves asset files.|yes|Flowable\<Files>
`listOpenBitmap(String, Int, Boolean)`|**path**: asset folder path, **mode**: file access mode, **all**: list all files|Lists and opens asset files as bitmaps.|yes|Flowable\<Bitmap>
`listOpenTypeface(String, Int, Boolean)`|**path**: asset folder path, **mode**: file access mode, **all**: list all files|Lists and opens asset files as type faces.|yes|Flowable\<Typeface>
`listOpenFd(String, Boolean)`|**path**: asset folder path, **all**: list all files|Lists and opens asset files as file descriptors.|yes|Flowable\<AssetFileDescriptor>
`listOpenNonAssetFd(Int, String, Boolean)`|**cookie**: non asset file cookie, **path**: non asset folder path, **all**: list all files|Lists and opens non asset files as file descriptors.|yes|Flowable\<AssetFileDescriptor>
`listOpenXmlResourceParser(Int, String, Boolean)`|**cookie**: xml file cookie, **path**: xml folder path, **all**: list all files|Lists and opens files as xml resource parsers.|yes|Flowable\<XmlResourceParser>


## TODOs
**Probable**
- Properly setup scripts for doc and source jars. (*\*partially done*\*)
- Add testing for the app and mocks for the lib. (*\*partially done*\*)
- Make docs descriptive and add comments.
- Use an actual versioning structure(debug, release & dev, beta, production & flavors etc...).
- Put sample app on google play.
- ...

**Possible**
- ~~Figure out a better build configuration(maybe buildSrc and/or .kts).~~
- Create an advanced fragment in the sample app, for more real examples.
- Consider handling errors in the api(?).
- ...

## Credits
- [RxRecipes][RX-RECIPES] by [Scott Meschke][SCOTT-MESCHKE].
- [RxJava][RXJAVA] by [Reactivex-io][REACTIVEXIO]
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
[REACTIVEXIO]: http://reactivex.io/
[ANDROID]: https://source.android.com/
[GOOGLE]: https://android-developers.googleblog.com/
