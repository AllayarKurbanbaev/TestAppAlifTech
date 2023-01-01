package uz.akfadiler.testappaliftech.utils

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CirclePagerIndicatorDecoration : RecyclerView.ItemDecoration() {

    private val colorActive = Color.parseColor("#E02329")
    private val colorInactive = Color.parseColor("#DFE3EB")

    //todo Height of the space the indicator takes up at the bottom of the view.
    private val mIndicatorHeight = (DP * 36).toInt()

    //todo Indicator stroke width.
    private val mIndicatorStrokeWidth = DP * 12

    //todo Indicator width.
    private val mIndicatorItemLength = 0

    //todo Padding between indicators.
    private val mIndicatorItemPadding = DP * 16

    /**
     * Some more natural animation interpolation
     */
    private val mInterpolator = AccelerateDecelerateInterpolator()

    private val mPaint = Paint()

    init {
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = mIndicatorStrokeWidth
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        //todo itemCount
        val itemCount = parent.adapter!!.itemCount
        val dotsCount = 3

        //todo center horizontally, calculate width and subtract half from center
        val totalLength = mIndicatorItemLength * dotsCount
        val paddingBetweenItems = Math.max(0, dotsCount - 1) * mIndicatorItemPadding
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX = (parent.width - indicatorTotalWidth) / 2f

        //todo center vertically in the allotted space
        val indicatorPosY = parent.height - mIndicatorHeight / 2f

        drawInactiveIndicators(c, indicatorStartX, indicatorPosY, itemCount)

        //todo find active page (which should be highlighted)
        val layoutManager = parent.layoutManager as LinearLayoutManager?
        val activePosition = layoutManager!!.findFirstVisibleItemPosition()
        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }

        //todo find offset of active page (if the user is scrolling)
        val activeChild = layoutManager.findViewByPosition(activePosition)
        val left = activeChild!!.left
        val width = activeChild.width

        //todo on swipe the active item will be positioned from [-width, 0]
        //todo interpolate offset for smooth animation
        val progress = mInterpolator.getInterpolation(left * -1 / width.toFloat())

        drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress, itemCount)
    }

    private fun drawInactiveIndicators(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosY: Float,
        itemCount: Int
    ) {
        mPaint.color = colorInactive

        //todo width of item indicator including padding
        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding

        if (itemCount > 1) {
            var start = indicatorStartX
            var dotsCount = 3
            if (itemCount == 2) dotsCount = 2
            for (i in 0 until dotsCount) {
                //todo draw the line for every item
                c.drawLine(
                    start,
                    indicatorPosY,
                    start + mIndicatorItemLength,
                    indicatorPosY,
                    mPaint
                )
                start += itemWidth
            }
        }
    }

    private fun drawHighlights(
        c: Canvas, indicatorStartX: Float, indicatorPosY: Float,
        highlightPosition: Int, progress: Float, itemCount: Int
    ) {
        mPaint.color = colorActive

        //todo width of item indicator including padding
        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding
        val dotsCount = 3

        if (progress == 0f) {
            //todo no swipe, draw a normal indicator
            val highlightStart = indicatorStartX + itemWidth * highlightPosition

            c.drawLine(
                highlightStart, indicatorPosY,
                highlightStart + mIndicatorItemLength, indicatorPosY, mPaint
            )

        } else {
            val position = highlightPosition % dotsCount
            var highlightStart = indicatorStartX + itemWidth * position
            //todo calculate partial highlight
            val partialLength = mIndicatorItemLength * progress

            //todo draw the cut off highlight
            //todo active dot
            if (itemCount > 1)
                c.drawLine(
                    highlightStart + partialLength, indicatorPosY,
                    highlightStart + mIndicatorItemLength, indicatorPosY, mPaint
                )

            //todo draw the highlight overlapping to the next item as well
            if (highlightPosition < itemCount - 1) {
                highlightStart += itemWidth

//                c.drawLine(
//                    highlightStart, indicatorPosY,
//                    highlightStart + partialLength, indicatorPosY, mPaint
//                )
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = mIndicatorHeight
    }

    companion object {
        private val DP = Resources.getSystem().displayMetrics.density
    }
}