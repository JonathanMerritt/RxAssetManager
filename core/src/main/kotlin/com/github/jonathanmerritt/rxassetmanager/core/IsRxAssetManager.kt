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
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import java.io.InputStream

interface IsRxAssetManager {
  fun getLocales(): Flowable<String> = Flowable.empty()
  fun close(): Completable = Completable.complete()
  fun open(name: String, mode: Int = ACCESS_STREAMING): Maybe<InputStream> = Maybe.empty()
  fun openFd(name: String): Single<AssetFileDescriptor> = Single.never()
  fun list(name: String): Flowable<String> = Flowable.empty()
  fun openNonAssetFd(cookie: Int = 0, name: String): Single<AssetFileDescriptor> = Single.never()
  fun openXmlResourceParser(cookie: Int = 0, name: String): Single<XmlResourceParser> = Single.never()

  fun openPair(name: String, mode: Int = ACCESS_STREAMING): Maybe<Pair<String, InputStream>> = Maybe.empty()
  fun openFdPair(name: String): Single<Pair<String, AssetFileDescriptor>> = Single.never()
  fun openNonAssetFdPair(cookie: Int = 0, name: String): Single<Pair<String, AssetFileDescriptor>> = Single.never()
  fun openXmlResourceParserPair(cookie: Int = 0, name: String): Single<Pair<String, XmlResourceParser>> =
      Single.never()
}
