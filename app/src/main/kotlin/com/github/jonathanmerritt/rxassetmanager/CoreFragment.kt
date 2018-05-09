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

import com.github.jonathanmerritt.rxassetmanager.extensions.click
import kotlinx.android.synthetic.main.fragment_core.getLocals
import kotlinx.android.synthetic.main.fragment_core.list
import kotlinx.android.synthetic.main.fragment_core.open
import kotlinx.android.synthetic.main.fragment_core.openFd
import kotlinx.android.synthetic.main.fragment_core.openFdPair
import kotlinx.android.synthetic.main.fragment_core.openNonAssetFd
import kotlinx.android.synthetic.main.fragment_core.openNonAssetFdPair
import kotlinx.android.synthetic.main.fragment_core.openPair
import kotlinx.android.synthetic.main.fragment_core.openTypeface
import kotlinx.android.synthetic.main.fragment_core.openTypefacePair
import kotlinx.android.synthetic.main.fragment_core.openXmlResParser
import kotlinx.android.synthetic.main.fragment_core.openXmlResParserPair

class CoreFragment : RxAssetManagerFragment(R.layout.fragment_core, {
  getLocals click { it.getLocales().subscribed() }

  open click { it.open(FILE).subscribed() }
  openPair click { it.openPair(FILE).subscribed() }

  openTypeface click { (it openTypeface FONT).subscribed() }
  openTypefacePair click { (it openTypefacePair FONT1).subscribed() }

  openFd click { (it openFd FILE1).subscribed() }
  openFdPair click { (it openFdPair FILE1).subscribed() }

  list click { it.list().subscribed() }

  openNonAssetFd click { it.openNonAssetFd(path = MANIFEST).subscribed() }
  openNonAssetFdPair click { it.openNonAssetFdPair(path = MANIFEST).subscribed() }

  openXmlResParser click { it.openXmlResourceParser(path = MANIFEST).subscribed() }
  openXmlResParserPair click { it.openXmlResourceParserPair(path = MANIFEST).subscribed() }
})
