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

import kotlinx.android.synthetic.main.fragment_core.getLocals
import kotlinx.android.synthetic.main.fragment_core.list
import kotlinx.android.synthetic.main.fragment_core.open
import kotlinx.android.synthetic.main.fragment_core.openFd
import kotlinx.android.synthetic.main.fragment_core.openFdPair
import kotlinx.android.synthetic.main.fragment_core.openNonAssetFd
import kotlinx.android.synthetic.main.fragment_core.openNonAssetFdPair
import kotlinx.android.synthetic.main.fragment_core.openPair
import kotlinx.android.synthetic.main.fragment_core.openXmlResParser
import kotlinx.android.synthetic.main.fragment_core.openXmlResParserPair
import kotlinx.android.synthetic.main.fragment_core.openFont
import kotlinx.android.synthetic.main.fragment_core.openFontPair

class CoreFragment : RxAssetManagerFragment(R.layout.fragment_core, {
  getLocals click { it.getLocales().subscribe }

  open click { it.open(FILE).subscribe }
  openPair click { it.openPair(FILE).subscribe }

  openFd click { (it openFd FILE1).subscribe }
  openFdPair click { (it openFdPair FILE1).subscribe }

  openFont click { (it openFont FONT).subscribe }
  openFontPair click { (it openFontPair FONT1).subscribe }

  list click { it.list().subscribe }

  openNonAssetFd click { it.openNonAssetFd(name = MANIFEST).subscribe }
  openNonAssetFdPair click { it.openNonAssetFdPair(name = MANIFEST).subscribe }

  openXmlResParser click { it.openXmlResourceParser(name = MANIFEST).subscribe }
  openXmlResParserPair click { it.openXmlResourceParserPair(name = MANIFEST).subscribe }
})
