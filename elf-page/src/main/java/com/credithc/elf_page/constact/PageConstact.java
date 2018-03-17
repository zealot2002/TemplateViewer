package com.credithc.elf_page.constact;

import com.credithc.elf_page.model.Item;
import com.credithc.elf_page.model.Page;
import com.credithc.elf_page.model.Section;
import com.zzy.commonlib.base.BaseLoadingView;
import com.zzy.commonlib.base.BasePresenter;

import java.util.List;


/**
 * @author zzy
 * @date 2018/2/27
 */

public interface PageConstact {
    interface View extends BaseLoadingView {
        void updatePage(Page page,int pageNum);
    }

    interface Presenter extends BasePresenter {
        void getPageData(final String pageName, boolean bShow,int pageNum);
    }
}
