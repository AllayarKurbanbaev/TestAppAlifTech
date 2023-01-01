/*
 * Copyright 2015 lujun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed toDate in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uz.akfadiler.testappaliftech.utils.widget.androidtagview

import android.graphics.Color

/**
 * Author: lujun(http://blog.lujun.co)
 * Date: 2016-1-4 23:20
 */
object ColorFactory {

    /**
     * ============= --border color
     * background color---||-  Text --||--text color
     * =============
     */

    val BG_COLOR_ALPHA = "33"
    val BD_COLOR_ALPHA = "88"

    val RED = "F44336"
    val LIGHTBLUE = "03A9F4"
    val AMBER = "FFC107"
    val ORANGE = "FF9800"
    val YELLOW = "FFEB3B"
    val LIME = "CDDC39"
    val BLUE = "2196F3"
    val INDIGO = "3F51B5"
    val LIGHTGREEN = "8BC34A"
    val GREY = "9E9E9E"
    val DEEPPURPLE = "673AB7"
    val TEAL = "009688"
    val CYAN = "00BCD4"

    enum class PURE_COLOR {
        CYAN, TEAL
    }

    val NONE = -1
    val RANDOM = 0
    val PURE_CYAN = 1
    val PURE_TEAL = 2

    val SHARP666666 = Color.parseColor("#FF666666")
    val SHARP727272 = Color.parseColor("#FF727272")

    private val COLORS = arrayOf(
        RED,
        LIGHTBLUE,
        AMBER,
        ORANGE,
        YELLOW,
        LIME,
        BLUE,
        INDIGO,
        LIGHTGREEN,
        GREY,
        DEEPPURPLE,
        TEAL,
        CYAN
    )

    fun onRandomBuild(): IntArray {
        val random = (Math.random() * COLORS.size).toInt()
        val bgColor = Color.parseColor("#" + BG_COLOR_ALPHA + COLORS[random])
        val bdColor = Color.parseColor("#" + BD_COLOR_ALPHA + COLORS[random])
        val tColor = SHARP666666
        val tColor2 = SHARP727272
        return intArrayOf(bgColor, bdColor, tColor, tColor2)
    }

    fun onPureBuild(type: PURE_COLOR): IntArray {
        val color = if (type == PURE_COLOR.CYAN) CYAN else TEAL
        val bgColor = Color.parseColor("#" + BG_COLOR_ALPHA + color)
        val bdColor = Color.parseColor("#" + BD_COLOR_ALPHA + color)
        val tColor = SHARP727272
        val tColor2 = SHARP666666
        return intArrayOf(bgColor, bdColor, tColor, tColor2)
    }

}
