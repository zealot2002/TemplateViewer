package com.credithc.elf_page.model;
import android.content.Context;

import com.credithc.common.constants.Constants;
import com.credithc.common.utils.HttpUtils;
import com.credithc.elf_page.constants.PageTypeEnum;
import com.credithc.elf_page.model.pageElement.Body;
import com.credithc.elf_page.model.pageElement.ItemListPageBody;
import com.credithc.elf_page.model.pageElement.PageGroupBody;
import com.credithc.elf_page.model.pageElement.SectionListPageBody;
import com.credithc.elf_page.view.template.TemplateHelper;
import com.zzy.commonlib.http.HInterface;
import com.zzy.commonlib.http.HProxy;
import com.zzy.commonlib.http.RequestCtx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zzy.commonlib.http.HConstant.HTTP_METHOD_POST;

/**
 * @author zzy
 * @date 2018/2/27
 */
public class DataProvider {
    public static void getPageData(Context context, String pageCode, int pageNum, final HInterface.DataCallback callback){
        Map<String, String> headerMap = HttpUtils.getCommonHeader();
        String body = HttpUtils.getBody(pageNum,pageCode);

        RequestCtx ctx = new RequestCtx.Builder(Constants.SERVER_URL)
                .method(HTTP_METHOD_POST)
                .headerMap(headerMap)
                .body(body)
                .contentType("text/plain")
                .tagObj(new Object[]{pageCode,pageNum})
                .callback(callback)
                .jsonParser(getPageDataJsonParser)
                .timerout(10*1000)
                .build();
        try {
            HProxy.getInstance().request(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static HInterface.JsonParser getPageDataJsonParser = new HInterface.JsonParser() {
        @Override
        public Object[] parse(String str) throws JSONException {
            JSONTokener jsonParser = new JSONTokener(str);
            JSONObject obj = (JSONObject) jsonParser.nextValue();
            int errorCode = obj.getInt(Constants.ERROR_CODE);
            if (errorCode == Constants.NO_ERROR) {
                Page page = new Page();
                JSONObject dataObj = obj.getJSONObject("data").getJSONObject("pageData");
                if (!dataObj.has("pageType")) {
                    throw new JSONException("no pageType!");
                }
                page.setType(dataObj.getString("pageType"));
                //fill body
                Body body;
                if (page.getType().equals(PageTypeEnum.PAGE_TYPE_SECTION_LIST.value())) {
                     /*sectionList*/
                    if(!dataObj.has("sectionList")){
                        throw new JSONException("pageType is 'sectionList' but no sectionList body!");
                    }
                    body = new SectionListPageBody();
                    List<Section> sectionList = parseSectionList(dataObj);
                    body.setDataList(sectionList);
                } else if (page.getType().equals(PageTypeEnum.PAGE_TYPE_PAGE_GROUP.value())) {
                    /*pageGroup*/
                    if (!dataObj.has("pageList")) {
                        throw new JSONException("pageType is 'pageGroup' but no pageList body!");
                    }
                    body = new PageGroupBody();
                    JSONArray pageArray = dataObj.getJSONArray("pageList");
                    for (int i = 0; i < pageArray.length(); i++) {
                        JSONObject pageObj = pageArray.getJSONObject(i);
                        Page p = new Page();
                        p.setName(pageObj.getString("name"));
                        p.setCode(pageObj.getString("action"));
                        body.getDataList().add(p);
                    }
                }else if (page.getType().equals(PageTypeEnum.PAGE_TYPE_ITEM_LIST.value())) {
                    /*itemList*/
                    if (!dataObj.has("itemList")) {
                        throw new JSONException("pageType is 'itemList' but no itemList body!");
                    }
                    body = new ItemListPageBody();
                    List<Item> itemList = parseItemList(dataObj);
                    body.setDataList(itemList);

                    if (!dataObj.has("templateId")) {
                        throw new JSONException("pageType is 'itemList' but no templateId body!");
                    }
                    int templateId = dataObj.getInt("templateId");
                    if(!TemplateHelper.isSimpleTemplateId(templateId)){
                        throw new JSONException("pageType PAGE_TYPE_ITEM_LIST must have a simple templateId!");
                    }
                    ((ItemListPageBody) body).setTemplateId(templateId);
                }else{
                    throw new JSONException("unknown pageType!");
                }
                page.setBody(body);
                //fill others
                if (dataObj.has("background")) {
                    page.setBackground(dataObj.getString("background"));
                }
                return new Object[]{Constants.SUCCESS, page};
            } else {
                String msg = obj.getString(Constants.ERROR_MESSAGE);
                return new Object[]{Constants.FAIL, msg};
            }
        }
    };
    private static List<Section> parseSectionList(JSONObject dataObj) throws JSONException{
        List<Section> sectionList = new ArrayList<>();
        JSONArray sectionArray = dataObj.getJSONArray("sectionList");
        for (int i = 0; i < sectionArray.length(); i++) {
            JSONObject sectionObj = sectionArray.getJSONObject(i);
            Section section = new Section();
            if (!sectionObj.has("templateId")) {
                throw new JSONException("A section must have a templateId!");
            }
            /*如果templateId客户端不支持，继续下一条*/
            int templateId = sectionObj.getInt("templateId");
            if(!TemplateHelper.isValidTemplateId(templateId)){
                continue;
            }
            section.setTemplateId(templateId);

            if (sectionObj.has("background")) {
                section.setBackground(sectionObj.getString("background"));
            }
            if (sectionObj.has("marginTop")) {
                section.setMarginTop(sectionObj.getInt("marginTop"));
            }
            /*itemList*/
            List<Item> itemList = parseItemList(sectionObj);
            section.setItemList(itemList);
            sectionList.add(section);
        }
        return sectionList;
    }
    private static List<Item> parseItemList(JSONObject obj) throws JSONException{
        List<Item> itemList = new ArrayList<>();
        /*itemList*/
        JSONArray contentArray = obj.getJSONArray("itemList");
        for (int j = 0; j < contentArray.length(); j++) {
            JSONObject contentObj = contentArray.getJSONObject(j);
            Item item = new Item();
                        /*widgetList*/
            JSONArray widgetArray = contentObj.getJSONArray("widgetList");
            for (int m = 0; m < widgetArray.length(); m++) {
                JSONObject widgetObj = widgetArray.getJSONObject(m);
                Widget widget = new Widget();

                widget.setId(widgetObj.getString("id"));
                widget.setVisible(widgetObj.getString("visible"));
                if (widgetObj.has("route")) {
                    widget.setRoute(widgetObj.getString("route"));
                }
                if (widgetObj.has("text")) {
                    widget.setText(widgetObj.getString("text"));
                }
                if (widgetObj.has("textColor")) {
                    widget.setTextColor(widgetObj.getString("textColor"));
                }
                if (widgetObj.has("image")) {
                    widget.setImage(widgetObj.getString("image"));
                }
                if (widgetObj.has("url")) {
                    widget.setUrl(widgetObj.getString("url"));
                }
                item.getWidgetList().add(widget);
            }
            itemList.add(item);
        }
        return itemList;
    }
}
