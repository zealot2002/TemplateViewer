package com.credithc.elf_page.model.pageElement;
import com.credithc.elf_page.model.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2018/2/9
 */

public class SectionListPageBody implements Body<Section> {
    private List<Section> dataList;

    public SectionListPageBody() {
        dataList = new ArrayList<>();
    }
    @Override
    public void setDataList(List<Section> dataList) {
        this.dataList = dataList;
    }
    @Override
    public List<Section> getDataList() {
        return dataList;
    }
}

