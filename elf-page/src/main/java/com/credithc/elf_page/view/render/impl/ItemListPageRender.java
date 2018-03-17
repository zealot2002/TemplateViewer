package com.credithc.elf_page.view.render.impl;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.credithc.common.utils.ImageLoaderUtils;
import com.credithc.elf_page.R;
import com.credithc.elf_page.constants.PageTypeEnum;
import com.credithc.elf_page.model.Item;
import com.credithc.elf_page.model.Page;
import com.credithc.elf_page.model.pageElement.ItemListPageBody;
import com.credithc.elf_page.statis.StatisticsTool;
import com.credithc.elf_page.view.inner.EndLessOnScrollListener;
import com.credithc.elf_page.view.render.WaterfallPageRender;
import com.credithc.elf_page.view.template.TemplateHelper;
import com.credithc.elf_page.view.inner.MyMultiAdapter;
import com.credithc.elf_page.view.inner.PageEventListener;
import com.yalantis.taurus.PullToRefreshView;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;

import java.util.List;

/**
 * @author zzy
 * @date 2018/2/27
 */

public class ItemListPageRender implements WaterfallPageRender<Item> {
    //sectionList page
    private View rootView;
    private Context context;
    private PullToRefreshView pullToRefreshView;
    private RecyclerView recyclerView;
    private MyMultiAdapter adapter;
    private Page currentPage;
    private PageEventListener pageEventListener;
    private int pageNum;
    private EndLessOnScrollListener onScrollListener;

    /****************************************************************************************************/
    public ItemListPageRender(Context context) {
        this.context = context;
    }

    public void setPageEventListener(PageEventListener pageEventListener) {
        this.pageEventListener = pageEventListener;
    }

    @Override
    public void render(ViewGroup container, Page page) {
        if(rootView==null){
            rootView = View.inflate(context, R.layout.elf_page_content_item_list,null);
            container.addView(rootView);
        }
        if(pullToRefreshView!=null){
            pullToRefreshView.setRefreshing(false);
        }
        currentPage = page;
        findPageViews();
    }

    @Override
    public void appendUpdateData(List<Item> list) {
        if(list.isEmpty()){
            pageNum = -1;
            return;
        }
        currentPage.getBody().getDataList().addAll(list);
        /*统计*/
        StatisticsTool.sighElfPage(currentPage);
        adapter.setDataList(currentPage.getBody().getDataList());
        adapter.notifyDataSetChanged();
    }

    private void findPageViews() {
        if(recyclerView == null){
            int templateId = ((ItemListPageBody) currentPage.getBody()).getTemplateId();
            ItemViewDelegate delegate = TemplateHelper.makeSimpleTemplateDetegate(context,templateId);
            if(delegate==null){
                Toast.makeText(context, " delegate==null!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            recyclerView = rootView.findViewById(R.id.recyclerView);

            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            adapter = new MyMultiAdapter(context,null);
            adapter.addItemViewDelegate(delegate);
            recyclerView.setAdapter(adapter);

            pullToRefreshView = rootView.findViewById(R.id.pull_to_refresh);
//            pullToRefreshView.set
            pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    pullToRefreshView.setRefreshing(true);
                    if(pageEventListener!=null){
                        pageNum = 1;
                        onScrollListener.reset();
                        pageEventListener.onReload();
                    }
                }
            });

            /*load more*/
            onScrollListener = new EndLessOnScrollListener(layoutManager) {
                @Override
                public void onLoadMore() {
                    if(pageEventListener!=null){
                        if(pageNum!=-1){
                            pageEventListener.onLoadMore(++pageNum);
                        }
                    }
                }
            };
            recyclerView.addOnScrollListener(onScrollListener);
        }
        ImageLoaderUtils.getInstance().showImg(context,currentPage.getBackground(),recyclerView);
        /*统计*/
        StatisticsTool.sighElfPage(currentPage);
        adapter.setDataList(currentPage.getBody().getDataList());
        adapter.notifyDataSetChanged();
    }
}
