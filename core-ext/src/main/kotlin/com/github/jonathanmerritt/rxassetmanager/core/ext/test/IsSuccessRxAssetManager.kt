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
import android.graphics.Typeface.DEFAULT
import com.github.jonathanmerritt.rxassetmanager.core.ext.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.ext.Sorting
import com.github.jonathanmerritt.rxassetmanager.core.test.IsSuccessRxAssetManager.Companion.EmptyAssetFileDescriptor
import com.github.jonathanmerritt.rxassetmanager.core.test.IsSuccessRxAssetManager.Companion.EmptyInputStream
import com.github.jonathanmerritt.rxassetmanager.core.test.IsSuccessRxAssetManager.Companion.EmptyXmlResourceParser
import io.reactivex.Flowable
import io.reactivex.Flowable.just
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream
import com.github.jonathanmerritt.rxassetmanager.core.test.IsSuccessRxAssetManager as isSuccessRxAssetManager


interface IsSuccessRxAssetManager : isSuccessRxAssetManager, IsRxAssetManager {
  object SuccessRxAssetManager : IsSuccessRxAssetManager

  companion object {
    val emptyBytes: ByteArray = byteArrayOf(0)
    val emptyFile = File("emptyFile")
    val emptyBitmap: Bitmap = createBitmap(1, 1, ARGB_8888)
  }

  override fun openString(path: String, mode: Int): Maybe<String> = Maybe.just("")
  override fun openStringPair(path: String, mode: Int): Maybe<Pair<String, String>> = Maybe.just(path to "")
  override fun openBytes(path: String, mode: Int): Maybe<ByteArray> = Maybe.just(emptyBytes)
  override fun openBytesPair(path: String, mode: Int): Maybe<Pair<String, ByteArray>> =
      Maybe.just(path to emptyBytes)

  override fun openSave(path: String, mode: Int, to: String): Maybe<File> = Maybe.just(emptyFile)
  override fun openSavePair(path: String, mode: Int, to: String): Maybe<Pair<String, File>> =
      Maybe.just(path to emptyFile)

  override fun openBitmap(path: String, mode: Int): Maybe<Bitmap> = Maybe.just(emptyBitmap)
  override fun openBitmapPair(path: String, mode: Int): Maybe<Pair<String, Bitmap>> =
      Maybe.just(path to emptyBitmap)

  override fun listAll(path: String, sorting: Sorting): Flowable<String> = just("", "")
  override fun listOpen(path: String, mode: Int, all: Boolean): Flowable<InputStream> =
      just(EmptyInputStream, EmptyInputStream)

  override fun listOpenPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, InputStream>> =
      just(path to EmptyInputStream, path to EmptyInputStream)

  override fun listOpenString(path: String, mode: Int, all: Boolean): Flowable<String> = just("", "")
  override fun listOpenStringPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, String>> =
      just(path to "", path to "")

  override fun listOpenBytes(path: String, mode: Int, all: Boolean): Flowable<ByteArray> =
      just(emptyBytes, emptyBytes)

  override fun listOpenBytesPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, ByteArray>> =
      just(path to emptyBytes, path to emptyBytes)

  override fun listOpenSave(path: String, mode: Int, to: String, all: Boolean): Flowable<File> =
      just(emptyFile, emptyFile)

  override fun listOpenSavePair(path: String, mode: Int, to: String, all: Boolean): Flowable<Pair<String, File>> =
      just(path to emptyFile, path to emptyFile)

  override fun listOpenBitmap(path: String, mode: Int, all: Boolean): Flowable<Bitmap> =
      just(emptyBitmap, emptyBitmap)

  override fun listOpenBitmapPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, Bitmap>> =
      just(path to emptyBitmap, path to emptyBitmap)

  override fun listOpenTypeface(path: String, all: Boolean): Flowable<Typeface> = just(DEFAULT, DEFAULT)

  override fun listOpenTypefacePair(path: String, all: Boolean): Flowable<Pair<String, Typeface>> =
      just(path to DEFAULT, path to DEFAULT)

  override fun listOpenFd(path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      just(EmptyAssetFileDescriptor, EmptyAssetFileDescriptor)

  override fun listOpenFdPair(path: String, all: Boolean): Flowable<Pair<String, AssetFileDescriptor>> =
      just(path to EmptyAssetFileDescriptor, path to EmptyAssetFileDescriptor)

  override fun listOpenNonAssetFd(cookie: Int, path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      just(EmptyAssetFileDescriptor, EmptyAssetFileDescriptor)

  override fun listOpenNonAssetFdPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, AssetFileDescriptor>> =
      just(path to EmptyAssetFileDescriptor, path to EmptyAssetFileDescriptor)

  override fun listOpenXmlResourceParser(cookie: Int, path: String, all: Boolean): Flowable<XmlResourceParser> =
      just(EmptyXmlResourceParser, EmptyXmlResourceParser)

  override fun listOpenXmlResourceParserPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, XmlResourceParser>> =
      just(path to EmptyXmlResourceParser, path to EmptyXmlResourceParser)
}
