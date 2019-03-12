package com.neosoft.architecture.presentation.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.neosoft.architecture.R
import com.neosoft.architecture.data.enums.Status
import com.neosoft.architecture.presentation.UserApplication
import com.neosoft.architecture.presentation.ui.viewModelFactory.ViewModelFactory
import com.neosoft.architecture.presentation.ui.viewmodel.SignInViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var mSignInViewModel: SignInViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as UserApplication).getComponent()!!.inject(this)

        mSignInViewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
        showResponseData()
        btn_login.setOnClickListener(this)

    }

    private fun loadData() {
        mSignInViewModel!!.doLoginVM(edt_email.text.toString(), edt_password.text.toString())
    }

    private fun showResponseData() {
        mSignInViewModel!!.loginResponse().observe(this, Observer { signInModel ->
            when (signInModel!!.status) {
                Status.LOADING -> {
                    Log.d("TAG", "LOADING")
                }
                Status.SUCCESS -> {
                    Log.d("TAG", "SUCCESS")
                    Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    Log.d("TAG", "ERROR" + signInModel.error!!.message)
                }
            }
        })
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btn_login ->
                loadData()
        }
    }

}
