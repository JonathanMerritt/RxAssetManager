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
import android.content.res.AssetManager
import android.content.res.XmlResourceParser
import io.reactivex.Flowable
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream

interface IsRxAssetManager : com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager {

  fun openString(fileName: String, accessMode: Int): Maybe<String>

  fun openString(fileName: String): Maybe<String> {
    return openString(fileName, AssetManager.ACCESS_STREAMING)
  }

  fun openBytes(fileName: String, accessMode: Int): Maybe<ByteArray>

  fun openBytes(fileName: String): Maybe<ByteArray> {
    return openBytes(fileName, AssetManager.ACCESS_STREAMING)
  }

  fun openSave(fileName: String, accessMode: Int, saveFolder: String): Maybe<File>

  fun openSave(fileName: String, saveFolder: String): Maybe<File> {
    return openSave(fileName, AssetManager.ACCESS_STREAMING, saveFolder)
  }

  fun listAll(folderName: String): Flowable<String>

  fun listOpen(folderName: String, accessMode: Int, listAll: Boolean): Flowable<InputStream>

  fun listOpen(folderName: String, listAll: Boolean = false): Flowable<InputStream> {
    return listOpen(folderName, AssetManager.ACCESS_STREAMING, listAll)
  }

  fun listOpen(folderName: String, accessMode: Int): Flowable<InputStream> {
    return listOpen(folderName, accessMode, false)
  }

  fun listOpenString(folderName: String, accessMode: Int, listAll: Boolean): Flowable<String>

  fun listOpenString(folderName: String, listAll: Boolean = false): Flowable<String> {
    return listOpenString(folderName, AssetManager.ACCESS_STREAMING, listAll)
  }

  fun listOpenString(folderName: String, accessMode: Int): Flowable<String> {
    return listOpenString(folderName, accessMode, false)
  }

  fun listOpenBytes(folderName: String, accessMode: Int, listAll: Boolean): Flowable<ByteArray>

  fun listOpenBytes(folderName: String, listAll: Boolean = false): Flowable<ByteArray> {
    return listOpenBytes(folderName, AssetManager.ACCESS_STREAMING, listAll)
  }

  fun listOpenBytes(folderName: String, accessMode: Int): Flowable<ByteArray> {
    return listOpenBytes(folderName, accessMode, false)
  }

  fun listOpenSave(folderName: String, accessMode: Int, saveFolder: String, listAll: Boolean): Flowable<File>

  fun listOpenSave(folderName: String, saveFolder: String, listAll: Boolean = false): Flowable<File> {
    return listOpenSave(folderName, AssetManager.ACCESS_STREAMING, saveFolder, listAll)
  }

  fun listOpenSave(folderName: String, accessMode: Int, saveFolder: String): Flowable<File> {
    return listOpenSave(folderName, accessMode, saveFolder, false)
  }

  fun listOpenFd(folderName: String, listAll: Boolean): Flowable<AssetFileDescriptor>

  fun listOpenFd(folderName: String): Flowable<AssetFileDescriptor> {
    return listOpenFd(folderName, false)
  }

  fun listOpenNonAssetFd(cookie: Int, folderName: String, listAll: Boolean): Flowable<AssetFileDescriptor>

  fun listOpenNonAssetFd(folderName: String, listAll: Boolean = false): Flowable<AssetFileDescriptor> {
    return listOpenNonAssetFd(0, folderName, listAll)
  }

  fun listOpenNonAssetFd(cookie: Int, folderName: String): Flowable<AssetFileDescriptor> {
    return listOpenNonAssetFd(cookie, folderName, false)
  }

  fun listOpenXmlResourceParser(cookie: Int, folderName: String, listAll: Boolean): Flowable<XmlResourceParser>

  fun listOpenXmlResourceParser(folderName: String, listAll: Boolean = false): Flowable<XmlResourceParser> {
    return listOpenXmlResourceParser(0, folderName, listAll)
  }

  fun listOpenXmlResourceParser(cookie: Int, folderName: String): Flowable<XmlResourceParser> {
    return listOpenXmlResourceParser(cookie, folderName, false)
  }
}