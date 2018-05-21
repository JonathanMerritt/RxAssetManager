
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter.BASIC_ISO_DATE

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
object VERSION {
  const val name = "0.6.0"
  val code = (name.replace(".", "") + now().format(BASIC_ISO_DATE)).toBigInteger()
}

@Suppress("unused")
object ANDROID {
  const val compile = "android-P"
  const val target = 27
  const val min = 17
  const val buildTools = "28.0.0-rc2"
}

@Suppress("unused")
object PASSKEY {
  val bintray = Property("PASSKEY_BINTRAY")
  val gpg = Property("PASSKEY_GPG")
  val maven = Property("PASSKEY_MAVEN")
}

@Suppress("unused")
object DEPENDENCY {
  object VERSION {
    const val appcompat = "1.0.0-alpha1"
    const val viewpager = appcompat
    const val coordinatorlayout = appcompat
    const val material = appcompat
    const val constraintlayout = "1.1.0"
    const val kotlin = "1.2.41"
  }

  const val appcompat = "androidx.appcompat:appcompat:${VERSION.appcompat}"
  const val viewpager = "androidx.viewpager:viewpager:${VERSION.viewpager}"
  const val coordinatorlayout = "androidx.coordinatorlayout:coordinatorlayout:${VERSION.coordinatorlayout}"
  const val material = "com.google.android.material:material:${VERSION.material}"
  val constraintlayouts = arrayOf(
      "androidx.constraintlayout:constraintlayout:${VERSION.constraintlayout}",
      "androidx.constraintlayout:constraintlayout-solver:${VERSION.constraintlayout}"
  )

  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${VERSION.kotlin}"
  const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${VERSION.kotlin}"

  val rxjavas = arrayOf(
      "io.reactivex.rxjava2:rxjava:2.1.7",
      "io.reactivex.rxjava2:rxandroid:2.0.1"
  )
}

