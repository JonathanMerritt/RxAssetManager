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

package com.github.jonathanmerritt.rxassetmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.get_locales
import kotlinx.android.synthetic.main.activity_main.list
import kotlinx.android.synthetic.main.activity_main.open
import kotlinx.android.synthetic.main.activity_main.open_fd
import kotlinx.android.synthetic.main.activity_main.open_non_asset_fd
import kotlinx.android.synthetic.main.activity_main.open_xml_resource_parser
import timber.log.Timber

class MainActivity : AppCompatActivity() {
  private lateinit var manager: IsRxAssetManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    manager = RxAssetManager(this)
  }

  override fun onStart() {
    super.onStart()
    if (disposables.isDisposed) disposables = CompositeDisposable()
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    open.setOnClickListener { manager.open("Folder/File.txt").toObservable().subscribe }
    open_fd.setOnClickListener { manager.openFd("Folder/File2.txt").toObservable().subscribe }
    list.setOnClickListener { manager.list("").toObservable().subscribe }
    open_non_asset_fd.setOnClickListener { manager.openNonAssetFd("AndroidManifest.xml").toObservable().subscribe }
    open_xml_resource_parser.setOnClickListener {
      manager.openXmlResourceParser("AndroidManifest.xml").toObservable().subscribe
    }
    get_locales.setOnClickListener { manager.locales.toObservable().subscribe }
  }

  override fun onStop() {
    super.onStop()
    if (!disposables.isDisposed) disposables.dispose()
  }
}

private var disposables = CompositeDisposable().also { it.dispose() }

private val <T> Observable<T>.subscribe: Disposable
  get() = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
      { Timber.i("next(%s)", it) }, { Timber.e(it, it.message) }, { Timber.i("complete()") },
      { disposables.add(it) })
