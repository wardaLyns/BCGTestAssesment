package warda.test.assesment.feature

import warda.test.assesment.base.BaseView
import warda.test.assesment.model.ApiResponse

interface MusicView : BaseView {
    fun onLoadMusic(response: ApiResponse)
}