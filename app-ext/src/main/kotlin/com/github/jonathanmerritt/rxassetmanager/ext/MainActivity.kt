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
import com.github.jonathanmerritt.rxassetmanager.common.schedule
import com.github.jonathanmerritt.rxassetmanager.common.subscribeAnd
import com.github.jonathanmerritt.rxassetmanager.core.ext.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.ext.RxAssetManager
import kotlinx.android.synthetic.main.activity_main.list_all
import kotlinx.android.synthetic.main.activity_main.list_open
import kotlinx.android.synthetic.main.activity_main.list_open_bytes
import kotlinx.android.synthetic.main.activity_main.list_open_fd
import kotlinx.android.synthetic.main.activity_main.list_open_non_asset_fd
import kotlinx.android.synthetic.main.activity_main.list_open_save
import kotlinx.android.synthetic.main.activity_main.list_open_string
import kotlinx.android.synthetic.main.activity_main.list_open_xml_resource_parser
import kotlinx.android.synthetic.main.activity_main.open_bytes
import kotlinx.android.synthetic.main.activity_main.open_save
import kotlinx.android.synthetic.main.activity_main.open_string

class MainActivity : DisposingActivity() {
  private lateinit var manager: IsRxAssetManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    manager = RxAssetManager(this)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    open_string.setOnClickListener {
      manager.openString("Folder/File.txt").toObservable().schedule.subscribeAnd { add(it) }
    }
    open_bytes.setOnClickListener {
      manager.openBytes("Folder/File2.txt").toObservable().schedule.subscribeAnd { add(it) }
    }
    open_save.setOnClickListener {
      manager.openSave("Folder/Folder2/File.txt",
          saveFolder = cacheDir.absolutePath).toObservable().schedule.subscribeAnd { add(it) }
    }
    list_all.setOnClickListener { manager.listAll("").toObservable().schedule.subscribeAnd { add(it) } }
    list_open.setOnClickListener {
      manager.listOpen("", listAll = true).toObservable().schedule.subscribeAnd { add(it) }
    }
    list_open_string.setOnClickListener {
      manager.listOpenString("Folder/Folder2", listAll = true).toObservable().schedule.subscribeAnd { add(it) }
    }
    list_open_bytes.setOnClickListener {
      manager.listOpenBytes("Folder").toObservable().schedule.subscribeAnd { add(it) }
    }
    list_open_save.setOnClickListener {
      manager.listOpenSave("Folder", saveFolder = cacheDir.absolutePath,
          listAll = true).toObservable().schedule.subscribeAnd { add(it) }
    }
    list_open_fd.setOnClickListener {
      manager.listOpenFd("", listAll = true).toObservable().schedule.subscribeAnd { add(it) }
    }
    list_open_non_asset_fd.setOnClickListener {
      manager.listOpenNonAssetFd(folderName = "/").toObservable().schedule.subscribeAnd { add(it) }
    }
    list_open_xml_resource_parser.setOnClickListener {
      manager.listOpenXmlResourceParser(folderName = "/",
          listAll = true).toObservable().schedule.subscribeAnd { add(it) }
    }
  }
}

