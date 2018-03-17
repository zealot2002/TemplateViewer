package com.credithc.common.utils;

import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zzy
 * @date 2018/3/2
 */

public class HttpUtils {
    public static Map<String, String> getCommonHeader(){
        String token = "eyJwaG9uZSI6IjE4NzAxMTIxMTI1IiwiaHlyS2V5IjoiNTQ5NGY2NDQ2ZjU4ZGQ0ODA0YjVjYjYwNDEzMzQ5NTYiLCJjdXN0SWQiOiI2MDAwMDYwMDA4MDI3OTIyIiwic2Vzc2lvbklkIjoiNDNiOGFhYjRiMmRlNGVjYmFlYTJhNDNkYTI5YWM2Y2MiLCJleHAiOjE1MjIyMTM5MTcsInVzZXJJZCI6IjQzNjc2OCIsInBsYXRmb3JtIjoiYXBwIn0.QBPqZNWFYYKHZoDg1FVwcOW46QLAVSeQix8EL38t6oc";
        Map<String, String> headerMap = new LinkedHashMap<String, String>();
        headerMap.put("platform","app");
        headerMap.put("token",token);
        headerMap.put("device","android");
        headerMap.put("deviceVer","11111111111");
        headerMap.put("appName","hccf");
        headerMap.put("appVer","1.0");
        headerMap.put("appMarket","baidu");
        Log.e("zzy","header:"+headerMap.toString());
        return headerMap;
    }

    public static String getBody(int pageNum,String action) {
        StringBuilder sb = new StringBuilder("{\"apiName\": \"getAppPageData\",\n" +
                "        \"sysName\": \"cf\",\n" +
                "        \"data\": {");
        sb.append("\"pageNum\":"+pageNum+",");
        sb.append("\"pageCode\":\""+action+"\"");
        sb.append("}}");

        Log.e("zzy","body:"+sb.toString());
        return sb.toString();
    }
}
