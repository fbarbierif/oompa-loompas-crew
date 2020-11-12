package com.example.oompaloompascrew.service

import com.example.oompaloompascrew.response.OLWorkerDetailResponse
import com.example.oompaloompascrew.response.OLWorkersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface OLWorkersService {
    /**
     * Method to GET the data from API
     *
     * @return the workers list
     */
    @GET(URL_WORKERS)
    fun getOLWorkersData(@Query("page") page: String?): Observable<OLWorkersResponse?>?

    /**
     * Method to GET the data from API
     *
     * @return the worker details
     */
    @GET(URL_WORKER_DETAILS)
    fun getOLWorkerDetails(@Path("id") id: Int): Observable<OLWorkerDetailResponse?>?

    companion object {
        const val URL_WORKERS = "/napptilus/oompa-loompas"
        const val URL_WORKER_DETAILS = "/napptilus/oompa-loompas/{id}"
    }
}