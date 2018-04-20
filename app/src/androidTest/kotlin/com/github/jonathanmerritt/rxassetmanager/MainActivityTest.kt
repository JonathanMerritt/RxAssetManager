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

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.matcher.ViewMatchers.withClassName
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

  @Rule
  var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  private fun childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("Child at position $position in parent ")
        parentMatcher.describeTo(description)
      }

      public override fun matchesSafely(view: View): Boolean {
        val parent = view.parent
        return parent is ViewGroup && parentMatcher.matches(parent) && view == parent.getChildAt(position)
      }
    }
  }

  @Test
  fun buttonClicks() {
    val appCompatButton = onView(allOf(withId(R.id.getLocals), withText("Get Locals"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 0)))
    appCompatButton.perform(scrollTo(), click())

    val appCompatButton2 = onView(allOf(withId(R.id.open), withText("Open"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 1)))
    appCompatButton2.perform(scrollTo(), click())

    val appCompatButton3 = onView(allOf(withId(R.id.openPair), withText("Open Pair"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 2)))
    appCompatButton3.perform(scrollTo(), click())

    val appCompatButton4 = onView(allOf(withId(R.id.openFd), withText("Open Fd"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 3)))
    appCompatButton4.perform(scrollTo(), click())

    val appCompatButton5 = onView(allOf(withId(R.id.openFdPair), withText("Open Fd Pair"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 4)))
    appCompatButton5.perform(scrollTo(), click())

    val appCompatButton6 = onView(allOf(withId(R.id.list), withText("List"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 5)))
    appCompatButton6.perform(scrollTo(), click())

    val appCompatButton7 = onView(allOf(withId(R.id.openNonAssetFd), withText("Open Non Asset Fd"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 6)))
    appCompatButton7.perform(scrollTo(), click())

    val appCompatButton8 = onView(
        allOf(withId(R.id.openNonAssetFdPair), withText("Open Non Asset Fd Pair"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 7)))
    appCompatButton8.perform(scrollTo(), click())

    val appCompatButton9 = onView(
        allOf(withId(R.id.openXmlResParser), withText("Open Xml Resource Parser"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 8)))
    appCompatButton9.perform(scrollTo(), click())

    val appCompatButton10 = onView(
        allOf(withId(R.id.openXmlResParserPair), withText("Open Xml Resource Parser Pair"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 9)))
    appCompatButton10.perform(scrollTo(), click())
  }
}
