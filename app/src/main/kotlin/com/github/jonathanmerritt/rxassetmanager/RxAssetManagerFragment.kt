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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jonathanmerritt.rxassetmanager.core.ext.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.ext.RxAssetManager
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_core.getLocals
import kotlinx.android.synthetic.main.fragment_core.list
import kotlinx.android.synthetic.main.fragment_core.open
import kotlinx.android.synthetic.main.fragment_core.openFd
import kotlinx.android.synthetic.main.fragment_core.openFdPair
import kotlinx.android.synthetic.main.fragment_core.openNonAssetFd
import kotlinx.android.synthetic.main.fragment_core.openNonAssetFdPair
import kotlinx.android.synthetic.main.fragment_core.openPair
import kotlinx.android.synthetic.main.fragment_core.openXmlResParser
import kotlinx.android.synthetic.main.fragment_core.openXmlResParserPair
import kotlinx.android.synthetic.main.fragment_core_ext.listAll
import kotlinx.android.synthetic.main.fragment_core_ext.listOpen
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBitmap
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBitmapPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBytes
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBytesPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenFd
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenFdPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenNonAssetFd
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenNonAssetFdPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenSave
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenSavePair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenString
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenStringPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenXmlResourceParser
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenXmlResourceParserPair
import kotlinx.android.synthetic.main.fragment_core_ext.openBitmap
import kotlinx.android.synthetic.main.fragment_core_ext.openBitmapPair
import kotlinx.android.synthetic.main.fragment_core_ext.openBytes
import kotlinx.android.synthetic.main.fragment_core_ext.openBytesPair
import kotlinx.android.synthetic.main.fragment_core_ext.openSave
import kotlinx.android.synthetic.main.fragment_core_ext.openSavePair
import kotlinx.android.synthetic.main.fragment_core_ext.openString
import kotlinx.android.synthetic.main.fragment_core_ext.openStringPair


const val ROOT = "/"
const val MANI = "AndroidManifest.xml"
const val FOLDER = "folder"
const val FILE = "folder/file.txt"
const val ICON = "folder/icon.png"
const val FILE1 = "folder/folder1/file1.txt"
const val FILE2 = "folder/folder1/folder2/file2.txt"

class Core : RxAssetManagerFragment(R.layout.fragment_core, { manager ->
  getLocals subscribe manager.getLocales()

  open subscribe manager.open(FILE)
  openPair subscribe manager.openPair(FILE)

  openFd subscribe manager.openFd(FILE1)
  openFdPair subscribe manager.openFdPair(FILE1)

  list subscribe manager.list()

  openNonAssetFd subscribe manager.openNonAssetFd(name = MANI)
  openNonAssetFdPair subscribe manager.openNonAssetFdPair(name = MANI)

  openXmlResParser subscribe manager.openXmlResourceParser(name = MANI)
  openXmlResParserPair subscribe manager.openXmlResourceParserPair(name = MANI)
})

class CoreExt : RxAssetManagerFragment(R.layout.fragment_core_ext, { manager ->
  openString subscribe manager.openString(FILE)
  openStringPair subscribe manager.openStringPair(FILE)

  openBytes subscribe manager.openBytes(FILE1)
  openBytesPair subscribe manager.openBytesPair(FILE1)

  openSave subscribe manager.openSave(FILE2, to = context!!.cacheDir.path)
  openSavePair subscribe manager.openSavePair(FILE2, to = context!!.cacheDir.path)

  openBitmap subscribe manager.openBitmap(ICON)
  openBitmapPair subscribe manager.openBitmapPair(ICON)

  listAll subscribe manager.listAll()

  listOpen subscribe manager.listOpen(FOLDER, all = true)
  listOpenPair subscribe manager.listOpenPair(FOLDER, all = true)

  listOpenString subscribe manager.listOpenString(FOLDER, all = true)
  listOpenStringPair subscribe manager.listOpenStringPair(FOLDER, all = true)

  listOpenBytes subscribe manager.listOpenBytes(FOLDER, all = true)
  listOpenBytesPair subscribe manager.listOpenBytesPair(FOLDER, all = true)

  listOpenSave subscribe manager.listOpenSave(FOLDER, to = context!!.cacheDir.path, all = true)
  listOpenSavePair subscribe manager.listOpenSavePair(FOLDER, to = context!!.cacheDir.path, all = true)

  listOpenBitmap subscribe manager.listOpenBitmap(FOLDER, all = true)
  listOpenBitmapPair subscribe manager.listOpenBitmapPair(FOLDER, all = true)

  listOpenFd subscribe manager.listOpenFd(FOLDER, all = true)
  listOpenFdPair subscribe manager.listOpenFdPair(FOLDER, all = true)

  listOpenNonAssetFd subscribe manager.listOpenNonAssetFd(name = ROOT)
  listOpenNonAssetFdPair subscribe manager.listOpenNonAssetFdPair(name = ROOT)

  listOpenXmlResourceParser subscribe manager.listOpenXmlResourceParser(name = ROOT, all = true)
  listOpenXmlResourceParserPair subscribe manager.listOpenXmlResourceParserPair(name = ROOT, all = true)
})

sealed class RxAssetManagerFragment(
    private val layout: Int,
    private val created: RxAssetManagerFragment.(IsRxAssetManager) -> Unit,
    private val disposables: CompositeDisposable = CompositeDisposable()) : Fragment() {

  companion object {
    val TAG: String = this::class.java.simpleName
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(layout, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    created.invoke(this, RxAssetManager(context!!))
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onStop() {
    disposables.clear()
    super.onStop()
  }

  infix fun <T> View.subscribe(t: T) = setOnClickListener {
    when (t) {
      is Completable -> t.toObservable()
      is Single<*> -> t.toObservable()
      is Maybe<*> -> t.toObservable()
      is Flowable<*> -> t.toObservable()
      else -> Observable.never()
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeBy(
            { Log.e(TAG, it.message, it) },
            { Log.i(TAG, "complete()") },
            { Log.i(TAG, "next($it)") }
        ).addTo(disposables)
  }
}
