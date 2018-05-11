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

package com.github.jonathanmerritt.rxassetmanager.core

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager.ACCESS_STREAMING
import android.content.res.XmlResourceParser
import android.graphics.Typeface
import io.reactivex.Completable
import io.reactivex.Completable.complete
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Maybe.empty
import java.io.InputStream

interface IsRxAssetManager {
  companion object {
    const val MODE = ACCESS_STREAMING
    const val PATH = ""
    const val COOKIE = 0
  }

  fun getLocales(): Flowable<String> = Flowable.empty()
  fun close(): Completable = complete()
  fun open(path: String, mode: Int = MODE): Maybe<InputStream> = empty()
  fun openPair(path: String, mode: Int = MODE): Maybe<Pair<String, InputStream>> = empty()
  infix fun openTypeface(path: String): Maybe<Typeface> = Maybe.empty()
  infix fun openTypefacePair(path: String): Maybe<Pair<String, Typeface>> = Maybe.empty()
  infix fun openFd(path: String): Maybe<AssetFileDescriptor> = empty()
  infix fun openFdPair(path: String): Maybe<Pair<String, AssetFileDescriptor>> = empty()
  fun list(path: String = PATH): Flowable<String> = Flowable.empty()
  fun openNonAssetFd(cookie: Int = COOKIE, path: String): Maybe<AssetFileDescriptor> = empty()
  fun openNonAssetFdPair(cookie: Int = COOKIE, path: String): Maybe<Pair<String, AssetFileDescriptor>> = empty()
  fun openXmlResourceParser(cookie: Int = COOKIE, path: String): Maybe<XmlResourceParser> = empty()
  fun openXmlResourceParserPair(cookie: Int = COOKIE, path: String): Maybe<Pair<String, XmlResourceParser>> =
      empty()
}
