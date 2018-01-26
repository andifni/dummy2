package id.co.aeon.aeoncreditmerchant.views

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import id.co.aeon.aeoncreditmerchant.R
import kotlinx.android.synthetic.main.activity_transaction_input.*
import org.jetbrains.anko.toast
import java.io.File

class TransactionInputActivity : AppCompatActivity() {

    private lateinit var mCurrentPhotoPath: String

    companion object {
        const val CAMERA_REQUEST_CODE = 1111
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_input)

        val token = intent.getStringExtra("token")
        tokenTxt.text = token

        viewSelfieBtn.setOnClickListener {
            showPhoto()
        }

        uploadSelfieBtn.setOnClickListener {
            validatePermissions()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
/*                if(resultCode == Activity.RESULT_OK && data != null) {
                    photoImageView.setImageBitmap(data.extras.get("data") as Bitmap)
                }*/
                if (resultCode == Activity.RESULT_OK) {
                    if (viewSelfieBtn.visibility == View.GONE){
                        viewSelfieBtn.visibility = View.VISIBLE
                    }
                }
            }
            else -> {
                toast( "Unrecognized request code")
            }
        }
    }


    private fun launchCamera() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        val fileUri = contentResolver
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager) != null) {
            mCurrentPhotoPath = fileUri.toString()
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }

    private fun validatePermissions() {

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .withListener(object: MultiplePermissionsListener {
                    override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
                        token?.continuePermissionRequest()
                    }
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        if (report != null) {
                            when {
                                report.areAllPermissionsGranted() -> launchCamera()
                                report.isAnyPermissionPermanentlyDenied -> showSettingsDialog()
                                else -> toast("Need permission")
                            }
                        }
                    }
                })
                .check()
    }

    fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permission")
        builder.setMessage("this app_needs_permission_to_use_this_feature_you_can_grant_them_in_app_settings")
        builder.setPositiveButton("goto settings", {
            dialog, _ ->
            dialog.cancel()
            openSettings()
        })
        builder.setNegativeButton("cancel", {
            dialog, _ ->  dialog.cancel()
        })
        builder.show()

    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    private fun showPhoto() {
        val cursor = contentResolver.query(Uri.parse(mCurrentPhotoPath),
                Array(1) {android.provider.MediaStore.Images.ImageColumns.DATA},
                null, null, null)
        cursor.moveToFirst()
        val photoPath = cursor.getString(0)
        cursor.close()
        val file = File(photoPath)
        val uri = Uri.fromFile(file)

        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setDataAndType(uri, "image/*")
        startActivity(intent)
    }
}
