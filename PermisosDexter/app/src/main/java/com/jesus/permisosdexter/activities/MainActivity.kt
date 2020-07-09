package com.jesus.permisosdexter.activities

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.jesus.permisosdexter.R
import com.jesus.permisosdexter.enums.PermissionStatusEnum
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener
import com.karumi.dexter.listener.single.CompositePermissionListener
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import com.karumi.dexter.listener.single.PermissionListener
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setButtonClicks()


    }

    private fun setButtonClicks() {
        buttonCamera.setOnClickListener {
            /* checkPermissions(
                 Manifest.permission.CAMERA,
                 textViewCamera
             )*/
           // setCameraPermissionHandlerWithDialog()
            setCameraPermissionHandlerWithSnackbar()
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

    private fun setCameraPermissionHandlerWithDialog() {
        val dialogoPermissionListener = DialogOnDeniedPermissionListener.Builder.withContext(this)
            .withTitle("Camera Permission")
            .withMessage("Camera Permission is needed to take pictures")
            .withButtonText(android.R.string.ok)
            .withIcon(R.mipmap.ic_launcher_round)
            .build()

        val permission = object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                setPermissionStatus(
                    textViewCamera,
                    R.string.permission_status_granted,
                    R.color.colorPermissionGranted
                )
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse) {
                if (!p0.isPermanentlyDenied) {
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

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {
                p1?.continuePermissionRequest()
            }


        }
        val composite = CompositePermissionListener(permission, dialogoPermissionListener)
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(composite)
            .check()

    }

    private fun setCameraPermissionHandlerWithSnackbar() {
        val snackbarPermissionListener = SnackbarOnDeniedPermissionListener.Builder.with(
            root,
            "Camera is needed to take pictures!"
        ).withOpenSettingsButton("settings")
            .withCallback(object: Snackbar.Callback(){
                override fun onShown(sb: Snackbar?) {

                }

                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {

                }
            }).build()

        val permission = object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                setPermissionStatus(
                    textViewCamera,
                    R.string.permission_status_granted,
                    R.color.colorPermissionGranted
                )
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse) {
                if (!p0.isPermanentlyDenied) {
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

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {
                p1?.continuePermissionRequest()
            }


        }
        val composite = CompositePermissionListener(permission,snackbarPermissionListener)
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(composite)
            .check()
    }


}