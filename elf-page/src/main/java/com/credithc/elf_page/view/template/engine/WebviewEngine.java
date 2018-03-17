package com.credithc.elf_page.view.template.engine;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.credithc.common.constants.Constants;
import com.credithc.elf_page.constants.PageTypeEnum;
import com.credithc.elf_page.model.Item;
import com.credithc.elf_page.model.Section;
import com.credithc.webh5.JsAgentWebFragment;
import com.just.agentweb.AgentWebView;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * @author zzy
 * @date 2018/2/11
 */
public class WebviewEngine implements ItemViewDelegate {
    private Context context;
    private int templateId,layoutId;
    private AgentWebView webView;
    private String url;
/*****************************************************************************************************/
    public WebviewEngine(Context context, int templateId, int layoutId) {
        this.context = context;
        this.templateId = templateId;
        this.layoutId = layoutId;
    }

    public WebviewEngine() {}
    @Override
    public int getItemViewLayoutId() {
        return layoutId;
    }

    @Override
    public boolean isForViewType(Object obj, int position) {
        if(obj instanceof Section){
            return ((Section)obj).getTemplateId()==templateId;
        }else if(obj instanceof Item){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void convert(ViewHolder holder, Object obj, int position) {
//        webView = holder.getView(R.id.webView);
        if(obj instanceof Section){
            url = ((Section)obj).getItemList().get(0).getWidgetList().get(0).getUrl();
        }else if(obj instanceof Item){
            url = ((Item)obj).getWidgetList().get(0).getUrl();
        }
//        url = "file:///android_asset/demo.html";
//        webView.loadUrl(url);
//        webView.registerHandler("submitFromWeb", new BridgeHandler() {
//            @Override
//            public void handler(String data, CallBackFunction function) {
//                Toast.makeText(context, "收到消息！！！！！！！！！！！！！！！！！！！！！！！" + data, Toast.LENGTH_SHORT).show();
////                Log.e("zzy", "收到消息！！！！！！！！！！！！！！！！！！！！！！！" + data);
//                function.onCallBack("消息应答！！！！！！！！！！！！！！！！！！！！！！");
//            }
//        });

        Bundle bundle = new Bundle();
        bundle.putString(Constants.PARAM_KEY, url);
        FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        ft.add(com.credithc.common.R.id.container_framelayout, JsAgentWebFragment.getInstance(bundle));
        ft.commit();
    }
}
