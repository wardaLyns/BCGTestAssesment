package warda.test.assesment.feature

import warda.test.assesment.model.ApiResponse
import warda.test.assesment.remote.repository.Repository
import warda.test.assesment.remote.repository.RepositoryCallback

class MusicPresenter internal constructor(
    private var view: MusicView?,
    private val repository: Repository
) {

    fun unBind() {
        view = null
    }

    fun searchByArtist(query: String) {
        view?.showProgressDialog()
        repository.searchByArtist(query, object : RepositoryCallback<ApiResponse> {
            override fun onDataLoaded(response: ApiResponse) {
                view?.hideProgressDialog()
                view?.onLoadMusic(response)
            }

            override fun onDataError(status: Int?, error: String?) {
                view?.hideProgressDialog()
                view?.showError(status, error)
            }
        })
    }
}