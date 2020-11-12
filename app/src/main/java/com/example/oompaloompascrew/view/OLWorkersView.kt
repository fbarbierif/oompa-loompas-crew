package com.example.oompaloompascrew.view

import com.example.oompaloompascrew.response.OLWorkerDetailResponse
import com.example.oompaloompascrew.response.OLWorkersResponse

interface OLWorkersView {
    /**
     * Order and display the data received from API on main screen
     *
     * @param olWorkers The olWorkers to order and display
     */
    fun showWorkersData(olWorkers: OLWorkersResponse?)

    /**
     * Order and display the data received from API on main screen
     *
     * @param olWorker The olWorker details to order and display
     */
    fun showWorkerDetails(olWorker: OLWorkerDetailResponse?)

    /**
     * Show the progress bar on main screen
     */
    fun showProgressBar()

    /**
     * Hide the progress bar on main screen
     */
    fun hideProgressBar()

    /**
     * Show the error view on main screen when api call fails
     */
    fun showErrorView()

    /**
     * Show the empty view on main screen when api call returns empty results
     */
    fun showEmptyView()

    /**
     * Hide the error view
     */
    fun hideErrorView()
}