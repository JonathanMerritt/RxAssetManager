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
      openString.setOnClickListener { openString("Folder/File.txt").dispose() }
      openBytes.setOnClickListener { openBytes("Folder/File2.txt").dispose() }
      openSave.setOnClickListener { openSave("Folder/Folder2/File.txt", to = cacheDir.path).dispose() }
      listAll.setOnClickListener { listAll("").dispose() }
      listOpen.setOnClickListener { listOpen("", all = true).dispose() }
      listOpenString.setOnClickListener { listOpenString("Folder/Folder2", all = true).dispose() }
      listOpenBytes.setOnClickListener { listOpenBytes("Folder").dispose() }
      listOpenSave.setOnClickListener { listOpenSave("Folder", to = cacheDir.path, all = true).dispose() }
      listOpenFd.setOnClickListener { listOpenFd("", all = true).dispose() }
      listOpenNonAssetFd.setOnClickListener { listOpenNonAssetFd(name = "/").dispose() }
      listOpenXmlResourceParser.setOnClickListener { listOpenXmlResourceParser(name = "/", all = true).dispose() }
    }
  }
}
