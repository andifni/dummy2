package id.co.aeon.aeoncreditmerchant.views


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.co.aeon.aeoncreditmerchant.R
import kotlinx.android.synthetic.main.activity_scan_qr.*

import org.jetbrains.anko.toast
import me.dm7.barcodescanner.zbar.BarcodeFormat
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import org.jetbrains.anko.startActivity


class ScanQRActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {
    override fun handleResult(result: Result?) {
        toast("Reading success")
        startActivity<TransactionInputActivity>("token" to result?.contents.toString())
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
