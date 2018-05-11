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

import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.depth


sealed class Sorting(private val compares: (String, String) -> Int = { _, _ -> 0 }) : Comparator<String> {
  override fun compare(s1: String?, s2: String?) = if (s1 == null || s2 == null) 0 else compares(s1, s2)
}

object Normal : Sorting()
object Depth : Sorting({ s1, s2 ->
  when {
    s1.depth == s2.depth -> 1
    s1.depth < s2.depth -> -1
    else -> 0
  }
})
