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
  private const val NAME = "0.6.0"
  private val CODE = (NAME.replace(".", "") + now().format(BASIC_ISO_DATE)).toBigInteger()
}

@Suppress("unused")
private object ANDROID {
  private const val COMPILE = "android-P"
  private const val TARGET = 27
  private const val MIN = 17
  private const val TOOLS = "28.0.0-rc2"
}

@Suppress("unused")
private object PASSKEY {
  private val BINTRAY = Property("PASSKEY_BINTRAY")
  private val GPG = Property("PASSKEY_GPG")
  private val MAVEN = Property("PASSKEY_MAVEN")
}

@Suppress("unused")
private object DEPENDENCY {
  private const val vANDROID_X = "1.0.0-alpha1"
  private const val vCONSTRAINTS = "1.1.0"
  private const val vKOTLIN = "1.2.41"

  private const val APP_COMPAT = "androidx.appcompat:appcompat:$vANDROID_X"
  private const val VIEW_PAGER = "androidx.viewpager:viewpager:$vANDROID_X"
  private const val COORDINATOR = "androidx.coordinatorlayout:coordinatorlayout:$vANDROID_X"
  private const val MATERIAL = "com.google.android.material:material:$vANDROID_X"
  private val CONSTRAINTS = arrayOf(
      "androidx.constraintlayout:constraintlayout:$vCONSTRAINTS",
      "androidx.constraintlayout:constraintlayout-solver:$vCONSTRAINTS"
  )

  private const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:$vKOTLIN"
  private const val KOTLIN_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$vKOTLIN"

  private val RX_JAVAS = arrayOf("io.reactivex.rxjava2:rxjava:2.1.7", "io.reactivex.rxjava2:rxandroid:2.0.1")
}

private object Property: Properties() {
 operator fun invoke(name: String): String = apply {
   File("local.properties").run { if (exists()) inputStream().use(::load) } }.getProperty(name)
}
