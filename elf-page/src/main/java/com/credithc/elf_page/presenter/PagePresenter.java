package com.credithc.elf_page.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.credithc.common.base.BaseApplication;
import com.credithc.common.constants.Constants;
import com.credithc.elf_page.constact.PageConstact;
import com.credithc.elf_page.model.DataProvider;
import com.credithc.elf_page.model.Page;
import com.zzy.commonlib.http.HInterface;
import com.zzy.commonlib.util.NetUtils;


/**
 * @author zzy
 * @Description:
 * @date 2017/12/06 15:06:30
 */

public class PagePresenter implements PageConstact.Presenter {
    private final PageConstact.View view;

/****************************************************************************************************/
    public PagePresenter(@NonNull PageConstact.View view) {
        this.view = view;
    }
    @Override
    public void start() {
    }

    @Override
    public void getPageData(String pageCode, boolean bShow, int pageNum) {
        if (!NetUtils.isNetworkAvailable(BaseApplication.getInstance())) {
            //do nothing..just go on
            view.showDisconnect();
            return;
        }
        if(bShow){view.showLoading();}
        DataProvider.getPageData(BaseApplication.getInstance(), pageCode, pageNum,new HInterface.DataCallback() {
            @Override
            public void requestCallback(int result, Object o, Object o1) {
                view.closeLoading();
                if (result == Constants.SUCCESS) {
                    try{
                        Page page = (Page) o;
                        Object[] objects = (Object[]) o1;
                        String code = (String) objects[0];
                        int pageNum = (int) objects[1];
                        page.setCode(code);
                        view.updatePage(page,pageNum);
                    }catch(Exception e1){
                        e1.printStackTrace();
                        Toast.makeText(BaseApplication.getInstance()," err :"+e1.toString(),Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(BaseApplication.getInstance()," err:"+o.toString(),Toast.LENGTH_LONG).show();
                    view.showDisconnect();
                }
            }
        });
    }
}