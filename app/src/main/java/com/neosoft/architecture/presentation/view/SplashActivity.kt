package com.neosoft.architecture.presentation.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.neosoft.architecture.R
import com.neosoft.architecture.presentation.BaseActivity


class SplashActivity : BaseActivity() {

    val SPLASH_DISPLAY_LENGTH: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(mainIntent)
            finish()

        }, SPLASH_DISPLAY_LENGTH)
    }

    override fun initViewmodel() {

    }

    override fun injectComponent() {

    }


}
