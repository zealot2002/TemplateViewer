package com.credithc.elf_page.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2018/2/11
 */

public class Section {
    private int templateId;
    private int marginTop;
    private String background;
    private List<Item> itemList;

/***********************************************************************************************/
    public Section() {
        itemList = new ArrayList<>();
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

}
