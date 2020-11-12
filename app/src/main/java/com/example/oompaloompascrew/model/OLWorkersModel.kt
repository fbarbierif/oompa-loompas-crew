package com.example.oompaloompascrew.model

import com.example.oompaloompascrew.response.OLWorkerDetailResponse
import com.example.oompaloompascrew.response.OLWorkersResponse
import com.example.oompaloompascrew.service.OLWorkersService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class OLWorkersModel {
    /**
     * Instance Retrofit and create service to communicate with API
     *
     * @return the service created.
     */
    private val service: OLWorkersService
        get() {
            val retrofitBuilder = Retrofit.Builder().baseUrl(BASE_URL)
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
            retrofitBuilder.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            return retrofitBuilder.build().create(OLWorkersService::class.java)
        }

    /**
     * Call the endpoint to retrieve the workers list
     *
     * @return the response containing the olWorkers
     */
    fun getOLWorkersData(page: String?): Observable<OLWorkersResponse?>? {
        return service.getOLWorkersData(page)
    }

    /**
     * Call the endpoint to retrieve the worker details
     *
     * @return the response containing the olWorker details
     */
    fun getOLWorkerDetails(id: Int): Observable<OLWorkerDetailResponse?>? {
        return service.getOLWorkerDetails(id)
    }

    companion object {
        /**
         * Factory method
         *
         * @return the model's singleton
         */
        val instance = OLWorkersModel()
        private const val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/"
    }
}