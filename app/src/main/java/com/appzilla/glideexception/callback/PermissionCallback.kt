package com.appzilla.glideexception.callback

interface PermissionCallback {
    fun allPermissionGranted(isGranted : Boolean = false)
}