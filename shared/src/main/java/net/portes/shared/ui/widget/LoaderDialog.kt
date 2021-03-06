package net.portes.shared.ui.widget

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import net.portes.shared.R

/**
 * @author amadeus.portes
 */
class LoaderDialog(context: Context) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loader)
        setCanceledOnTouchOutside(false)

        window?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context, R.color.white_opacity)))
        setCancelable(false)
    }

}