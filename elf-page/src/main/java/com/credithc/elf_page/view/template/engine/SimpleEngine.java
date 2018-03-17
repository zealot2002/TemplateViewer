package com.credithc.elf_page.view.template.engine;

import android.content.Context;
import android.view.View;

import com.credithc.elf_page.R;
import com.credithc.elf_page.constants.PageTypeEnum;
import com.credithc.elf_page.model.Item;
import com.credithc.elf_page.model.Section;
import com.credithc.elf_page.model.Widget;
import com.credithc.elf_page.view.template.TemplateHelper;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author zzy
 * @date 2018/2/11
 */

public class SimpleEngine implements ItemViewDelegate {
    private Context context;
    private int templateId,layoutId;

/*****************************************************************************************************/
    public SimpleEngine(Context context, int templateId, int layoutId) {
        this.context = context;
        this.templateId = templateId;
        this.layoutId = layoutId;
    }

    public SimpleEngine() {}
    @Override
    public int getItemViewLayoutId() {
        return layoutId;
    }

    @Override
    public boolean isForViewType(Object obj, int position) {
        if(obj instanceof Section){
            return ((Section)obj).getTemplateId()==templateId;
        }else if(obj instanceof Item){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void convert(ViewHolder holder, Object obj, int position) {
        if(obj instanceof Section){
            View rootView = holder.getView(R.id.rootView);
            List<Widget> widgets = ((Section)obj).getItemList().get(0).getWidgetList();
            TemplateHelper.fillView(context,widgets,rootView);
        }else if(obj instanceof Item){
            View rootView = holder.getView(R.id.rootView);
            List<Widget> widgets = ((Item)obj).getWidgetList();
            TemplateHelper.fillView(context,widgets,rootView);
        }
    }
}
