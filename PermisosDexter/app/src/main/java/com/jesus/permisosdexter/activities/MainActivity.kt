package com.jesus.permisosdexter.activities

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jesus.permisosdexter.R
import com.jesus.permisosdexter.enums.PermissionStatusEnum
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
        buttonCamera.setOnClickListener { checkCameraPermission() }
        buttonContact.setOnClickListener { checkContactPermission() }
        buttonAudio.setOnClickListener { checkAudioPermission() }
    }

    private fun checkCameraPermission() =
        setPermissionHandler(Manifest.permission.CAMERA, textViewCamera)

    private fun checkContactPermission() =
        setPermissionHandler(Manifest.permission.READ_CONTACTS, textViewContact)

    private fun checkAudioPermission() =
        setPermissionHandler(Manifest.permission.RECORD_AUDIO, textViewAudio)

    private fun setPermissionHandler(permission: String, textView: TextView) {
        Dexter.withActivity(this).withPermission(permission)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    setPermissionStatus(textView, PermissionStatusEnum.GRANTED)
                }


                override fun onPermissionDenied(p0: PermissionDeniedResponse) {
                    if (!p0.isPermanentlyDenied) {
                        setPermissionStatus(textView, PermissionStatusEnum.DENIED)
                    } else {
                        setPermissionStatus(textView, PermissionStatusEnum.PERMANENTLY_DENIED)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest,
                    p1: PermissionToken
                ) {
                    p1.continuePermissionRequest()
                }

            }).check()
    }

    private fun setPermissionStatus(textView: TextView, status: PermissionStatusEnum) {
        when (status) {
            PermissionStatusEnum.GRANTED -> {
                textView.text = getString(R.string.permission_status_granted)
                textView.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorPermissionGranted
                    )
                )
            }
            PermissionStatusEnum.DENIED -> {
                textView.text = getString(R.string.permission_status_denied)
                textView.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorPermissionDenied
                    )
                )
            }
            PermissionStatusEnum.PERMANENTLY_DENIED -> {
                textView.text =
                    getString(R.string.permission_status_denied_permanently)
                textView.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorPermissionStatusPermanentlyDenied
                    )
                )
            }
        }
    }
}