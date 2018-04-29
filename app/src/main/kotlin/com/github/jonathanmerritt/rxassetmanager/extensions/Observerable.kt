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

package com.github.jonathanmerritt.rxassetmanager.extensions

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io

internal fun <T> T.asObservable() = when(this) {
  is Completable -> toObservable()
  is Single<*> -> toObservable()
  is Maybe<*> -> toObservable()
  is Flowable<*> -> toObservable()
  else -> Observable.error(TypeCastException("${this?.TAG} must be Completable, Single, Maybe or Flowable!"))
}

internal fun <T> Observable<T>.scheduleIoMain() = subscribeOn(io()).observeOn(mainThread())
