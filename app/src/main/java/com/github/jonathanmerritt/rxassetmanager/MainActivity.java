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

package com.github.jonathanmerritt.rxassetmanager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager;
import com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager;
import com.github.jonathanmerritt.rxassetmanager.databinding.ActivityMainBinding;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
  private Disposable openPath() {
    return new RxAssetManager(this).open("Asset").subscribe(is -> {});
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
      case R.id.open:
        return manager.open(getString(R.string.folder_file)).toObservable();
      case R.id.open_fd:
        return manager.openFd(getString(R.string.folder_file_2)).toObservable();
      case R.id.list:
        return manager.list(getString(R.string.empty)).toObservable();
      case R.id.open_non_asset_fd:
        return manager.openNonAssetFd(getString(R.string.manifest)).toObservable();
      case R.id.open_xml_resource_parser:
        return manager.openXmlResourceParser(getString(R.string.manifest)).toObservable();
      default:
        return manager.getLocales().toObservable();
    }
  }
}