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

package com.github.jonathanmerritt.rxassetmanager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog.Builder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.github.jonathanmerritt.rxassetmanager.core.ext.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.ext.RxAssetManager
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io

abstract class RxAssetManagerFragment(
    private val layout: Int,
    private val created: RxAssetManagerFragment.(IsRxAssetManager) -> Unit,
    private val disposables: CompositeDisposable = CompositeDisposable(),
    private var manager: IsRxAssetManager = object : IsRxAssetManager {}) : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
      inflater.inflate(layout, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    manager = RxAssetManager(context!!).also { created(this, it) }
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onStop() {
    disposables.clear()
    super.onStop()
  }

  infix fun View.click(disposable: () -> Disposable) = setOnClickListener { disposables.add(disposable()) }

  val <T> T.subscribe: Disposable
    get() = when (this) {
      is Completable -> toObservable()
      is Maybe<*> -> toObservable()
      is Flowable<*> -> toObservable()
      else -> Observable.never()
    }
        .map(Any::toString)
        .toList()
        .subscribeOn(io())
        .observeOn(mainThread())
        .subscribe(
            { Builder(context!!).setItems(it.toTypedArray(), { d, _ -> d.dismiss() }).show() },
            { makeText(context, it.message, LENGTH_LONG).show() }
        )
}
