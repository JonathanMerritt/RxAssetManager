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
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Maybe.fromCallable
import io.reactivex.rxkotlin.toCompletable
import io.reactivex.rxkotlin.toFlowable
import java.io.InputStream

open class RxAssetManager(protected val manager: AssetManager) : IsRxAssetManager {

  constructor(context: Context) : this(context.assets)

  override fun getLocales() = manager.locales.toFlowable()
  override fun close() = manager::close.toCompletable()
  override fun open(name: String, mode: Int): Maybe<InputStream> = fromCallable { manager.open(name, mode) }
  override fun openPair(name: String, mode: Int): Maybe<Pair<String, InputStream>> =
      fromCallable { name to manager.open(name, mode) }

  override infix fun openFd(name: String): Maybe<AssetFileDescriptor> = fromCallable { manager.openFd(name) }

  override infix fun openFdPair(name: String): Maybe<Pair<String, AssetFileDescriptor>> =
      fromCallable { name to manager.openFd(name) }

  override fun list(name: String): Flowable<String> = manager.list(name).toFlowable()
  override fun openNonAssetFd(cookie: Int, name: String): Maybe<AssetFileDescriptor> =
      fromCallable { manager.openNonAssetFd(cookie, name) }

  override fun openNonAssetFdPair(cookie: Int, name: String): Maybe<Pair<String, AssetFileDescriptor>> =
      fromCallable { name to manager.openNonAssetFd(cookie, name) }

  override fun openXmlResourceParser(cookie: Int, name: String): Maybe<XmlResourceParser> =
      fromCallable { manager.openXmlResourceParser(cookie, name) }

  override fun openXmlResourceParserPair(cookie: Int, name: String): Maybe<Pair<String, XmlResourceParser>> =
      fromCallable { name to manager.openXmlResourceParser(cookie, name) }
}
