package net.portes.mytodo.ui.ipc

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import net.portes.config.prefs.ConfigSharedPref
import net.portes.ipc.domain.models.IpcDto
import net.portes.mytodo.R
import net.portes.mytodo.databinding.FragmentIpcBinding
import net.portes.mytodo.ui.base.BaseFragment
import net.portes.mytodo.ui.login.LoginActivity
import net.portes.shared.extensions.*
import net.portes.shared.ui.base.ViewState
import javax.inject.Inject

@AndroidEntryPoint
class IpcFragment : BaseFragment<FragmentIpcBinding>(), View.OnClickListener,
    EasyPermissions.PermissionCallbacks {

    companion object {
        const val TODAY_HOURS = 24
        const val TWELVE_TODAY = 12
        const val FOUR_TODAY = 4
        private const val RC_WRITE_READ_EXTERNAL_STORAGE = 1
        private const val KEY_URL_IPC = "urlIpc"
    }

    @Inject
    lateinit var firebaseRemoteConfig: FirebaseRemoteConfig

    @Inject
    lateinit var configSharedPref: ConfigSharedPref

    private val viewModel: IpcViewModel by viewModels()

    override fun getLayoutRes(): Int = R.layout.fragment_ipc

    override fun initializeView() {
        dataBinding().errorLayout.retryButton.setOnClickListener(this)
        dataBinding().ipcChipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.ipcToday -> viewModel.toFilter(TODAY_HOURS)
                R.id.ipcSinceTwelveHours -> viewModel.toFilter(TWELVE_TODAY)
                R.id.ipcSinceFourHours -> viewModel.toFilter(FOUR_TODAY)
            }
        }
        toCreateMenu()
        toCreateLink()
        viewModel.getListIpc()
    }

    override fun initObservers() {
        observe(viewModel.ipcResponse, ::resultIpc)
        observe(viewModel.totalBalance, ::resultTotalBalance)
        observe(viewModel.toLogout, ::resultToLogout)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.retryButton -> viewModel.getListIpc()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        dataBinding().contentLinearLayout.takeScreen()?.toSaveStorage(requireContext()) {
            requireContext().toShareEmail(
                text = configSharedPref.messageShareDefault,
                imageFile = it
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun toCreateLink() {
        val url = firebaseRemoteConfig[KEY_URL_IPC].asString()
        if (!url.isValidUrl()) {
            dataBinding().whatIsIPCTextView.isVisible = false
            return
        }

        dataBinding().whatIsIPCTextView.clickLink(R.string.message_what_is_ipc) {
            requireContext().browse(url)
        }
    }

    private fun toCreateMenu() {
        dataBinding().toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_refresh -> {
                    viewModel.getListIpc()
                    true
                }
                R.id.action_logout -> {
                    viewModel.toLogout()
                    true
                }
                R.id.action_share -> {
                    requestPermissions()
                    true
                }
                else -> false
            }
        }
    }

    private fun requestPermissions() {
        if (hasReadAndWriteExternalStorage()) {
            dataBinding().contentLinearLayout.takeScreen()?.toSaveStorage(requireContext()) {
                requireContext().toShareEmail(
                    text = configSharedPref.messageShareDefault,
                    imageFile = it
                )
            }
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.message_permissions_read_write),
                RC_WRITE_READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun resultIpc(result: ViewState<List<IpcDto>>) {
        when (result) {
            is ViewState.Loading -> showLoader()
            is ViewState.Success -> {
                hideLoader()
                if (result.data.isNotEmpty()) {
                    // TODO: 08/09/21 Implementar FactoryMethod
                    dataBinding().containerNestedScrollView.isVisible = true
                    dataBinding().errorLayout.commonNestedScrollView.isVisible = false
                    LineChartFactory.createLineChart(
                        dataBinding().ipcLineChart,
                        result.data,
                        getString(R.string.name_ipc_line_chart),
                        getString(R.string.description_ipc_line_chart)
                    )
                } else {
                    dataBinding().containerNestedScrollView.isGone = true
                    dataBinding().errorLayout.commonNestedScrollView.isVisible = true
                }
            }
            is ViewState.Error -> {
                hideLoader()
                dataBinding().containerNestedScrollView.isGone = true
                dataBinding().errorLayout.commonNestedScrollView.isVisible = true
            }
        }
    }

    private fun resultToLogout(result: Unit) {
        LoginActivity.launch(activity as AppCompatActivity)
    }

    private fun hasReadAndWriteExternalStorage(): Boolean = EasyPermissions.hasPermissions(
        requireContext(),
        READ_EXTERNAL_STORAGE,
        WRITE_EXTERNAL_STORAGE
    )

    private fun resultTotalBalance(totalBalance: Float) {
        dataBinding().totalBalanceTextView.text = totalBalance.parseMoney()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideLoader()
    }

}