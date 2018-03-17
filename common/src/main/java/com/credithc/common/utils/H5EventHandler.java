package com.credithc.common.utils;

import android.app.Activity;
import android.content.Context;
import com.credithc.common.constants.H5Constant;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * @author zzy
 * @date 2018/3/16
 */

public class H5EventHandler {
    public static void handle(Context context, String json) throws Exception{
        JSONTokener jsonParser = new JSONTokener(json);
        JSONObject obj = (JSONObject) jsonParser.nextValue();
        String action = obj.getString("action");
        JSONObject dataObj = null;
        if (obj.has("data")) {
            dataObj = obj.getJSONObject("data");
        }
        dispatchH5Event(context,Integer.valueOf(action), dataObj);
    }

    private static void dispatchH5Event(Context context,Integer action, JSONObject dataObj) {
        if(action == H5Constant.ActionEnum.CLOSE_SELF_WINDOW.value()){ //关闭当前页面
            ((Activity)context).finish();
        }
    }
}
