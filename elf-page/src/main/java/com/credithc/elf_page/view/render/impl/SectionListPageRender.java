package com.credithc.elf_page.view.render.impl;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.credithc.common.utils.ImageLoaderUtils;
import com.credithc.elf_page.R;
import com.credithc.elf_page.model.Page;
import com.credithc.elf_page.model.Section;
import com.credithc.elf_page.statis.StatisticsTool;
import com.credithc.elf_page.view.render.WaterfallPageRender;
import com.credithc.elf_page.view.template.TemplateHelper;
import com.credithc.elf_page.view.inner.EndLessOnScrollListener;
import com.credithc.elf_page.view.inner.MyMultiAdapter;
import com.credithc.elf_page.view.inner.PageEventListener;
import com.credithc.elf_page.view.inner.SpaceItemDecoration;
import com.yalantis.taurus.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2018/2/27
 */
//sectionList page
public class SectionListPageRender implements WaterfallPageRender<Section> {
    private View rootView;
    private Context context;
    private PullToRefreshView pullToRefreshView;
    private RecyclerView recyclerView;
    private MyMultiAdapter adapter;
    private Page currentPage;
    private SpaceItemDecoration itemDecoration;
    private PageEventListener pageEventListener;
    private int pageNum = 1;
    private EndLessOnScrollListener onScrollListener;

    public SectionListPageRender(Context context) {
        this.context = context;
    }
    @Override
    public void setPageEventListener(PageEventListener pageEventListener) {
        this.pageEventListener = pageEventListener;
    }

    @Override
    public void render(ViewGroup container, Page page) {
        if(rootView==null){
            rootView = View.inflate(context, R.layout.elf_page_content_section_list,null);
            container.addView(rootView);
        }
        if(pullToRefreshView!=null){
            pullToRefreshView.setRefreshing(false);
        }
        currentPage = page;
        updateViews();
    }

    @Override
    public void appendUpdateData(List<Section> list) {
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

    private void updateViews() {
        try{
            if(recyclerView == null){
                recyclerView = rootView.findViewById(R.id.recyclerView);
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

            /*adapter*/
                adapter = new MyMultiAdapter(context,new ArrayList());
                TemplateHelper.addAllTemplateDetegate(context,adapter);
                recyclerView.setAdapter(adapter);
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
            /*reload*/
                pullToRefreshView = rootView.findViewById(R.id.pull_to_refresh);
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
            }
        /*below refresh every time*/
            ImageLoaderUtils.getInstance().showImg(context,currentPage.getBackground(),recyclerView);
            if(itemDecoration!=null){
                recyclerView.removeItemDecoration(itemDecoration);
            }
            itemDecoration = new SpaceItemDecoration(context,currentPage.getBody().getDataList());
            recyclerView.addItemDecoration(itemDecoration);

            /*统计*/
            StatisticsTool.sighElfPage(currentPage);
            adapter.setDataList(currentPage.getBody().getDataList());
            adapter.notifyDataSetChanged();
        }catch(Exception e){
            e.printStackTrace();
            Log.e("zzy",e.toString());
            Toast.makeText(context, "err: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
