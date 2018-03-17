package com.credithc.elf_page.model.pageElement;
import com.credithc.elf_page.model.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2018/2/9
 */

public class PageGroupBody implements Body<Page> {
    private List<Page> dataList;
    public PageGroupBody() {
        dataList = new ArrayList<>();
    }
    /**********************************************************************************************/
    @Override
    public void setDataList(List<Page> dataList) {
        this.dataList = dataList;
    }
    @Override
    public List<Page> getDataList() {
        return dataList;
    }
}

