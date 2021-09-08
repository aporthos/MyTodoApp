package net.portes.shared.ui.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment

/**
 * @author amadeus.portes
 */
abstract class BaseDialogFragment : DialogFragment(), View.OnClickListener {

    companion object {
        const val TITLE = "title"
        const val MESSAGE = "message"
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initializeView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        //closeImageButton.setOnClickListener(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.let {
            it.requestFeature(Window.FEATURE_NO_TITLE)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        isCancelable = false
        return dialog
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //R.id.closeImageButton -> dismiss()
        }
    }

}