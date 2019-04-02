package com.neosoft.architecture.presentation.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.neosoft.architecture.R
import com.neosoft.architecture.data.enums.Status
import com.neosoft.architecture.presentation.BaseActivity
import com.neosoft.architecture.presentation.UserApplication
import com.neosoft.architecture.presentation.ui.viewModelFactory.ViewModelFactory
import com.neosoft.architecture.presentation.viewmodel.SignInViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject


class SignInActivity : BaseActivity(), View.OnClickListener {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var mSignInViewModel: SignInViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        observeResponse()

    }

    override fun injectViewModel() {
        mSignInViewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    override fun injectComponent() {
        (application as UserApplication).getComponent()!!.inject(this)
    }

    override fun setListeners() {
        signIn_btn_login.setOnClickListener(this)
    }

    override fun loadData() {
        showLoading()
        mSignInViewModel?.doLoginVM(edt_email.text.toString(), edt_password.text.toString())
    }

    /**
     * TODO
     * observe data changes in livedata
     */
    private fun observeResponse() {
        mSignInViewModel?.loginResponse()?.observe(this, Observer { signInModel ->
            when (signInModel?.status) {
                Status.SUCCESS -> {
                    //success
                    hideLoading()
                    Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    //error
                    hideLoading()
                    Toast.makeText(this, "ERROR" + signInModel.error!!.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.signIn_btn_login ->
                loadData()
        }
    }

}
