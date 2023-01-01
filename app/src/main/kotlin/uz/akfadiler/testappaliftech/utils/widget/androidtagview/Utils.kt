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

import android.content.Context
import android.graphics.Color

/**
 * Author: lujun(http://blog.lujun.co)
 * Date: 2016-12-7 21:53
 */

object Utils {

    fun dp2px(context: Context, dp: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dp * scale + 0.5f
    }

    fun sp2px(context: Context, sp: Float): Float {
        val scale = context.resources.displayMetrics.scaledDensity
        return sp * scale
    }

    /**
     * If the color is Dark, make it lighter and vice versa

     * @param color in int,
     * *
     * @param factor The factor greater than 0.0 and smaller than 1.0
     * *
     * @return int
     */
    fun manipulateColorBrightness(color: Int, factor: Float): Int {
        val a = Color.alpha(color)
        var r = Color.red(color)
        var g = Color.green(color)
        var b = Color.blue(color)
        //        if (r + b + g < 128 * 3) factor = 1 / factor;// check if the color is bright or dark
        //        r = Math.round(r * factor);
        //        b = Math.round(b * factor);
        //        g = Math.round(g * factor);
        if (r > 127) r = 255 - Math.round((255 - r) * factor)
        if (g > 127) g = 255 - Math.round((255 - g) * factor)
        if (b > 127) b = 255 - Math.round((255 - b) * factor)

        return Color.argb(
            a,
            Math.min(r, 255),
            Math.min(g, 255),
            Math.min(b, 255)
        )
    }
}
