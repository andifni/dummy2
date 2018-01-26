package id.co.aeon.aeoncreditmerchant.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.co.aeon.aeoncreditmerchant.R
import kotlinx.android.synthetic.main.activity_login_first_time.*
import org.jetbrains.anko.startActivity

class LoginFirstTimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_first_time)

        loginBtn.setOnClickListener {
            startActivity<LoginOTPActivity>()
        }

    }
}
