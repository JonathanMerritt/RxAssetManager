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
import io.reactivex.disposables.Disposable
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
const val MANIFEST = "AndroidManifest.xml"
const val FOLDER = "folder"
const val FILE = "folder/file.txt"
const val ICON = "folder/icon.png"
const val FILE1 = "folder/folder1/file1.txt"
const val FILE2 = "folder/folder1/folder2/file2.txt"

class Core : RxAssetManagerFragment(R.layout.fragment_core, {
  getLocals click { it.getLocales().subscribe }
  open click { it.open(FILE).subscribe }
  openPair click { it.openPair(FILE).subscribe }
  openFd click { (it openFd FILE1).subscribe }
  openFdPair click { (it openFdPair FILE1).subscribe }
  list click { it.list().subscribe }
  openNonAssetFd click { it.openNonAssetFd(name = MANIFEST).subscribe }
  openNonAssetFdPair click { it.openNonAssetFdPair(name = MANIFEST).subscribe }
  openXmlResParser click { it.openXmlResourceParser(name = MANIFEST).subscribe }
  openXmlResParserPair click { it.openXmlResourceParserPair(name = MANIFEST).subscribe }
})

class CoreExt : RxAssetManagerFragment(R.layout.fragment_core_ext, {
  openString click { it.openString(FILE).subscribe }
  openStringPair click { it.openStringPair(FILE).subscribe }
  openBytes click { it.openBytes(FILE1).subscribe }
  openBytesPair click { it.openBytesPair(FILE1).subscribe }
  openSave click { it.openSave(FILE2, to = context!!.cacheDir.path).subscribe }
  openSavePair click { it.openSavePair(FILE2, to = context!!.cacheDir.path).subscribe }
  openBitmap click { it.openBitmap(ICON).subscribe }
  openBitmapPair click { it.openBitmapPair(ICON).subscribe }
  listAll click { it.listAll().subscribe }
  listOpen click { it.listOpen(FOLDER, all = true).subscribe }
  listOpenPair click { it.listOpenPair(FOLDER, all = true).subscribe }
  listOpenString click { it.listOpenString(FOLDER, all = true).subscribe }
  listOpenStringPair click { it.listOpenStringPair(FOLDER, all = true).subscribe }
  listOpenBytes click { it.listOpenBytes(FOLDER, all = true).subscribe }
  listOpenBytesPair click { it.listOpenBytesPair(FOLDER, all = true).subscribe }
  listOpenSave click { it.listOpenSave(FOLDER, to = context!!.cacheDir.path, all = true).subscribe }
  listOpenSavePair click { it.listOpenSavePair(FOLDER, to = context!!.cacheDir.path, all = true).subscribe }
  listOpenBitmap click { it.listOpenBitmap(FOLDER, all = true).subscribe }
  listOpenBitmapPair click { it.listOpenBitmapPair(FOLDER, all = true).subscribe }
  listOpenFd click { it.listOpenFd(FOLDER, all = true).subscribe }
  listOpenFdPair click { it.listOpenFdPair(FOLDER, all = true).subscribe }
  listOpenNonAssetFd click { it.listOpenNonAssetFd(name = ROOT).subscribe }
  listOpenNonAssetFdPair click { it.listOpenNonAssetFdPair(name = ROOT).subscribe }
  listOpenXmlResourceParser click { it.listOpenXmlResourceParser(name = ROOT, all = true).subscribe }
  listOpenXmlResourceParserPair click { it.listOpenXmlResourceParserPair(name = ROOT, all = true).subscribe }
})

sealed class RxAssetManagerFragment(
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

  inline infix fun View.click(crossinline then: () -> Unit) = setOnClickListener { then() }

  val <T> T.subscribe: Disposable
    get() {
      val tag = this@RxAssetManagerFragment.javaClass.simpleName
      return when (this) {
        is Completable -> toObservable()
        is Single<*> -> toObservable()
        is Maybe<*> -> toObservable()
        is Flowable<*> -> toObservable()
        else -> Observable.never()
      }
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeBy(
              { Log.e(tag, it.message, it) },
              { Log.i(tag, "complete()") },
              { Log.i(tag, "next($it)") })
          .addTo(disposables)
    }
}
