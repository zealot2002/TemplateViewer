package com.credithc.elf_page.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.credithc.common.base.BaseLoadingFragment;
import com.credithc.common.constants.BusConstants;
import com.credithc.elf_page.R;
import com.credithc.elf_page.constact.PageConstact;
import com.credithc.elf_page.constants.PageTypeEnum;
import com.credithc.elf_page.model.Page;
import com.credithc.elf_page.presenter.PagePresenter;
import com.credithc.elf_page.statis.StatisticsTool;
import com.credithc.elf_page.view.inner.PageEventListener;
import com.credithc.elf_page.view.render.PageRender;
import com.credithc.elf_page.view.render.impl.ItemListPageRender;
import com.credithc.elf_page.view.render.impl.RenderGroupTop1PageRender;
import com.credithc.elf_page.view.render.impl.SectionListPageRender;
import com.credithc.elf_page.view.render.WaterfallPageRender;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.zzy.commonlib.core.BusHelper;

/**
 * @author zzy
 * @date 2018/2/28
 */
public class ElfFragment extends BaseLoadingFragment implements PageConstact.View{
    private View rootView;
    private Context context;
    private PagePresenter presenter;
    private String pageName;
    private boolean needReload;
    private ViewGroup container;
    //page pageRender delegate
    private PageRender pageRender;
/********************************************************************************************************/
    public ElfFragment(){}

    @SuppressLint("ValidFragment")
    public ElfFragment(String pageName){
        this.pageName = pageName;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater,container,savedInstanceState);
        context = getActivity();
        setContentViewLayoutId(R.layout.elf_fragment_page);

        presenter = new PagePresenter(this);
        presenter.getPageData(pageName,true,1);

        BusHelper.getBus().register(this);

        return rootView;
    }

    @Override
    public void reload() {
        super.reload();
        presenter.getPageData(pageName,true,1);
    }

    @Override
    public void updatePage(Page page,int pageNum) {
        super.updateUI(null);
        if(container == null){
            container = rootView.findViewById(R.id.container);
            if(page.getType().equals(PageTypeEnum.PAGE_TYPE_SECTION_LIST.value())){
                if(pageRender == null){
                    pageRender = new SectionListPageRender(context);
                    ((WaterfallPageRender) pageRender).setPageEventListener(new PageEventListener() {
                        @Override
                        public void onReload() {
                            presenter.getPageData(pageName,false,1);
                        }
                        @Override
                        public void onLoadMore(int pageNum) {
                            presenter.getPageData(pageName,true,pageNum);
                        }
                    });
                }
            }else if(page.getType().equals(PageTypeEnum.PAGE_TYPE_PAGE_GROUP.value())){
                // TODO: 2018/2/28  RenderGroupDelegate need match the navigatorType
                if(pageRender ==null){
                    pageRender = new RenderGroupTop1PageRender(context,ElfFragment.this);
                }
            }else if(page.getType().equals(PageTypeEnum.PAGE_TYPE_ITEM_LIST.value())){
                if(pageRender ==null){
                    pageRender = new ItemListPageRender(context);
                    ((WaterfallPageRender) pageRender).setPageEventListener(new PageEventListener() {
                        @Override
                        public void onReload() {
                            presenter.getPageData(pageName,false,1);
                        }
                        @Override
                        public void onLoadMore(int pageNum) {
                            presenter.getPageData(pageName,true,pageNum);
                        }
                    });
                }
            }
        }
        if(pageNum == 1){
            pageRender.render(container,page);
        }else{
            ((WaterfallPageRender) pageRender).appendUpdateData(page.getBody().getDataList());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(needReload){
            reload();
        }
        StatisticsTool.onResumeEvent(pageName);
    }

    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag(value = BusConstants.BUS_EVENT_LOGIN_SUCCESS)})
    public void onLoanSuccessEventReceived(String s){
        needReload = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusHelper.getBus().unregister(this);
    }
}
