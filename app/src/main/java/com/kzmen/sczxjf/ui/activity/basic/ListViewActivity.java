package com.kzmen.sczxjf.ui.activity.basic;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

/**
 * 创建者：Administrator
 * 时间：2016/4/6
 * 功能描述：
 */
public abstract class ListViewActivity extends SuperActivity implements PullToRefreshBase.OnRefreshListener2,OnItemClickListener{
    private ListView listView;
    private PullToRefreshListView mPullRefreshListView;
    private BaseAdapter adapter;
    private ImageLoader imageLoader;

    public void setListView(ListView listView,BaseAdapter adapter) {
        this.listView = listView;
        this.adapter=adapter;
        this.listView.setAdapter(this.adapter);
    }

    public void setmPullRefreshListView(PullToRefreshListView mPullRefreshListView,BaseAdapter adapter) {
        this.mPullRefreshListView = mPullRefreshListView;
        this.adapter = adapter;
        setPullToRefreshListView();
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 设置{@link PullToRefreshListView}
     * 如果需要上拉增加，下啦刷新情调用此方法
     */
    public void setPullToRefreshListView(){
        mPullRefreshListView.getRefreshableView().setAdapter(adapter);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.getLoadingLayoutProxy().setRefreshingLabel("正在获取数据");
        mPullRefreshListView.getLoadingLayoutProxy().setPullLabel("数据更新");
        mPullRefreshListView.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
        mPullRefreshListView.setOnRefreshListener(this);
        mPullRefreshListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_FLING) {
                    mPullRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
                } else {
                    mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        mPullRefreshListView.getRefreshableView().setOnItemClickListener(this);
        //TODO 设置快速滑动不加载图片
        if(imageLoader!=null){
            mPullRefreshListView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
        }


    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    public  void onrequestDone() {
        if(null!=mPullRefreshListView) {
            mPullRefreshListView.onRefreshComplete();
        }
        adapter.notifyDataSetChanged();
        dismissProgressDialog();
    }

    /**
     * 设置PullToRefreshListView自动加载
     */
    public void setADD(){
        final Handler h=new Handler();
        ViewTreeObserver vto = mPullRefreshListView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("tag" + "开始加载");
                mPullRefreshListView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshListView.onRefreshComplete();
                        mPullRefreshListView.setRefreshing(true);
                    }
                }, 1000);

            }
        });
    }
}
