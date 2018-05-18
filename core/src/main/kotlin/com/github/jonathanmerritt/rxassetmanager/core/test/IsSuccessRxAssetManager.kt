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
import android.graphics.Typeface.DEFAULT
import android.os.ParcelFileDescriptor.fromFd
import com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Maybe.just
import java.io.InputStream
import java.io.Reader


interface IsSuccessRxAssetManager : IsRxAssetManager {
  object SuccessRxAssetManager : IsSuccessRxAssetManager

  companion object {
    object EmptyInputStream : InputStream() {
      override fun read() = 0
    }

    object EmptyAssetFileDescriptor : AssetFileDescriptor(fromFd(0), 0L, 0L)
    object EmptyXmlResourceParser : XmlResourceParser {
      override fun isWhitespace() = false
      override fun getAttributePrefix(p0: Int) = ""
      override fun getAttributeUnsignedIntValue(p0: String?, p1: String?, p2: Int) = 0
      override fun getAttributeUnsignedIntValue(p0: Int, p1: Int) = 0
      override fun getText() = ""
      override fun getAttributeIntValue(p0: String?, p1: String?, p2: Int) = 0
      override fun getAttributeIntValue(p0: Int, p1: Int) = 0
      override fun getColumnNumber() = 0
      override fun getAttributeListValue(p0: String?, p1: String?, p2: Array<out String>?, p3: Int) = 0
      override fun getAttributeListValue(p0: Int, p1: Array<out String>?, p2: Int) = 0
      override fun close() {}
      override fun getNamespaceUri(p0: Int) = ""
      override fun setProperty(p0: String?, p1: Any?) {}
      override fun getNamespaceCount(p0: Int) = 0
      override fun getLineNumber() = 0
      override fun require(p0: Int, p1: String?, p2: String?) {}
      override fun isEmptyElementTag() = false
      override fun nextText() = ""
      override fun isAttributeDefault(p0: Int) = false
      override fun getIdAttributeResourceValue(p0: Int) = 0
      override fun getAttributeFloatValue(p0: String?, p1: String?, p2: Float) = 0f
      override fun getAttributeFloatValue(p0: Int, p1: Float) = 0f
      override fun getStyleAttribute() = 0
      override fun getPrefix() = ""
      override fun getAttributeName(p0: Int) = ""
      override fun defineEntityReplacementText(p0: String?, p1: String?) {}
      override fun getClassAttribute() = ""
      override fun getPositionDescription() = ""
      override fun setInput(p0: Reader?) {}
      override fun setInput(p0: InputStream?, p1: String?) {}
      override fun getName() = ""
      override fun getFeature(p0: String?) = false
      override fun getNamespacePrefix(p0: Int) = ""
      override fun getProperty(p0: String?) = 0
      override fun next() = 0
      override fun getAttributeType(p0: Int) = ""
      override fun getEventType() = 0
      override fun getAttributeBooleanValue(p0: String?, p1: String?, p2: Boolean) = false
      override fun getAttributeBooleanValue(p0: Int, p1: Boolean) = false
      override fun nextTag() = 0
      override fun getAttributeResourceValue(p0: String?, p1: String?, p2: Int) = 0
      override fun getAttributeResourceValue(p0: Int, p1: Int) = 0
      override fun getAttributeCount() = 0
      override fun getInputEncoding() = ""
      override fun getAttributeNameResource(p0: Int) = 0
      override fun getTextCharacters(p0: IntArray?) = charArrayOf()
      override fun nextToken() = 0
      override fun getAttributeNamespace(p0: Int) = ""
      override fun getDepth() = 0
      override fun getAttributeValue(p0: Int) = ""
      override fun getAttributeValue(p0: String?, p1: String?) = ""
      override fun getIdAttribute() = ""
      override fun setFeature(p0: String?, p1: Boolean) {}
      override fun getNamespace(p0: String?) = ""
      override fun getNamespace() = ""
    }
  }

  override fun getLocales(): Flowable<String> = Flowable.just("", "")
  override fun close(): Completable = Completable.complete()
  override fun open(path: String, mode: Int): Maybe<InputStream> = just(EmptyInputStream)
  override fun openPair(path: String, mode: Int): Maybe<Pair<String, InputStream>> = just(path to EmptyInputStream)
  override fun openTypeface(path: String): Maybe<Typeface> = just(DEFAULT)
  override fun openTypefacePair(path: String): Maybe<Pair<String, Typeface>> = just(path to DEFAULT)
  override fun openFd(path: String): Maybe<AssetFileDescriptor> = just(EmptyAssetFileDescriptor)
  override fun openFdPair(path: String): Maybe<Pair<String, AssetFileDescriptor>> =
      just(path to EmptyAssetFileDescriptor)

  override fun list(path: String): Flowable<String> = Flowable.just("", "")
  override fun openNonAssetFd(cookie: Int, path: String): Maybe<AssetFileDescriptor> =
      just(EmptyAssetFileDescriptor)

  override fun openNonAssetFdPair(cookie: Int, path: String): Maybe<Pair<String, AssetFileDescriptor>> =
      just(path to EmptyAssetFileDescriptor)

  override fun openXmlResourceParser(cookie: Int, path: String): Maybe<XmlResourceParser> =
      just(EmptyXmlResourceParser)

  override fun openXmlResourceParserPair(cookie: Int, path: String): Maybe<Pair<String, XmlResourceParser>> =
      just(path to EmptyXmlResourceParser)
}
