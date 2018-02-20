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

package com.github.jonathanmerritt.rxassetmanager.core.ext;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.XmlResourceParser;
import com.github.jonathanmerritt.rxassetmanager.core.Utilities;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.io.File;
import java.io.InputStream;
import javax.annotation.Nonnull;

import static com.github.jonathanmerritt.rxassetmanager.core.Utilities.byteSource;
import static com.github.jonathanmerritt.rxassetmanager.core.Utilities.mapPath;
import static com.github.jonathanmerritt.rxassetmanager.core.Utilities.saveFile;
import static com.google.common.base.Charsets.UTF_8;
import static hu.akarnokd.rxjava2.operators.FlowableTransformers.expand;

public class RxAssetManager extends com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager
    implements IsRxAssetManager {

  public RxAssetManager(@Nonnull Context context) {
    super(context);
  }

  @Override public Maybe<String> openString(String fileName, int accessMode) {
    return open(fileName, accessMode).map(stream -> byteSource(stream).asCharSource(UTF_8).read());
  }

  @Override public Maybe<byte[]> openBytes(String fileName, int accessMode) {
    return open(fileName, accessMode).map(stream -> byteSource(stream).read());
  }

  @Override public Maybe<File> openSave(String fileName, int accessMode, String saveFolder) {
    return openBytes(fileName, accessMode).map(saveFile(fileName, saveFolder));
  }

  @Override public Flowable<String> listAll(String folderName) {
    return listPath(folderName).compose(expand(this::listPath));
  }

  @Override public Flowable<InputStream> listOpen(String folderName, int accessMode, boolean listAll) {
    return listFiles(folderName, listAll).flatMapMaybe(fileName -> open(fileName, accessMode));
  }

  @Override public Flowable<String> listOpenString(String folderName, int accessMode, boolean listAll) {
    return listFiles(folderName, listAll).flatMapMaybe(fileName -> openString(fileName, accessMode));
  }

  @Override public Flowable<byte[]> listOpenBytes(String folderName, int accessMode, boolean listAll) {
    return listFiles(folderName, listAll).flatMapMaybe(fileName -> openBytes(fileName, accessMode));
  }

  @Override
  public Flowable<File> listOpenSave(String folderName, int accessMode, String saveFolder, boolean listAll) {
    return listFiles(folderName, listAll).flatMapMaybe(fileName -> openSave(fileName, accessMode, saveFolder));
  }

  @Override public Flowable<AssetFileDescriptor> listOpenFd(String folderName, boolean listAll) {
    return listFiles(folderName, listAll).flatMapSingle(this::openFd);
  }

  @Override
  public Flowable<AssetFileDescriptor> listOpenNonAssetFd(int cookie, String folderName, boolean listAll) {
    return listFiles(folderName, listAll).flatMapSingle(fileName -> openNonAssetFd(cookie, fileName));
  }

  @Override
  public Flowable<XmlResourceParser> listOpenXmlResourceParser(int cookie, String folderName, boolean listAll) {
    return listFiles(folderName, listAll).filter(Utilities::isXml)
        .flatMapSingle(fileName -> openXmlResourceParser(cookie, fileName));
  }

  private Flowable<String> listPath(String folderName) {
    return list(folderName).map(name -> mapPath(folderName, name));
  }

  private Flowable<String> listFiles(String folderName, boolean listAll) {
    return (listAll ? listAll(folderName) : listPath(folderName)).filter(Utilities::isFile);
  }
}