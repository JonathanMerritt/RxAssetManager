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
import android.content.res.XmlResourceParser
import android.graphics.Bitmap
import android.graphics.Typeface
import com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager.Companion.COOKIE
import com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager.Companion.MODE
import com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager.Companion.PATH
import io.reactivex.Flowable
import io.reactivex.Flowable.empty
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream
import com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager as isRxAssetManager

/**
 * Reactive asset manager extended interface.
 *
 * This class outlines the extended api for the library.
 */
interface IsRxAssetManager : isRxAssetManager {
  /**
   * Companion object used for default parameter values.
   */
  companion object {
    /**
     * Default list all = false.
     */
    const val ALL = false
  }

  /**
   * Defaults to an empty maybe.
   */
  fun openString(path: String, mode: Int = MODE): Maybe<String> = Maybe.empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openStringPair(path: String, mode: Int = MODE): Maybe<Pair<String, String>> = Maybe.empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openBytes(path: String, mode: Int = MODE): Maybe<ByteArray> = Maybe.empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openBytesPair(path: String, mode: Int = MODE): Maybe<Pair<String, ByteArray>> = Maybe.empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openSave(path: String, mode: Int = MODE, to: String): Maybe<File> = Maybe.empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openSavePair(path: String, mode: Int = MODE, to: String): Maybe<Pair<String, File>> = Maybe.empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openBitmap(path: String, mode: Int = MODE): Maybe<Bitmap> = Maybe.empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openBitmapPair(path: String, mode: Int = MODE): Maybe<Pair<String, Bitmap>> = Maybe.empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listAll(path: String = PATH, sorting: Sorting = Normal): Flowable<String> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpen(path: String = PATH, mode: Int = MODE, all: Boolean = ALL): Flowable<InputStream> =
      empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenPair(path: String = PATH, mode: Int = MODE, all: Boolean = ALL):
      Flowable<Pair<String, InputStream>> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenString(path: String = PATH, mode: Int = MODE, all: Boolean = ALL): Flowable<String> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenStringPair(path: String = PATH, mode: Int = MODE, all: Boolean = ALL):
      Flowable<Pair<String, String>> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenBytes(path: String = PATH, mode: Int = MODE, all: Boolean = ALL): Flowable<ByteArray> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenBytesPair(path: String = PATH, mode: Int = MODE, all: Boolean = ALL):
      Flowable<Pair<String, ByteArray>> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenSave(path: String = PATH, mode: Int = MODE, to: String, all: Boolean = ALL): Flowable<File> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenSavePair(path: String = PATH, mode: Int = MODE, to: String, all: Boolean = ALL):
      Flowable<Pair<String, File>> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenBitmap(path: String = PATH, mode: Int = MODE, all: Boolean = ALL): Flowable<Bitmap> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenBitmapPair(path: String = PATH, mode: Int = MODE, all: Boolean = ALL):
      Flowable<Pair<String, Bitmap>> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenTypeface(path: String = PATH, all: Boolean = ALL): Flowable<Typeface> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenTypefacePair(path: String = PATH, all: Boolean = ALL): Flowable<Pair<String, Typeface>> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenFd(path: String = PATH, all: Boolean = ALL): Flowable<AssetFileDescriptor> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenFdPair(path: String = PATH, all: Boolean = ALL): Flowable<Pair<String, AssetFileDescriptor>> =
      empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenNonAssetFd(cookie: Int = COOKIE, path: String = PATH,
      all: Boolean = ALL): Flowable<AssetFileDescriptor> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenNonAssetFdPair(cookie: Int = COOKIE, path: String = PATH, all: Boolean = ALL):
      Flowable<Pair<String, AssetFileDescriptor>> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenXmlResourceParser(cookie: Int = COOKIE, path: String = PATH, all: Boolean = ALL):
      Flowable<XmlResourceParser> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun listOpenXmlResourceParserPair(cookie: Int = COOKIE, path: String = PATH, all: Boolean = ALL):
      Flowable<Pair<String, XmlResourceParser>> = empty()
}
