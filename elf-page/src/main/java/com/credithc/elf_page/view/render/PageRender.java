package com.credithc.elf_page.view.render;

import android.view.ViewGroup;

import com.credithc.elf_page.model.Page;
import com.credithc.elf_page.view.inner.PageEventListener;


/**
 * @author zzy
 * @date 2018/2/27
 */

public interface PageRender {
    void render(ViewGroup container, Page page);
}
