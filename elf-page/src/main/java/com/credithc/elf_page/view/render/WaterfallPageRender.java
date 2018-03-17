package com.credithc.elf_page.view.render;

import com.credithc.elf_page.view.inner.PageEventListener;

import java.util.List;


/**
 * @author zzy
 * @date 2018/2/27
 */

public interface WaterfallPageRender<T> extends PageRender {
    void appendUpdateData(List<T> list);
    void setPageEventListener(PageEventListener pageEventListener);
}
