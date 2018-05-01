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
import com.github.jonathanmerritt.rxassetmanager.core.ext.FilesFirst
import com.github.jonathanmerritt.rxassetmanager.core.ext.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.ext.RxAssetManager
import com.github.jonathanmerritt.rxassetmanager.extensions.TAG
import com.github.jonathanmerritt.rxassetmanager.extensions.asObservable
import com.github.jonathanmerritt.rxassetmanager.extensions.click
import com.github.jonathanmerritt.rxassetmanager.extensions.scheduleIoMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
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

sealed class RxAssetManagerFragment(
    private val layout: Int,
    private val create: RxAssetManagerFragment.(IsRxAssetManager) -> Unit,
    private val disposables: CompositeDisposable = CompositeDisposable()) : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(layout, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    create.invoke(this, RxAssetManager(context!!))
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onStop() {
    disposables.clear()
    super.onStop()
  }

  fun <T> T.toSubscribe() = asObservable().scheduleIoMain().subscribeBy(
      { Log.e(TAG, it.message, it) }, { Log.i(TAG, "complete()") }, { Log.i(TAG, "next($it)") }).addTo(disposables)
}

class Core : RxAssetManagerFragment(R.layout.fragment_core, {
  getLocals click { it.getLocales().toSubscribe() }
  open click { it.open(FILE).toSubscribe() }
  openPair click { it.openPair(FILE).toSubscribe() }
  openFd click { it.openFd(FILE1).toSubscribe() }
  openFdPair click { it.openFdPair(FILE1).toSubscribe() }
  list click { it.list().toSubscribe() }
  openNonAssetFd click { it.openNonAssetFd(name = MANI).toSubscribe() }
  openNonAssetFdPair click { it.openNonAssetFdPair(name = MANI).toSubscribe() }
  openXmlResParser click { it.openXmlResourceParser(name = MANI).toSubscribe() }
  openXmlResParserPair click { it.openXmlResourceParserPair(name = MANI).toSubscribe() }
})

class CoreExt : RxAssetManagerFragment(R.layout.fragment_core_ext, {
  openString click { it.openString(FILE).toSubscribe() }
  openStringPair click { it.openStringPair(FILE).toSubscribe() }
  openBytes click { it.openBytes(FILE1).toSubscribe() }
  openBytesPair click { it.openBytesPair(FILE1).toSubscribe() }
  openSave click { it.openSave(FILE2, to = context!!.cacheDir.path).toSubscribe() }
  openSavePair click { it.openSavePair(FILE2, to = context!!.cacheDir.path).toSubscribe() }
  openBitmap click { it.openBitmap(ICON).toSubscribe() }
  openBitmapPair click { it.openBitmapPair(ICON).toSubscribe() }
  listAll click { it.listAll(strategy = FilesFirst()).toSubscribe() }
  listOpen click { it.listOpen(FOLDER, all = true).toSubscribe() }
  listOpenPair click { it.listOpenPair(FOLDER, all = true).toSubscribe() }
  listOpenString click { it.listOpenString(FOLDER, all = true).toSubscribe() }
  listOpenStringPair click { it.listOpenStringPair(FOLDER, all = true).toSubscribe() }
  listOpenBytes click { it.listOpenBytes(FOLDER, all = true).toSubscribe() }
  listOpenBytesPair click { it.listOpenBytesPair(FOLDER, all = true).toSubscribe() }
  listOpenSave click { it.listOpenSave(FOLDER, to = context!!.cacheDir.path, all = true).toSubscribe() }
  listOpenSavePair click { it.listOpenSavePair(FOLDER, to = context!!.cacheDir.path, all = true).toSubscribe() }
  listOpenBitmap click { it.listOpenBitmap(FOLDER, all = true).toSubscribe() }
  listOpenBitmapPair click { it.listOpenBitmapPair(FOLDER, all = true).toSubscribe() }
  listOpenFd click { it.listOpenFd(FOLDER, all = true).toSubscribe() }
  listOpenFdPair click { it.listOpenFdPair(FOLDER, all = true).toSubscribe() }
  listOpenNonAssetFd click { it.listOpenNonAssetFd(name = ROOT).toSubscribe() }
  listOpenNonAssetFdPair click { it.listOpenNonAssetFdPair(name = ROOT).toSubscribe() }
  listOpenXmlResourceParser click { it.listOpenXmlResourceParser(name = ROOT, all = true).toSubscribe() }
  listOpenXmlResourceParserPair click { it.listOpenXmlResourceParserPair(name = ROOT, all = true).toSubscribe() }
})
