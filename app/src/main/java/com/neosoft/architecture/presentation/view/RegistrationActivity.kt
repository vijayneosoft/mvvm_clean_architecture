package com.neosoft.architecture.presentation.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.firebase.FirebaseApp
import com.neosoft.architecture.R
import com.neosoft.architecture.data.enums.Status
import com.neosoft.architecture.presentation.BaseActivity
import com.neosoft.architecture.presentation.UserApplication
import com.neosoft.architecture.presentation.ui.view.SignInActivity
import com.neosoft.architecture.presentation.ui.viewModelFactory.ViewModelFactory
import com.neosoft.architecture.presentation.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.activity_registration.*
import javax.inject.Inject

class RegistrationActivity : BaseActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var mRegistrationViewModel: RegistrationViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        FirebaseApp.initializeApp(this)
        observeResponse()
        ButterKnife.bind(this)

    }

    override fun initViewmodel() {
        mRegistrationViewModel = ViewModelProviders.of(this, viewModelFactory).get(RegistrationViewModel::class.java)
    }

    override fun injectComponent() {
        (application as UserApplication).getComponent()?.inject(this)
    }

    fun loadData() {
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
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }
                Status.ERROR -> {
                    //error
                    hideLoading()
                    Toast.makeText(this, "" + response.mError!!.message, Toast.LENGTH_LONG).show()
                }
            }
        })


    }

    @OnClick(R.id.reg_btn_register)
    fun onRegisterButtonClick() {
        if (!TextUtils.isEmpty(reg_edt_email.text.toString()) && !TextUtils.isEmpty(reg_edt_password.text.toString())) {
            loadData()
        } else {
            Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_LONG).show()
        }
    }

}

