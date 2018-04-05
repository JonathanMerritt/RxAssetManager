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
import android.content.res.XmlResourceParser
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import java.io.InputStream

open class RxAssetManager(context: Context) : IsRxAssetManager {

  private val manager = context.assets!!

  final override val locales: Flowable<String> = Flowable.fromArray(*manager.locales)

  final override val close: Completable = Completable.fromAction(manager::close)

  override fun open(name: String, mode: Int): Maybe<InputStream> =
      Maybe.defer { Maybe.fromCallable { manager.open(name, mode) } }

  override fun openFd(name: String): Single<AssetFileDescriptor> =
      Single.defer { Single.fromCallable { manager.openFd(name) } }

  override fun list(name: String): Flowable<String> = Flowable.defer { Flowable.fromArray(*manager.list(name)) }

  override fun openNonAssetFd(cookie: Int, name: String): Single<AssetFileDescriptor> =
      Single.defer { Single.fromCallable { manager.openNonAssetFd(name) } }

  override fun openXmlResourceParser(cookie: Int, name: String): Single<XmlResourceParser> =
      Single.defer { Single.fromCallable { manager.openXmlResourceParser(cookie, name) } }
}