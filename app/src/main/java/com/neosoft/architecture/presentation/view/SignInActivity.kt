package com.neosoft.architecture.presentation.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import butterknife.OnClick
import com.neosoft.architecture.R
import com.neosoft.architecture.data.enums.Status
import com.neosoft.architecture.presentation.BaseActivity
import com.neosoft.architecture.presentation.UserApplication
import com.neosoft.architecture.presentation.ui.viewModelFactory.ViewModelFactory
import com.neosoft.architecture.presentation.viewmodel.SignInViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject


class SignInActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var mSignInViewModel: SignInViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        observeResponse()

    }

    override fun initViewmodel() {
        mSignInViewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    override fun injectComponent() {
        (application as UserApplication).getComponent()!!.inject(this)
    }

    fun loadData() {
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
                    showToastMessage(getString(R.string.information_success))
                }
                Status.ERROR -> {
                    //error
                    hideLoading()
                    showToastMessage(getString(R.string.information_error) + signInModel.error!!.message)

                }
            }
        })
    }

    @OnClick(R.id.signIn_btn_login)
    fun onSignInClick() {
        loadData()
    }


}
