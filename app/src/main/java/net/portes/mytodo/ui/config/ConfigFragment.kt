package net.portes.mytodo.ui.config

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.portes.mytodo.R
import net.portes.mytodo.databinding.FragmentConfigBinding
import net.portes.mytodo.ui.base.BaseFragment
import net.portes.mytodo.ui.login.LoginActivity
import net.portes.shared.extensions.hideKeyboard
import net.portes.shared.extensions.observe
import net.portes.shared.extensions.toast
import net.portes.shared.extensions.value
import net.portes.shared.ui.base.ViewState

@AndroidEntryPoint
class ConfigFragment : BaseFragment<FragmentConfigBinding>(), View.OnClickListener {
    
    private val viewModel: ConfigViewModel by viewModels()

    override fun getLayoutRes(): Int = R.layout.fragment_config


    override fun initializeView() {
        dataBinding().updateButton.setOnClickListener(this)
        toCreateMenu()
        viewModel.getMessageShareDefault()
    }

    override fun initObservers() {
        observe(viewModel.messageShareResponse, ::resultMessageShareSuccess)
        observe(viewModel.messageShareUpdateResponse, ::resultMessageShareUpdateSuccess)
        observe(viewModel.toLogout, ::resultToLogout)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.updateButton -> {
                dataBinding().messageDefaultTextInputEditText.hideKeyboard()
                val messageDefault = dataBinding().messageDefaultTextInputEditText.value
                if (messageDefault.isEmpty()) {
                    requireContext().toast(requireContext().getString(R.string.message_share_default_not_empty))
                    return
                }
                viewModel.updateMessageShareDefault(messageDefault)
            }
        }
    }

    private fun toCreateMenu() {
        dataBinding().toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_logout -> {
                    viewModel.toLogout()
                    true
                }
                else -> false
            }
        }
    }

    private fun resultMessageShareSuccess(result: ViewState<String>) {
        when (result) {
            is ViewState.Loading -> showLoader()
            is ViewState.Success -> {
                hideLoader()
                dataBinding().messageDefaultTextInputEditText.setText(result.data)
            }
            is ViewState.Error -> {
                hideLoader()
                dataBinding().messageDefaultTextInputEditText.setText(R.string.message_share_default)
            }
        }
    }

    private fun resultMessageShareUpdateSuccess(result: ViewState<Unit>) {
        when (result) {
            is ViewState.Loading -> showLoader()
            is ViewState.Success -> {
                hideLoader()
                requireContext().toast(requireContext().getString(R.string.message_share_default_success))
            }
            is ViewState.Error -> {
                hideLoader()
            }
        }
    }

    private fun resultToLogout(result: Unit) {
        LoginActivity.launch(activity as AppCompatActivity)
    }
}