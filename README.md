<img src="/app/src/main/ic_launcher-web.png" width="156" align="left"/>

# Rx AssetManager
[![JCenter][J-CENTER-SVG]][J-CENTER]
[![Maven Central][MAVEN-SVG]][MAVEN]
[![Travis][TRAVIS-CI-SVG]][TRAVIS-CI]

**An RxJava2 implementation of the Android AssetManager.**

#

Based on the blog [RxRecipes: Wrap your way to Rx][RX-RECIPES] by [Scott Meschke][SCOTT-MESCHKE].

_Any help or contribution is welcome._

## Download
#### Gradle
```groovy
  repositories {
    jcenter()
    //OR
    mavenCentral()
  }
 
  dependencies {
    implementation "com.github.jonathanmerritt.rxassetmanager:cor OR* core-ext:x.y.z"
  }
```

#### Maven
```xml
<dependencies>
 <dependency>
   <groupId>com.github.jonathanmerritt.rxassetmanager</groupId>
   <artifactId>core OR* core-ext</artifactId>
   <version>x.y.z</version>
   <type>pom</type>
 </dependency>
</dependencies>
```

*`core-ext` will include `core` automatically.


## Usage


## TODO
- Finish README.
- ...

## License
[Apache-2.0][LICENSE]

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

[J-CENTER-SVG]:https://api.bintray.com/packages/jonathanmerritt/RxAssetManager/core/images/download.svg
[J-CENTER]:https://api.bintray.com/packages/jonathanmerritt/RxAssetManager/core/
[MAVEN-SVG]: https://maven-badges.herokuapp.com/maven-central/com.github.jonathanmerritt.rxassetmanager/core/badge.svg
[MAVEN]: https://maven-badges.herokuapp.com/maven-central/com.github.jonathanmerritt.rxassetmanager/core/
[TRAVIS-CI-SVG]: https://travis-ci.org/JonathanMerritt/RxAssetManager.svg?branch=master
[TRAVIS-CI]: https://travis-ci.org/JonathanMerritt/RxAssetManager
[RELEASES]: https://github.com/JonathanMerritt/RxAssetManager/releases
[LICENSE]: https://github.com/JonathanMerritt/RxAssetManager/blob/master/LICENSE.txt
[RX-RECIPES]: https://hackernoon.com/rxrecipes-wrap-your-way-to-rx-fd40eb5254b6
[SCOTT-MESCHKE]: https://github.com/scottmeschke