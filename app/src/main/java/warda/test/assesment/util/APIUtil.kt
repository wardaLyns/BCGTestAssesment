package warda.test.assesment.util

import android.util.Log
import com.google.gson.Gson
import org.json.JSONException
import warda.test.assesment.model.ApiResponse

object APIUtil {

    fun getResponseData(responseBody: String?, tag: String?): ApiResponse {
        Log.d(tag, responseBody.toString())
        var apiResponse = ApiResponse()
        try {
            apiResponse = Gson().fromJson(responseBody, ApiResponse::class.java)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return apiResponse
    }
}