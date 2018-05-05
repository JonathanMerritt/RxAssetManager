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
import android.content.res.AssetManager
import android.content.res.XmlResourceParser
import android.graphics.Bitmap
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.isBlankOrPath
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.isFile
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.isImage
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.isXml
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.readBitmap
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.readString
import com.github.jonathanmerritt.rxassetmanager.core.ext.extensions.save
import io.reactivex.BackpressureStrategy.BUFFER
import io.reactivex.Flowable
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream
import com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager as rxAssetManager

class RxAssetManager : rxAssetManager, IsRxAssetManager {

  constructor(manager: AssetManager) : super(manager)
  constructor(context: Context) : super(context)

  override fun openString(name: String, mode: Int): Maybe<String> = open(name, mode).map(InputStream::readString)
  override fun openStringPair(name: String, mode: Int): Maybe<Pair<String, String>> =
      open(name, mode).map { name to it.readString() }

  override fun openBytes(name: String, mode: Int): Maybe<ByteArray> = open(name, mode).map { it.readBytes() }
  override fun openBytesPair(name: String, mode: Int): Maybe<Pair<String, ByteArray>> =
      open(name, mode).map { name to it.readBytes() }

  override fun openSave(name: String, mode: Int, to: String): Maybe<File> =
      open(name, mode).map { it save "$to/$name" }

  override fun openSavePair(name: String, mode: Int, to: String): Maybe<Pair<String, File>> =
      open(name, mode).map { name to (it save "$to/$name") }

  override fun openBitmap(name: String, mode: Int): Maybe<Bitmap> =
      open(name, mode).map(InputStream::readBitmap)

  override fun openBitmapPair(name: String, mode: Int): Maybe<Pair<String, Bitmap>> =
      open(name, mode).map { name to it.readBitmap() }

  override fun listAll(name: String, strategy: ListAllStrategy): Flowable<String> =
      Flowable.create<String>({
        it.setDisposable(listExpand(name, it::onNext, it::onError).doOnComplete(it::onComplete).subscribe())
      }, BUFFER).sorted(strategy::compare)

  override fun listOpen(name: String, mode: Int, all: Boolean): Flowable<InputStream> =
      listFiles(name, all).flatMapMaybe { open(it, mode) }

  override fun listOpenPair(name: String, mode: Int, all: Boolean): Flowable<Pair<String, InputStream>> =
      listFiles(name, all).flatMapMaybe { openPair(it, mode) }

  override fun listOpenString(name: String, mode: Int, all: Boolean): Flowable<String> =
      listFiles(name, all).flatMapMaybe { openString(it, mode) }

  override fun listOpenStringPair(name: String, mode: Int, all: Boolean): Flowable<Pair<String, String>> =
      listFiles(name, all).flatMapMaybe { openStringPair(it, mode) }

  override fun listOpenBytes(name: String, mode: Int, all: Boolean): Flowable<ByteArray> =
      listFiles(name, all).flatMapMaybe { openBytes(it, mode) }

  override fun listOpenBytesPair(name: String, mode: Int, all: Boolean): Flowable<Pair<String, ByteArray>> =
      listFiles(name, all).flatMapMaybe { openBytesPair(it, mode) }

  override fun listOpenSave(name: String, mode: Int, to: String, all: Boolean): Flowable<File> =
      listFiles(name, all).flatMapMaybe { openSave(it, mode, to) }

  override fun listOpenSavePair(name: String, mode: Int, to: String, all: Boolean): Flowable<Pair<String, File>> =
      listFiles(name, all).flatMapMaybe { openSavePair(it, mode, to) }

  override fun listOpenBitmap(name: String, mode: Int, all: Boolean): Flowable<Bitmap> =
      listFiles(name, all).filter(String::isImage).flatMapMaybe { openBitmap(it, mode) }

  override fun listOpenBitmapPair(name: String, mode: Int, all: Boolean): Flowable<Pair<String, Bitmap>> =
      listFiles(name, all).filter(String::isImage).flatMapMaybe { openBitmapPair(it, mode) }

  override fun listOpenFd(name: String, all: Boolean): Flowable<AssetFileDescriptor> =
      listFiles(name, all).flatMapMaybe(::openFd)

  override fun listOpenFdPair(name: String, all: Boolean): Flowable<Pair<String, AssetFileDescriptor>> =
      listFiles(name, all).flatMapMaybe(::openFdPair)

  override fun listOpenNonAssetFd(cookie: Int, name: String, all: Boolean): Flowable<AssetFileDescriptor> =
      listFiles(name, all).flatMapMaybe { openNonAssetFd(cookie, it) }

  override fun listOpenNonAssetFdPair(cookie: Int, name: String, all: Boolean):
      Flowable<Pair<String, AssetFileDescriptor>> =
      listFiles(name, all).flatMapMaybe { openNonAssetFdPair(cookie, it) }

  override fun listOpenXmlResourceParser(cookie: Int, name: String, all: Boolean): Flowable<XmlResourceParser> =
      listFiles(name, all).filter(String::isXml).flatMapMaybe { openXmlResourceParser(cookie, it) }

  override fun listOpenXmlResourceParserPair(cookie: Int, name: String, all: Boolean):
      Flowable<Pair<String, XmlResourceParser>> =
      listFiles(name, all).filter(String::isXml).flatMapMaybe { openXmlResourceParserPair(cookie, it) }


  private fun listExpand(name: String, next: (String) -> Unit, error: (Throwable) -> Unit): Flowable<String> =
      listPath(name).doOnNext(next).doOnError(error).flatMap {
        if (it.isFile()) Flowable.empty() else listExpand(it, next, error)
      }

  private fun listFiles(name: String, all: Boolean): Flowable<String> =
      (if (all) listAll(name) else listPath(name)).filter(String::isFile)

  private fun listPath(name: String): Flowable<String> =
      list(name).map { if (name.isBlankOrPath()) it else "$name/$it" }
}
