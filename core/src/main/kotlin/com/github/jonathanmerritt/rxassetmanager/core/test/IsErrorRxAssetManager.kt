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

package com.github.jonathanmerritt.rxassetmanager.core.test

import android.content.res.AssetFileDescriptor
import android.content.res.XmlResourceParser
import android.graphics.Typeface
import com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Maybe.error
import java.io.InputStream


interface IsErrorRxAssetManager : IsRxAssetManager {
  object ErrorRxAssetManager : IsErrorRxAssetManager

  val throwable get() = { Throwable(javaClass.simpleName) }

  override fun getLocales(): Flowable<String> = Flowable.error(throwable)
  override fun close(): Completable = Completable.error(throwable)
  override fun open(path: String, mode: Int): Maybe<InputStream> = error(throwable)
  override fun openPair(path: String, mode: Int): Maybe<Pair<String, InputStream>> = error(throwable)
  override fun openTypeface(path: String): Maybe<Typeface> = error(throwable)
  override fun openTypefacePair(path: String): Maybe<Pair<String, Typeface>> = error(throwable)
  override fun openFd(path: String): Maybe<AssetFileDescriptor> = error(throwable)
  override fun openFdPair(path: String): Maybe<Pair<String, AssetFileDescriptor>> = error(throwable)
  override fun list(path: String): Flowable<String> = Flowable.error(throwable)
  override fun openNonAssetFd(cookie: Int, path: String): Maybe<AssetFileDescriptor> = error(throwable)
  override fun openNonAssetFdPair(cookie: Int, path: String): Maybe<Pair<String, AssetFileDescriptor>> =
      error(throwable)

  override fun openXmlResourceParser(cookie: Int, path: String): Maybe<XmlResourceParser> = error(throwable)
  override fun openXmlResourceParserPair(cookie: Int, path: String): Maybe<Pair<String, XmlResourceParser>> =
      error(throwable)
}
