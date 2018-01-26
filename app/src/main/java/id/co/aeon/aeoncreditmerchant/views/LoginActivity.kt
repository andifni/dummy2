package id.co.aeon.aeoncreditmerchant.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.co.aeon.aeoncreditmerchant.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener {
            startActivity<MainActivity>()
        }
    }

}
