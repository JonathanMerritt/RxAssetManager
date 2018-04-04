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