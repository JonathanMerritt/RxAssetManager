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
import com.github.jonathanmerritt.rxassetmanager.common.extensions.click
import com.github.jonathanmerritt.rxassetmanager.core.ext.RxAssetManager
import kotlinx.android.synthetic.main.activity_main.listAll
import kotlinx.android.synthetic.main.activity_main.listOpen
import kotlinx.android.synthetic.main.activity_main.listOpenBytes
import kotlinx.android.synthetic.main.activity_main.listOpenFd
import kotlinx.android.synthetic.main.activity_main.listOpenNonAssetFd
import kotlinx.android.synthetic.main.activity_main.listOpenSave
import kotlinx.android.synthetic.main.activity_main.listOpenString
import kotlinx.android.synthetic.main.activity_main.listOpenXmlResourceParser
import kotlinx.android.synthetic.main.activity_main.openBytes
import kotlinx.android.synthetic.main.activity_main.openSave
import kotlinx.android.synthetic.main.activity_main.openString

class MainActivity : BaseActivity(R.layout.activity_main) {
  override fun create() {
    RxAssetManager(this).run {
      openString.click { openString("F/F.txt").dispose() }
      openBytes.click { openBytes("F/F1/F1.txt").dispose() }
      openSave.click { openSave("F/F1/F2/F2.txt", to = cacheDir.path).dispose() }
      listAll.click { listAll("").dispose() }
      listOpen.click { listOpen("F", all = true).dispose() }
      listOpenString.click { listOpenString("F", all = true).dispose() }
      listOpenBytes.click { listOpenBytes("F", all = true).dispose() }
      listOpenSave.click { listOpenSave("F", to = cacheDir.path, all = true).dispose() }
      listOpenFd.click { listOpenFd("F", all = true).dispose() }
      listOpenNonAssetFd.click { listOpenNonAssetFd(name = "/").dispose() }
      listOpenXmlResourceParser.click { listOpenXmlResourceParser(name = "/", all = true).dispose() }
    }
  }
}
