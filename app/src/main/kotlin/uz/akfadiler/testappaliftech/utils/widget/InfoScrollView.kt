package uz.akfadiler.testappaliftech.utils.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.widget.NestedScrollView


/**
 * Created by Damir on 2019-01-18.
 */
class InfoScrollView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    NestedScrollView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null, 0)


    var mapView: View? = null

    fun addInterceptScrollView(view: View) {
        mapView = view
    }

    fun removeInterceptScrollView(view: View) {
        mapView = null
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (mapView == null)
            return super.onInterceptTouchEvent(ev)

        if (inRegion(ev.rawX, ev.rawY, mapView!!))
            return false

        return super.onInterceptTouchEvent(ev)
    }

    private fun inRegion(x: Float, y: Float, v: View): Boolean {
        val mCoordBuffer = intArrayOf(0, 0)

        v.getLocationOnScreen(mCoordBuffer)

        return mCoordBuffer[0] + v.width > x &&

                mCoordBuffer[1] + v.height > y &&

                mCoordBuffer[0] < x &&
                mCoordBuffer[1] < y
    }

    fun scrollTo(view: View?) {
        view?.let { post({ smoothScrollTo(0, it.bottom) }) }
    }
}