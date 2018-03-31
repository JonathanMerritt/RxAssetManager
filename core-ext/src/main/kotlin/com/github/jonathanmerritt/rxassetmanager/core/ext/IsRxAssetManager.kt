/*
 *     Copyright 2018 Jonathan Merritt 11R00TT00R11@GMAIL.COM
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

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager.ACCESS_STREAMING
import android.content.res.XmlResourceParser
import io.reactivex.Flowable
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream

interface IsRxAssetManager : com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager {

  fun openString(fileName: String, accessMode: Int = ACCESS_STREAMING): Maybe<String>

  fun openBytes(fileName: String, accessMode: Int = ACCESS_STREAMING): Maybe<ByteArray>

  fun openSave(fileName: String, accessMode: Int = ACCESS_STREAMING, saveFolder: String): Maybe<File>

  fun listAll(folderName: String): Flowable<String>

  fun listOpen(folderName: String, accessMode: Int = ACCESS_STREAMING,
      listAll: Boolean = false): Flowable<InputStream>

  fun listOpenString(folderName: String, accessMode: Int = ACCESS_STREAMING,
      listAll: Boolean = false): Flowable<String>

  fun listOpenBytes(folderName: String, accessMode: Int = ACCESS_STREAMING,
      listAll: Boolean = false): Flowable<ByteArray>

  fun listOpenSave(folderName: String, accessMode: Int = ACCESS_STREAMING, saveFolder: String,
      listAll: Boolean = false): Flowable<File>

  fun listOpenFd(folderName: String, listAll: Boolean = false): Flowable<AssetFileDescriptor>

  fun listOpenNonAssetFd(cookie: Int = 0, folderName: String,
      listAll: Boolean = false): Flowable<AssetFileDescriptor>

  fun listOpenXmlResourceParser(cookie: Int = 0, folderName: String,
      listAll: Boolean = false): Flowable<XmlResourceParser>
}