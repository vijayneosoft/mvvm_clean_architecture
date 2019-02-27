package com.neosoft.architecture.presentation.loginActivity

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
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mLoginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        showResponseData()
        btn_login.setOnClickListener(this)

    }

    private fun loadData() {
        mLoginViewModel.doLoginVM(edt_email.text.toString(), edt_password.text.toString())
    }

    private fun showResponseData() {
        mLoginViewModel.loginResponse().observe(this, Observer { loginModel ->
            when (loginModel!!.status) {
                Status.LOADING -> {
                    Log.d("TAG", "LOADING")
                }
                Status.SUCCESS -> {
                    Log.d("TAG", "SUCCESS")
                    Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    Log.d("TAG", "ERROR" + loginModel.error!!.message)
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
