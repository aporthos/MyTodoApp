package net.portes.shared.ui.widget

import android.os.Bundle
import android.view.View
import net.portes.shared.R
import net.portes.shared.ui.base.BaseDialogFragment

/**
 * @author amadeus.portes
 */
class AlertDialog : BaseDialogFragment() {

    companion object {
        const val TAG = "AlertDialog"

        fun newInstance(title: Int, message: String) = AlertDialog().apply() {
            arguments = Bundle().apply {
                putString(MESSAGE, message)
                putInt(TITLE, title)
            }
        }
    }

    private var acceptListener: () -> Unit = {}

    override fun getLayoutRes(): Int = R.layout.dialog_alert

    override fun initializeView() {
        //acceptButton.setOnClickListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            //titleMaterialTextView.text = getString(it.getInt(TITLE))
            //messageMaterialTextView.text = it.getString(MESSAGE)
        } ?: run {
            throw IllegalArgumentException("Need parameters")
        }
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
//            R.id.acceptButton -> {
//                dismiss()
//                acceptListener.invoke()
//            }
        }
    }

    fun setAcceptListener(listener: () -> Unit) {
        acceptListener = listener
    }

}