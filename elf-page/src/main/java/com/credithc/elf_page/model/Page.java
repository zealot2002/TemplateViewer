package com.credithc.elf_page.model;


import com.credithc.elf_page.model.pageElement.Body;
import com.credithc.elf_page.model.pageElement.Footer;
import com.credithc.elf_page.model.pageElement.Header;

/**
 * @author zzy
 * @date 2018/2/9
 */

public class Page {
    private String name;  //page group专用，用来显示
    private String code;  //page的名字，也是page data的请求参数
    private String background;
    private String type;
    private Body body;    // TODO: 2018/3/17   body不打算支持header和footer!
    private Header header;// TODO: 2018/3/17   下期支持
    private Footer footer;// TODO: 2018/3/17   下期支持

    public Page() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }
}

