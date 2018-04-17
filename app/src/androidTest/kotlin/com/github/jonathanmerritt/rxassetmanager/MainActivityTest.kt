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
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

  @Rule
  @JvmField
  var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun buttonClicks() {
    val appCompatButton = onView(
        allOf(withId(R.id.open), withText("Open"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                0),
            isDisplayed()))
    appCompatButton.perform(click())

    val appCompatButton2 = onView(
        allOf(withId(R.id.openFd), withText("Open Fd"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                1),
            isDisplayed()))
    appCompatButton2.perform(click())

    val appCompatButton3 = onView(
        allOf(withId(R.id.list), withText("List"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                2),
            isDisplayed()))
    appCompatButton3.perform(click())

    val appCompatButton4 = onView(
        allOf(withId(R.id.openNonAssetFd), withText("Open Non Asset Fd"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                3),
            isDisplayed()))
    appCompatButton4.perform(click())

    val appCompatButton5 = onView(
        allOf(withId(R.id.openXmlResParser), withText("Open Xml Resource Parser"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                4),
            isDisplayed()))
    appCompatButton5.perform(click())

    val appCompatButton6 = onView(
        allOf(withId(R.id.getLocals), withText("Get Locals"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                5),
            isDisplayed()))
    appCompatButton6.perform(click())

  }

  private fun childAtPosition(
      parentMatcher: Matcher<View>, position: Int): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("Child at position $position in parent ")
        parentMatcher.describeTo(description)
      }

      public override fun matchesSafely(view: View): Boolean {
        val parent = view.parent
        return parent is ViewGroup && parentMatcher.matches(parent)
            && view == parent.getChildAt(position)
      }
    }
  }
}
