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
import android.graphics.Typeface
import com.github.jonathanmerritt.rxassetmanager.core.ext.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.ext.Sorting
import io.reactivex.Flowable
import io.reactivex.Flowable.error
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream
import com.github.jonathanmerritt.rxassetmanager.core.test.IsErrorRxAssetManager as isErrorRxAssetManager


interface IsErrorRxAssetManager : isErrorRxAssetManager, IsRxAssetManager {
  object ErrorRxAssetManager : IsErrorRxAssetManager

  override fun openString(path: String, mode: Int): Maybe<String> =
      Maybe.error(Throwable("openString(path = $path, mode = $mode)"))

  override fun openStringPair(path: String, mode: Int): Maybe<Pair<String, String>> =
      Maybe.error(Throwable("openStringPair(path = $path, mode = $mode)"))

  override fun openBytes(path: String, mode: Int): Maybe<ByteArray> =
      Maybe.error(Throwable("openBytes(path = $path, mode = $mode)"))

  override fun openBytesPair(path: String, mode: Int): Maybe<Pair<String, ByteArray>> =
      Maybe.error(Throwable("openBytesPair(path = $path, mode = $mode)"))

  override fun openSave(path: String, mode: Int, to: String): Maybe<File> =
      Maybe.error(Throwable("openSave(path = $path, mode = $mode, to = $to)"))

  override fun openSavePair(path: String, mode: Int, to: String): Maybe<Pair<String, File>> =
      Maybe.error(Throwable("openSavePair(path = $path, mode = $mode, to = $to)"))

  override fun openBitmap(path: String, mode: Int): Maybe<Bitmap> =
      Maybe.error(Throwable("openBitmap(path = $path, mode = $mode)"))

  override fun openBitmapPair(path: String, mode: Int): Maybe<Pair<String, Bitmap>> =
      Maybe.error(Throwable("openBitmapPair(path = $path, mode = $mode)"))

  override fun listAll(path: String, sorting: Sorting): Flowable<String> =
      error(Throwable("listAll(path = $path, sorting = $sorting)"))

  override fun listOpen(path: String, mode: Int, all: Boolean): Flowable<InputStream> =
      error(Throwable("listOpen(path = $path, mode = $mode, all = $all)"))

  override fun listOpenPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, InputStream>> =
      error(Throwable("listOpenPair(path = $path, mode = $mode, all = $all)"))

  override fun listOpenString(path: String, mode: Int, all: Boolean): Flowable<String> =
      error(Throwable("listOpenString(path = $path, mode = $mode, all = $all)"))

  override fun listOpenStringPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, String>> =
      error(Throwable("listOpenStringPair(path = $path, mode = $mode, all = $all)"))

  override fun listOpenBytes(path: String, mode: Int, all: Boolean): Flowable<ByteArray> =
      error(Throwable("listOpenBytes(path = $path, mode = $mode, all = $all)"))

  override fun listOpenBytesPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, ByteArray>> =
      error(Throwable("listOpenBytesPair(path = $path, mode = $mode, all = $all)"))

  override fun listOpenSave(path: String, mode: Int, to: String, all: Boolean): Flowable<File> =
      error(Throwable("listOpenSave(path = $path, mode = $mode, to = $to, all = $all)"))

  override fun listOpenSavePair(path: String, mode: Int, to: String, all: Boolean): Flowable<Pair<String, File>> =
      error(Throwable("listOpenSavePair(path = $path, mode = $mode, to = $to, all = $all)"))

  override fun listOpenBitmap(path: String, mode: Int, all: Boolean): Flowable<Bitmap> =
      error(Throwable("listOpenBitmap(path = $path, mode = $mode, all = $all)"))

  override fun listOpenBitmapPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, Bitmap>> =
      error(Throwable("listOpenBitmapPair(path = $path, mode = $mode, all = $all)"))

  override fun listOpenTypeface(path: String, all: Boolean): Flowable<Typeface> =
      error(Throwable("listOpenTypeface(path = $path, all = $all)"))

  override fun listOpenTypefacePair(path: String, all: Boolean): Flowable<Pair<String, Typeface>> =
      error(Throwable("listOpenTypefacePair(path = $path, all = $all)"))

  override fun listOpenFd(path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      error(Throwable("listOpenFd(path = $path, all = $all)"))

  override fun listOpenFdPair(path: String, all: Boolean): Flowable<Pair<String, AssetFileDescriptor>> =
      error(Throwable("listOpenFdPair(path = $path, all = $all)"))

  override fun listOpenNonAssetFd(cookie: Int, path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      error(Throwable("listOpenNonAssetFd(cookie = $cookie, path = $path, all = $all)"))

  override fun listOpenNonAssetFdPair(cookie: Int, path: String,
      all: Boolean): Flowable<Pair<String, AssetFileDescriptor>> =
      error(Throwable("listOpenNonAssetFdPair(cookie = $cookie, path = $path, all = $all)"))

  override fun listOpenXmlResourceParser(cookie: Int, path: String, all: Boolean): Flowable<XmlResourceParser> =
      error(Throwable("listOpenXmlResourceParser(cookie = $cookie, path = $path, all = $all)"))

  override fun listOpenXmlResourceParserPair(cookie: Int, path: String,
      all: Boolean): Flowable<Pair<String, XmlResourceParser>> =
      error(Throwable("listOpenXmlResourceParserPair(cookie = $cookie, path = $path, all = $all)"))
}
