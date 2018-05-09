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

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.content.res.XmlResourceParser
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.isFile
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.isFont
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.isImage
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.isXml
import io.reactivex.Flowable
import io.reactivex.Flowable.just
import io.reactivex.Maybe
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager as rxAssetManager

class RxAssetManager : rxAssetManager, IsRxAssetManager {

  constructor(manager: AssetManager) : super(manager)
  constructor(context: Context) : super(context)

  override fun openString(path: String, mode: Int): Maybe<String> =
      open(path, mode).map { it.bufferedReader().use(BufferedReader::readText) }

  override fun openStringPair(path: String, mode: Int): Maybe<Pair<String, String>> =
      openString(path, mode).map { path to it }

  override fun openBytes(path: String, mode: Int): Maybe<ByteArray> = open(path, mode).map { it.readBytes() }

  override fun openBytesPair(path: String, mode: Int): Maybe<Pair<String, ByteArray>> =
      openBytes(path, mode).map { path to it }

  override fun openSave(path: String, mode: Int, to: String): Maybe<File> =
      open(path, mode).map { input ->
        File("$to/$path").apply { parentFile.mkdirs().run { outputStream().use { (input::copyTo) } } }
      }

  override fun openSavePair(path: String, mode: Int, to: String): Maybe<Pair<String, File>> =
      openSave(path, mode, to).map { path to it }

  override fun openBitmap(path: String, mode: Int): Maybe<Bitmap> =
      open(path, mode).map(BitmapFactory::decodeStream)

  override fun openBitmapPair(path: String, mode: Int): Maybe<Pair<String, Bitmap>> =
      openBitmap(path, mode).map { path to it }

  override fun listAll(path: String, sorting: Sorting): Flowable<String> =
      listPath(path).flatMap { if (it.isFile()) just(it) else listAll(it, sorting) }.sorted(sorting::compare)

  override fun listOpen(path: String, mode: Int, all: Boolean): Flowable<InputStream> =
      listFiles(path, all).flatMapMaybe { open(it, mode) }

  override fun listOpenPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, InputStream>> =
      listFiles(path, all).flatMapMaybe { openPair(it, mode) }

  override fun listOpenString(path: String, mode: Int, all: Boolean): Flowable<String> =
      listFiles(path, all).flatMapMaybe { openString(it, mode) }

  override fun listOpenStringPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, String>> =
      listFiles(path, all).flatMapMaybe { openStringPair(it, mode) }

  override fun listOpenBytes(path: String, mode: Int, all: Boolean): Flowable<ByteArray> =
      listFiles(path, all).flatMapMaybe { openBytes(it, mode) }

  override fun listOpenBytesPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, ByteArray>> =
      listFiles(path, all).flatMapMaybe { openBytesPair(it, mode) }

  override fun listOpenSave(path: String, mode: Int, to: String, all: Boolean): Flowable<File> =
      listFiles(path, all).flatMapMaybe { openSave(it, mode, to) }

  override fun listOpenSavePair(path: String, mode: Int, to: String, all: Boolean): Flowable<Pair<String, File>> =
      listFiles(path, all).flatMapMaybe { openSavePair(it, mode, to) }

  override fun listOpenBitmap(path: String, mode: Int, all: Boolean): Flowable<Bitmap> =
      listFiles(path, all).filter(String::isImage).flatMapMaybe { openBitmap(it, mode) }

  override fun listOpenBitmapPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, Bitmap>> =
      listFiles(path, all).filter(String::isImage).flatMapMaybe { openBitmapPair(it, mode) }

  override fun listOpenTypeface(path: String, all: Boolean): Flowable<Typeface> =
      listFiles(path, all).filter(String::isFont).flatMapMaybe(::openTypeface)

  override fun listOpenTypefacePair(path: String, all: Boolean): Flowable<Pair<String, Typeface>> =
      listFiles(path, all).filter(String::isFont).flatMapMaybe(::openTypefacePair)

  override fun listOpenFd(path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      listFiles(path, all).flatMapMaybe(::openFd)

  override fun listOpenFdPair(path: String, all: Boolean): Flowable<Pair<String, AssetFileDescriptor>> =
      listFiles(path, all).flatMapMaybe(::openFdPair)

  override fun listOpenNonAssetFd(cookie: Int, path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      listFiles(path, all).flatMapMaybe { openNonAssetFd(cookie, it) }

  override fun listOpenNonAssetFdPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, AssetFileDescriptor>> =
      listFiles(path, all).flatMapMaybe { openNonAssetFdPair(cookie, it) }

  override fun listOpenXmlResourceParser(cookie: Int, path: String, all: Boolean): Flowable<XmlResourceParser> =
      listFiles(path, all).filter(String::isXml).flatMapMaybe { openXmlResourceParser(cookie, it) }

  override fun listOpenXmlResourceParserPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, XmlResourceParser>> =
      listFiles(path, all).filter(String::isXml).flatMapMaybe { openXmlResourceParserPair(cookie, it) }

  private fun listFiles(path: String, all: Boolean): Flowable<String> =
      (if (all) listAll(path, Depth) else listPath(path)).filter(String::isFile)

  private fun listPath(path: String): Flowable<String> =
      list(path).map { if (path.isBlank() || path == "/") it else "$path/$it" }
}
