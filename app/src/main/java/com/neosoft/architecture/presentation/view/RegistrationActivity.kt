package com.neosoft.architecture.presentation.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.neosoft.architecture.R
import com.neosoft.architecture.data.enums.Status
import com.neosoft.architecture.presentation.UserApplication
import com.neosoft.architecture.presentation.ui.view.SignInActivity
import com.neosoft.architecture.presentation.ui.viewModelFactory.ViewModelFactory
import com.neosoft.architecture.presentation.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.activity_registration.*
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var mRegistrationViewModel: RegistrationViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        FirebaseApp.initializeApp(this);
        (application as UserApplication).getComponent()!!.inject(this)

        mRegistrationViewModel = ViewModelProviders.of(this, viewModelFactory).get(RegistrationViewModel::class.java)

        mRegistrationViewModel!!.registrationResponse().observe(this, Observer { response ->
            when (response!!.mStatus) {
                Status.LOADING -> {
                    Log.d("ACTIVITY_TAG", "LOADING")
                }
                Status.SUCCESS -> {
                    Log.d("ACTIVITY_TAG", "SUCCESS" + response.mUser!!.email)
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }
                Status.ERROR -> {
                    Log.d("ACTIVITY_TAG", "Error" + response.mError!!.message)
                    Toast.makeText(this, "" + response.mError!!.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        reg_btn_register.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.reg_btn_register -> {
                if (!TextUtils.isEmpty(reg_edt_email.text.toString()) && !TextUtils.isEmpty(reg_edt_password.text.toString())) {
                    mRegistrationViewModel!!.registerUser(
                        reg_edt_email.text.toString(),
                        reg_edt_password.text.toString()
                    )
                } else {
                    Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

