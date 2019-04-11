package com.neosoft.architecture.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.OnClick
import com.google.firebase.FirebaseApp
import com.neosoft.architecture.R
import com.neosoft.architecture.data.enums.Status
import com.neosoft.architecture.presentation.BaseActivity
import com.neosoft.architecture.presentation.UserApplication
import com.neosoft.architecture.presentation.di.component.ActivityComponent
import com.neosoft.architecture.presentation.di.component.DaggerActivityComponent
import com.neosoft.architecture.presentation.di.module.ActivityModule
import com.neosoft.architecture.presentation.ui.viewModelFactory.ViewModelFactory
import com.neosoft.architecture.presentation.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.activity_registration.*
import javax.inject.Inject


class RegistrationActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    var component: ActivityComponent? = null

    var mRegistrationViewModel: RegistrationViewModel? = null

    companion object {
        fun getCallingIntent(context: Context): Intent {
            val intent = Intent(context, RegistrationActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        FirebaseApp.initializeApp(this)
        observeResponse()
        setToolbar(getString(R.string.information_register))

    }

    override fun initViewmodel() {
        mRegistrationViewModel = ViewModelProviders.of(this, viewModelFactory).get(RegistrationViewModel::class.java)
    }

    override fun injectComponent() {

        component = DaggerActivityComponent.builder()
            .applicationComponent((application as UserApplication).getComponent())
            .activityModule(ActivityModule(this))
            .build()

        component?.inject(this)

    }


    fun doRegister() {
        showLoading()
        mRegistrationViewModel?.registerUser(
            reg_edt_email.text.toString(),
            reg_edt_password.text.toString()
        )
    }


    /**
     * TODO
     * observe data changes in livedata
     */
    private fun observeResponse() {
        mRegistrationViewModel?.registrationResponse()?.observe(this, Observer { response ->
            when (response?.mStatus) {
                Status.SUCCESS -> {
                    //success
                    hideLoading()
                    mNavigator?.navigateToSignInActivity(this)

                }
                Status.ERROR -> {
                    //error
                    hideLoading()
                    showToastMessage("" + response.mError!!.message)
                }
            }
        })
    }

    @OnClick(R.id.reg_btn_register)
    fun onRegisterButtonClick() {
        if (!TextUtils.isEmpty(reg_edt_email.text.toString()) && !TextUtils.isEmpty(reg_edt_password.text.toString())) {
            doRegister()
        } else {
            showToastMessage(getString(R.string.error_empty_field))
        }
    }


}

