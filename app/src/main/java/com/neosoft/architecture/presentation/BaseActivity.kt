package com.neosoft.architecture.presentation

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.neosoft.architecture.R
import kotlinx.android.synthetic.main.activity_base.*

/**
 * Created by Vijay on 2/4/19.
 */

open abstract class BaseActivity : AppCompatActivity() {

    val mFrameLayoutContainer: FrameLayout? = null
    var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        initViewmodel()
    }

    abstract fun initViewmodel()
    abstract fun injectComponent()

    /**
     * TODO
     * child should override for retry on API failure
     */
    fun retry() {

    }


    override fun setContentView(layoutResID: Int) {
        super.setContentView(R.layout.activity_base)
        mFrameLayoutContainer?.addView(LayoutInflater.from(this).inflate(layoutResID, null))
        setFullScreenLoader()
    }

    fun setFullScreenLoader() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog?.setCancelable(false)
    }

    fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    fun showToastMessage(message : String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }


}