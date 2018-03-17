package com.credithc.elf_page.view.template;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.credithc.common.utils.ImageLoaderUtils;
import com.credithc.elf_page.R;
import com.credithc.elf_page.model.Widget;
import com.credithc.elf_page.utils.RouteTool;
import com.credithc.elf_page.statis.StatisticsTool;
import com.credithc.elf_page.view.inner.MyMultiAdapter;
import com.credithc.elf_page.view.template.engine.BannerEngine;
import com.credithc.elf_page.view.template.engine.RecyclerViewEngine;
import com.credithc.elf_page.view.template.engine.SimpleEngine;
import com.credithc.elf_page.view.template.engine.Engine3;
import com.credithc.elf_page.view.template.engine.WebviewEngine;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zzy
 * @date 2018/2/26
 */

public class TemplateHelper {

    private static final int RECYCLER_HORIZONTAL = 1;
    private static final int RECYCLER_VERTICAL = 2;
    private static final int RECYCLER_GRID = 3;

    public static final HashMap<Integer,Integer> simpleTemplateMap = new HashMap<Integer,Integer>() {{
        put(1,R.layout.elf_template1);
        put(5,R.layout.elf_template5);
        put(8,R.layout.elf_template8);
        put(10,R.layout.elf_template10);
        put(13,R.layout.elf_template6_item);
        put(14,R.layout.elf_template14);
    }};
    public static final HashMap<Integer,Integer[]> recyclerViewTemplateMap = new HashMap<Integer,Integer[]>() {{
        put(2,new Integer[]{R.layout.elf_template2,R.layout.elf_template2_item,RECYCLER_HORIZONTAL});
        put(6,new Integer[]{R.layout.elf_template6,R.layout.elf_template6_item,RECYCLER_VERTICAL});
        put(11,new Integer[]{R.layout.elf_template11,R.layout.elf_template11_item,RECYCLER_VERTICAL});
        put(9,new Integer[]{R.layout.elf_template9,R.layout.elf_template9_item,RECYCLER_GRID,3});
    }};
    public static final HashMap<Integer,Integer> bannerTemplateMap = new HashMap<Integer,Integer>() {{
        put(4,R.layout.elf_template4);
        put(7,R.layout.elf_template7);
    }};
    public static final HashMap<Integer,Integer> webviewTemplateMap = new HashMap<Integer,Integer>() {{
        put(12,R.layout.elf_template12);
    }};

//    public static final HashMap<Integer,String> specialTemplateMap = new HashMap<Integer,String>() {{
//        put(3,Engine3.class.getName());
//    }};

/**************************************************************************************************************/
    public static void fillView(final Context context, final List<Widget> widgets, final View rootView){
        try{
            for(final Widget w:widgets){
                int viewId = 0;
                if(w.getId().equals("w0")){
                    viewId = R.id.w0;
                }else if(w.getId().equals("w1")){
                    viewId = R.id.w1;
                } else if(w.getId().equals("w2")){
                    viewId = R.id.w2;
                }else if(w.getId().equals("w3")){
                    viewId = R.id.w3;
                }else if(w.getId().equals("w4")){
                    viewId = R.id.w4;
                }else if(w.getId().equals("w5")){
                    viewId = R.id.w5;
                }else if(w.getId().equals("w6")){
                    viewId = R.id.w6;
                }else if(w.getId().equals("w7")){
                    viewId = R.id.w7;
                }else if(w.getId().equals("w8")){
                    viewId = R.id.w8;
                }else if(w.getId().equals("w9")){
                    viewId = R.id.w9;
                }else if(w.getId().equals("w10")){
                    viewId = R.id.w10;
                }else if(w.getId().equals("w11")){
                    viewId = R.id.w11;
                }else if(w.getId().equals("w12")){
                    viewId = R.id.w12;
                }
                final View view = rootView.findViewById(viewId);
                if(view == null){
                    /*接口错误：模板没有此id，此处不作处理*/
                    Log.e("zzy","接口错误：模板没有此id，此处不作处理 w.getId():"+w.getId());
                    continue;
                }
                if(w.getVisible().equals("1")){
                    view.setVisibility(View.VISIBLE);
                }else{
                    view.setVisibility(View.GONE);
                    continue;
                }
                if(view instanceof TextView){
                    ((TextView) view).setText(w.getText());
                    if(!w.getTextColor().isEmpty()){
//                            Log.e("zzy","color:"+w.getTextColor());
                        ((TextView) view).setTextColor(Color.parseColor(w.getTextColor()));
                    }
                }else if(view instanceof ImageView){
                    if(!w.getImage().isEmpty()){
                        ImageLoaderUtils.getInstance().showImg(context,w.getImage(), (ImageView) view);
                    }
                }else if(view instanceof RelativeLayout){
                    if(!w.getImage().isEmpty()){
                        ImageLoaderUtils.getInstance().showImg(context,w.getImage(), view);
                    }
                }

                /*set view clickable*/
                if(w.getRoute()!=null&&!w.getRoute().equals("[]")){
                    view.setClickable(true);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Toast.makeText(context,"route:"+w.getRoute(),Toast.LENGTH_LONG).show();
                                StatisticsTool.onClickEvent(w.getStatisInfo().toString());
                                RouteTool.handleRoute(context,w.getRoute());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(context,"err:"+e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.e("zzy","err:"+e.toString());
            Toast.makeText(context,"err:"+e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public static void addAllTemplateDetegate(Context context, MyMultiAdapter adapter) throws Exception{
        /*SimpleEngine*/
        Iterator simpleIt = simpleTemplateMap.entrySet().iterator();
        while (simpleIt.hasNext()) {
            Map.Entry entry = (Map.Entry) simpleIt.next();
            adapter.addItemViewDelegate(new SimpleEngine(context,(int)entry.getKey(),(int)entry.getValue()));
        }
        /*RecyclerViewEngine*/
        Iterator recyclerIt = recyclerViewTemplateMap.entrySet().iterator();
        while (recyclerIt.hasNext()) {
            Map.Entry entry = (Map.Entry) recyclerIt.next();
            Integer[] params = (Integer[]) entry.getValue();
            RecyclerView.LayoutManager layoutManager = null;
            if(params[2] == RECYCLER_HORIZONTAL){
                layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            }else if(params[2] == RECYCLER_VERTICAL){
                layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            }else if(params[2] == RECYCLER_GRID){
                layoutManager = new GridLayoutManager(context,params[3]);
            }
            adapter.addItemViewDelegate(
                new RecyclerViewEngine(context,(int)entry.getKey(),params[0],params[1],layoutManager));
        }

        /*BannerEngine*/
        Iterator bannerIt = bannerTemplateMap.entrySet().iterator();
        while (bannerIt.hasNext()) {
            Map.Entry entry = (Map.Entry) bannerIt.next();
            adapter.addItemViewDelegate(new BannerEngine(context,(int)entry.getKey(),(int)entry.getValue()));
        }

        /*WebviewEngine*/
        Iterator webIt = webviewTemplateMap.entrySet().iterator();
        while (webIt.hasNext()) {
            Map.Entry entry = (Map.Entry) webIt.next();
            adapter.addItemViewDelegate(new WebviewEngine(context,(int)entry.getKey(),(int)entry.getValue()));
        }

        // TODO: 2018/3/9 需要封装viewpaper类型
        adapter.addItemViewDelegate(new Engine3(context));
    }

    /*注：对于itemList类型的page，只能使用simple，其他类型禁止使用*/
    public static ItemViewDelegate makeSimpleTemplateDetegate(Context context, int templateId){
        if(simpleTemplateMap.containsKey(templateId)){
            return new SimpleEngine(context,templateId,simpleTemplateMap.get(templateId));
        }
        return null;
    }

    public static boolean isValidTemplateId(int templateId){
        return (simpleTemplateMap.containsKey(templateId)
                ||recyclerViewTemplateMap.containsKey(templateId)
                ||bannerTemplateMap.containsKey(templateId)
                ||webviewTemplateMap.containsKey(templateId)
                ||templateId==3
                );
    }

    public static boolean isSimpleTemplateId(int templateId){
        return simpleTemplateMap.containsKey(templateId);
    }

}
