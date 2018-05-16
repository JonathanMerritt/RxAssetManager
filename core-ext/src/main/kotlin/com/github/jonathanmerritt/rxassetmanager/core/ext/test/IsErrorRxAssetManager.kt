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

  override fun openString(path: String, mode: Int): Maybe<String> = Maybe.error(throwable)
  override fun openStringPair(path: String, mode: Int): Maybe<Pair<String, String>> = Maybe.error(throwable)
  override fun openBytes(path: String, mode: Int): Maybe<ByteArray> = Maybe.error(throwable)
  override fun openBytesPair(path: String, mode: Int): Maybe<Pair<String, ByteArray>> = Maybe.error(throwable)
  override fun openSave(path: String, mode: Int, to: String): Maybe<File> = Maybe.error(throwable)
  override fun openSavePair(path: String, mode: Int, to: String): Maybe<Pair<String, File>> = Maybe.error(
      throwable)

  override fun openBitmap(path: String, mode: Int): Maybe<Bitmap> = Maybe.error(throwable)
  override fun openBitmapPair(path: String, mode: Int): Maybe<Pair<String, Bitmap>> = Maybe.error(throwable)
  override fun listAll(path: String, sorting: Sorting): Flowable<String> = error(throwable)
  override fun listOpen(path: String, mode: Int, all: Boolean): Flowable<InputStream> = error(throwable)
  override fun listOpenPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, InputStream>> =
      error(throwable)

  override fun listOpenString(path: String, mode: Int, all: Boolean): Flowable<String> = error(throwable)
  override fun listOpenStringPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, String>> =
      error(throwable)

  override fun listOpenBytes(path: String, mode: Int, all: Boolean): Flowable<ByteArray> = error(throwable)
  override fun listOpenBytesPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, ByteArray>> =
      error(throwable)

  override fun listOpenSave(path: String, mode: Int, to: String, all: Boolean): Flowable<File> = error(throwable)
  override fun listOpenSavePair(path: String, mode: Int, to: String, all: Boolean): Flowable<Pair<String, File>> =
      error(throwable)

  override fun listOpenBitmap(path: String, mode: Int, all: Boolean): Flowable<Bitmap> = error(throwable)
  override fun listOpenBitmapPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, Bitmap>> =
      error(throwable)

  override fun listOpenTypeface(path: String, all: Boolean): Flowable<Typeface> = error(throwable)
  override fun listOpenTypefacePair(path: String, all: Boolean): Flowable<Pair<String, Typeface>> = error(
      throwable)

  override fun listOpenFd(path: String, all: Boolean): Flowable<AssetFileDescriptor> = error(throwable)
  override fun listOpenFdPair(path: String, all: Boolean): Flowable<Pair<String, AssetFileDescriptor>> =
      error(throwable)

  override fun listOpenNonAssetFd(cookie: Int, path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      error(throwable)

  override fun listOpenNonAssetFdPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, AssetFileDescriptor>> = error(throwable)

  override fun listOpenXmlResourceParser(cookie: Int, path: String, all: Boolean): Flowable<XmlResourceParser> =
      error(throwable)

  override fun listOpenXmlResourceParserPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, XmlResourceParser>> = error(throwable)
}
