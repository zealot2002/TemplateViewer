package com.credithc.elf_page.view.template.engine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.credithc.elf_page.R;
import com.credithc.elf_page.constants.PageTypeEnum;
import com.credithc.elf_page.model.Item;
import com.credithc.elf_page.model.Section;
import com.credithc.elf_page.model.Widget;
import com.credithc.elf_page.view.template.TemplateHelper;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author zzy
 * @date 2018/2/11
 */

public class RecyclerViewEngine implements ItemViewDelegate {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private Context context;
    private int layoutId,itemLayoutId,templateId;
    private RecyclerView.LayoutManager layoutManager;
/*****************************************************************************************************/
    public RecyclerViewEngine(Context context,
                              int templateId,
                              int layoutId,
                              int itemLayoutId,
                              RecyclerView.LayoutManager layoutManager
                                        ) {
        this.context = context;
        this.templateId = templateId;
        this.layoutId = layoutId;
        this.itemLayoutId = itemLayoutId;
        this.layoutManager = layoutManager;
    }

    public RecyclerViewEngine() {}
    @Override
    public int getItemViewLayoutId() {
        return layoutId;
    }

    @Override
    public boolean isForViewType(Object obj,int position) {
        if(obj instanceof Section){
            return ((Section)obj).getTemplateId()==templateId;
        }else{
            return false;
        }
    }

    @Override
    public void convert(ViewHolder holder,Object obj, int position) {
        if(obj instanceof Section){
            recyclerView = holder.getView(R.id.recyclerView);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new MyAdapter(context,itemLayoutId,((Section)obj).getItemList());
            recyclerView.setAdapter(adapter);
            recyclerView.setFocusable(false);
        }
    }

    public class MyAdapter extends CommonAdapter<Item> {
        public MyAdapter(Context context, int layoutId, List<Item> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Item item, int position) {
            View rootView = holder.getView(R.id.rootView);
            List<Widget> widgets = item.getWidgetList();
            TemplateHelper.fillView(context,widgets,rootView);
        }
    }
}
