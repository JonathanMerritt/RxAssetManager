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

import android.os.Bundle
import com.github.jonathanmerritt.rxassetmanager.common.DisposingActivity
import com.github.jonathanmerritt.rxassetmanager.common.schedule
import com.github.jonathanmerritt.rxassetmanager.common.subscribeAnd
import com.github.jonathanmerritt.rxassetmanager.core.IsRxAssetManager
import com.github.jonathanmerritt.rxassetmanager.core.RxAssetManager
import kotlinx.android.synthetic.main.activity_main.get_locales
import kotlinx.android.synthetic.main.activity_main.list
import kotlinx.android.synthetic.main.activity_main.open
import kotlinx.android.synthetic.main.activity_main.open_fd
import kotlinx.android.synthetic.main.activity_main.open_non_asset_fd
import kotlinx.android.synthetic.main.activity_main.open_xml_resource_parser

class MainActivity : DisposingActivity() {
  private lateinit var manager: IsRxAssetManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    manager = RxAssetManager(this)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    open.setOnClickListener { manager.open("Folder/File.txt").toObservable().schedule.subscribeAnd { add(it) } }
    open_fd.setOnClickListener {
      manager.openFd("Folder/File2.txt").toObservable().schedule.subscribeAnd { add(it) }
    }
    list.setOnClickListener { manager.list("").toObservable().schedule.subscribeAnd { add(it) } }
    open_non_asset_fd.setOnClickListener {
      manager.openNonAssetFd(fileName = "AndroidManifest.xml").toObservable().schedule.subscribeAnd { add(it) }
    }
    open_xml_resource_parser.setOnClickListener {
      manager.openXmlResourceParser(fileName = "AndroidManifest.xml").toObservable().schedule.subscribeAnd {
        add(it)
      }
    }
    get_locales.setOnClickListener { manager.locales.toObservable().schedule.subscribeAnd { add(it) } }
  }
}