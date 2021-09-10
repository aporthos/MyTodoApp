package net.portes.mytodo.ui.topten

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.portes.mytodo.R
import net.portes.mytodo.databinding.FragmentToptenBinding
import net.portes.mytodo.ui.base.BaseFragment
import net.portes.shared.extensions.observe
import net.portes.shared.ui.base.ViewState
import net.portes.topten.domain.models.TopTenItem

@AndroidEntryPoint
class TopTenFragment : BaseFragment<FragmentToptenBinding>() {

    private val viewModel: TopTenViewModel by viewModels()

    private val topTenEpoxyController by lazy {
        TopTenEpoxyController()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_topten

    override fun initializeView() {
        dataBinding().topTenRecyclerView.adapter = topTenEpoxyController.adapter
        dataBinding().topTenSwipeRefreshLayout.setOnRefreshListener {
            dataBinding().topTenSwipeRefreshLayout.isRefreshing = false
            viewModel.startUpdates()
        }

        viewModel.startUpdates()
    }

    override fun initObservers() {
        observe(viewModel.topTenResponse, ::resultTopTenList)
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

}