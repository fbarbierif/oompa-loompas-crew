package com.example.oompaloompascrew.ui.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.oompaloompascrew.view.OLWorkersView
import com.example.oompaloompascrew.presenter.OLWorkersPresenter
import com.example.oompaloompascrew.ui.adapter.OLWorkersAdapter
import androidx.recyclerview.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.LinearLayout
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.oompaloompascrew.dto.OLWorkerDTO
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import com.example.oompaloompascrew.R
import com.example.oompaloompascrew.ui.activity.OLWorkersListActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.oompaloompascrew.response.OLWorkersResponse
import com.example.oompaloompascrew.response.OLWorkerDetailResponse
import com.example.oompaloompascrew.ui.adapter.OLWorkersAdapter.OnItemClickedCallback
import android.content.Intent
import com.example.oompaloompascrew.ui.activity.OLWorkerDetailsActivity
import com.example.oompaloompascrew.utils.RealmUtils
import android.view.MenuInflater
import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuItem
import android.view.View
import java.util.ArrayList

class OLWorkersListActivity : AppCompatActivity(), OLWorkersView {
    private var olWorkersPresenter: OLWorkersPresenter? = null
    private var olWorkersAdapter: OLWorkersAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var llErrorEmptyView: LinearLayout? = null
    private var tvMessage: TextView? = null
    private var srLayout: SwipeRefreshLayout? = null
    private val olWorkers = ArrayList<OLWorkerDTO>()
    private var layoutManager: LinearLayoutManager? = null
    private var page = 0
    private var pastVisibleItems = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var isFiltered = false
    private var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ol_activity_workers_list)
        bindViews()
        setupRecyclerViewAndRefresh()
        showProgressBar()
        createPresenterAndGetData()
    }

    private fun createPresenterAndGetData() {
        olWorkersPresenter = OLWorkersPresenter(this)
        olWorkersPresenter!!.getOLWorkersData(FIRST_PAGE.toString())
    }

    private fun setupRecyclerViewAndRefresh() {
        recyclerView!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!isFiltered && dy > 0) {
                    visibleItemCount = layoutManager!!.childCount
                    totalItemCount = layoutManager!!.itemCount - 5
                    pastVisibleItems = layoutManager!!.findFirstVisibleItemPosition()
                    if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                        isLoading = true
                        olWorkersPresenter!!.getOLWorkersData(page.toString())
                    }
                }
            }
        })
        srLayout!!.setOnRefreshListener {
            olWorkers.clear()
            olWorkersPresenter!!.getOLWorkersData(FIRST_PAGE.toString())
        }
    }

    private fun bindViews() {
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.rvWorkers)
        llErrorEmptyView = findViewById(R.id.llErrorEmptyView)
        tvMessage = findViewById(R.id.tvMessage)
        srLayout = findViewById(R.id.swipeRefreshLt)
    }

    override fun showWorkersData(olWorkers: OLWorkersResponse?) {
        recyclerView!!.visibility = View.VISIBLE
        showOLWorkersList(olWorkers!!)
    }

    override fun showWorkerDetails(olWorker: OLWorkerDetailResponse?) {
        //NOTHING TO DO
    }

    private fun showOLWorkersList(olWorkersResponse: OLWorkersResponse) {
        olWorkers.addAll(olWorkersResponse.olWorkersList)
        page = olWorkersResponse.currentPage + 1
        if (olWorkersAdapter == null) {
            olWorkersAdapter = OLWorkersAdapter(olWorkers)
            olWorkersAdapter!!.setOnItemClickedCallback { idWorker: Int ->
                val intent = Intent(this, OLWorkerDetailsActivity::class.java)
                intent.putExtra(WORKER_ID, idWorker)
                startActivity(intent)
            }
            recyclerView!!.adapter = olWorkersAdapter
        } else {
            olWorkersAdapter!!.notifyDataSetChanged()
        }
        srLayout!!.isRefreshing = false
        isLoading = false
        RealmUtils.storeResults(olWorkersResponse.olWorkersList)
    }

    private fun restoreOLWorkersList() {
        olWorkers.clear()
        olWorkers.addAll(RealmUtils.restoreSearchFromDB())
        olWorkersAdapter!!.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar!!.visibility = View.GONE
    }

    override fun showErrorView() {
        showErrorEmptyLayout(getString(R.string.error))
    }

    override fun showEmptyView() {
        showErrorEmptyLayout(getString(R.string.empty))
    }

    override fun hideErrorView() {
        hideErrorLayout()
    }

    private fun hideErrorLayout() {
        llErrorEmptyView!!.visibility = View.GONE
    }

    private fun showErrorEmptyLayout(message: String) {
        recyclerView!!.visibility = View.GONE
        if (olWorkersAdapter != null) {
            olWorkersAdapter!!.notifyDataSetChanged()
        }
        srLayout!!.isRefreshing = false
        tvMessage!!.text = message
        llErrorEmptyView!!.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.ol_menu, menu)
        return true
    }

    @SuppressLint("NonConstantResourceId")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        olWorkers.clear()
        when (item.itemId) {
            R.id.menu_filter_f -> {
                isFiltered = true
                olWorkers.addAll(RealmUtils.restoreSearchFromDBByGender("F"))
            }
            R.id.menu_filter_m -> {
                isFiltered = true
                olWorkers.addAll(RealmUtils.restoreSearchFromDBByGender("M"))
            }
            R.id.menu_filter_all -> {
                isFiltered = false
                olWorkers.addAll(RealmUtils.restoreSearchFromDB())
            }
        }
        olWorkersAdapter!!.notifyDataSetChanged()
        layoutManager!!.scrollToPositionWithOffset(0, 0)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        RealmUtils.deleteFromRealmWithoutKey()
    }

    override fun onBackPressed() {
        if (isFiltered) {
            restoreOLWorkersList()
            layoutManager!!.scrollToPositionWithOffset(0, 0)
            isFiltered = false
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val WORKER_ID = "olworker_id"
        private const val FIRST_PAGE = 1
    }
}