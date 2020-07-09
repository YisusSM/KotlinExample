package com.jesus.permisosdexter.activities

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jesus.permisosdexter.R
import com.jesus.permisosdexter.enums.PermissionStatusEnum
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
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
        buttonAllPermission.setOnClickListener { checkPermissionsAll() }
    }

    private fun checkPermissionsAll() {
        Dexter.withContext(this).withPermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.RECORD_AUDIO
        )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    p0?.let {
                        for (permission in p0.grantedPermissionResponses) {
                            when (permission.permissionName) {
                                Manifest.permission.CAMERA -> setPermissionStatus(
                                    textViewCamera,
                                    R.string.permission_status_granted,
                                    R.color.colorPermissionGranted
                                )
                                Manifest.permission.READ_CONTACTS -> setPermissionStatus(
                                    textViewContact,
                                    R.string.permission_status_granted,
                                    R.color.colorPermissionGranted
                                )
                                Manifest.permission.RECORD_AUDIO -> setPermissionStatus(
                                    textViewAudio,
                                    R.string.permission_status_granted,
                                    R.color.colorPermissionGranted
                                )

                            }
                        }
                        for (permission in p0.deniedPermissionResponses) {
                            when (permission.permissionName) {
                                Manifest.permission.CAMERA -> {
                                    if (!permission.isPermanentlyDenied) {
                                        setPermissionStatus(
                                            textViewCamera,
                                            R.string.permission_status_denied,
                                            R.color.colorPermissionDenied
                                        )
                                    } else {
                                        setPermissionStatus(
                                            textViewCamera,
                                            R.string.permission_status_denied_permanently,
                                            R.color.colorPermissionStatusPermanentlyDenied
                                        )
                                    }

                                }
                                Manifest.permission.RECORD_AUDIO -> {
                                    if (!permission.isPermanentlyDenied) {
                                        setPermissionStatus(
                                            textViewAudio,
                                            R.string.permission_status_denied,
                                            R.color.colorPermissionDenied
                                        )
                                    } else {
                                        setPermissionStatus(
                                            textViewAudio,
                                            R.string.permission_status_denied_permanently,
                                            R.color.colorPermissionStatusPermanentlyDenied
                                        )
                                    }

                                }
                                Manifest.permission.READ_CONTACTS -> {
                                    if (!permission.isPermanentlyDenied) {
                                        setPermissionStatus(
                                            textViewContact,
                                            R.string.permission_status_denied,
                                            R.color.colorPermissionDenied
                                        )
                                    } else {
                                        setPermissionStatus(
                                            textViewContact,
                                            R.string.permission_status_denied_permanently,
                                            R.color.colorPermissionStatusPermanentlyDenied
                                        )
                                    }

                                }
                            }
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    p1?.continuePermissionRequest()
                }

            }).check()
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