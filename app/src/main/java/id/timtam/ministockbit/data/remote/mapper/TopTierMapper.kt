package id.timtam.ministockbit.data.remote.mapper

import id.timtam.core.abstraction.Mapper
import id.timtam.ministockbit.data.remote.response.TotalTopTierResponse
import id.timtam.ministockbit.domain.model.TopTierVolume

class TopTierMapper : Mapper<TotalTopTierResponse, List<TopTierVolume>> {
    override fun map(raw: TotalTopTierResponse): List<TopTierVolume> {
        return raw.data?.map {
            TopTierVolume(
                id = it.coinInfo?.id ?: "",
                name = it.coinInfo?.name ?: "",
                fullName = it.coinInfo?.fullName ?: "",
                price = it.display?.usd?.price ?: ""
            )
        } ?: emptyList()
    }
}