package com.credithc.elf_page.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.credithc.common.constants.Constants;
import com.credithc.common.constants.RouterConstants;
import com.zzy.commonlib.core.BusHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zzy
 * @date 2018/3/10
 */

public class RouteTool {
    public static final HashMap<String,String> pageMap = new HashMap<String,String>() {{
        put("elfPage", RouterConstants.ROUTER_ELF);
        put("login",RouterConstants.ROUTER_LOGIN);
        put("webview",RouterConstants.ROUTER_WEBVIEW);
    }};

    public static void handleRoute(Context context,String str) throws Exception {
        Log.e("zzy","handleRoute:"+str);
        RouteBean routeBean = parseRoute(str);
        if(routeBean.getParams().isEmpty()){
            return;
        }
        String intent = routeBean.getParams().get(0);
        if(intent.equals("openPage")){
            if(pageMap.containsKey(routeBean.getParams().get(1))){
                String target = pageMap.get(routeBean.getParams().get(1));
                String param = "";
                if(routeBean.getParams().size()>2){
                    param = routeBean.getParams().get(2);
                }
                ARouter.getInstance().build(target)
                        .withString(Constants.PARAM_KEY,param).navigation();
            }
        }else if(intent.equals("closeMe")){
            ((Activity)context).finish();
        }
    }

    private static RouteBean parseRoute(String json) throws JSONException{
        JSONTokener jsonParser = new JSONTokener(json);
        JSONArray paramArray = (JSONArray) jsonParser.nextValue();

        RouteBean routeBean = new RouteBean();
        for (int i = 0; i < paramArray.length(); i++) {
            routeBean.getParams().add(paramArray.get(i).toString());
        }
        return routeBean;
    }

    static final class RouteBean{
        List<String> params;

        public RouteBean() {
            params = new ArrayList<>();
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }
    }
}
