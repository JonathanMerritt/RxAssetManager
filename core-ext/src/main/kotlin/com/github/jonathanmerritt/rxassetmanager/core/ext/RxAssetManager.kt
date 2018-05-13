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

/**
 * Reactive asset manager extended.
 *
 * This class extends core.RxAssetManager, and has additional functionality.
 */
class RxAssetManager : rxAssetManager, IsRxAssetManager {

  /**
   * Main constructor.
   *
   * @property manager android asset manager.
   * @constructor Creates a RxAssetManager instance.
   */
  constructor(manager: AssetManager) : super(manager)

  /**
   * Secondary constructor.
   *
   * @property context android context.
   * @constructor Creates a RxAssetManager instance.
   */
  constructor(context: Context) : super(context)

  /**
   * Opens asset file as string.
   * @param path asset file path.
   * @param mode file access mode.
   * @return a string maybe.
   */
  override fun openString(path: String, mode: Int): Maybe<String> =
      open(path, mode).map { it.bufferedReader().use(BufferedReader::readText) }

  /**
   * Opens asset file as string pair.
   * @param path asset file path.
   * @param mode file access mode.
   * @return a string string pair maybe.
   */
  override fun openStringPair(path: String, mode: Int): Maybe<Pair<String, String>> =
      openString(path, mode).map { path to it }

  /**
   * Opens asset file as a byte array.
   * @param path asset file path.
   * @param mode file access mode.
   * @return a byte array maybe.
   */
  override fun openBytes(path: String, mode: Int): Maybe<ByteArray> = open(path, mode).map { it.readBytes() }

  /**
   * Opens asset file as a byte array pair.
   * @param path asset file path.
   * @param mode file access mode.
   * @return a string byte array pair maybe.
   */
  override fun openBytesPair(path: String, mode: Int): Maybe<Pair<String, ByteArray>> =
      openBytes(path, mode).map { path to it }

  /**
   * Opens and save asset file.
   * @param path asset file path.
   * @param mode file access mode.
   * @param to save to location.
   * @return a file maybe.
   */
  override fun openSave(path: String, mode: Int, to: String): Maybe<File> =
      open(path, mode).map { input ->
        File("$to/$path").apply { parentFile.mkdirs().run { outputStream().use { (input::copyTo) } } }
      }

  /**
   * Opens and save asset file pair.
   * @param path asset file path.
   * @param mode file access mode.
   * @param to save to location.
   * @return a string file pair maybe.
   */
  override fun openSavePair(path: String, mode: Int, to: String): Maybe<Pair<String, File>> =
      openSave(path, mode, to).map { path to it }

  /**
   * Opens asset file as a bitmap.
   * @param path asset file path.
   * @param mode file access mode.
   * @return a bitmap maybe.
   */
  override fun openBitmap(path: String, mode: Int): Maybe<Bitmap> =
      open(path, mode).map(BitmapFactory::decodeStream)

  /**
   * Opens asset file as a bitmap pair.
   * @param path asset file path.
   * @param mode file access mode.
   * @return a string bitmap pair maybe.
   */
  override fun openBitmapPair(path: String, mode: Int): Maybe<Pair<String, Bitmap>> =
      openBitmap(path, mode).map { path to it }

  /**
   * Lists asset files recursively.
   * @param path asset file path.
   * @return a string flowable.
   */
  override fun listAll(path: String, sorting: Sorting): Flowable<String> =
      listPath(path).flatMap { if (it.isFile()) just(it) else listAll(it, sorting) }.sorted(sorting::compare)

  /**
   * Lists and opens asset files as input streams.
   * @param path asset folder path.
   * @param mode file access mode.
   * @param all list all files.
   * @return an input stream flowable.
   */
  override fun listOpen(path: String, mode: Int, all: Boolean): Flowable<InputStream> =
      listFiles(path, all).flatMapMaybe { open(it, mode) }

  /**
   * Lists and opens asset files as input stream pairs.
   * @param path asset folder path.
   * @param mode file access mode.
   * @param all list all files.
   * @return a string input stream pair flowable.
   */
  override fun listOpenPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, InputStream>> =
      listFiles(path, all).flatMapMaybe { openPair(it, mode) }

  /**
   * Lists and opens asset files as strings.
   * @param path asset folder path.
   * @param mode file access mode.
   * @param all list all files.
   * @return a string flowable.
   */
  override fun listOpenString(path: String, mode: Int, all: Boolean): Flowable<String> =
      listFiles(path, all).flatMapMaybe { openString(it, mode) }

  /**
   * Lists and opens asset files as string pairs.
   * @param path asset folder path.
   * @param mode file access mode.
   * @param all list all files.
   * @return a string string pair flowable.
   */
  override fun listOpenStringPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, String>> =
      listFiles(path, all).flatMapMaybe { openStringPair(it, mode) }

  /**
   * Lists and opens asset files as byte arrays.
   * @param path asset folder path.
   * @param mode file access mode.
   * @param all list all files.
   * @return a byte array flowable.
   */
  override fun listOpenBytes(path: String, mode: Int, all: Boolean): Flowable<ByteArray> =
      listFiles(path, all).flatMapMaybe { openBytes(it, mode) }

