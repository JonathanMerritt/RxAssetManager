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

package com.github.jonathanmerritt.rxassetmanager.core.ext.test

import android.content.res.AssetFileDescriptor
import android.content.res.XmlResourceParser
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Bitmap.createBitmap
import android.graphics.Typeface
import com.github.jonathanmerritt.rxassetmanager.core.ext.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.ext.Sorting
import com.github.jonathanmerritt.rxassetmanager.core.test.IsSuccessRxAssetManager.Companion.successString
import io.reactivex.Flowable
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream
import com.github.jonathanmerritt.rxassetmanager.core.test.IsSuccessRxAssetManager as isSuccessRxAssetManager


interface IsSuccessRxAssetManager : isSuccessRxAssetManager, IsRxAssetManager {
  object SuccessRxAssetManager : IsSuccessRxAssetManager

  companion object {
    val successBytes: ByteArray = byteArrayOf(0)
    val successFile = File("successFile")
    val successBitmap: Bitmap = createBitmap(1, 1, ARGB_8888)
  }

  override fun openString(path: String, mode: Int): Maybe<String> = Maybe.just(successString)
  override fun openStringPair(path: String, mode: Int): Maybe<Pair<String, String>> =
      Maybe.just(path to successString)

  override fun openBytes(path: String, mode: Int): Maybe<ByteArray> = Maybe.just(successBytes)
  override fun openBytesPair(path: String, mode: Int): Maybe<Pair<String, ByteArray>> =
      Maybe.just(path to successBytes)

  override fun openSave(path: String, mode: Int, to: String): Maybe<File> = Maybe.just(successFile)
  override fun openSavePair(path: String, mode: Int, to: String): Maybe<Pair<String, File>> =
      Maybe.just(path to successFile)

  override fun openBitmap(path: String, mode: Int): Maybe<Bitmap> = Maybe.just(successBitmap)
  override fun openBitmapPair(path: String, mode: Int): Maybe<Pair<String, Bitmap>> =
      Maybe.just(path to successBitmap)

  override fun listAll(path: String, sorting: Sorting): Flowable<String> = list(path).repeat(4)
  override fun listOpen(path: String, mode: Int, all: Boolean): Flowable<InputStream> =
      listAll(path).flatMapMaybe { open(path, mode) }

  override fun listOpenPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, InputStream>> =
      listAll(path).flatMapMaybe { openPair(path, mode) }

  override fun listOpenString(path: String, mode: Int, all: Boolean): Flowable<String> =
      listAll(path).flatMapMaybe { openString(path, mode) }

  override fun listOpenStringPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, String>> =
      listAll(path).flatMapMaybe { openStringPair(path, mode) }

  override fun listOpenBytes(path: String, mode: Int, all: Boolean): Flowable<ByteArray> =
      listAll(path).flatMapMaybe { openBytes(path, mode) }

  override fun listOpenBytesPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, ByteArray>> =
      listAll(path).flatMapMaybe { openBytesPair(path, mode) }

  override fun listOpenSave(path: String, mode: Int, to: String, all: Boolean): Flowable<File> =
      listAll(path).flatMapMaybe { openSave(path, mode, to) }

  override fun listOpenSavePair(path: String, mode: Int, to: String, all: Boolean): Flowable<Pair<String, File>> =
      listAll(path).flatMapMaybe { openSavePair(path, mode, to) }

  override fun listOpenBitmap(path: String, mode: Int, all: Boolean): Flowable<Bitmap> =
      listAll(path).flatMapMaybe { openBitmap(path, mode) }

  override fun listOpenBitmapPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, Bitmap>> =
      listAll(path).flatMapMaybe { openBitmapPair(path, mode) }

  override fun listOpenTypeface(path: String, all: Boolean): Flowable<Typeface> =
      listAll(path).flatMapMaybe(::openTypeface)

  override fun listOpenTypefacePair(path: String, all: Boolean): Flowable<Pair<String, Typeface>> =
      listAll(path).flatMapMaybe(::openTypefacePair)

  override fun listOpenFd(path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      listAll(path).flatMapMaybe(::openFd)

  override fun listOpenFdPair(path: String, all: Boolean): Flowable<Pair<String, AssetFileDescriptor>> =
      listAll(path).flatMapMaybe(::openFdPair)

  override fun listOpenNonAssetFd(cookie: Int, path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      listAll(path).flatMapMaybe { openNonAssetFd(cookie, path) }

  override fun listOpenNonAssetFdPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, AssetFileDescriptor>> =
      listAll(path).flatMapMaybe { openNonAssetFdPair(cookie, path) }

  override fun listOpenXmlResourceParser(cookie: Int, path: String, all: Boolean): Flowable<XmlResourceParser> =
      listAll(path).flatMapMaybe { openXmlResourceParser(cookie, path) }

  override fun listOpenXmlResourceParserPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, XmlResourceParser>> =
      listAll(path).flatMapMaybe { openXmlResourceParserPair(cookie, path) }
}
