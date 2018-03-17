package com.credithc.elf_page.model;

import com.credithc.elf_page.statis.StatisInfo;

/**
 * @author zzy
 * @date 2018/2/11
 */


public class Widget {
    private String id;
    private String route;
    private String visible;

    private String text;
    private String textColor;
    private String image;

    private String url;

    private StatisInfo statisInfo;/*统计专用*/

    public Widget() {
        statisInfo = new StatisInfo();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public StatisInfo getStatisInfo() {
        return statisInfo;
    }

    public void setStatisInfo(StatisInfo statisInfo) {
        this.statisInfo = statisInfo;
    }
}
