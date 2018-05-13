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

/**
 * Comparator to sort thru file path/name strings.
 *
 * This class implements a Comparator<String>, and compares file path/name strings based on its type.
 */
sealed class Sorting : Comparator<String> {
  override fun compare(s1: String?, s2: String?) = if (s1 != null && s2 != null) when (this) {
    is Normal -> 0
    is Depth -> compareValuesBy(s1, s2, { it.length - it.replace("/", "").length })
  } else 0
}

/**
 * Will do nothing, denotes no sorting.
 *
 * Example:
 * --------folder/file.txt
 * --------folder/folder1/file.txt
 * --------folder/folder1/folder2/file.txt
 * --------folder/folder1/folder2/file1.txt
 * --------folder/folder1/file1.txt
 * --------folder/file1.txt
 */
object Normal : Sorting()

/**
 * Will sort file path/name strings based on their depth in their directories.
 *
 * Example:
 * --------folder/file.txt
 * --------folder/file1.txt
 * --------folder/folder1/file.txt
 * --------folder/folder1/file1.txt
 * --------folder/folder1/folder2/file.txt
 * --------folder/folder1/folder2/file1.txt
 */
object Depth : Sorting()
