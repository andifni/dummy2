package id.co.aeon.aeoncreditmerchant.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.co.aeon.aeoncreditmerchant.R
import kotlinx.android.synthetic.main.activity_dummy.*
import org.jetbrains.anko.startActivity

class DummyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)

        firstTimeLoginBtn.setOnClickListener {
            startActivity<SplashScreenActivity>("type" to 1)
        }

        loginBtn.setOnClickListener {
            startActivity<SplashScreenActivity>("type" to 0)
        }
    }
}
