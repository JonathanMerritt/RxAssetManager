import java.util.Date
import java.util.Properties

/*
 *     Copyright 2018 Jonathan Merritt
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

/*
 *     Copyright 2018 Jonathan Merritt 11R00TT00R11@GMAIL.COM
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

buildscript {
  val dev = hashMapOf<String, Any>()
  dev["name"] = "jonathanmerritt"
  dev["email"] = "11r00tt00r11@gmail.com"


  val pass = hashMapOf<String, Any>()
  pass["bintray"] = "pass.bintray"
  pass["gpg"] = "pass.gpg"
  pass["maven"] = "pass.maven"
  dev["pass"] = pass
  extra.set("dev", dev)


  val rep = hashMapOf<String, Any>()
  rep["name"] = rootProject.name
  rep["id"] = "com.github.${dev["name"]}.${(rep["name"] as String).toLowerCase()}"
  rep["labels"] = arrayOf("android", "android-library", "asset", "assets", "assetmanager", "assetsmanager", "android-asset",
    "android-assets", "android-asset-manager", "android-assets-manager")
  rep["licenses"] = arrayOf("Apache-2.0")
  extra.set("rep", rep)


  val sdk = hashMapOf<String, Any>()
  sdk["compile"] = 27
  sdk["min"] = 17
  sdk["tools"] = "27.0.3"
  extra.set("sdk", sdk)


  val dep = hashMapOf<String, Any>()
  dep["rxjava2Ext"] = "com.github.akarnokd:rxjava2-extensions:0.17.0"
  dep["timber"] = "com.jakewharton.timber:timber:4.5.1"

  dep["tests"] = arrayOf("junit:junit:4.12", "org.mockito:mockito-core:2.10.0", "org.assertj:assertj-core:3.8.0",
    "com.nhaarman:mockito-kotlin-kt1.1:1.5.0")
  dep["mockitoKotlin"] = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"

  val grp = hashMapOf<String, Any>()
  grp["support"] = "com.android.support"
  grp["constraint"] = "${grp["support"]}.constraint"
  grp["test"] = "${grp["support"]}.test"
  grp["kotlin"] = "org.jetbrains.kotlin"
  grp["rxjava2"] = "io.reactivex.rxjava2"
  extra.set("grp", grp)

  val mod = hashMapOf<String, Any>()
  mod["layout"] = "constraint-layout"
  mod["annots"] = "support-annotations"
  mod["reflect"] = "kotlin-reflect"
  extra.set("mod", mod)

  val ver = hashMapOf<String, Any>()
  ver["support"] = "27.1.0"
  ver["constraint"] = "1.1.0-beta6"
  ver["kotlin"] = "1.2.31"

  val support = hashMapOf<String, Any>()
  support["appcompat"] = "${grp["support"]}:appcompat-v7:${ver["support"]}"
  support["constraint"] = arrayOf("${grp["constraint"]}:${mod["layout"]}:${ver["constraint"]}",
    "${grp["constraint"]}:${mod["layout"]}-solver:${ver["constraint"]}")
  support["tests"] = arrayOf("${grp["test"]}:runner:1.0.1", "${grp["test"]}:rules:1.0.1", "${grp["support"]}:${mod["annots"]}:${ver["support"]}")
  support["espresso"] = "${grp["test"]}.espresso:espresso-core:3.0.1"
  dep["support"] = support

  val kotlin = hashMapOf<String, Any>()
  kotlin["gradle"] = "${grp["kotlin"]}:kotlin-gradle-plugin:${ver["kotlin"]}"
  kotlin["stdlib"] = "${grp["kotlin"]}:kotlin-stdlib:${ver["kotlin"]}"
  kotlin["tests"] = "${grp["kotlin"]}:kotlin-test-junit:${ver["kotlin"]}"
  dep["kotlin"] = kotlin

  /* reactivex rxjava2 */
  dep["rxjava2"] = arrayOf("${grp["rxjava2"]}:rxjava:2.1.7", "${grp["rxjava2"]}:rxandroid:2.0.1", "${grp["rxjava2"]}:rxkotlin:2.2.0")
  extra.set("dep", dep)

  repositories {
    google()
    jcenter()
    mavenCentral()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:3.2.0-alpha10")
    classpath("com.github.dcendents:android-maven-gradle-plugin:2.0")
    classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.0")
    classpath((dep["kotlin"] as HashMap<*, *>)["gradle"] as String)
    classpath("org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.16")
  }
}

subprojects {
  repositories {
    google()
    jcenter()
    mavenCentral()
  }

  beforeEvaluate {
    val lib = hashMapOf<String, Any>()
    lib["name"] = name
    lib["desc"] = "${(rootProject.extra["rep"] as HashMap<*, *>)["name"]}s ${lib["name"]} library"
    rootProject.extra.set("lib", lib)

    /*
    Version
    */
    val ver = hashMapOf<String, Any>()
    ver["name"] = if ("${lib["name"]}".contains("app")) "1.0.0" else "0.6.0"
    ver["code"] = "${ver["name"]}".replace(".", "").let { "$it${Date().date}" }.toBigInteger()
    rootProject.extra.set("ver", ver)
   // println(Date().date)
  }
}
task("clean", { delete(rootProject.buildDir) })

