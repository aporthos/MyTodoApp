package net.portes.mytodo.ui.ipc

import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.portes.ipc.domain.model.IpcDto
import net.portes.mytodo.R
import net.portes.mytodo.databinding.FragmentIpcBinding
import net.portes.mytodo.ui.base.BaseFragment
import net.portes.shared.extensions.observe
import net.portes.shared.extensions.parseMoney
import net.portes.shared.ui.base.ViewState
import net.portes.shared.util.URL_IPC
import net.portes.shared.util.ZERO_INT
import java.text.DecimalFormat

@AndroidEntryPoint
class IpcFragment : BaseFragment<FragmentIpcBinding>(), View.OnClickListener {

    private val viewModel: IpcViewModel by viewModels()

    override fun getLayoutRes(): Int = R.layout.fragment_ipc

    override fun initializeView() {
        viewModel.getListIpc()
        dataBinding().errorLayout.retryButton.setOnClickListener(this)
        dataBinding().ipcChipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.ipcToday -> viewModel.toFilter(24)
                R.id.ipcSinceTwelveHours -> viewModel.toFilter(12)
                R.id.ipcSinceFourHours -> viewModel.toFilter(4)
            }
        }
        toCreateMenu()
        toCreateLink()
    }

    override fun initObservers() {
        observe(viewModel.ipcResponse, ::resultIpc)
        observe(viewModel.totalBalance, ::resultTotalBalance)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.retryButton -> viewModel.getListIpc()
        }
    }

    private fun toCreateLink() {
        dataBinding().whatIsIPCTextView.movementMethod = LinkMovementMethod.getInstance()
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(URL_IPC)
                }
                startActivity(intent)
            }
        }

        val spannableString = SpannableString(getString(R.string.message_what_is_ipc))
        spannableString.setSpan(
            clickableSpan,
            ZERO_INT,
            getString(R.string.message_what_is_ipc).length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        dataBinding().whatIsIPCTextView.text = spannableString
    }

    private fun toCreateMenu() {
        dataBinding().toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_refresh -> {
                    viewModel.getListIpc()
                    false
                }
                else -> false
            }
        }
    }

    private fun resultIpc(result: ViewState<List<IpcDto>>) {
        when (result) {
            is ViewState.Loading -> showLoader()
            is ViewState.Success -> {
                hideLoader()
                if (result.data.isNotEmpty()) {
                    // TODO: 08/09/21 Implementar FactoryMethod
                    dataBinding().ipcLineChart.isVisible = true
                    dataBinding().errorLayout.commonNestedScrollView.isVisible = false
                    LineChartFactory.createLineChart(
                        dataBinding().ipcLineChart,
                        result.data,
                        getString(R.string.name_ipc_line_chart),
                        getString(R.string.description_ipc_line_chart)
                    )
                } else {
                    dataBinding().ipcLineChart.isVisible = false
                    dataBinding().errorLayout.commonNestedScrollView.isVisible = true
                }
            }
            is ViewState.Error -> {
                hideLoader()
                dataBinding().ipcLineChart.isVisible = false
                dataBinding().errorLayout.commonNestedScrollView.isVisible = true
            }
        }
    }

    private fun resultTotalBalance(totalBalance: Float) {
        dataBinding().totalBalanceTextView.text = totalBalance.parseMoney()
    }

}