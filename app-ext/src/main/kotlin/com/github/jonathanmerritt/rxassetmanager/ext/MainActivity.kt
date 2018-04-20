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
import com.github.jonathanmerritt.rxassetmanager.common.F1_TXT
import com.github.jonathanmerritt.rxassetmanager.common.F2_TXT
import com.github.jonathanmerritt.rxassetmanager.common.F_DIR
import com.github.jonathanmerritt.rxassetmanager.common.F_TXT
import com.github.jonathanmerritt.rxassetmanager.common.ROOT
import com.github.jonathanmerritt.rxassetmanager.common.extensions.click
import com.github.jonathanmerritt.rxassetmanager.core.ext.RxAssetManager
import kotlinx.android.synthetic.main.activity_main.listAll
import kotlinx.android.synthetic.main.activity_main.listOpen
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
import kotlinx.android.synthetic.main.activity_main.openBytes
import kotlinx.android.synthetic.main.activity_main.openBytesPair
import kotlinx.android.synthetic.main.activity_main.openSave
import kotlinx.android.synthetic.main.activity_main.openSavePair
import kotlinx.android.synthetic.main.activity_main.openString
import kotlinx.android.synthetic.main.activity_main.openStringPair

class MainActivity : BaseActivity(R.layout.activity_main) {
  override fun create() {
    RxAssetManager(this).run {
      openString.click { openString(F_TXT).dispose() }
      openStringPair.click { openStringPair(F_TXT).dispose() }
      openBytes.click { openBytes(F1_TXT).dispose() }
      openBytesPair.click { openBytesPair(F1_TXT).dispose() }
      openSave.click { openSave(F2_TXT, to = cacheDir.path).dispose() }
      openSavePair.click { openSavePair(F2_TXT, to = cacheDir.path).dispose() }
      listAll.click { listAll().dispose() }
      listOpen.click { listOpen(F_DIR, all = true).dispose() }
      listOpenPair.click { listOpenPair(F_DIR, all = true).dispose() }
      listOpenString.click { listOpenString(F_DIR, all = true).dispose() }
      listOpenStringPair.click { listOpenStringPair(F_DIR, all = true).dispose() }
      listOpenBytes.click { listOpenBytes(F_DIR, all = true).dispose() }
      listOpenBytesPair.click { listOpenBytesPair(F_DIR, all = true).dispose() }
      listOpenSave.click { listOpenSave(F_DIR, to = cacheDir.path, all = true).dispose() }
      listOpenSavePair.click { listOpenSavePair(F_DIR, to = cacheDir.path, all = true).dispose() }
      listOpenFd.click { listOpenFd(F_DIR, all = true).dispose() }
      listOpenFdPair.click { listOpenFdPair(F_DIR, all = true).dispose() }
      listOpenNonAssetFd.click { listOpenNonAssetFd(name = ROOT).dispose() }
      listOpenNonAssetFdPair.click { listOpenNonAssetFdPair(name = ROOT).dispose() }
      listOpenXmlResourceParser.click { listOpenXmlResourceParser(name = ROOT, all = true).dispose() }
      listOpenXmlResourceParserPair.click { listOpenXmlResourceParserPair(name = ROOT, all = true).dispose() }
    }
  }
}
