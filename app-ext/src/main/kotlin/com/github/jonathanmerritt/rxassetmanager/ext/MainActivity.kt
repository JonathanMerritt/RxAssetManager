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

import android.os.Bundle
import com.github.jonathanmerritt.rxassetmanager.common.DisposingActivity
import com.github.jonathanmerritt.rxassetmanager.core.ext.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.ext.RxAssetManager
import kotlinx.android.synthetic.main.activity_main.list_all as listAll
import kotlinx.android.synthetic.main.activity_main.list_open as listOpen
import kotlinx.android.synthetic.main.activity_main.list_open_bytes as listOpenBytes
import kotlinx.android.synthetic.main.activity_main.list_open_fd as listOpenFd
import kotlinx.android.synthetic.main.activity_main.list_open_non_asset_fd as listOpenNonAssetFd
import kotlinx.android.synthetic.main.activity_main.list_open_save as listOpenSave
import kotlinx.android.synthetic.main.activity_main.list_open_string as listOpenString
import kotlinx.android.synthetic.main.activity_main.list_open_xml_resource_parser as listOpenXmlResParser
import kotlinx.android.synthetic.main.activity_main.open_bytes as openBytes
import kotlinx.android.synthetic.main.activity_main.open_save as openSave
import kotlinx.android.synthetic.main.activity_main.open_string as openString


class MainActivity : DisposingActivity() {
  private lateinit var manager: IsRxAssetManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    manager = RxAssetManager(this)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)

    openString.setOnClickListener { manager.openString("Folder/File.txt").dispose() }
    openBytes.setOnClickListener { manager.openBytes("Folder/File2.txt").dispose() }
    openSave.setOnClickListener { manager.openSave("Folder/Folder2/File.txt", to = cacheDir.path).dispose() }
    listAll.setOnClickListener { manager.listAll("").dispose() }
    listOpen.setOnClickListener { manager.listOpen("", all = true).dispose() }
    listOpenString.setOnClickListener { manager.listOpenString("Folder/Folder2", all = true).dispose() }
    listOpenBytes.setOnClickListener { manager.listOpenBytes("Folder").dispose() }
    listOpenSave.setOnClickListener { manager.listOpenSave("Folder", to = cacheDir.path, all = true).dispose() }
    listOpenFd.setOnClickListener { manager.listOpenFd("", all = true).dispose() }
    listOpenNonAssetFd.setOnClickListener { manager.listOpenNonAssetFd(name = "/").dispose() }
    listOpenXmlResParser.setOnClickListener { manager.listOpenXmlResourceParser(name = "/", all = true).dispose() }
  }
}
