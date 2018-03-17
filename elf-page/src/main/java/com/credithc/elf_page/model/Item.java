package com.credithc.elf_page.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2018/2/27
 */

public class Item {
    private List<Widget> widgetList;
    public Item(){
        widgetList = new ArrayList<>();
    }
    public List<Widget> getWidgetList() {
        return widgetList;
    }
}
