package id.timtam.ministockbit.ui.toptier

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import id.timtam.core.abstraction.BaseFragmentBinding
import id.timtam.core.extension.*
import id.timtam.core.util.Paginator
import id.timtam.core.util.PagingConstants
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
        setPaginator()

        with(binding.rvTopTier) {
            setTopTierResultAction()
            paginator?.let { addOnScrollListener(it) }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            renewData()
        }
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
        paginator = object : Paginator(binding.rvTopTier.layoutManager) {
            override val isLastPage: Boolean
                get() = vm.isLastPage()

            override fun loadMore(page: Int) {
                updateData(page)
            }
        }
    }

    private fun observeData() {
        vm.uiState.observe(viewLifecycleOwner, { state ->
            when(state) {
                is TopTierViewModel.TopTierUI.Loading -> {
                    with(binding) {
                        swipeRefreshLayout.disable()
                        layoutTopTierError.root.gone()
                        progressBar.visible()
                    }
                }
                is TopTierViewModel.TopTierUI.Success -> {
                    with(binding) {
                        swipeRefreshLayout.enable()
                        layoutTopTierError.root.gone()
                        progressBar.gone()
                    }
                    putTopTierResultData(state.data)
                }
                is TopTierViewModel.TopTierUI.Failed -> {
                    with(binding) {
                        swipeRefreshLayout.disable()
                        progressBar.gone()
                        layoutTopTierError.root.visible()
                        layoutTopTierError.btnRetry.setOnClickListener {
                            renewData()
                        }
                    }
                    e { "${state.failure.throwable.message}" }
                }
            }
        })
    }

    private fun renewData() {
        binding.swipeRefreshLayout.isRefreshing = false
        paginator?.reset()
        topTierAdapter.clearData()
        updateData(1)
    }

    private fun updateData(page: Int) {
        vm.loadBook(page, PagingConstants.BATCH_SIZE)
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

}