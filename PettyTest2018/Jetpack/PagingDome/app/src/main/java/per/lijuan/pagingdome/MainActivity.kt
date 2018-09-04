package per.lijuan.pagingdome

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import per.lijuan.pagingdome.adapter.StudentAdapter
import per.lijuan.pagingdome.paging.ServiceLocator
import per.lijuan.pagingdome.paging.Status

class MainActivity : AppCompatActivity() {
    private lateinit var selectViewModel: StudentViewModel
    private lateinit var mAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectViewModel=getViewModel()
        initAdapter()
        initSwipeToRefresh()
        selectViewModel.showDatas("")
    }

    private fun getViewModel(): StudentViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ServiceLocator.instance()
                        .getRepository()
                return StudentViewModel(application, repo) as T
            }
        })[StudentViewModel::class.java]
    }

    private fun initSwipeToRefresh() {
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW)
        swipeRefreshLayout.setOnRefreshListener {
            selectViewModel.refresh()
        }
        selectViewModel.refreshState.observe(this, Observer { resource->
            if (resource==null){
                return@Observer
            }
            when(resource.status){
                Status.LOADING->{
                    swipeRefreshLayout.isRefreshing=true
                }
                Status.SUCCESS->{
                    swipeRefreshLayout.isRefreshing=false
                }
                Status.ERROR->{
                    Toast.makeText(this,resource.message,Toast.LENGTH_LONG).show()
                    swipeRefreshLayout.isRefreshing=false
                }
            }
        })
    }

    private fun initAdapter() {
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayout.VERTICAL
        rv!!.layoutManager = mLayoutManager
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))//添加分割线

        mAdapter= StudentAdapter(this)
        rv.adapter = mAdapter

        selectViewModel.posts.observe(this, Observer((mAdapter::submitList)))
    }
}
