package com.appzilla.glideexception

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import com.appzilla.glideexception.callback.PermissionCallback
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener


class PermissionManager constructor(
    var mcontext: Context,
    var mPermissionCallback: PermissionCallback,
    var permissionArray: ArrayList<String>
) {

    init {
        checkPermission(permissionArray)
    }

    private fun checkPermission(arrayCollection: ArrayList<String>) {
        Dexter.withActivity(mcontext as Activity?)
            .withPermissions(arrayCollection)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    //check if all permission granted
                    when {
                        report!!.areAllPermissionsGranted() -> {
                            mPermissionCallback.allPermissionGranted(true)
                        }
                        report.isAnyPermissionPermanentlyDenied -> {
                            // show alert dialog navigating to Settings
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts("package", mcontext.packageName, null)
                            intent.data = uri
                            mcontext.startActivity(intent)
                        }
                        else -> {
                            mPermissionCallback.allPermissionGranted()
                        }
                    }
                }
                override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
                      token?.continuePermissionRequest()
                }
            }).withErrorListener {
                Log.d("Error",it.name)
            }.check()
    }


}