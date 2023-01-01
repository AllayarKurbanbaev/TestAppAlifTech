package uz.akfadiler.testappaliftech.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class AppCurrency {

    private lateinit var format: NumberFormat
    private lateinit var mFormatWithoutCurrency: NumberFormat

    companion object {
        private val instance: AppCurrency = AppCurrency()
        fun getInstance(): AppCurrency {
            return instance
        }
    }

    private fun localStyleForeignFormat(locale: Locale): NumberFormat {
        val format = NumberFormat.getCurrencyInstance(locale)
        if (format is DecimalFormat) {
            val df = format as DecimalFormat
            // use local/default decimal symbols with original currency symbol
            val dfs = DecimalFormat().decimalFormatSymbols
            dfs.currency = df.currency
            df.decimalFormatSymbols = dfs
        }
        return format
    }

    private fun localStyleForeignWithoutCurrencyFormat(
        locale: Locale
    ): NumberFormat {
        val format = NumberFormat.getCurrencyInstance(locale)
        if (format is DecimalFormat) {
            val df = format as DecimalFormat
            // use local/default decimal symbols with original currency symbol
            val dfs = DecimalFormat().decimalFormatSymbols
            dfs.currency = df.currency
            dfs.currencySymbol = ""

            df.decimalFormatSymbols = dfs
        }
        return format
    }

    private fun localStyleForeignWithoutCurrencyFormatNoFraction(
        locale: Locale
    ): NumberFormat {
        val format = NumberFormat.getCurrencyInstance(locale)
        if (format is DecimalFormat) {
            val df = format
            df.maximumFractionDigits = 0
            // use local/default decimal symbols with original currency symbol
            val dfs = DecimalFormat().decimalFormatSymbols
            dfs.currency = df.currency
            dfs.currencySymbol = ""
            df.decimalFormatSymbols = dfs
        }
        return format
    }

    fun formatWithCurrency(value: Double): String? {
        if (!this::format.isInitialized)
            format = localStyleForeignFormat(Locale("uz"))
        return format.format(value)
    }

    fun formatWithoutCurrency(value: Double): String? {
        if (!this::mFormatWithoutCurrency.isInitialized)
            mFormatWithoutCurrency = localStyleForeignWithoutCurrencyFormat(Locale("uz"))
        return mFormatWithoutCurrency.format(value)
    }

    fun formatWithoutCurrencyNoFraction(value: Double): String? {
        if (!this::mFormatWithoutCurrency.isInitialized)
            mFormatWithoutCurrency =
                localStyleForeignWithoutCurrencyFormatNoFraction(Locale("uz"))
        return mFormatWithoutCurrency.format(value)
    }

}