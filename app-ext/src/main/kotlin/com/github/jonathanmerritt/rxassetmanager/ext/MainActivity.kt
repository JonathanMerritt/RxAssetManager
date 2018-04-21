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

package com.github.jonathanmerritt.rxassetmanager.ext

import com.github.jonathanmerritt.rxassetmanager.common.BaseActivity
import com.github.jonathanmerritt.rxassetmanager.common.FILE
import com.github.jonathanmerritt.rxassetmanager.common.FILE1
import com.github.jonathanmerritt.rxassetmanager.common.FILE2
import com.github.jonathanmerritt.rxassetmanager.common.FOLDER
import com.github.jonathanmerritt.rxassetmanager.common.ICON
import com.github.jonathanmerritt.rxassetmanager.common.ROOT
import com.github.jonathanmerritt.rxassetmanager.common.extensions.onClick
import com.github.jonathanmerritt.rxassetmanager.core.ext.RxAssetManager
import kotlinx.android.synthetic.main.activity_main.listAll
import kotlinx.android.synthetic.main.activity_main.listOpen
import kotlinx.android.synthetic.main.activity_main.listOpenBitmap
import kotlinx.android.synthetic.main.activity_main.listOpenBitmapPair
import kotlinx.android.synthetic.main.activity_main.listOpenBytes
import kotlinx.android.synthetic.main.activity_main.listOpenBytesPair
import kotlinx.android.synthetic.main.activity_main.listOpenFd
import kotlinx.android.synthetic.main.activity_main.listOpenFdPair
import kotlinx.android.synthetic.main.activity_main.listOpenNonAssetFd
import kotlinx.android.synthetic.main.activity_main.listOpenNonAssetFdPair
import kotlinx.android.synthetic.main.activity_main.listOpenPair
import kotlinx.android.synthetic.main.activity_main.listOpenSave
import kotlinx.android.synthetic.main.activity_main.listOpenSavePair
import kotlinx.android.synthetic.main.activity_main.listOpenString
import kotlinx.android.synthetic.main.activity_main.listOpenStringPair
import kotlinx.android.synthetic.main.activity_main.listOpenXmlResourceParser
import kotlinx.android.synthetic.main.activity_main.listOpenXmlResourceParserPair
import kotlinx.android.synthetic.main.activity_main.openBitmap
import kotlinx.android.synthetic.main.activity_main.openBitmapPair
import kotlinx.android.synthetic.main.activity_main.openBytes
import kotlinx.android.synthetic.main.activity_main.openBytesPair
import kotlinx.android.synthetic.main.activity_main.openSave
import kotlinx.android.synthetic.main.activity_main.openSavePair
import kotlinx.android.synthetic.main.activity_main.openString
import kotlinx.android.synthetic.main.activity_main.openStringPair

class MainActivity : BaseActivity(R.layout.activity_main) {
  override fun create() {
    RxAssetManager(this).run {
      openString.onClick { openString(FILE).toSubscribe() }
      openStringPair.onClick { openStringPair(FILE).toSubscribe() }
      openBytes.onClick { openBytes(FILE1).toSubscribe() }
      openBytesPair.onClick { openBytesPair(FILE1).toSubscribe() }
      openSave.onClick { openSave(FILE2, to = cacheDir.path).toSubscribe() }
      openSavePair.onClick { openSavePair(FILE2, to = cacheDir.path).toSubscribe() }
      openBitmap.onClick { openBitmap(ICON).toSubscribe() }
      openBitmapPair.onClick { openBitmapPair(ICON).toSubscribe() }
      listAll.onClick { listAll().toSubscribe() }
      listOpen.onClick { listOpen(FOLDER, all = true).toSubscribe() }
      listOpenPair.onClick { listOpenPair(FOLDER, all = true).toSubscribe() }
      listOpenString.onClick { listOpenString(FOLDER, all = true).toSubscribe() }
      listOpenStringPair.onClick { listOpenStringPair(FOLDER, all = true).toSubscribe() }
      listOpenBytes.onClick { listOpenBytes(FOLDER, all = true).toSubscribe() }
      listOpenBytesPair.onClick { listOpenBytesPair(FOLDER, all = true).toSubscribe() }
      listOpenSave.onClick { listOpenSave(FOLDER, to = cacheDir.path, all = true).toSubscribe() }
      listOpenSavePair.onClick { listOpenSavePair(FOLDER, to = cacheDir.path, all = true).toSubscribe() }
      listOpenBitmap.onClick { listOpenBitmap(FOLDER, all = true).toSubscribe() }
      listOpenBitmapPair.onClick { listOpenBitmapPair(FOLDER, all = true).toSubscribe() }
      listOpenFd.onClick { listOpenFd(FOLDER, all = true).toSubscribe() }
      listOpenFdPair.onClick { listOpenFdPair(FOLDER, all = true).toSubscribe() }
      listOpenNonAssetFd.onClick { listOpenNonAssetFd(name = ROOT).toSubscribe() }
      listOpenNonAssetFdPair.onClick { listOpenNonAssetFdPair(name = ROOT).toSubscribe() }
      listOpenXmlResourceParser.onClick { listOpenXmlResourceParser(name = ROOT, all = true).toSubscribe() }
      listOpenXmlResourceParserPair.onClick {
        listOpenXmlResourceParserPair(name = ROOT, all = true).toSubscribe()
      }
    }
  }
}
