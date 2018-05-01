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
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.pager
import kotlinx.android.synthetic.main.activity_main.tablayout
import kotlinx.android.synthetic.main.activity_main.toolbar

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    toolbar.run {
      title = getString(R.string.app_name)
      setSupportActionBar(this)
    }

    pager.run {
      adapter = PagerAdapter(supportFragmentManager)
      tablayout.setupWithViewPager(this)
    }
  }

  class PagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    override fun getCount(): Int = 2
    override fun getItem(position: Int): Fragment = if (position == 0) Core() else CoreExt()
    override fun getPageTitle(position: Int): CharSequence? = if (position == 0) "Core" else "Core-Ext"
  }
}
