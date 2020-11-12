package com.example.oompaloompascrew.ui.activity

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.oompaloompascrew.R
import com.example.oompaloompascrew.presenter.OLWorkersPresenter
import com.example.oompaloompascrew.response.OLWorkerDetailResponse
import com.example.oompaloompascrew.response.OLWorkersResponse
import com.example.oompaloompascrew.view.OLWorkersView
import com.facebook.drawee.view.SimpleDraweeView

class OLWorkerDetailsActivity : AppCompatActivity(), OLWorkersView {
    private var progressBar: ProgressBar? = null
    private var llErrorView: LinearLayout? = null
    private var tvMessage: TextView? = null
    private var tvName: TextView? = null
    private var tvDescription: TextView? = null
    private var tvProfessionAndAge: TextView? = null
    private var imageView: SimpleDraweeView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ol_activity_worker_details)
        bindViews()
        showProgressBar()
        createPresenterAndGetData()
    }

    private fun createPresenterAndGetData() {
        val olWorkersPresenter = OLWorkersPresenter(this)
        val olworkerId = intent.getIntExtra(WORKER_ID, 0)
        olWorkersPresenter.getOLWorkerDetails(olworkerId)
    }

    private fun bindViews() {
        tvName = findViewById(R.id.tvName)
        tvDescription = findViewById(R.id.tvDescription)
        tvProfessionAndAge = findViewById(R.id.tvProfession)
        imageView = findViewById(R.id.imageView)
        progressBar = findViewById(R.id.progressBar)
        llErrorView = findViewById(R.id.llErrorEmptyView)
        tvMessage = findViewById(R.id.tvMessage)
    }

    override fun showWorkerDetails(olWorker: OLWorkerDetailResponse?) {
        tvName!!.text = getOLWorkerFullname(olWorker!!)
        tvProfessionAndAge!!.text = getOlWorkerProfesionAndAge(olWorker)
        imageView!!.setImageURI(olWorker.image)
        tvDescription!!.text = Html.fromHtml(olWorker.description)
    }

    private fun getOlWorkerProfesionAndAge(olWorker: OLWorkerDetailResponse): String {
        return olWorker.profession + ", " + olWorker.age
    }

    private fun getOLWorkerFullname(olWorker: OLWorkerDetailResponse): String {
        return (olWorker.firstName + " "
                + olWorker.lastName)
    }

    override fun showProgressBar() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar!!.visibility = View.GONE
    }

    override fun showErrorView() {
        showErrorLayout(getString(R.string.error))
    }

    private fun showErrorLayout(message: String) {
        tvMessage!!.text = message
        llErrorView!!.visibility = View.VISIBLE
    }

    override fun showEmptyView() {
        //NOTHING TO DO
    }

    override fun hideErrorView() {
        //NOTHING TO DO
    }

    override fun showWorkersData(olWorkers: OLWorkersResponse?) {
        //NOTHING TO DO
    }

    companion object {
        private const val WORKER_ID = "olworker_id"
    }
}