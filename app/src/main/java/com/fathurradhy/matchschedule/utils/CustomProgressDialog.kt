package com.fathurradhy.matchschedule.utils

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.drawable.ColorDrawable
import com.fathurradhy.matchschedule.R

object CustomProgressDialog {

    private var mProgressDialog: ProgressDialog? = null

    fun showDialog(context: Activity) {
        mProgressDialog = ProgressDialog(context)
        if (!mProgressDialog!!.isShowing) {
            mProgressDialog!!.show()
        }
        mProgressDialog!!.setContentView(R.layout.custom_progressdialog)
        mProgressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        mProgressDialog!!.setProgressStyle(android.R.style.Widget_ProgressBar_Small)
    }

    fun stopDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
        }
    }

}