  /**
   * Lists and opens asset files as byte array pairs.
   * @param path asset folder path.
   * @param mode file access mode.
   * @param all list all files.
   * @return a string byte array pair flowable.
   */
  override fun listOpenBytesPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, ByteArray>> =
      listFiles(path, all).flatMapMaybe { openBytesPair(it, mode) }

  /**
   * Lists, opens and saves asset files.
   * @param path asset folder path.
   * @param mode file access mode.
   * @param to save to location.
   * @param all list all files.
   * @return a file flowable.
   */
  override fun listOpenSave(path: String, mode: Int, to: String, all: Boolean): Flowable<File> =
      listFiles(path, all).flatMapMaybe { openSave(it, mode, to) }

  /**
   * Lists, opens and saves asset file pairs.
   * @param path asset folder path.
   * @param mode file access mode.
   * @param to save to location.
   * @param all list all files.
   * @return a string file pair flowable.
   */
  override fun listOpenSavePair(path: String, mode: Int, to: String, all: Boolean): Flowable<Pair<String, File>> =
      listFiles(path, all).flatMapMaybe { openSavePair(it, mode, to) }

  /**
   * Lists and opens asset files as bitmaps.
   * @param path asset folder path.
   * @param mode file access mode.
   * @param all list all files.
   * @return a bitmap flowable.
   */
  override fun listOpenBitmap(path: String, mode: Int, all: Boolean): Flowable<Bitmap> =
      listFiles(path, all).filter(String::isImage).flatMapMaybe { openBitmap(it, mode) }

  /**
   * Lists and opens asset files as bitmap pairs.
   * @param path asset folder path.
   * @param mode file access mode.
   * @param all list all files.
   * @return a string bitmap pair flowable.
   */
  override fun listOpenBitmapPair(path: String, mode: Int, all: Boolean): Flowable<Pair<String, Bitmap>> =
      listFiles(path, all).filter(String::isImage).flatMapMaybe { openBitmapPair(it, mode) }

  /**
   * Lists and opens asset files as typefaces.
   * @param path asset folder path.
   * @param all list all files.
   * @return a typeface flowable.
   */
  override fun listOpenTypeface(path: String, all: Boolean): Flowable<Typeface> =
      listFiles(path, all).filter(String::isFont).flatMapMaybe(::openTypeface)

  /**
   * Lists and opens asset files as typeface pairs.
   * @param path asset folder path.
   * @param all list all files.
   * @return a string typeface pair flowable.
   */
  override fun listOpenTypefacePair(path: String, all: Boolean): Flowable<Pair<String, Typeface>> =
      listFiles(path, all).filter(String::isFont).flatMapMaybe(::openTypefacePair)

  /**
   * Lists and opens asset files as file descriptors.
   * @param path asset folder path.
   * @param all list all files.
   * @return a file descriptor flowable.
   */
  override fun listOpenFd(path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      listFiles(path, all).flatMapMaybe(::openFd)

  /**
   * Lists and opens asset files as file descriptor pairs.
   * @param path asset folder path.
   * @param all list all files.
   * @return a string file descriptor pair flowable.
   */
  override fun listOpenFdPair(path: String, all: Boolean): Flowable<Pair<String, AssetFileDescriptor>> =
      listFiles(path, all).flatMapMaybe(::openFdPair)

  /**
   * Lists and opens non asset files as file descriptors.
   * @param path non asset folder path.
   * @param cookie non asset file cookie.
   * @param all list all files.
   * @return a file descriptor flowable.
   */
  override fun listOpenNonAssetFd(cookie: Int, path: String, all: Boolean): Flowable<AssetFileDescriptor> =
      listFiles(path, all).flatMapMaybe { openNonAssetFd(cookie, it) }

  /**
   * Lists and opens non asset files as file descriptor pairs.
   * @param path non asset folder path.
   * @param cookie non asset file cookie.
   * @param all list all files.
   * @return a string file descriptor pair flowable.
   */
  override fun listOpenNonAssetFdPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, AssetFileDescriptor>> =
      listFiles(path, all).flatMapMaybe { openNonAssetFdPair(cookie, it) }

  /**
   * Lists and opens xml files as xml resource parsers.
   * @param path xml folder path.
   * @param cookie xml file cookie.
   * @param all list all files.
   * @return an xml resource parser flowable.
   */
  override fun listOpenXmlResourceParser(cookie: Int, path: String, all: Boolean): Flowable<XmlResourceParser> =
      listFiles(path, all).filter(String::isXml).flatMapMaybe { openXmlResourceParser(cookie, it) }

  /**
   * Lists and opens xml files as xml resource parser pairs.
   * @param path xml folder path.
   * @param cookie xml file cookie.
   * @param all list all files.
   * @return a string xml resource parser pair flowable.
   */
  override fun listOpenXmlResourceParserPair(cookie: Int, path: String, all: Boolean):
      Flowable<Pair<String, XmlResourceParser>> =
      listFiles(path, all).filter(String::isXml).flatMapMaybe { openXmlResourceParserPair(cookie, it) }


  // Will list or list all file paths.
  private fun listFiles(path: String, all: Boolean): Flowable<String> =
      (if (all) listAll(path, Depth) else listPath(path)).filter(String::isFile)

  // Will list paths, then it either combines it into a new path or does nothing.
  private fun listPath(path: String): Flowable<String> =
      list(path).map { if (path.isBlank() || path == "/") it else "$path/$it" }
}
