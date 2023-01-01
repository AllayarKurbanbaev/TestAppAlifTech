package uz.akfadiler.testappaliftech.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import uz.akfadiler.testappaliftech.R
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}


fun Fragment.snackMessage(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).apply {
        setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.color_snack_default))
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }.show()
}

fun Fragment.snackMessageLong(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).apply {
        setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.color_snack_default))
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }.show()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Fragment.showKeyboard() {
    view?.let { activity?.showKeyboard(it) }
}

fun Activity.showKeyboard() {
    showKeyboard(currentFocus ?: View(this))
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.SHOW_IMPLICIT)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.visible() {
    if (!this.isVisible)
        this.visibility = View.VISIBLE
}

fun View.gone() {
    if (this.isVisible)
        this.visibility = View.GONE
}

fun View.invisible() {
    if (this.isVisible)
        this.visibility = View.INVISIBLE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.clickable() {
    this.isClickable = true
}

fun View.unClickable() {
    this.isClickable = false
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun getCurrentDay(): String {
    val currentYear: Number
    val currentMonth: Number
    val currentDayOfMonth: Int
    val calendar = Calendar.getInstance()
    currentYear = calendar.get(Calendar.YEAR)
    currentMonth = calendar.get(Calendar.MONTH)
    currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val monString: String
    if (currentMonth < 9) {
        monString = "0${currentMonth + 1}"
    } else {
        monString = (currentMonth + 1).toString()
    }
    val dayString: String
    if (currentDayOfMonth < 10) {
        dayString = "0${currentDayOfMonth}"
    } else {
        dayString = currentDayOfMonth.toString()
    }
    return "$dayString.$monString.$currentYear"
}

fun maskToDate(date: String): String {
    val day = date.substring(0, 2)
    val month = date.substring(2, 4)
    val year = date.substring(4)
    return "$year.$month.$day"
}

fun dateParseToString(date: Date, format: String): String {
    val formatter = SimpleDateFormat(format)
    return formatter.format(date)
}



