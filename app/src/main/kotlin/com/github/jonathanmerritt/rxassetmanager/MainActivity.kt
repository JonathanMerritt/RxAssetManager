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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.pager
import kotlinx.android.synthetic.main.activity_main.tablayout
import kotlinx.android.synthetic.main.activity_main.toolbar

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
    tablayout.setupWithViewPager(pager.apply {
      adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
        val pairs = arrayOf(R.string.core to CoreFragment(), R.string.core_ext to CoreExtFragment())
        override fun getCount() = pairs.size
        override fun getPageTitle(position: Int) = getString(pairs[position].first)
        override fun getItem(position: Int) = pairs[position].second
      }
    })
  }
}
