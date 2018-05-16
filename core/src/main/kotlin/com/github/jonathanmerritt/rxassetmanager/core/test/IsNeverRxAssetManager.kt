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
import io.reactivex.Maybe.never
import java.io.InputStream


interface IsNeverRxAssetManager : IsRxAssetManager {
  object NeverRxAssetManager : IsNeverRxAssetManager

  override fun getLocales(): Flowable<String> = Flowable.never()
  override fun close(): Completable = Completable.never()
  override fun open(path: String, mode: Int): Maybe<InputStream> = never()
  override fun openPair(path: String, mode: Int): Maybe<Pair<String, InputStream>> = never()
  override fun openTypeface(path: String): Maybe<Typeface> = never()
  override fun openTypefacePair(path: String): Maybe<Pair<String, Typeface>> = never()
  override fun openFd(path: String): Maybe<AssetFileDescriptor> = never()
  override fun openFdPair(path: String): Maybe<Pair<String, AssetFileDescriptor>> = never()
  override fun list(path: String): Flowable<String> = Flowable.never()
  override fun openNonAssetFd(cookie: Int, path: String): Maybe<AssetFileDescriptor> = never()
  override fun openNonAssetFdPair(cookie: Int, path: String): Maybe<Pair<String, AssetFileDescriptor>> = never()
  override fun openXmlResourceParser(cookie: Int, path: String): Maybe<XmlResourceParser> = never()
  override fun openXmlResourceParserPair(cookie: Int, path: String): Maybe<Pair<String, XmlResourceParser>> =
      never()
}
