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
import com.github.jonathanmerritt.rxassetmanager.common.E
import com.github.jonathanmerritt.rxassetmanager.common.FO1
import com.github.jonathanmerritt.rxassetmanager.common.FO1FI1
import com.github.jonathanmerritt.rxassetmanager.common.FO1FI2
import com.github.jonathanmerritt.rxassetmanager.common.FO2
import com.github.jonathanmerritt.rxassetmanager.common.FO2FI1
import com.github.jonathanmerritt.rxassetmanager.common.S
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

    open_string.setOnClickListener { manager.openString(FO1FI1).dispose() }
    open_bytes.setOnClickListener { manager.openBytes(FO1FI2).dispose() }
    open_save.setOnClickListener { manager.openSave(FO2FI1, to = cacheDir.path).dispose() }
    list_all.setOnClickListener { manager.listAll(E).dispose() }
    list_open.setOnClickListener { manager.listOpen(E, all = true).dispose() }
    list_open_string.setOnClickListener { manager.listOpenString(FO2, all = true).dispose() }
    list_open_bytes.setOnClickListener { manager.listOpenBytes(FO1).dispose() }
    list_open_save.setOnClickListener { manager.listOpenSave(FO1, to = cacheDir.path, all = true).dispose() }
    list_open_fd.setOnClickListener { manager.listOpenFd(E, all = true).dispose() }
    list_open_non_asset_fd.setOnClickListener { manager.listOpenNonAssetFd(name = S).dispose() }
    list_open_xml_resource_parser.setOnClickListener {
      manager.listOpenXmlResourceParser(name = S, all = true).dispose()
    }
  }
}
