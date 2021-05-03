package warda.test.assesment.remote

import androidx.multidex.BuildConfig
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import warda.test.assesment.util.Constant
import warda.test.assesment.util.UnsafeOkHttpClient

interface ApiService {

    companion object Factory {
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)

            val client = UnsafeOkHttpClient.getUnsafeOkHttpClient()
            return retrofit.client(client).build().create(ApiService::class.java)
        }
    }

    @Multipart
    @POST("search")
    fun searchByArtist(
        @Query("term") term: String?,
        @Query("limit") limit: String?,
        @Query("entity") entity: String?
    ): Call<String>
}