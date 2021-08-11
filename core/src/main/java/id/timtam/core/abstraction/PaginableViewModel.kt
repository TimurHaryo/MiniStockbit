package id.timtam.core.abstraction

interface PaginableViewModel {
    var dataSize: Int
    fun isLastPage() : Boolean
}