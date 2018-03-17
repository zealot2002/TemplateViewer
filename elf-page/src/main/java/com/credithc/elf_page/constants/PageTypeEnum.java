package com.credithc.elf_page.constants;

/**
 * @author zzy
 * @date 2018/3/1
 */

public enum PageTypeEnum {
    PAGE_TYPE_SECTION_LIST("sectionListPage"),
    PAGE_TYPE_PAGE_GROUP("pageGroup"),
    PAGE_TYPE_ITEM_LIST("itemListPage");

    private String value;
    private PageTypeEnum(String value) {
        this.value = value;
    }
    public String value(){return value;}
}
