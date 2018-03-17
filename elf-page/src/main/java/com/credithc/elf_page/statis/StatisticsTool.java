package com.credithc.elf_page.statis;

import android.util.Log;

import com.credithc.elf_page.constants.PageTypeEnum;
import com.credithc.elf_page.model.Item;
import com.credithc.elf_page.model.Page;
import com.credithc.elf_page.model.Section;
import com.credithc.elf_page.model.Widget;

import java.util.List;

/**
 * @author zzy
 * @date 2018/3/10
 */

public class StatisticsTool {
    public static void onClickEvent(String statisInfo){
        Log.e("StatisticsTool","onClickEvent:"+statisInfo);
    }
    public static void onResumeEvent(String pageName){
        Log.e("StatisticsTool","onResumeEvent:"+pageName);
    }

    public static void sighElfPage(Page page){
        String pageType = page.getType();
        if(pageType.equals(PageTypeEnum.PAGE_TYPE_SECTION_LIST.value())){
            sighSectionList(page.getBody().getDataList(),page.getCode());
        }else if(pageType.equals(PageTypeEnum.PAGE_TYPE_ITEM_LIST.value())){
            sighItemList(page.getBody().getDataList(),page.getCode());
        }else{

        }
    }





    private static void sighSectionList(List<Section> dataList,String pageName){
        for(int i=0;i<dataList.size();++i){
            for(int j=0;j<dataList.get(i).getItemList().size();++j){
                for(Widget w:dataList.get(i).getItemList().get(j).getWidgetList()){
                    if(w.getStatisInfo().getPageName()!=null){
                        continue;
                    }
                    w.getStatisInfo().setPageName(pageName);
                    w.getStatisInfo().setSectionId(i+1);
                    w.getStatisInfo().setItemId(j+1);
                    w.getStatisInfo().setWidgetId(w.getId());
                }
            }
        }
    }
    private static void sighItemList(List<Item> dataList,String pageName){
        for(int j=0;j<dataList.size();++j){
            for(Widget w:dataList.get(j).getWidgetList()){
                if(w.getStatisInfo().getPageName()!=null){
                    continue;
                }
                w.getStatisInfo().setPageName(pageName);
                w.getStatisInfo().setSectionId(1);  /*只有1个section*/
                w.getStatisInfo().setItemId(j+1);
                w.getStatisInfo().setWidgetId(w.getId());
            }
        }
//        Log.e("zzy","sighItemList time:"+((time2-time1)));
    }
}
