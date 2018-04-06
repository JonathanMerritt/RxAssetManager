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

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.XmlResourceParser
import hu.akarnokd.rxjava2.operators.FlowableTransformers
import io.reactivex.Flowable
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream
import com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager as core

class RxAssetManager(context: Context) : core(context), IsRxAssetManager {

  override fun openString(name: String, mode: Int): Maybe<String> =
      open(name, mode).map { it.bufferedReader().use { it.readText() } }

  override fun openBytes(name: String, mode: Int): Maybe<ByteArray> = open(name, mode).map { it.readBytes() }

  override fun openSave(name: String, mode: Int, to: String): Maybe<File> =
      open(name, mode).map {
        File("$to/$name").apply {
          parentFile.run { mkdirs() }
          outputStream().use { (::copyTo) }
        }
      }

  override fun listAll(name: String): Flowable<String> =
      listPath(name).compose(FlowableTransformers.expand(::listPath))

  override fun listOpen(name: String, mode: Int, all: Boolean): Flowable<InputStream> =
      listFiles(name, all).flatMapMaybe { open(it, mode) }

  override fun listOpenString(name: String, mode: Int, all: Boolean): Flowable<String> =
      listFiles(name, all).flatMapMaybe { openString(it, mode) }

  override fun listOpenBytes(name: String, mode: Int, all: Boolean): Flowable<ByteArray> =
      listFiles(name, all).flatMapMaybe { openBytes(it, mode) }

  override fun listOpenSave(name: String, mode: Int, to: String, all: Boolean): Flowable<File> =
      listFiles(name, all).flatMapMaybe { openSave(it, mode, to) }

  override fun listOpenFd(name: String, all: Boolean): Flowable<AssetFileDescriptor> =
      listFiles(name, all).flatMapSingle(::openFd)

  override fun listOpenNonAssetFd(cookie: Int, name: String, all: Boolean): Flowable<AssetFileDescriptor> =
      listFiles(name, all).flatMapSingle { openNonAssetFd(cookie, it) }

  override fun listOpenXmlResourceParser(cookie: Int, name: String, all: Boolean): Flowable<XmlResourceParser> =
      listFiles(name, all).filter { it.endsWith(".xml") }.flatMapSingle { openXmlResourceParser(cookie, it) }

  private fun listPath(folderName: String): Flowable<String> =
      list(folderName).map {
        "$folderName/$it".run {
          if (length > 1 && substring(0, 1) == "/") replace(substring(0, 1), "") else this
        }
      }

  private fun listFiles(folderName: String, listAll: Boolean): Flowable<String> =
      (if (listAll) listAll(folderName) else listPath(folderName)).filter { it.contains(".") }
}
