package id.timtam.core.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class Paginator(recyclerViewLayout: RecyclerView.LayoutManager?) : RecyclerView.OnScrollListener() {

    private var startPage: Long = START_PAGE

    private val threshold = THRESHOLD_SIZE

    private var isLoading = false

    private var previousTotal = 0

    private val layoutManager: RecyclerView.LayoutManager? = recyclerViewLayout

    private val currentPage: Long
        get() = ++startPage

    abstract val isLastPage: Boolean

    abstract fun loadMore(page: Long)

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = layoutManager?.itemCount ?: 0

        var firstVisibleItemPosition = 0
        if (layoutManager is LinearLayoutManager) {
            firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        } else if (layoutManager is GridLayoutManager) {
            firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        }

        if (isLastPage) return

        if (isLoading) {
            if (totalItemCount > previousTotal) {
                isLoading = false
                previousTotal = totalItemCount
            }
        }

        if (!isLoading && totalItemCount - visibleItemCount <= firstVisibleItemPosition + threshold) {
            loadMore(currentPage)
            isLoading = true
        }
    }

    fun reset() {
        startPage = START_PAGE
    }

    companion object {
        private const val START_PAGE = 1L

        const val BATCH_SIZE = 10L
        const val THRESHOLD_SIZE = 2
    }

}