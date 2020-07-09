package com.jesus.permisosdexter.activities

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jesus.permisosdexter.R
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

        setButtonClicks()


    }

    private fun setButtonClicks() {
        buttonCamera.setOnClickListener {
            checkPermissions(
                Manifest.permission.CAMERA,
                textViewCamera
            )
        }
        buttonContact.setOnClickListener {
            checkPermissions(
                Manifest.permission.READ_CONTACTS,
                textViewContact
            )
        }
        buttonAudio.setOnClickListener {
            checkPermissions(
                Manifest.permission.RECORD_AUDIO,
                textViewAudio
            )
        }
    }

    private fun checkPermissions(permission: String, textView: TextView) {
        Dexter.withActivity(this)
            .withPermission(permission)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    setPermissionStatus(
                        textView,
                        R.string.permission_status_granted,
                        R.color.colorPermissionGranted
                    )
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    token!!.continuePermissionRequest()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    if (response!!.isPermanentlyDenied) {
                        setPermissionStatus(
                            textView,
                            R.string.permission_status_denied_permanently,
                            R.color.colorPermissionStatusPermanentlyDenied
                        )
                    } else {
                        setPermissionStatus(
                            textView,
                            R.string.permission_status_denied,
                            R.color.colorPermissionDenied
                        )
                    }
                }

            }).check()
    }

    private fun setPermissionStatus(textView: TextView, status: Int, statusColor: Int) {
        textView.setText(status)
        textView.setTextColor(ContextCompat.getColor(applicationContext, statusColor))
    }


}