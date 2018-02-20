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

package com.github.jonathanmerritt.rxassetmanager.core;

import android.content.res.AssetFileDescriptor;
import android.content.res.XmlResourceParser;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.io.InputStream;

import static android.content.res.AssetManager.ACCESS_STREAMING;

public interface IsRxAssetManager {

  Completable close();

  Maybe<InputStream> open(String fileName, int accessMode);

  default Maybe<InputStream> open(String fileName) {
    return open(fileName, ACCESS_STREAMING);
  }

  Single<AssetFileDescriptor> openFd(String fileName);

  Flowable<String> list(String folderName);

  Single<AssetFileDescriptor> openNonAssetFd(int cookie, String fileName);

  default Single<AssetFileDescriptor> openNonAssetFd(String fileName) {
    return openNonAssetFd(0, fileName);
  }

  Single<XmlResourceParser> openXmlResourceParser(int cookie, String fileName);

  default Single<XmlResourceParser> openXmlResourceParser(String fileName) {
    return openXmlResourceParser(0, fileName);
  }

  Flowable<String> getLocales();
}