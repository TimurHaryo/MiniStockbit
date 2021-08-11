package id.timtam.ministockbit.data.remote.service

import id.timtam.ministockbit.data.remote.response.TotalTopTierResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("data/top/totaltoptiervolfull")
    suspend fun getTotalTopTier(@QueryMap queries: Map<String, String>): Response<TotalTopTierResponse>
}