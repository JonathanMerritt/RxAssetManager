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

/**
 * Reactive asset manager interface.
 *
 * This class outlines the main api for the library.
 */
interface IsRxAssetManager {
  /**
   * Companion object used for default parameter values.
   */
  companion object {
    /**
     *  Default access mode = [ACCESS_STREAMING].
     */
    const val MODE = ACCESS_STREAMING

    /**
     * Default path = "".
     */
    const val PATH = ""

    /**
     * Default file cookie = 0.
     */
    const val COOKIE = 0
  }

  /**
   * Defaults to an empty flowable.
   */
  fun getLocales(): Flowable<String> = Flowable.empty()

  /**
   * Defaults to a complete completable.
   */
  fun close(): Completable = complete()

  /**
   * Defaults to an empty maybe.
   */
  fun open(path: String, mode: Int = MODE): Maybe<InputStream> = empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openPair(path: String, mode: Int = MODE): Maybe<Pair<String, InputStream>> = empty()

  /**
   * Defaults to an empty maybe.
   */
  infix fun openTypeface(path: String): Maybe<Typeface> = empty()

  /**
   * Defaults to an empty maybe.
   */
  infix fun openTypefacePair(path: String): Maybe<Pair<String, Typeface>> = empty()

  /**
   * Defaults to an empty maybe.
   */
  infix fun openFd(path: String): Maybe<AssetFileDescriptor> = empty()

  /**
   * Defaults to an empty maybe.
   */
  infix fun openFdPair(path: String): Maybe<Pair<String, AssetFileDescriptor>> = empty()

  /**
   * Defaults to an empty flowable.
   */
  fun list(path: String = PATH): Flowable<String> = Flowable.empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openNonAssetFd(cookie: Int = COOKIE, path: String): Maybe<AssetFileDescriptor> = empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openNonAssetFdPair(cookie: Int = COOKIE, path: String): Maybe<Pair<String, AssetFileDescriptor>> = empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openXmlResourceParser(cookie: Int = COOKIE, path: String): Maybe<XmlResourceParser> = empty()

  /**
   * Defaults to an empty maybe.
   */
  fun openXmlResourceParserPair(cookie: Int = COOKIE, path: String): Maybe<Pair<String, XmlResourceParser>> =
      empty()
}
