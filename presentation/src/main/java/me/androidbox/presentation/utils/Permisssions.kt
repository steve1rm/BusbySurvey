package me.androidbox.presentation.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat

fun ComponentActivity.shouldShowNoticationPermissionRationale(): Boolean {
    return Build.VERSION.SDK_INT >= 33 && shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)
}

private fun Context.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Context.hasNoticationPermission(): Boolean {
    return if(Build.VERSION.SDK_INT >= 33) {
        this.hasPermission(Manifest.permission.POST_NOTIFICATIONS)
    }
    else {
        true
    }
}
