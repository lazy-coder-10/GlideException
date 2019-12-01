package com.appzilla.glideexception

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.appzilla.glideexception.callback.PermissionCallback
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), PermissionCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionManager(this,this, arrayListOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION))

    }

    override fun allPermissionGranted(isGranted: Boolean) {
        if(isGranted){
            Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
        }else{
            PermissionManager(this,this, arrayListOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION))
        }
    }
}
