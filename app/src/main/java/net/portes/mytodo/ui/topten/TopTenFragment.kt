package net.portes.mytodo.ui.topten

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import net.portes.mytodo.R
import net.portes.mytodo.databinding.FragmentToptenBinding
import net.portes.mytodo.ui.base.BaseFragment
import net.portes.mytodo.ui.login.LoginActivity
import net.portes.shared.extensions.observe
import net.portes.shared.extensions.toShareEmail
import net.portes.shared.ui.base.ViewState
import net.portes.topten.domain.models.TopTenItem
import java.io.File

@AndroidEntryPoint
class TopTenFragment : BaseFragment<FragmentToptenBinding>(), EasyPermissions.PermissionCallbacks, View.OnClickListener {

    companion object {
        private const val RC_WRITE_READ_EXTERNAL_STORAGE = 1
    }

    private val viewModel: TopTenViewModel by viewModels()

    private val topTenEpoxyController by lazy {
        TopTenEpoxyController()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_topten

    override fun initializeView() {
        dataBinding().topTenRecyclerView.adapter = topTenEpoxyController.adapter
        // TODO: 13/09/21 Pending review
        dataBinding().topTenSwipeRefreshLayout.setOnRefreshListener {
            dataBinding().topTenSwipeRefreshLayout.isRefreshing = false
            viewModel.startUpdates()
        }
        dataBinding().errorLayout.retryButton.setOnClickListener(this)
        toCreateMenu()
        viewModel.startUpdates()
    }

    private fun toCreateMenu() {
        dataBinding().toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
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

    override fun initObservers() {
        observe(viewModel.topTenResponse, ::resultTopTenList)
        observe(viewModel.toLogout, ::resultToLogout)
        observe(viewModel.pdfDocument, ::resultPdfDocument)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.retryButton -> viewModel.startUpdates()
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
        toCreatePDF()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun resultTopTenList(result: ViewState<List<TopTenItem>>) {
        when (result) {
            is ViewState.Loading -> showLoader()
            is ViewState.Success -> {
                hideLoader()
                if (result.data.isNotEmpty()) {
                    dataBinding().topTenSwipeRefreshLayout.isVisible = true
                    dataBinding().errorLayout.commonNestedScrollView.isVisible = false
                    topTenEpoxyController.topTenList = result.data
                } else {
                    dataBinding().topTenSwipeRefreshLayout.isVisible = false
                    dataBinding().errorLayout.commonNestedScrollView.isVisible = true
                }
            }
            is ViewState.Error -> {
                hideLoader()
                dataBinding().topTenSwipeRefreshLayout.isVisible = false
                dataBinding().errorLayout.commonNestedScrollView.isVisible = true
            }
        }
    }

    private fun toCreatePDF() {
        viewModel.toPdfCreate(requireContext())
    }

    private fun resultToLogout(result: Unit) {
        LoginActivity.launch(activity as AppCompatActivity)
    }

    private fun requestPermissions() {
        if (hasReadAndWriteExternalStorage()) {
            toCreatePDF()
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.message_permissions_read_write),
                RC_WRITE_READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun resultPdfDocument(result: File) {
        requireContext().toShareEmail(text = "", imageFile = result)
    }

    private fun hasReadAndWriteExternalStorage(): Boolean = EasyPermissions.hasPermissions(
        requireContext(),
        READ_EXTERNAL_STORAGE,
        WRITE_EXTERNAL_STORAGE
    )

}