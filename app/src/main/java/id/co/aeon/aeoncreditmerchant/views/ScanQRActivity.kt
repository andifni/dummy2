package id.co.aeon.aeoncreditmerchant.views

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import com.google.android.gms.vision.CameraSource
import id.co.aeon.aeoncreditmerchant.R
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_scan_qr.*
import android.util.Log
import com.karumi.dexter.Dexter
import java.io.IOException
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import android.Manifest.permission
import android.annotation.SuppressLint
import com.karumi.dexter.listener.PermissionRequest
import org.jetbrains.anko.toast
import java.nio.file.Files.size
import android.util.SparseArray
import com.google.android.gms.vision.Detector
import me.dm7.barcodescanner.zbar.BarcodeFormat
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView


class ScanQRActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {
    override fun handleResult(result: Result?) {
        toast(result?.contents.toString())
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)
        setupScanner()
    }

    private fun setupScanner() {
        val formats = ArrayList<BarcodeFormat>()
        formats.add(BarcodeFormat.QRCODE)
        cameraView.setFormats(formats)
        cameraView.setResultHandler(this)
        cameraView.startCamera()
    }

    override fun onResume() {
        setupScanner()
        super.onResume()
    }

    override fun onPause() {
        cameraView.stopCamera()
        super.onPause()
    }

}
