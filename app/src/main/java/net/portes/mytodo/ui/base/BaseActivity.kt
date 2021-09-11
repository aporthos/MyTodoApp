package net.portes.mytodo.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import net.portes.shared.ui.widget.LoaderDialog

/**
 * @author amadeus.portes
 */
abstract class BaseActivity<DB : ViewDataBinding>: AppCompatActivity() {

    private var loaderDialog: LoaderDialog? = null

    private lateinit var binding: DB
    protected fun dataBinding(): DB = binding
    protected abstract fun initializeView()
    protected abstract fun initListeners()
    protected abstract fun initObservers()

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loaderDialog = LoaderDialog(this)

        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        initializeView()
        initListeners()
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

}