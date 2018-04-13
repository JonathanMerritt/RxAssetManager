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

import com.github.jonathanmerritt.rxassetmanager.common.BaseActivity
import com.github.jonathanmerritt.rxassetmanager.common.extensions.click
import com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager
import kotlinx.android.synthetic.main.activity_main.getLocals
import kotlinx.android.synthetic.main.activity_main.list
import kotlinx.android.synthetic.main.activity_main.open
import kotlinx.android.synthetic.main.activity_main.openFd
import kotlinx.android.synthetic.main.activity_main.openNonAssetFd
import kotlinx.android.synthetic.main.activity_main.openXmlResParser

class MainActivity : BaseActivity(R.layout.activity_main) {
  override fun create() {
    RxAssetManager(this).run {
      open.click { open("Folder/File.txt").dispose() }
      openFd.click { openFd("Folder/File2.txt").dispose() }
      list.click { list("").dispose() }
      openNonAssetFd.click { openNonAssetFd(name = "AndroidManifest.xml").dispose() }
      openXmlResParser.click { openXmlResourceParser(name = "AndroidManifest.xml").dispose() }
      getLocals.click { getLocales().dispose() }
    }
  }
}
