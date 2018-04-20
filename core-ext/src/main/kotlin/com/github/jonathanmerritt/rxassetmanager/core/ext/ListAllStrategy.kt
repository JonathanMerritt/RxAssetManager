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

package com.github.jonathanmerritt.rxassetmanager.core.ext

import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.isFile

enum class ListAllStrategy {
  NORMAL, FOLDERS_FIRST, FILES_FIRST;

  fun compare(one: String, two: String): Int = when (this) {
      FOLDERS_FIRST -> if (one.isFile() || !two.isFile()) 1 else -1
      FILES_FIRST -> if (one.isFile() && !two.isFile()) -1 else 1
      NORMAL -> 0
  }
}
