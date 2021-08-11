package id.timtam.ministockbit.data.remote.response


import com.google.gson.annotations.SerializedName

data class TotalTopTierResponse(
    @SerializedName("Data")
    val `data`: List<Data>? = null
) {

    data class Data(
        @SerializedName("CoinInfo")
        val coinInfo: CoinInfo? = null,
        @SerializedName("DISPLAY")
        val display: DISPLAY? = null
    ) {
        data class CoinInfo(
            @SerializedName("Id")
            val id: String? = null,
            @SerializedName("Name")
            val name: String? = null,
            @SerializedName("FullName")
            val fullName: String? = null
        )

        data class DISPLAY(
            @SerializedName("USD")
            val usd: USD? = null
        ) {
            data class USD(
                @SerializedName("PRICE")
                val price: String? = null
            )
        }
    }
}