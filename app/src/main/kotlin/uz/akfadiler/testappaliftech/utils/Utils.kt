package uz.akfadiler.testappaliftech.utils

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import androidx.fragment.app.FragmentActivity

object Utils {
    @SuppressLint("Range")
    fun getFileName(uri: Uri, activity: FragmentActivity?): String {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = activity?.contentResolver?.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result!!.lastIndexOf('/')
            if (cut != -1) {
                result = result.substring(cut + 1)
            }
        }
        return result
    }

   /* fun setImage(image: ImageView, type: String) {
        when (type) {
            "png" -> {
                image.setImageResource(R.drawable.ic_png)
            }
            "jpg" -> {
                image.setImageResource(R.drawable.ic_jpg)
            }
            "pdf" -> {
                image.setImageResource(R.drawable.ic_pdf)
            }
            "txt" -> {
                image.setImageResource(R.drawable.ic_txt)
            }
            "mp4" -> {
                image.setImageResource(R.drawable.ic_mp4)
            }
            "zip" -> {
                image.setImageResource(R.drawable.ic_zip)
            }
            "avi" -> {
                image.setImageResource(R.drawable.ic_avi)
            }
            "mp3" -> {
                image.setImageResource(R.drawable.ic_mp3)
            }
            "ppt" -> {
                image.setImageResource(R.drawable.ic_ppt)
            }
            "doc", "docx" -> {
                image.setImageResource(R.drawable.ic_doc)
            }
            "xlsx", "xls" -> {
                image.setImageResource(R.drawable.ic_xls)
            }
            else -> {
                image.setImageResource(R.drawable.ic_file)
            }
        }
    }*/

    fun call(activity: FragmentActivity?, phoneNumber : String){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = Uri.parse("tel:$phoneNumber")
        activity?.startActivity(intent)
    }
}