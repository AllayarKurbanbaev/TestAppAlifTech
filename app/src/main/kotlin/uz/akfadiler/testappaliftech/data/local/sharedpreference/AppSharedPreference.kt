package uz.akfadiler.testappaliftech.data.local.sharedpreference

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.akfadiler.testappaliftech.utils.SharedPreference

class AppSharedPreference constructor(@ApplicationContext context: Context) :
    SharedPreference(context) {
    val userId: Int by IntPreference()
}