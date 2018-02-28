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

import android.content.res.AssetFileDescriptor;
import android.content.res.XmlResourceParser;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.io.File;
import java.io.InputStream;

import static android.content.res.AssetManager.ACCESS_STREAMING;

public interface IsRxAssetManager extends com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager {

  Maybe<String> openString(String fileName, int accessMode);

  default Maybe<String> openString(String fileName) {
    return openString(fileName, ACCESS_STREAMING);
  }

  Maybe<byte[]> openBytes(String fileName, int accessMode);

  default Maybe<byte[]> openBytes(String fileName) {
    return openBytes(fileName, ACCESS_STREAMING);
  }

  Maybe<File> openSave(String fileName, int accessMode, String saveFolder);

  default Maybe<File> openSave(String fileName, String saveFolder) {
    return openSave(fileName, ACCESS_STREAMING, saveFolder);
  }

  Flowable<String> listAll(String folderName);

  Flowable<InputStream> listOpen(String folderName, int accessMode, boolean listAll);

  default Flowable<InputStream> listOpen(String folderName, boolean listAll) {
    return listOpen(folderName, ACCESS_STREAMING, listAll);
  }

  default Flowable<InputStream> listOpen(String folderName, int accessMode) {
    return listOpen(folderName, accessMode, false);
  }

  default Flowable<InputStream> listOpen(String folderName) {
    return listOpen(folderName, false);
  }

  Flowable<String> listOpenString(String folderName, int accessMode, boolean listAll);

  default Flowable<String> listOpenString(String folderName, boolean listAll) {
    return listOpenString(folderName, ACCESS_STREAMING, listAll);
  }

  default Flowable<String> listOpenString(String folderName, int accessMode) {
    return listOpenString(folderName, accessMode, false);
  }

  default Flowable<String> listOpenString(String folderName) {
    return listOpenString(folderName, false);
  }

  Flowable<byte[]> listOpenBytes(String folderName, int accessMode, boolean listAll);

  default Flowable<byte[]> listOpenBytes(String folderName, boolean listAll) {
    return listOpenBytes(folderName, ACCESS_STREAMING, listAll);
  }

  default Flowable<byte[]> listOpenBytes(String folderName, int accessMode) {
    return listOpenBytes(folderName, accessMode, false);
  }

  default Flowable<byte[]> listOpenBytes(String folderName) {
    return listOpenBytes(folderName, false);
  }

  Flowable<File> listOpenSave(String folderName, int accessMode, String saveFolder, boolean listAll);

  default Flowable<File> listOpenSave(String folderName, String saveFolder, boolean listAll) {
    return listOpenSave(folderName, ACCESS_STREAMING, saveFolder, listAll);
  }

  default Flowable<File> listOpenSave(String folderName, int accessMode, String saveFolder) {
    return listOpenSave(folderName, accessMode, saveFolder, false);
  }

  default Flowable<File> listOpenSave(String folderName, String saveFolder) {
    return listOpenSave(folderName, saveFolder, false);
  }

  Flowable<AssetFileDescriptor> listOpenFd(String folderName, boolean listAll);

  default Flowable<AssetFileDescriptor> listOpenFd(String folderName) {
    return listOpenFd(folderName, false);
  }

  Flowable<AssetFileDescriptor> listOpenNonAssetFd(int cookie, String folderName, boolean listAll);

  default Flowable<AssetFileDescriptor> listOpenNonAssetFd(String folderName, boolean listAll) {
    return listOpenNonAssetFd(0, folderName, listAll);
  }

  default Flowable<AssetFileDescriptor> listOpenNonAssetFd(int cookie, String folderName) {
    return listOpenNonAssetFd(cookie, folderName, false);
  }

  default Flowable<AssetFileDescriptor> listOpenNonAssetFd(String folderName) {
    return listOpenNonAssetFd(folderName, false);
  }

  Flowable<XmlResourceParser> listOpenXmlResourceParser(int cookie, String folderName, boolean listAll);

  default Flowable<XmlResourceParser> listOpenXmlResourceParser(String folderName, boolean listAll) {
    return listOpenXmlResourceParser(0, folderName, listAll);
  }

  default Flowable<XmlResourceParser> listOpenXmlResourceParser(int cookie, String folderName) {
    return listOpenXmlResourceParser(cookie, folderName, false);
  }

  default Flowable<XmlResourceParser> listOpenXmlResourceParser(String folderName) {
    return listOpenXmlResourceParser(folderName, false);
  }
}