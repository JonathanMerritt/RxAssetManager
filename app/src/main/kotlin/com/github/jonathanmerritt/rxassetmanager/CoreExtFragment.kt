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

import com.github.jonathanmerritt.rxassetmanager.core.ext.Depth
import com.github.jonathanmerritt.rxassetmanager.extensions.cacheDir
import com.github.jonathanmerritt.rxassetmanager.extensions.click
import kotlinx.android.synthetic.main.fragment_core_ext.listAll
import kotlinx.android.synthetic.main.fragment_core_ext.listOpen
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBitmap
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBitmapPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBytes
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenBytesPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenFd
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenFdPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenNonAssetFd
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenNonAssetFdPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenSave
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenSavePair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenString
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenStringPair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenTypeface
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenTypefacePair
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenXmlResourceParser
import kotlinx.android.synthetic.main.fragment_core_ext.listOpenXmlResourceParserPair
import kotlinx.android.synthetic.main.fragment_core_ext.openBitmap
import kotlinx.android.synthetic.main.fragment_core_ext.openBitmapPair
import kotlinx.android.synthetic.main.fragment_core_ext.openBytes
import kotlinx.android.synthetic.main.fragment_core_ext.openBytesPair
import kotlinx.android.synthetic.main.fragment_core_ext.openSave
import kotlinx.android.synthetic.main.fragment_core_ext.openSavePair
import kotlinx.android.synthetic.main.fragment_core_ext.openString
import kotlinx.android.synthetic.main.fragment_core_ext.openStringPair

class CoreExtFragment : RxAssetManagerFragment(R.layout.fragment_core_ext, {
  openString click { it.openString(FILE).subscribed() }
  openStringPair click { it.openStringPair(FILE).subscribed() }

  openBytes click { it.openBytes(FILE1).subscribed() }
  openBytesPair click { it.openBytesPair(FILE1).subscribed() }

  openSave click { it.openSave(FILE2, to = cacheDir).subscribed() }
  openSavePair click { it.openSavePair(FILE2, to = cacheDir).subscribed() }

  openBitmap click { it.openBitmap(ICON).subscribed() }
  openBitmapPair click { it.openBitmapPair(ICON).subscribed() }

  listAll click { it.listAll(sorting = Depth).subscribed() }

  listOpen click { it.listOpen(FOLDER, all = true).subscribed() }
  listOpenPair click { it.listOpenPair(FOLDER, all = true).subscribed() }

  listOpenString click { it.listOpenString(FOLDER, all = true).subscribed() }
  listOpenStringPair click { it.listOpenStringPair(FOLDER, all = true).subscribed() }

  listOpenBytes click { it.listOpenBytes(FOLDER, all = true).subscribed() }
  listOpenBytesPair click { it.listOpenBytesPair(FOLDER, all = true).subscribed() }

  listOpenSave click { it.listOpenSave(FOLDER, to = cacheDir, all = true).subscribed() }
  listOpenSavePair click { it.listOpenSavePair(FOLDER, to = cacheDir, all = true).subscribed() }

  listOpenBitmap click { it.listOpenBitmap(FOLDER, all = true).subscribed() }
  listOpenBitmapPair click { it.listOpenBitmapPair(FOLDER, all = true).subscribed() }

  listOpenTypeface click { it.listOpenTypeface(FOLDER, all = true).subscribed() }
  listOpenTypefacePair click { it.listOpenTypefacePair(FOLDER, all = true).subscribed() }

  listOpenFd click { it.listOpenFd(FOLDER, all = true).subscribed() }
  listOpenFdPair click { it.listOpenFdPair(FOLDER, all = true).subscribed() }

  listOpenNonAssetFd click { it.listOpenNonAssetFd(path = ROOT).subscribed() }
  listOpenNonAssetFdPair click { it.listOpenNonAssetFdPair(path = ROOT).subscribed() }

  listOpenXmlResourceParser click { it.listOpenXmlResourceParser(path = ROOT, all = true).subscribed() }
  listOpenXmlResourceParserPair click { it.listOpenXmlResourceParserPair(path = ROOT, all = true).subscribed() }
})
