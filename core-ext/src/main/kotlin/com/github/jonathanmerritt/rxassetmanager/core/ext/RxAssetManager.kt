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

class RxAssetManager(context: Context) : com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager(
    context), IsRxAssetManager {

  override fun openString(fileName: String, accessMode: Int): Maybe<String> =
      open(fileName, accessMode).map { it.bufferedReader().use { it.readText() } }

  override fun openBytes(fileName: String, accessMode: Int): Maybe<ByteArray> =
      open(fileName, accessMode).map { it.readBytes() }

  override fun openSave(fileName: String, accessMode: Int, saveFolder: String): Maybe<File> =
      open(fileName, accessMode).map {
        val file = File(String.format("%s/%s", saveFolder, fileName))
        file.parentFile!!.mkdirs()
        file.outputStream().use { out -> it.copyTo(out) }
        file
      }

  override fun listAll(folderName: String): Flowable<String> =
      listPath(folderName).compose(FlowableTransformers.expand(::listPath))

  override fun listOpen(folderName: String, accessMode: Int, listAll: Boolean): Flowable<InputStream> =
      listFiles(folderName, listAll).flatMapMaybe { open(it, accessMode) }

  override fun listOpenString(folderName: String, accessMode: Int, listAll: Boolean): Flowable<String> =
      listFiles(folderName, listAll).flatMapMaybe { openString(it, accessMode) }

  override fun listOpenBytes(folderName: String, accessMode: Int, listAll: Boolean): Flowable<ByteArray> =
      listFiles(folderName, listAll).flatMapMaybe { openBytes(it, accessMode) }

  override fun listOpenSave(folderName: String, accessMode: Int, saveFolder: String,
      listAll: Boolean): Flowable<File> =
      listFiles(folderName, listAll).flatMapMaybe { openSave(it, accessMode, saveFolder) }

  override fun listOpenFd(folderName: String, listAll: Boolean): Flowable<AssetFileDescriptor> =
      listFiles(folderName, listAll).flatMapSingle(::openFd)

  override fun listOpenNonAssetFd(cookie: Int, folderName: String,
      listAll: Boolean): Flowable<AssetFileDescriptor> =
      listFiles(folderName, listAll).flatMapSingle { openNonAssetFd(cookie, it) }

  override fun listOpenXmlResourceParser(cookie: Int, folderName: String,
      listAll: Boolean): Flowable<XmlResourceParser> =
      listFiles(folderName, listAll).filter { it.endsWith(".xml") }
          .flatMapSingle { openXmlResourceParser(cookie, it) }

  private fun listPath(folderName: String): Flowable<String> = list(folderName).map {
    val path = String.format("%s/%s", folderName, it)
    if (path.length > 1 && path.substring(0, 1) == "/") path.replace(path.substring(0, 1), "") else path
  }

  private fun listFiles(folderName: String, listAll: Boolean): Flowable<String> =
      (if (listAll) listAll(folderName) else listPath(folderName)).filter { it.contains(".") }
}
