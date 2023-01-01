package uz.akfadiler.testappaliftech.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.ParseException
import android.os.Handler
import android.os.ResultReceiver
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.load.model.GlideUrl
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Damir on 2018-12-13.
 */
object AppCommon {
    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideKeyboardFrom(view: View) {
        val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideKeyboardFrom(view: View, callback: () -> Boolean) {
        val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            view.windowToken,
            0,
            ResultReceiver(Handler(Handler.Callback { callback.invoke() }))
        )
    }

    fun showKeyboard(view: View) {
        val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInputFromWindow(view.windowToken, InputMethodManager.SHOW_FORCED, 0)
    }

    fun lastSynchronizationDateConvert(dateInMillis: Long): String {
        val date = if (dateInMillis > 0) Date(dateInMillis) else Date(1483210800000)
        val format = SimpleDateFormat("dd.MM.yyyy_HH:mm", Locale.getDefault())
        return format.format(date)
    }

    fun convertStringToDate(value: String?, pattern: String): Date? {
        if (value == null)
            return null
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        try {
            return format.parse(value)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    fun getDateFromApi(value: String?) = convertStringToDate(value, "yyyy-MM-dd HH:mm:ss")

    private fun convertDateToString(value: Date?, pattern: String): String? {
        if (value == null)
            return ""
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        try {
            return format.format(value)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

    fun getString(date: Date?) = convertDateToString(date, "yyyy-MM-dd")

    fun screenDp(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return dpWidth
    }

    fun screenPx(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels.toFloat()
    }

    fun screenDensity(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.density
    }

    fun isNetworkConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun atEndOfDay(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.time
    }

    fun atStartOfDay(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun atStartOfMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun toGlideUrl(url: String?, token: String?): GlideUrl {

        return GlideUrl(url) { mapOf(Pair("Authorization", token)) }
    }


}