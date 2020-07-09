package com.jesus.permisosdexter

import android.Manifest
import android.app.Activity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCamera.setOnClickListener { checkCameraPermission() }
    }

    private fun checkCameraPermission() {
        val context = this
        Dexter.withActivity(context).withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    textViewCamera.text = "Granted!"
                    textViewCamera.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorPermissionGranted
                        )
                    )
                }


                override fun onPermissionDenied(p0: PermissionDeniedResponse) {
                    if (p0.isPermanentlyDenied) {
                        textViewCamera.text = " Permanently Denied!"; textViewCamera.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorPermissionStatusPermanentlyDenied
                            )
                        )
                    } else {
                        textViewCamera.text = "Denied!"
                        textViewCamera.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorPermissionDenied
                            )
                        )
                    }

                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {

                }

            }).check()
    }
}