import java.io.File
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter.BASIC_ISO_DATE
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

@Suppress("unused")
private object VERSION {
  private const val name = "0.6.0"
  private val code = (name.replace(".", "") + now().format(BASIC_ISO_DATE)).toBigInteger()
}

@Suppress("unused")
private object ANDROID {
  private const val compile = "android-P"
  private const val target = 27
  private const val min = 17
  private const val buildTools = "28.0.0-rc2"
}

@Suppress("unused")
private object PASSKEY {
  private val bintray = Property("PASSKEY_BINTRAY")
  private val gpg = Property("PASSKEY_GPG")
  private val maven = Property("PASSKEY_MAVEN")
}

@Suppress("unused")
private object DEPENDENCY {
  internal object VERSION {
    internal const val androidx = "1.0.0-alpha1"
    internal const val constraints = "1.1.0"
    internal const val kotlin = "1.2.41"
  }

  private const val appcompat = "androidx.appcompat:appcompat:${VERSION.androidx}"
  private const val viewpager = "androidx.viewpager:viewpager:${VERSION.androidx}"
  private const val coordinator = "androidx.coordinatorlayout:coordinatorlayout:${VERSION.androidx}"
  private const val material = "com.google.android.material:material:${VERSION.androidx}"
  private val constraints = arrayOf("androidx.constraintlayout:constraintlayout:${VERSION.constraints}",
      "androidx.constraintlayout:constraintlayout-solver:${VERSION.constraints}"
  )
  private const val espresso = "androidx.test.espresso:espresso-core:3.1.0-alpha2"

  private val androidxTests = arrayOf(
      "androidx.test:core:1.0.0-alpha2"
  )

  private const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${VERSION.kotlin}"
  private const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${VERSION.kotlin}"

  private val rxjavas = arrayOf("io.reactivex.rxjava2:rxjava:2.1.7", "io.reactivex.rxjava2:rxandroid:2.0.1")


  private val tests = arrayOf(
      "junit:junit:4.12",
      "org.mockito:mockito-core:2.10.0",
      "org.assertj:assertj-core:3.8.0",
      "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
  )
}

private object Property: Properties() {
 operator fun invoke(name: String): String = apply {
   File("local.properties").run { if (exists()) inputStream().use(::load) } }.getProperty(name)
}
