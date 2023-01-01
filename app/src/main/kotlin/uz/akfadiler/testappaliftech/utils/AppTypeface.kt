package uz.akfadiler.testappaliftech.utils

import android.content.res.AssetManager
import android.graphics.Typeface
import android.widget.TextView

class AppTypeface(
    var regular: Typeface?,
    var bold: Typeface?,
    var italic: Typeface?,
    var italicBold: Typeface?
) {

    companion object {
        var DEFAULT: AppTypeface = AppTypeface(
            Typeface.create(Typeface.DEFAULT, Typeface.NORMAL),
            Typeface.create(Typeface.DEFAULT, Typeface.BOLD),
            Typeface.create(Typeface.DEFAULT, Typeface.ITALIC),
            Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
        )

        val NeoSans = "NeoSans"
        val REGULAR_STYLE = 0
        val BOLD_STYLE = 1
        val ITALIC_BOLD_STYLE = 2
        val ITALIC_STYLE = 3
        val hashMap: MutableMap<String, AppTypeface> = mutableMapOf()

        fun getFont(fontName: String, useDefault: Boolean = true): AppTypeface? =
            if (hashMap.containsKey(fontName)) hashMap[fontName] else if (useDefault) DEFAULT else null

        fun setFont(fontName: String, view: TextView, style: Int) {
            val typeface = getFont(fontName, true)
            when (style) {
                REGULAR_STYLE -> typeface?.setRegularToView(view)
                BOLD_STYLE -> typeface?.setBoldToView(view)
                ITALIC_STYLE -> typeface?.setItalicToView(view)
                ITALIC_BOLD_STYLE -> typeface?.setItalicBoldToView(view)
            }
        }

        fun setFontArray(fontName: String, style: Int, views: Array<TextView>) {
            val typeface = getFont(fontName, true)
            when (style) {
                REGULAR_STYLE -> typeface?.setRegularToViewArray(views)
                BOLD_STYLE -> typeface?.setBoldToViewArray(views)
                ITALIC_STYLE -> typeface?.setItalicToViewArray(views)
                ITALIC_BOLD_STYLE -> typeface?.setItalicBoldToViewArray(views)
            }
        }
    }

    fun regular(): Typeface? = regular
    fun bold(): Typeface? = bold
    fun italic(): Typeface? = italic
    fun italicBold(): Typeface? = italicBold

    fun setRegularToView(view: TextView) {
        view.typeface = regular()
    }

    fun setBoldToView(view: TextView) {
        view.typeface = bold()
    }

    fun setItalicToView(view: TextView) {
        view.typeface = italic()
    }

    fun setItalicBoldToView(view: TextView) {
        view.typeface = italicBold()
    }

    fun setRegularToViewArray(views: Array<TextView>) {
        views.forEach { it.typeface = regular() }
    }

    fun setBoldToViewArray(views: Array<TextView>) {
        views.forEach { it.typeface = bold() }
    }

    fun setItalicToViewArray(views: Array<TextView>) {
        views.forEach { it.typeface = italic() }
    }

    fun setItalicBoldToViewArray(views: Array<TextView>) {
        views.forEach { it.typeface = italicBold() }
    }

    class AssetFontBuilder {
        private var assetManager: AssetManager? = null
        private var regularPath: String? = null
        private var boldFontPath: String? = null
        private var italicFontPath: String? = null
        private var italicBoldFontPath: String? = null

        fun regularFromAsset(path: String): AssetFontBuilder {
            regularPath = path
            return this
        }

        fun boldFromAsset(path: String): AssetFontBuilder {
            boldFontPath = path
            return this
        }

        fun italicFromAsset(path: String): AssetFontBuilder {
            italicFontPath = path
            return this
        }

        fun italicBoldFromAsset(path: String): AssetFontBuilder {
            italicBoldFontPath = path
            return this
        }

        fun build(assetManager: AssetManager, fontName: String): AppTypeface {

            var regular: Typeface? = null
            var bold: Typeface? = null
            var italic: Typeface? = null
            var italicBold: Typeface? = null
            if (!regularPath.isNullOrEmpty())
                regular = Typeface.createFromAsset(assetManager, regularPath)
            if (!boldFontPath.isNullOrEmpty())
                bold = Typeface.createFromAsset(assetManager, boldFontPath)
            if (!italicFontPath.isNullOrEmpty())
                italic = Typeface.createFromAsset(assetManager, italicFontPath)
            if (!italicBoldFontPath.isNullOrEmpty())
                italicBold = Typeface.createFromAsset(assetManager, italicBoldFontPath)

            val typeface = AppTypeface(regular, bold, italic, italicBold)
            if (hashMap.containsKey(fontName))
                hashMap.remove(fontName)

            hashMap.put(fontName, typeface)
            return typeface
        }
    }


}