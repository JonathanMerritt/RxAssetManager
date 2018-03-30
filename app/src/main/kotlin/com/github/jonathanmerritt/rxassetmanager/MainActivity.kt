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

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager
import com.github.jonathanmerritt.rxassetmanager.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivity : AppCompatActivity() {
  private var manager: IsRxAssetManager? = null
  private var binding: ActivityMainBinding? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    when (manager) {
      null -> manager = RxAssetManager(this)
    }
  }

  override fun onStart() {
    super.onStart()
    when {
      disposables == null || disposables!!.isDisposed -> disposables = CompositeDisposable()
    }
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    when {
      binding != null && manager != null -> binding!!.setClicks {
        getObserverable(it.id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ Timber.i("next(%s)", it) }, { Timber.e(it, it.message) },
                { Timber.i("complete()") }, { disposables!!.add(it) })
      }
    }
  }

  override fun onStop() {
    super.onStop()
    when {
      disposables != null && !disposables!!.isDisposed -> {
        disposables!!.dispose()
        disposables = null
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    when {
      binding != null -> {
        binding!!.unbind()
        binding = null
      }
    }
    when {
      manager != null -> manager = null
    }
  }

  private fun getObserverable(id: Int): Observable<*> {
    return when (id) {
      R.id.open -> manager!!.open(getString(R.string.folder_file)).toObservable()
      R.id.open_fd -> manager!!.openFd(getString(R.string.folder_file_2)).toObservable()
      R.id.list -> manager!!.list(getString(R.string.empty)).toObservable()
      R.id.open_non_asset_fd -> manager!!.openNonAssetFd(getString(R.string.manifest)).toObservable()
      R.id.open_xml_resource_parser -> manager!!.openXmlResourceParser(
          getString(R.string.manifest)).toObservable()
      else -> manager!!.locales.toObservable()
    }
  }

  companion object {
    private var disposables: CompositeDisposable? = null
  }
}