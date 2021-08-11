package id.timtam.core.abstraction

interface Mapper<in Raw, out Domain> {
    fun map(raw: Raw) : Domain
}