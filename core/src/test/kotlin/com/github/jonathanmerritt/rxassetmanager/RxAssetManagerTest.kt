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

package com.github.jonathanmerritt.rxassetmanager

import com.github.jonathanmerritt.rxassetmanager.core.test.IsEmptyRxAssetManager.EmptyRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.test.IsErrorRxAssetManager.ErrorRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.test.IsNeverRxAssetManager.NeverRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.test.IsSuccessRxAssetManager.Companion.successString
import com.github.jonathanmerritt.rxassetmanager.core.test.IsSuccessRxAssetManager.SuccessRxAssetManager
import org.junit.Test

class RxAssetManagerTest {

  @Test
  fun `getLocales() has no values, will complete`() {
    EmptyRxAssetManager
        .getLocales()

        .test()

        .assertNoValues()
        .assertComplete()
  }

  @Test
  fun `getLocales() has errors, will not complete`() {
    ErrorRxAssetManager
        .getLocales()

        .test()

        .assertErrorMessage("getLocales()")
        .assertNotComplete()
  }

  @Test
  fun `getLocales() has no values, will not complete`() {
    NeverRxAssetManager
        .getLocales()

        .test()

        .assertNoValues()
        .assertNotComplete()
  }

  @Test
  fun `getLocales() has values, will complete`() {
    SuccessRxAssetManager
        .getLocales()

        .test()

        .assertValueAt(0, successString)
        .assertComplete()
  }

}