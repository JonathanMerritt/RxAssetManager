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

package com.github.jonathanmerritt.rxassetmanager.ext

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
    val appCompatButton = onView(allOf(withId(R.id.openString), withText("Open String"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 0)))
    appCompatButton.perform(scrollTo(), click())

    val appCompatButton2 = onView(allOf(withId(R.id.openStringPair), withText("Open String Pair"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 1)))
    appCompatButton2.perform(scrollTo(), click())

    val appCompatButton3 = onView(allOf(withId(R.id.openBytes), withText("Open Bytes"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 2)))
    appCompatButton3.perform(scrollTo(), click())

    val appCompatButton4 = onView(allOf(withId(R.id.openBytesPair), withText("Open Bytes Pair"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 3)))
    appCompatButton4.perform(scrollTo(), click())

    val appCompatButton5 = onView(allOf(withId(R.id.openSave), withText("Open And Save"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 4)))
    appCompatButton5.perform(scrollTo(), click())

    val appCompatButton6 = onView(allOf(withId(R.id.openSavePair), withText("Open And Save Pair"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 5)))
    appCompatButton6.perform(scrollTo(), click())

    val appCompatButton7 = onView(allOf(withId(R.id.openBitmap), withText("Open Bitmap"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 6)))
    appCompatButton7.perform(scrollTo(), click())

    val appCompatButton8 = onView(allOf(withId(R.id.openBitmapPair), withText("Open Bitmap Pair"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 7)))
    appCompatButton8.perform(scrollTo(), click())

    val appCompatButton9 = onView(allOf(withId(R.id.listAll), withText("List All"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 8)))
    appCompatButton9.perform(scrollTo(), click())

    val appCompatButton10 = onView(allOf(withId(R.id.listOpen), withText("List And Open"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 9)))
    appCompatButton10.perform(scrollTo(), click())

    val appCompatButton11 = onView(allOf(withId(R.id.listOpenPair), withText("List And Open Pair"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 10)))
    appCompatButton11.perform(scrollTo(), click())

    val appCompatButton12 = onView(allOf(withId(R.id.listOpenString), withText("List And Open String"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 11)))
    appCompatButton12.perform(scrollTo(), click())

    val appCompatButton13 = onView(
        allOf(withId(R.id.listOpenStringPair), withText("List And Open String Pair"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 12)))
    appCompatButton13.perform(scrollTo(), click())

    val appCompatButton14 = onView(allOf(withId(R.id.listOpenBytes), withText("List And Open Bytes"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 13)))
    appCompatButton14.perform(scrollTo(), click())

    val appCompatButton15 = onView(
        allOf(withId(R.id.listOpenBytesPair), withText("List And Open Bytes Pair"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 14)))
    appCompatButton15.perform(scrollTo(), click())

    val appCompatButton16 = onView(allOf(withId(R.id.listOpenSave), withText("List, Open And Save"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 15)))
    appCompatButton16.perform(scrollTo(), click())

    val appCompatButton17 = onView(
        allOf(withId(R.id.listOpenSavePair), withText("List, Open And Save Pair"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 16)))
    appCompatButton17.perform(scrollTo(), click())

    val appCompatButton18 = onView(allOf(withId(R.id.listOpenBitmap), withText("List Open Bitmap"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 17)))
    appCompatButton18.perform(scrollTo(), click())

    val appCompatButton19 = onView(
        allOf(withId(R.id.listOpenBitmapPair), withText("List Open Bitmap Pair"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 18)))
    appCompatButton19.perform(scrollTo(), click())

    val appCompatButton20 = onView(allOf(withId(R.id.listOpenFd), withText("List And Open Fd"),
        childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 19)))
    appCompatButton20.perform(scrollTo(), click())

    val appCompatButton21 = onView(
        allOf(withId(R.id.listOpenFdPair), withText("List And Open Fd Pair"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 20)))
    appCompatButton21.perform(scrollTo(), click())

    val appCompatButton22 = onView(
        allOf(withId(R.id.listOpenNonAssetFd), withText("List And Open Non Asset Fd"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 21)))
    appCompatButton22.perform(scrollTo(), click())

    val appCompatButton23 = onView(
        allOf(withId(R.id.listOpenNonAssetFdPair), withText("List And Open Non Asset Fd Pair"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 22)))
    appCompatButton23.perform(scrollTo(), click())

    val appCompatButton24 = onView(
        allOf(withId(R.id.listOpenXmlResourceParser), withText("List And Open Xml Resource Parser"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 23)))
    appCompatButton24.perform(scrollTo(), click())

    val appCompatButton25 = onView(
        allOf(withId(R.id.listOpenXmlResourceParserPair), withText("List And Open Xml Resource Parser Pair"),
            childAtPosition(childAtPosition(withClassName(`is`("android.widget.ScrollView")), 0), 24)))
    appCompatButton25.perform(scrollTo(), click())
  }
}
