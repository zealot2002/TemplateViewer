package com.credithc.elf_page.model.pageElement;

import java.util.List;

/**
 * @author zzy
 * @date 2018/2/27
 */

public interface Body<T> extends Element{
    List<T> getDataList();
    void setDataList(List<T> dataList);
}
