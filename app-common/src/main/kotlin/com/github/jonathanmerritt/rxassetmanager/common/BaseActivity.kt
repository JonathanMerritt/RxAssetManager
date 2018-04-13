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

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.jonathanmerritt.rxassetmanager.common.extensions.observe
import com.github.jonathanmerritt.rxassetmanager.common.extensions.schedule
import com.github.jonathanmerritt.rxassetmanager.common.extensions.tag
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

abstract class BaseActivity(private val layout: Int) : AppCompatActivity() {
  protected abstract fun create()
  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout)
    create()
  }

  override fun onStop() {
    super.onStop()
    disposables.clear()
  }

  protected fun <T> T.dispose() = observe().schedule().subscribeBy(
      { Timber.e(it, it.message) },
      { Timber.i("complete()") },
      { Timber.i("next(${it as? String ?: it.tag()})") }).addTo(disposables)
}
