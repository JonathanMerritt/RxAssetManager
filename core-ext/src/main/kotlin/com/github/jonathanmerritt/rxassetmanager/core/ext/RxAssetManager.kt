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
import com.google.common.base.Charsets
import com.google.common.io.ByteSource
import com.google.common.io.Files
import hu.akarnokd.rxjava2.operators.FlowableTransformers
import io.reactivex.Flowable
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream

class RxAssetManager(context: Context) : com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager(
    context), IsRxAssetManager {

  override fun openString(fileName: String, accessMode: Int): Maybe<String> {
    return open(fileName, accessMode).map { input ->
      object : ByteSource() {
        override fun openStream(): InputStream {
          return input
        }
      }.asCharSource(Charsets.UTF_8).read()
    }
  }

  override fun openBytes(fileName: String, accessMode: Int): Maybe<ByteArray> {
    return open(fileName, accessMode).map { input ->
      object : ByteSource() {
        override fun openStream(): InputStream {
          return input
        }
      }.read()
    }
  }

  override fun openSave(fileName: String, accessMode: Int, saveFolder: String): Maybe<File> {
    return openBytes(fileName, accessMode).map { bytes ->
      val file = File(String.format("%s/%s", saveFolder, fileName))
      Files.createParentDirs(file)
      Files.write(bytes, file)
      file
    }
  }

  override fun listAll(folderName: String): Flowable<String> {
    return listPath(folderName).compose(FlowableTransformers.expand({ this.listPath(it) }))
  }

  override fun listOpen(folderName: String, accessMode: Int, listAll: Boolean): Flowable<InputStream> {
    return listFiles(folderName, listAll).flatMapMaybe { fileName -> open(fileName, accessMode) }
  }

  override fun listOpenString(folderName: String, accessMode: Int, listAll: Boolean): Flowable<String> {
    return listFiles(folderName, listAll).flatMapMaybe { fileName -> openString(fileName, accessMode) }
  }

  override fun listOpenBytes(folderName: String, accessMode: Int, listAll: Boolean): Flowable<ByteArray> {
    return listFiles(folderName, listAll).flatMapMaybe { fileName -> openBytes(fileName, accessMode) }
  }

  override fun listOpenSave(folderName: String, accessMode: Int, saveFolder: String,
      listAll: Boolean): Flowable<File> {
    return listFiles(folderName, listAll).flatMapMaybe { fileName -> openSave(fileName, accessMode, saveFolder) }
  }

  override fun listOpenFd(folderName: String, listAll: Boolean): Flowable<AssetFileDescriptor> {
    return listFiles(folderName, listAll).flatMapSingle({ this.openFd(it) })
  }

  override fun listOpenNonAssetFd(cookie: Int, folderName: String,
      listAll: Boolean): Flowable<AssetFileDescriptor> {
    return listFiles(folderName, listAll).flatMapSingle { fileName -> openNonAssetFd(cookie, fileName) }
  }

  override fun listOpenXmlResourceParser(cookie: Int, folderName: String,
      listAll: Boolean): Flowable<XmlResourceParser> {
    return listFiles(folderName, listAll).filter { fileName1 -> Files.getFileExtension(fileName1) == "xml" }
        .flatMapSingle { fileName -> openXmlResourceParser(cookie, fileName) }
  }

  private fun listPath(folderName: String): Flowable<String> {
    return list(folderName).map { name ->
      val path = String.format("%s/%s", folderName, name)
      if (path.length > 1 && path.substring(0, 1) == "/") path.replace(path.substring(0, 1), "") else path
    }
  }

  private fun listFiles(folderName: String, listAll: Boolean): Flowable<String> {
    return (if (listAll) listAll(folderName) else listPath(folderName)).filter { path ->
      !Files.getFileExtension(path).isEmpty()
    }
  }
}