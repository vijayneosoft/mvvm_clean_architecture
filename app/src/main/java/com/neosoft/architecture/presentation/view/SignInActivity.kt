package com.neosoft.architecture.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.OnClick
import com.neosoft.architecture.R
import com.neosoft.architecture.data.enums.Status
import com.neosoft.architecture.presentation.BaseActivity
import com.neosoft.architecture.presentation.UserApplication
import com.neosoft.architecture.presentation.ui.viewModelFactory.ViewModelFactory
import com.neosoft.architecture.presentation.viewmodel.SignInViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject


@SuppressLint("Registered")
class SignInActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var mSignInViewModel: SignInViewModel? = null

    companion object {
        fun getCallingIntent(context: Context): Intent {
            val intent = Intent(context, SignInActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        observeResponse()

    }

    override fun initViewmodel() {
        mSignInViewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    override fun injectComponent() {
        (application as UserApplication).getComponent()?.inject(this)
    }

    fun doLogin() {
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
//                    mNavigator?.navigateToMapsActivity(this)

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
        doLogin()
    }


}
