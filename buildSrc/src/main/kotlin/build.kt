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
    const val androidx = "1.0.0-alpha1"
    const val constraints = "1.1.0"
    const val kotlin = "1.2.41"
  }

  const val appcompat = "androidx.appcompat:appcompat:${VERSION.androidx}"
  const val viewpager = "androidx.viewpager:viewpager:${VERSION.androidx}"
  const val coordinator = "androidx.coordinatorlayout:coordinatorlayout:${VERSION.androidx}"
  const val material = "com.google.android.material:material:${VERSION.androidx}"
  val constraints = arrayOf(
      "androidx.constraintlayout:constraintlayout:${VERSION.constraints}",
      "androidx.constraintlayout:constraintlayout-solver:${VERSION.constraints}"
  )

  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${VERSION.kotlin}"
  const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${VERSION.kotlin}"

  val rxjavas = arrayOf(
      "io.reactivex.rxjava2:rxjava:2.1.7",
      "io.reactivex.rxjava2:rxandroid:2.0.1"
  )
}

private object Property : Properties() {
  operator fun invoke(name: String): String = apply {
    File("local.properties").run { if (exists()) inputStream().use(::load) }
  }.getProperty(name)
}
