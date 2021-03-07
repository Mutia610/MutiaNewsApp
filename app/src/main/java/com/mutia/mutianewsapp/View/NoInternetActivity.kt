package com.mutia.mutianewsapp.View

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mutia.mutianewsapp.R
import kotlinx.android.synthetic.main.dialog_no_internet.view.*

class NoInternetActivity : AppCompatActivity() {

    private var dialogView: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)

        val dialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_no_internet, null)
        dialog.setView(view)

        view.back.setOnClickListener {
            dialogView?.dismiss()
        }

        dialogView = dialog.create()
        dialogView?.show()
    }
}