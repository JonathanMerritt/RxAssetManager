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


@Suppress("unused")
object Sdks {
  const val compile = "android-P"
  const val target = 27
  const val min = 17
  const val tools = "28.0.0-rc2"
}

@Suppress("unused")
object Deps {

  object Versions {
    private const val androidx = "1.0.0-alpha1"
    const val appcompat = androidx
    const val viewpager = androidx
    const val coordinator = androidx
    const val material = androidx
    const val constraint = "1.1.0"
    const val kotlin = "1.2.41"
  }

  const val appcompat = "androidx.appcompat:appcompat:" + Versions.appcompat
  const val viewpager = "androidx.viewpager:viewpager:" + Versions.viewpager
  const val coordinator = "androidx.coordinatorlayout:coordinatorlayout:" + Versions.coordinator
  const val material = "com.google.android.material:material:" + Versions.material
  val constraint = arrayOf("androidx.constraintlayout:constraintlayout:" + Versions.constraint,
      "androidx.constraintlayout:constraintlayout-solver:" + Versions.constraint)

  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:" + Versions.kotlin
  const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + Versions.kotlin

  val rxjava = arrayOf("io.reactivex.rxjava2:rxjava:2.1.7", "io.reactivex.rxjava2:rxandroid:2.0.1")
}
