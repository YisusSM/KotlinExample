package com.jesus.permisos.activities


import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jesus.permisos.R
import kotlinx.android.synthetic.main.activity_permissions.*
import java.util.jar.Manifest


class PermissionsActivity : AppCompatActivity() {
    private val requestCameraPermission:Int = 1000
    private val requestCameraPicture:Int = 1005
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)


        buttonPicture.setOnClickListener { getPictureFromCameraAskingPermissions() }
    }
    private fun getPictureFromCamera(){
        //asegurarnos de que no haya permisos de camara en el manifest
        //crear intent para capturar foto
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //comprobar que podemos usar la captura
        if (pictureIntent.resolveActivity(packageManager) !=null){
            startActivityForResult(pictureIntent, requestCameraPicture)
        }else{
            //sin camara
            makeToast("0")
        }
    }
    private fun getPictureFromCameraAskingPermissions(){
        //Añadir permiso al manifest
        //comprobar el permiso de la camara
        if (ContextCompat.checkSelfPermission(this, CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this, arrayOf(CAMERA),requestCameraPermission)
        }//si ha sido aceptado previamente o versiones anteriores a API 23
        else{
                takePicture()
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            requestCameraPermission -> {
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permiso aceptado
                    takePicture()
                }else{
                    //permiso denegado
                    makeToast("You cant take a picture if you dont allow it")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            requestCameraPicture -> {
                //Comprobar si el resultado es bueno
                if(resultCode == Activity.RESULT_OK){
                    //obtenemos los extras del intent recibido
                    val extras = data!!.extras
                    //formamos el bitmap a partir de los extras
                    val imageBitMap = extras!!.get("data") as Bitmap
                    //cargamos la foto en el ImageView como Bitmap
                    imageViewPicture.setImageBitmap(imageBitMap)
                }else{
                    //La foto no ha sido tomada con éxito
                    makeToast("Picture has failed")
                }
            }
        }
    }
    private fun makeToast (msj:String) = Toast.makeText(this,"${msj}",Toast.LENGTH_SHORT).show()
    private fun takePicture() = startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE),requestCameraPicture)
}