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

  override fun getLocales(): Flowable<String> = Flowable.error(Throwable("getLocales()"))
  override fun close(): Completable = Completable.error(Throwable("close()"))
  override fun open(path: String, mode: Int): Maybe<InputStream> = error(
      Throwable("open(path = $path, mode = $mode)"))

  override fun openPair(path: String, mode: Int): Maybe<Pair<String, InputStream>> = error(
      Throwable("openPair(path = $path, mode = $mode)"))

  override fun openTypeface(path: String): Maybe<Typeface> = error(Throwable("openTypeface(path = $path)"))
  override fun openTypefacePair(path: String): Maybe<Pair<String, Typeface>> = error(
      Throwable("openTypefacePair(path = $path)"))

  override fun openFd(path: String): Maybe<AssetFileDescriptor> = error(Throwable("openFd(path = $path"))
  override fun openFdPair(path: String): Maybe<Pair<String, AssetFileDescriptor>> = error(
      Throwable("openFdPair(path = $path)"))

  override fun list(path: String): Flowable<String> = Flowable.error(Throwable("list(path = $path)"))
  override fun openNonAssetFd(cookie: Int, path: String): Maybe<AssetFileDescriptor> = error(
      Throwable("openNonAssetFd(cookie = $cookie, path = $path)"))

  override fun openNonAssetFdPair(cookie: Int, path: String): Maybe<Pair<String, AssetFileDescriptor>> =
      error(Throwable("openNonAssetFdPair(cookie = $cookie, path = $path)"))

  override fun openXmlResourceParser(cookie: Int, path: String): Maybe<XmlResourceParser> = error(
      Throwable("openXmlResourceParser(cookie = $cookie, path = $path)"))

  override fun openXmlResourceParserPair(cookie: Int, path: String): Maybe<Pair<String, XmlResourceParser>> =
      error(Throwable("openXmlResourceParserPair(cookie = $cookie, path = $path)"))
}
