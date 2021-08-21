package id.timtam.ministockbit.ui.toptier

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import id.timtam.core.abstraction.BaseFragmentBinding
import id.timtam.core.extension.*
import id.timtam.core.util.Paginator
import id.timtam.core.util.PagingConstants
import id.timtam.ministockbit.R
import id.timtam.ministockbit.databinding.FragmentTopTierBinding
import id.timtam.ministockbit.domain.model.TopTierVolume
import id.timtam.ministockbit.ui.toptier.adapter.TopTierAdapter
import id.timtam.widget.recyclerview.DividerItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopTierFragment : BaseFragmentBinding<FragmentTopTierBinding>() {

    private val vm: TopTierViewModel by viewModel()

    private val topTierAdapter: TopTierAdapter by lazy { TopTierAdapter() }

    private var paginator: Paginator? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopTierBinding
        get() = FragmentTopTierBinding::inflate

    override fun doWhenCreated() {
        d { "FRAG: Created" }
        observeData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            renewData()
        }

        setTopTierResultAction()
        setPaginator()
    }

    override fun doOnceWhenDisplayed() {
        d { "FRAG: Resumed/Displayed" }
        renewData()
    }

    override fun toBeCleared() {
        paginator = null
        binding.rvTopTier.adapter = null
    }

    private fun setPaginator() {
        paginator = Paginator(binding.rvTopTier.layoutManager as LinearLayoutManager)
        paginator?.setOnLoadMoreListener { updateData(it) }
        paginator?.let { binding.rvTopTier.addOnScrollListener(it) }
    }

    private fun observeData() {
        vm.uiState.observe(viewLifecycleOwner, { state ->
            when(state) {
                is TopTierViewModel.TopTierUI.Loading -> {
                    startLoading()
                }
                is TopTierViewModel.TopTierUI.Success -> {
                    stopLoading()
                    with(binding) {
                        swipeRefreshLayout.enable()
                        layoutTopTierError.root.gone()
                    }
                    putTopTierResultData(state.data)
                }
                is TopTierViewModel.TopTierUI.FailedLoadInitial -> {
                    stopLoading()
                    with(binding) {
                        topTierAdapter.clearData()
                        swipeRefreshLayout.disable()
                        layoutTopTierError.root.visible()
                        layoutTopTierError.btnRetry.setOnClickListener {
                            renewData()
                        }
                    }
                    e { "${state.failure.throwable.message}" }
                }
                is TopTierViewModel.TopTierUI.FailedLoadMore -> {
                    stopLoading()
                    toast(getString(R.string.text_error_message))
                    with(binding) {
                        progressBar.invisible()
                        swipeRefreshLayout.enable()
                        btnRefreshPage.visible()
                        btnRefreshPage.setOnClickListener {
                            updateData(state.lastPage)
                        }
                    }
                    e { "${state.failure.throwable.message}" }
                }
            }
        })
    }

    private fun renewData() {
        topTierAdapter.clearData()
        paginator?.reset()
        vm.loadFirstTier(PagingConstants.BATCH_SIZE)
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun updateData(page: Int) {
        vm.loadMoreTier(page, PagingConstants.BATCH_SIZE)
    }

    private fun putTopTierResultData(data: List<TopTierVolume>) {
        topTierAdapter.appendData(data)
    }

    private fun setTopTierResultAction() {
        topTierAdapter.setOnTierClickListener {
            binding.root.snack(it.fullName)
        }

        with(binding.rvTopTier) {
            addItemDecoration(DividerItemDecoration(requireContext()))
            adapter = topTierAdapter
            setHasFixedSize(true)
        }
    }

    private fun startLoading() {
        with(binding) {
            swipeRefreshLayout.disable()
            layoutTopTierError.root.gone()
            btnRefreshPage.gone()
            progressBar.visible()
        }
    }

    private fun stopLoading() {
        binding.progressBar.gone()
    }

}