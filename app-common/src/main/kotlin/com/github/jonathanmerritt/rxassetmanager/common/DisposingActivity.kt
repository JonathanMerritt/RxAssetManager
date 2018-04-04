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

package com.github.jonathanmerritt.rxassetmanager.common

import android.support.v7.app.AppCompatActivity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class DisposingActivity : AppCompatActivity() {
  private var disposables = CompositeDisposable().also { it.dispose() }

  override fun onStart() {
    super.onStart()
    if (disposables.isDisposed) disposables = CompositeDisposable()
  }

  override fun onStop() {
    super.onStop()
    if (!disposables.isDisposed) disposables.dispose()
  }

  protected fun Any.dispose(): Disposable = when {
    this is Single<*> -> toObservable()
    this is Maybe<*> -> toObservable()
    this is Flowable<*> -> toObservable()
    else -> {
      Timber.i("never()")
      Observable.never()
    }
  }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSubscribe { Timber.i("start()"); disposables.add(it) }
      .doOnNext { Timber.i("next(%s)", it) }
      .doOnComplete { Timber.i("complete()") }
      .doOnError { Timber.e(it, it.message) }
      .subscribe()
}
