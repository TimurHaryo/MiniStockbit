package id.timtam.core.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.timtam.core.util.PagingConstants.THRESHOLD_SIZE

class Paginator : RecyclerView.OnScrollListener {

    private var layoutManager: RecyclerView.LayoutManager

    private var threshold = THRESHOLD_SIZE

    private var onLoadMore: ((Int) -> Unit)? = null

    constructor(layoutManager: LinearLayoutManager) {
        this.layoutManager = layoutManager
    }

    constructor(layoutManager: GridLayoutManager) {
        this.layoutManager = layoutManager
        threshold *= layoutManager.spanCount
    }

    constructor(layoutManager: StaggeredGridLayoutManager) {
        this.layoutManager = layoutManager
        threshold *= layoutManager.spanCount
    }

    private var startPage: Int = 1

    private var isLoading = false

    private var previousTotal = 0

    private val currentPage: Int
        get() = ++startPage

    fun reset() {
        startPage = 1
        previousTotal = 0
        isLoading = false
    }

    fun setOnLoadMoreListener(action: (page: Int) -> Unit) {
        this.onLoadMore = action
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) return

        val totalItemCount = layoutManager.itemCount
        val lastVisibleItem = when(layoutManager) {
            is LinearLayoutManager -> {
                (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            }
            is GridLayoutManager -> {
                (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            }
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)

                // get maximum element within the list
                getLastVisibleItem(lastVisibleItemPositions)
            }
            else -> 0
        }


        if (isLoading) {
            if (totalItemCount > previousTotal) {
                isLoading = false
                previousTotal = totalItemCount
            }
        } else {
            if (totalItemCount <= lastVisibleItem + threshold) {
                onLoadMore?.invoke(currentPage)
                isLoading = true
            }
        }
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }
}