package com.app.geojeff.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.TextView
import com.app.geojeff.R


object AlertUtils {

    fun showAlertDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_main)
        val buttonAccept: TextView = dialog.findViewById(R.id.button_dialog_accept)

        buttonAccept.setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.let {
            val params = WindowManager.LayoutParams()
            params.copyFrom(it.attributes)
            params.width = (WindowManager.LayoutParams.MATCH_PARENT)
            params.height = (WindowManager.LayoutParams.WRAP_CONTENT)
            dialog.show()
            it.setBackgroundDrawable(ColorDrawable((Color.TRANSPARENT)))
            it.attributes = params
        }

        return dialog
    }

}