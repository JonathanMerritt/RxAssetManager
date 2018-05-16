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
import io.reactivex.Flowable.never
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream
import com.github.jonathanmerritt.rxassetmanager.core.test.IsNeverRxAssetManager as isNeverRxAssetManager


interface IsNeverRxAssetManager : isNeverRxAssetManager, IsRxAssetManager {
  object NeverRxAssetManager : IsNeverRxAssetManager

  override fun openString(path: String, mode: Int): Maybe<String> = Maybe.never()
  override fun openStringPair(path: String, mode: Int): Maybe<Pair<String, String>> = Maybe.never()
  override fun openBytes(path: String, mode: Int): Maybe<ByteArray> = Maybe.never()
  override fun openBytesPair(path: String, mode: Int): Maybe<Pair<String, ByteArray>> = Maybe.never()
  override fun openSave(path: String, mode: Int, to: String): Maybe<File> = Maybe.never()
  override fun openSavePair(path: String, mode: Int, to: String): Maybe<Pair<String, File>> = Maybe.never()
  override fun openBitmap(path: String, mode: Int): Maybe<Bitmap> = Maybe.never()
  override fun openBitmapPair(path: String, mode: Int): Maybe<Pair<String, Bitmap>> = Maybe.never()
  override fun listAll(path: String, sorting: Sorting): Flowable<String> = never()
  override fun listOpen(path: String, mode: Int, all: Boolean): Flowable<InputStream> = never()
  override fun listOpenPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, InputStream>> = never()
  override fun listOpenString(path: String, mode: Int, all: Boolean): Flowable<String> = never()
  override fun listOpenStringPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, String>> = never()
  override fun listOpenBytes(path: String, mode: Int, all: Boolean): Flowable<ByteArray> = never()
  override fun listOpenBytesPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, ByteArray>> =
      never()

  override fun listOpenSave(path: String, mode: Int, to: String, all: Boolean): Flowable<File> = never()
  override fun listOpenSavePair(path: String, mode: Int, to: String, all: Boolean): Flowable<Pair<String, File>> =
      never()

  override fun listOpenBitmap(path: String, mode: Int, all: Boolean): Flowable<Bitmap> = never()
  override fun listOpenBitmapPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, Bitmap>> = never()
  override fun listOpenTypeface(path: String, all: Boolean): Flowable<Typeface> = never()
  override fun listOpenTypefacePair(path: String, all: Boolean): Flowable<Pair<String, Typeface>> = never()
  override fun listOpenFd(path: String, all: Boolean): Flowable<AssetFileDescriptor> = never()
  override fun listOpenFdPair(path: String, all: Boolean): Flowable<Pair<String, AssetFileDescriptor>> = never()
  override fun listOpenNonAssetFd(cookie: Int, path: String, all: Boolean): Flowable<AssetFileDescriptor> = never()
  override fun listOpenNonAssetFdPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, AssetFileDescriptor>> = never()

  override fun listOpenXmlResourceParser(cookie: Int, path: String, all: Boolean): Flowable<XmlResourceParser> =
      never()

  override fun listOpenXmlResourceParserPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, XmlResourceParser>> = never()
}
