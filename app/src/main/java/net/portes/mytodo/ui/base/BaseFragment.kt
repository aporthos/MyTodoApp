package net.portes.mytodo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import net.portes.mytodo.R
import net.portes.shared.ui.widget.AlertDialog
import net.portes.shared.ui.widget.LoaderDialog

/**
 * @author amadeus.portes
 */
abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    private var loaderDialog: LoaderDialog? = null

    private lateinit var binding: DB
    protected fun dataBinding(): DB = binding
    protected abstract fun initializeView()
    protected abstract fun initObservers()

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let {
            loaderDialog = LoaderDialog(it)
        }
        initializeView()
        initObservers()
    }

    protected fun showLoader() {
        loaderDialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    protected fun hideLoader() {
        loaderDialog?.dismiss()
    }

    // TODO: 08/09/21 Revisar uso de alerts
    fun toShowDialog(
        title: Int = R.string.title_error_dialog,
        message: String,
        acceptListener: () -> Unit = {}
    ) {
        activity?.supportFragmentManager?.let {
            AlertDialog.newInstance(title = title, message = message).apply {
                show(it, AlertDialog.TAG)
                setAcceptListener {
                    acceptListener.invoke()
                }
            }
        }
    }

}