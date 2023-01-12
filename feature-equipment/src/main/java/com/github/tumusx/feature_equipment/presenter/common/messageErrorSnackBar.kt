package com.github.tumusx.feature_equipment.presenter.common

import android.view.View
import com.github.tumusx.common_design_system.R
import com.google.android.material.snackbar.Snackbar

fun View.messageErrorSnackBar() {
    Snackbar.make(
        this,
        this.context.getString(R.string.error_unknow),
        Snackbar.LENGTH_LONG
    ).show()
    return
}