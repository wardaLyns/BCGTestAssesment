package warda.test.assesment.remote.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import warda.test.assesment.model.ApiResponse
import warda.test.assesment.remote.ApiService
import warda.test.assesment.util.APIUtil.getResponseData
import warda.test.assesment.util.Constant.ERROR_STATUS

class Repository {

    private val service = ApiService.create()

    fun searchByArtist(name: String, callback: RepositoryCallback<ApiResponse>) {
        service.searchByArtist(name, "25", "audio").enqueue(object :
            Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                val apiResponse = getResponseData(response?.body(), "searchByArtist")
                callback.onDataLoaded(apiResponse)
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                callback.onDataError(ERROR_STATUS, t?.message.toString())
            }
        })
    }
}