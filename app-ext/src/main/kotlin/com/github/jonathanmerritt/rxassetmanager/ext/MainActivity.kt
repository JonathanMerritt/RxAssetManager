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
import com.github.jonathanmerritt.rxassetmanager.common.extensions.click
import com.github.jonathanmerritt.rxassetmanager.core.ext.ListAllStrategy.FilesFirst
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
      openString click { openString(FILE).toSubscribe() }
      openStringPair click { openStringPair(FILE).toSubscribe() }
      openBytes click { openBytes(FILE1).toSubscribe() }
      openBytesPair click { openBytesPair(FILE1).toSubscribe() }
      openSave click { openSave(FILE2, to = cacheDir.path).toSubscribe() }
      openSavePair click { openSavePair(FILE2, to = cacheDir.path).toSubscribe() }
      openBitmap click { openBitmap(ICON).toSubscribe() }
      openBitmapPair click { openBitmapPair(ICON).toSubscribe() }
      listAll click { listAll(strategy = FilesFirst()).toSubscribe() }
      listOpen click { listOpen(FOLDER, all = true).toSubscribe() }
      listOpenPair click { listOpenPair(FOLDER, all = true).toSubscribe() }
      listOpenString click { listOpenString(FOLDER, all = true).toSubscribe() }
      listOpenStringPair click { listOpenStringPair(FOLDER, all = true).toSubscribe() }
      listOpenBytes click { listOpenBytes(FOLDER, all = true).toSubscribe() }
      listOpenBytesPair click { listOpenBytesPair(FOLDER, all = true).toSubscribe() }
      listOpenSave click { listOpenSave(FOLDER, to = cacheDir.path, all = true).toSubscribe() }
      listOpenSavePair click { listOpenSavePair(FOLDER, to = cacheDir.path, all = true).toSubscribe() }
      listOpenBitmap click { listOpenBitmap(FOLDER, all = true).toSubscribe() }
      listOpenBitmapPair click { listOpenBitmapPair(FOLDER, all = true).toSubscribe() }
      listOpenFd click { listOpenFd(FOLDER, all = true).toSubscribe() }
      listOpenFdPair click { listOpenFdPair(FOLDER, all = true).toSubscribe() }
      listOpenNonAssetFd click { listOpenNonAssetFd(name = ROOT).toSubscribe() }
      listOpenNonAssetFdPair click { listOpenNonAssetFdPair(name = ROOT).toSubscribe() }
      listOpenXmlResourceParser click { listOpenXmlResourceParser(name = ROOT, all = true).toSubscribe() }
      listOpenXmlResourceParserPair click { listOpenXmlResourceParserPair(name = ROOT, all = true).toSubscribe() }
    }
  }
}
