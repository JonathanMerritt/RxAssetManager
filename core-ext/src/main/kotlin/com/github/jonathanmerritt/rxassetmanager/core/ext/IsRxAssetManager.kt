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

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager.ACCESS_STREAMING
import android.content.res.XmlResourceParser
import io.reactivex.Flowable
import io.reactivex.Maybe
import java.io.File
import java.io.InputStream

interface IsRxAssetManager : com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager {

  fun openString(name: String, mode: Int = ACCESS_STREAMING): Maybe<String>

  fun openBytes(name: String, mode: Int = ACCESS_STREAMING): Maybe<ByteArray>

  fun openSave(name: String, mode: Int = ACCESS_STREAMING, to: String): Maybe<File>

  fun listAll(name: String): Flowable<String>

  fun listOpen(name: String, mode: Int = ACCESS_STREAMING, all: Boolean = false): Flowable<InputStream>

  fun listOpenString(name: String, mode: Int = ACCESS_STREAMING, all: Boolean = false): Flowable<String>

  fun listOpenBytes(name: String, mode: Int = ACCESS_STREAMING, all: Boolean = false): Flowable<ByteArray>

  fun listOpenSave(name: String, mode: Int = ACCESS_STREAMING, to: String, all: Boolean = false): Flowable<File>

  fun listOpenFd(name: String, all: Boolean = false): Flowable<AssetFileDescriptor>

  fun listOpenNonAssetFd(cookie: Int = 0, name: String, all: Boolean = false): Flowable<AssetFileDescriptor>

  fun listOpenXmlResourceParser(cookie: Int = 0, name: String, all: Boolean = false): Flowable<XmlResourceParser>
}