package uz.akfadiler.testappaliftech.utils

import android.app.Activity
import android.content.Context
import com.nabinbhandari.android.permissions.PermissionHandler

fun Activity.checkPermissions2(
    permission: Array<String>, granted: () -> Unit,
    denied: () -> Unit,
    justBlocked: () -> Unit,
    blocked: () -> Unit,
) {
    com.nabinbhandari.android.permissions.Permissions.check(
        this, permission, null, null, permissionHandler(granted, denied, justBlocked, blocked)
    )
}

private fun permissionHandler(
    granted: () -> Unit, denied: () -> Unit, justBlocked: () -> Unit, blocked: () -> Unit
) = object : PermissionHandler() {
    override fun onGranted() {
        granted()
    }

    override fun onDenied(context: Context?, deniedPermissions: ArrayList<String>?) {
        super.onDenied(context, deniedPermissions)
        denied()
    }

    override fun onJustBlocked(
        context: Context?,
        justBlockedList: ArrayList<String>?,
        deniedPermissions: ArrayList<String>?
    ) {
        super.onJustBlocked(context, justBlockedList, deniedPermissions)
        justBlocked()
    }

    override fun onBlocked(context: Context?, blockedList: ArrayList<String>?): Boolean {
        blocked()
        return super.onBlocked(context, blockedList)
    }
}
