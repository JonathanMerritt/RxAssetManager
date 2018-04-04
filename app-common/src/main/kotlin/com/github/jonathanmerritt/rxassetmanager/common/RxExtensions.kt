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

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io
import timber.log.Timber

/** :JONATHAN MERRITT :11R00TT00RR11@GMAIL.COM :2018 **/

fun <T> Observable<T>.subscribeAnd(onSubscribe: (Disposable) -> Unit): Disposable {
  return subscribe({ Timber.i("next(%s)", it) }, { Timber.e(it, it.message) }, { Timber.i("complete()") },
      onSubscribe.also { Timber.i("subscribeAnd()") })
}

val <T> Observable<T>.schedule: Observable<T> get() = subscribeOn(io()).observeOn(mainThread())