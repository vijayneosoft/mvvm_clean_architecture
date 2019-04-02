package com.neosoft.architecture.presentation

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
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
        setListeners()
        injectComponent()
        injectViewModel()
    }

    abstract fun injectViewModel()
    abstract fun injectComponent()
    abstract fun setListeners()
    abstract fun loadData()


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


}