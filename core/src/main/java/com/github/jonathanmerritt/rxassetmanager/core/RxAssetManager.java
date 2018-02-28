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

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.io.InputStream;
import javax.annotation.Nonnull;

import static io.reactivex.Completable.fromAction;

public class RxAssetManager implements IsRxAssetManager {

  private final AssetManager manager;

  public RxAssetManager(@Nonnull Context context) {
    if (context == null) throw new RuntimeException("Context cannot be null!");
    final AssetManager manager = context.getAssets();
    if (manager == null) throw new RuntimeException("AssetManager cannot be null!");
    this.manager = manager;
  }

  @Override public Completable close() {
    return fromAction(manager::close);
  }

  @Override public Maybe<InputStream> open(String fileName, int accessMode) {
    return Maybe.defer(() -> Maybe.fromCallable(() -> manager.open(fileName, accessMode)));
  }

  @Override public Single<AssetFileDescriptor> openFd(String fileName) {
    return Single.defer(() -> Single.fromCallable(() -> manager.openFd(fileName)));
  }

  @Override public Flowable<String> list(String folderName) {
    return Flowable.defer(() -> Flowable.fromArray(manager.list(folderName)));
  }

  @Override public Single<AssetFileDescriptor> openNonAssetFd(int cookie, String fileName) {
    return Single.defer(() -> Single.fromCallable(() -> manager.openNonAssetFd(fileName)));
  }

  @Override public Single<XmlResourceParser> openXmlResourceParser(int cookie, String fileName) {
    return Single.defer(() -> Single.fromCallable(() -> manager.openXmlResourceParser(cookie, fileName)));
  }

  @Override public Flowable<String> getLocales() {
    return Flowable.fromArray(manager.getLocales());
  }
}
