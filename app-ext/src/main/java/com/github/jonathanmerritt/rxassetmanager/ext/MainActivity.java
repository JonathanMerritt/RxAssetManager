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

package com.github.jonathanmerritt.rxassetmanager.ext;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.github.jonathanmerritt.rxassetmanager.core.ext.IsRxAssetManager;
import com.github.jonathanmerritt.rxassetmanager.core.ext.RxAssetManager;
import com.github.jonathanmerritt.rxassetmanager.ext.databinding.ActivityMainBinding;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
  protected static CompositeDisposable disposables;
  private IsRxAssetManager manager;
  private ActivityMainBinding binding;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    if (manager == null) manager = new RxAssetManager(this);
  }

  @Override protected void onStart() {
    super.onStart();
    if (disposables == null || disposables.isDisposed()) disposables = new CompositeDisposable();
  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    if (binding != null && manager != null) binding.setClicks(
        view -> getObserverable(view.getId()).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(o -> Timber.i("next(%s)", o), t -> Timber.e(t, t.getMessage()),
                () -> Timber.i("complete()"), disposables::add));
  }

  @Override protected void onStop() {
    super.onStop();
    if (disposables != null && !disposables.isDisposed()) {
      disposables.dispose();
      disposables = null;
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (binding != null) {
      binding.unbind();
      binding = null;
    }
    if (manager != null) manager = null;
  }

  private Observable<?> getObserverable(int id) {
    switch (id) {
      case R.id.open_string:
        return manager.openString("Folder" + "/" + "File.txt").toObservable();
      case R.id.open_bytes:
        return manager.openBytes("Folder" + "/" + "File2.txt").toObservable();
      case R.id.open_save:
        return manager.openSave("Folder" + "/" + "Folder2" + "/" + "File.txt", getCacheDir().getAbsolutePath())
            .toObservable();
      case R.id.list_all:
        return manager.listAll("").toObservable();
      case R.id.list_open:
        return manager.listOpen("").toObservable();
      case R.id.list_open_string:
        return manager.listOpenString("Folder" + "/" + "Folder2").toObservable();
      case R.id.list_open_bytes:
        return manager.listOpenBytes("Folder", false).toObservable();
      case R.id.list_open_save:
        return manager.listOpenSave("Folder", getCacheDir().getAbsolutePath()).toObservable();
      case R.id.list_open_fd:
        return manager.listOpenFd("").toObservable();
      case R.id.list_open_non_asset_fd:
        return manager.listOpenNonAssetFd("/", false).toObservable();
      default:
        return manager.listOpenXmlResourceParser("/").toObservable();
    }
  }
}