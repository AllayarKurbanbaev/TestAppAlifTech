package uz.akfadiler.testappaliftech.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.TextView
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat


object ProjectUtils {
    fun setVectorForPreLollipop(resourceId: Int, activity: Context): Drawable? {
        val icon: Drawable? = if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            VectorDrawableCompat.create(activity.resources, resourceId, activity.theme)
        } else {
            activity.resources.getDrawable(resourceId, activity.theme)
        }
        return icon
    }

    const val DRAWABLE_LEFT: Int = 1
    const val DRAWABLE_RIGHT: Int = 2
    const val DRAWABLE_TOP: Int = 3
    const val DRAWABLE_BOTTOM: Int = 4

    fun setVectorForPreLollipop(
        textView: TextView?,
        resourceId: Int,
        activity: Context,
        position: Int
    ) {
        val icon: Drawable? = if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            VectorDrawableCompat.create(
                activity.resources, resourceId,
                activity.theme
            )
        } else {
            activity.resources.getDrawable(resourceId, activity.theme)
        }
        when (position) {
            DRAWABLE_LEFT -> textView?.setCompoundDrawablesWithIntrinsicBounds(
                icon,
                null,
                null,
                null
            )

            DRAWABLE_RIGHT -> textView?.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                icon,
                null
            )

            DRAWABLE_TOP -> textView?.setCompoundDrawablesWithIntrinsicBounds(
                null,
                icon,
                null,
                null
            )

            DRAWABLE_BOTTOM -> textView?.setCompoundDrawablesWithIntrinsicBounds(
                null, null, null,
                icon
            )
        }
    }
}