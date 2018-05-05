/*
 *     Copyright 2018 Jonathan Merritt
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

import kotlinx.android.synthetic.main.fragment_core_ext.listAll
import kotlinx.android.synthetic.main.fragment_core_ext.listOpen
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBitmap
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBitmapPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBytes
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBytesPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenFd
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenFdPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenFont
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenFontPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenNonAssetFd
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenNonAssetFdPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenSave
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenSavePair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenString
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenStringPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenXmlResourceParser
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenXmlResourceParserPair
import kotlinx.android.synthetic.main.fragment_core_ext.openBitmap
import kotlinx.android.synthetic.main.fragment_core_ext.openBitmapPair
import kotlinx.android.synthetic.main.fragment_core_ext.openBytes
import kotlinx.android.synthetic.main.fragment_core_ext.openBytesPair
import kotlinx.android.synthetic.main.fragment_core_ext.openFont
import kotlinx.android.synthetic.main.fragment_core_ext.openFontPair
import kotlinx.android.synthetic.main.fragment_core_ext.openSave
import kotlinx.android.synthetic.main.fragment_core_ext.openSavePair
import kotlinx.android.synthetic.main.fragment_core_ext.openString
import kotlinx.android.synthetic.main.fragment_core_ext.openStringPair

class CoreExtFragment : RxAssetManagerFragment(R.layout.fragment_core_ext, {
  openString click { it.openString(FILE).subscribe }
  openStringPair click { it.openStringPair(FILE).subscribe }

  openBytes click { it.openBytes(FILE1).subscribe }
  openBytesPair click { it.openBytesPair(FILE1).subscribe }

  openSave click { it.openSave(FILE2, to = context!!.cacheDir.path).subscribe }
  openSavePair click { it.openSavePair(FILE2, to = context!!.cacheDir.path).subscribe }

  openBitmap click { it.openBitmap(ICON).subscribe }
  openBitmapPair click { it.openBitmapPair(ICON).subscribe }

  openFont click { (it openFont FONT).subscribe }
  openFontPair click { (it openFontPair FONT1).subscribe }

  listAll click { it.listAll().subscribe }

  listOpen click { it.listOpen(FOLDER, all = true).subscribe }
  listOpenPair click { it.listOpenPair(FOLDER, all = true).subscribe }

  listOpenString click { it.listOpenString(FOLDER, all = true).subscribe }
  listOpenStringPair click { it.listOpenStringPair(FOLDER, all = true).subscribe }

  listOpenBytes click { it.listOpenBytes(FOLDER, all = true).subscribe }
  listOpenBytesPair click { it.listOpenBytesPair(FOLDER, all = true).subscribe }

  listOpenSave click { it.listOpenSave(FOLDER, to = context!!.cacheDir.path, all = true).subscribe }
  listOpenSavePair click { it.listOpenSavePair(FOLDER, to = context!!.cacheDir.path, all = true).subscribe }

  listOpenBitmap click { it.listOpenBitmap(FOLDER, all = true).subscribe }
  listOpenBitmapPair click { it.listOpenBitmapPair(FOLDER, all = true).subscribe }

  listOpenFont click { it.listOpenFont(FOLDER, all = true).subscribe }
  listOpenFontPair click { it.listOpenFontPair(FOLDER, all = true).subscribe }

  listOpenFd click { it.listOpenFd(FOLDER, all = true).subscribe }
  listOpenFdPair click { it.listOpenFdPair(FOLDER, all = true).subscribe }

  listOpenNonAssetFd click { it.listOpenNonAssetFd(name = ROOT).subscribe }
  listOpenNonAssetFdPair click { it.listOpenNonAssetFdPair(name = ROOT).subscribe }

  listOpenXmlResourceParser click { it.listOpenXmlResourceParser(name = ROOT, all = true).subscribe }
  listOpenXmlResourceParserPair click { it.listOpenXmlResourceParserPair(name = ROOT, all = true).subscribe }
})
