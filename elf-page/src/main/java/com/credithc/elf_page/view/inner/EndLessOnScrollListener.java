package com.credithc.elf_page.view.inner;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


/**
 * @author zzy
 * @date 2018/2/12
 */
public abstract class EndLessOnScrollListener extends  RecyclerView.OnScrollListener{


    private static final String TAG = "EndLessOnScrollListener";
    //声明一个LinearLayoutManager
    private LinearLayoutManager mLinearLayoutManager;

    //当前页，从0开始    private int currentPage = 0;
    //已经加载出来的Item的数量
    private int totalItemCount;

    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;

    //在屏幕上可见的item数量
    private int visibleItemCount;

    //在屏幕可见的Item中的第一个
    private int firstVisibleItem;

    //是否正在上拉数据
    private boolean loading = true;

    public EndLessOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    public void reset(){
        previousTotal = 0;
        totalItemCount = 0;
        visibleItemCount = 0;
        firstVisibleItem = 0;
    }
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
//        if(loading){
            Log.e(TAG,"firstVisibleItem: " +firstVisibleItem);
            Log.e(TAG, "visibleItemCount:" + visibleItemCount);
            Log.e(TAG,"totalPageCount:" +totalItemCount);
            Log.e(TAG, "previousTotal:" + previousTotal);
            Log.e(TAG, "loading:" + loading);

            if(totalItemCount > previousTotal){
                //说明数据已经加载结束
                loading = false;
                previousTotal = totalItemCount;
            }
//        }
        //这里需要好好理解
        if (!loading && totalItemCount-visibleItemCount <= firstVisibleItem){
            Log.e(TAG,"to load more!: ");
            onLoadMore();
            loading = true;
        }
    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     * */
    public abstract void onLoadMore();
}

