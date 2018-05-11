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

interface IsRxAssetManager : isRxAssetManager {
  companion object {
    const val ALL = false
  }

  fun openString(path: String, mode: Int = MODE): Maybe<String> = Maybe.empty()
  fun openStringPair(path: String, mode: Int = MODE): Maybe<Pair<String, String>> = Maybe.empty()
  fun openBytes(path: String, mode: Int = MODE): Maybe<ByteArray> = Maybe.empty()
  fun openBytesPair(path: String, mode: Int = MODE): Maybe<Pair<String, ByteArray>> = Maybe.empty()
  fun openSave(path: String, mode: Int = MODE, to: String): Maybe<File> = Maybe.empty()
  fun openSavePair(path: String, mode: Int = MODE, to: String): Maybe<Pair<String, File>> = Maybe.empty()

  fun openBitmap(path: String, mode: Int = MODE): Maybe<Bitmap> = Maybe.empty()
  fun openBitmapPair(path: String, mode: Int = MODE): Maybe<Pair<String, Bitmap>> = Maybe.empty()

  fun listAll(path: String = PATH, sorting: Sorting = Normal): Flowable<String> = empty()
  fun listOpen(path: String = PATH, mode: Int = MODE, all: Boolean = ALL): Flowable<InputStream> =
      empty()

  fun listOpenPair(path: String = PATH, mode: Int = MODE, all: Boolean = ALL):
      Flowable<Pair<String, InputStream>> = empty()

  fun listOpenString(path: String = PATH, mode: Int = MODE, all: Boolean = ALL): Flowable<String> = empty()

  fun listOpenStringPair(path: String = PATH, mode: Int = MODE, all: Boolean = ALL):
      Flowable<Pair<String, String>> = empty()

  fun listOpenBytes(path: String = PATH, mode: Int = MODE, all: Boolean = ALL): Flowable<ByteArray> = empty()

  fun listOpenBytesPair(path: String = PATH, mode: Int = MODE, all: Boolean = ALL):
      Flowable<Pair<String, ByteArray>> = empty()

  fun listOpenSave(path: String = PATH, mode: Int = MODE, to: String, all: Boolean = ALL): Flowable<File> = empty()

  fun listOpenSavePair(path: String = PATH, mode: Int = MODE, to: String, all: Boolean = ALL):
      Flowable<Pair<String, File>> = empty()

  fun listOpenBitmap(path: String = PATH, mode: Int = MODE, all: Boolean = ALL): Flowable<Bitmap> = empty()

  fun listOpenBitmapPair(path: String = PATH, mode: Int = MODE, all: Boolean = ALL):
      Flowable<Pair<String, Bitmap>> = empty()

  fun listOpenTypeface(path: String = PATH, all: Boolean = ALL): Flowable<Typeface> = empty()
  fun listOpenTypefacePair(path: String = PATH, all: Boolean = ALL): Flowable<Pair<String, Typeface>> = empty()
  fun listOpenFd(path: String = PATH, all: Boolean = ALL): Flowable<AssetFileDescriptor> = empty()
  fun listOpenFdPair(path: String = PATH, all: Boolean = ALL): Flowable<Pair<String, AssetFileDescriptor>> =
      empty()

  fun listOpenNonAssetFd(cookie: Int = COOKIE, path: String = PATH,
      all: Boolean = ALL): Flowable<AssetFileDescriptor> = empty()

  fun listOpenNonAssetFdPair(cookie: Int = COOKIE, path: String = PATH, all: Boolean = ALL):
      Flowable<Pair<String, AssetFileDescriptor>> = empty()

  fun listOpenXmlResourceParser(cookie: Int = COOKIE, path: String = PATH, all: Boolean = ALL):
      Flowable<XmlResourceParser> = empty()

  fun listOpenXmlResourceParserPair(cookie: Int = COOKIE, path: String = PATH, all: Boolean = ALL):
      Flowable<Pair<String, XmlResourceParser>> = empty()
}
