package warda.test.assesment.remote.repository

interface RepositoryCallback<T> {
    fun onDataLoaded(response: T)
    fun onDataError(status: Int?, error: String?)
}