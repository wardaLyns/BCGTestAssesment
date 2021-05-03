package warda.test.assesment.feature.musicList

import android.annotation.SuppressLint
import android.os.Bundle
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_music_list.*
import warda.test.assesment.R
import warda.test.assesment.base.BaseActivity
import warda.test.assesment.feature.MusicPresenter
import warda.test.assesment.feature.MusicView
import warda.test.assesment.model.ApiResponse
import warda.test.assesment.remote.repository.Repository
import java.util.concurrent.TimeUnit

class MusicListActivity : BaseActivity(), MusicView {
    var presenter: MusicPresenter? = null
    var query: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_list)
        presenter = MusicPresenter(this, Repository())
        initView()
    }

    @SuppressLint("CheckResult")
    private fun initView() {
        RxTextView.textChanges(txtSearch)
            .debounce(1, TimeUnit.SECONDS)
            .map { charSequence -> charSequence.toString() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { query ->
                if (query.isEmpty() || query.length > 2) {
                    this.query = query
                    onRefreshList(query)
                }
            }
    }

    private fun onRefreshList(query: String) {
        presenter!!.searchByArtist(query)
    }

    override fun onLoadMusic(response : ApiResponse) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.unBind()
    }
}