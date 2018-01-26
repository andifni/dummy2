package id.co.aeon.aeoncreditmerchant.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.co.aeon.aeoncreditmerchant.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val type = intent.extras.getInt("type", 0)

        if (type==0) {
            Handler().postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }

        else if (type==1){
            Handler().postDelayed({
                val intent = Intent(this, LoginFirstTimeActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }
    }
}
