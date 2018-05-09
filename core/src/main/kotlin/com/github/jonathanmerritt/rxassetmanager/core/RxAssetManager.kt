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

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.content.res.XmlResourceParser
import android.graphics.Typeface
import android.graphics.Typeface.createFromAsset
import io.reactivex.Completable
import io.reactivex.Completable.fromAction
import io.reactivex.Flowable
import io.reactivex.Flowable.fromIterable
import io.reactivex.Maybe
import io.reactivex.Maybe.fromCallable
import java.io.InputStream

/**
 * Reactive asset manager.
 *
 * This class wraps the android asset manager with rxjava2 types.
 * @property manager android asset manager.
 * @constructor Creates a RxAssetManager instance.
 */
open class RxAssetManager(private val manager: AssetManager) : IsRxAssetManager {

  /**
   * Secondary constructor using a context.
   * @property context android context.
   * @constructor Creates a RxAssetManager instance.
   */
  constructor(context: Context) : this(context.assets)

  /**
   * Gets all locales.
   * @return a string flowable.
   */
  override fun getLocales(): Flowable<String> = fromIterable(manager.locales.asIterable())

  /**
   * Will close the asset manager.
   * @return a completable.
   */
  override fun close(): Completable = fromAction(manager::close)

  /**
   * Opens an asset file.
   * @param name the assets file path.
   * @param mode the access mode for the file.
   * @return an input stream maybe.
   */
  override fun open(name: String, mode: Int): Maybe<InputStream> = fromCallable { manager.open(name, mode) }

  /**
   * Opens an asset file.
   * @param name the assets file path.
   * @param mode the access mode for the file.
   * @return a string input stream pair maybe.
   */
  override fun openPair(name: String, mode: Int): Maybe<Pair<String, InputStream>> =
      fromCallable { name to manager.open(name, mode) }

  /**
   * Opens an asset font.
   * @param name the assets file path.
   * @return a typeface maybe.
   */
  override infix fun openTypeface(name: String): Maybe<Typeface> = fromCallable { createFromAsset(manager, name) }

  /**
   * Opens an asset font.
   * @param name the assets file path.
   * @return a string typeface pair maybe.
   */
  override infix fun openTypefacePair(name: String): Maybe<Pair<String, Typeface>> =
      openTypeface(name).map { name to it }

  /**
   * Opens an asset file.
   * @param name the assets file path.
   * @return an asset file descriptor maybe.
   */
  override infix fun openFd(name: String): Maybe<AssetFileDescriptor> = fromCallable { manager.openFd(name) }

  /**
   * Opens an asset file.
   * @param name the assets file path.
   * @return a string asset file descriptor pair maybe.
   */
  override infix fun openFdPair(name: String): Maybe<Pair<String, AssetFileDescriptor>> =
      fromCallable { name to manager.openFd(name) }

  /**
   * Lists asset files.
   * @param name the assets folder path.
   * @return a string flowable.
   */
  override fun list(name: String): Flowable<String> = fromIterable(manager.list(name).asIterable())

  /**
   * Opens a non asset file.
   * @param cookie cookie for the non assets file.
   * @param name the non assets file path.
   * @return an asset file descriptor maybe.
   */
  override fun openNonAssetFd(cookie: Int, name: String): Maybe<AssetFileDescriptor> =
      fromCallable { manager.openNonAssetFd(cookie, name) }

  /**
   * Opens a non asset file.
   * @param cookie cookie for the non assets file.
   * @param name the non assets file path.
   * @return a string asset file descriptor pair maybe.
   */
  override fun openNonAssetFdPair(cookie: Int, name: String): Maybe<Pair<String, AssetFileDescriptor>> =
      fromCallable { name to manager.openNonAssetFd(cookie, name) }

  /**
   * Opens a xml file.
   * @param cookie cookie for the xml file.
   * @param name the xml file path.
   * @return an xml resource parser maybe.
   */
  override fun openXmlResourceParser(cookie: Int, name: String): Maybe<XmlResourceParser> =
      fromCallable { manager.openXmlResourceParser(cookie, name) }

  /**
   * Opens a xml file.
   * @param cookie cookie for the xml file.
   * @param name the xml file path.
   * @return a string xml resource parser pair maybe.
   */
  override fun openXmlResourceParserPair(cookie: Int, name: String): Maybe<Pair<String, XmlResourceParser>> =
      fromCallable { name to manager.openXmlResourceParser(cookie, name) }
}
