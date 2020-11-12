package com.example.oompaloompascrew.presenter

import com.example.oompaloompascrew.model.OLWorkersModel
import com.example.oompaloompascrew.response.OLWorkerDetailResponse
import com.example.oompaloompascrew.response.OLWorkersResponse
import com.example.oompaloompascrew.view.OLWorkersView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class OLWorkersPresenter
/**
 * Presenter constructor
 *
 * @param view the view interface to communicate with the activity
 */(private val olWorkersView: OLWorkersView) {
    /**
     * Begins de API call to get the olWorkers data
     *
     * @param page the number of the page to request
     */
    fun getOLWorkersData(page: String?) {
        val observable = OLWorkersModel.instance.getOLWorkersData(page)
        observable!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<OLWorkersResponse?>() {
                    override fun onCompleted() {
                        //Nothing to do
                    }

                    override fun onError(e: Throwable) {
                        olWorkersView.hideProgressBar()
                        olWorkersView.showErrorView()
                    }

                    override fun onNext(result: OLWorkersResponse?) {
                        olWorkersView.hideProgressBar()
                        olWorkersView.hideErrorView()
                        if (result == null || result.olWorkersList.isEmpty()) {
                            olWorkersView.showEmptyView()
                        } else {
                            olWorkersView.showWorkersData(result)
                        }
                    }
                })
    }

    /**
     * Begins de API call to get the olWorker details
     *
     * @param id the id of the worker to retrieve
     */
    fun getOLWorkerDetails(id: Int) {
        val observable = OLWorkersModel.instance.getOLWorkerDetails(id)
        observable!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<OLWorkerDetailResponse?>() {
                    override fun onCompleted() {
                        //Nothing to do
                    }

                    override fun onError(e: Throwable) {
                        olWorkersView.hideProgressBar()
                        olWorkersView.showErrorView()
                    }

                    override fun onNext(result: OLWorkerDetailResponse?) {
                        olWorkersView.hideProgressBar()
                        olWorkersView.showWorkerDetails(result)
                    }
                })
    }
}