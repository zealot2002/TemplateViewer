package com.credithc.elf_page.model.pageElement;
import com.credithc.elf_page.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2018/2/9
 */

public class ItemListPageBody implements Body<Item> {
    private int templateId;
    private List<Item> dataList;
/**********************************************************************************************/
    public ItemListPageBody() {
        dataList = new ArrayList<>();
    }
    public int getTemplateId() {
        return templateId;
    }
    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }
    @Override
    public void setDataList(List<Item> dataList) {
        this.dataList = dataList;
    }
    @Override
    public List<Item> getDataList() {
        return dataList;
    }
}

