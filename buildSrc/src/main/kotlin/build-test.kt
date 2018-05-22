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
object TESTDEPENDENCY {
  object VERSION {
    const val core = "1.0.0-alpha2"
    const val runner = "1.1.0-alpha2"
    const val rules = runner

  }

  val androidxTests = arrayOf(
      "androidx.test:core:${VERSION.core}",
      "androidx.test:runner:${VERSION.runner}",
      "androidx.test:rules:${VERSION.rules}"
  )
  const val espresso = "androidx.test.espresso:espresso-core:3.1.0-alpha2"

  const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${DEPENDENCY.VERSION.kotlin}"

  val tests = arrayOf(
      "junit:junit:4.12",
      "org.mockito:mockito-core:2.10.0",
      "org.assertj:assertj-core:3.8.0",
      "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
  )
}
