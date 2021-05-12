package top.houry.netty.barrage.utils;

import org.springframework.web.util.HtmlUtils;

/**
 * @Desc
 * @Author houry
 * @Date 2021/5/12 10:45
 **/
public class XssUtil {
    /**
     * 编码敏感信息
     *
     * @param value 原信息
     * @return 编码之后的信息
     */
    public static String xssEncode(String value) {
        if (value != null && !value.isEmpty()) {
            value = HtmlUtils.htmlEscape(value);
            value = value.replaceAll("<", "＜");
            value = value.replaceAll(">", "＞");
            value = value.replaceAll("'", "＇");
            value = value.replaceAll(";", "﹔");
            value = value.replaceAll("&", "＆");
            value = value.replaceAll("%", "﹪");
            value = value.replaceAll("#", "＃");
            value = value.replaceAll("select", "seleᴄt");
            value = value.replaceAll("truncate", "trunᴄate");
            value = value.replaceAll("exec", "exeᴄ");
            value = value.replaceAll("join", "jᴏin");
            value = value.replaceAll("union", "uniᴏn");
            value = value.replaceAll("drop", "drᴏp");
            value = value.replaceAll("count", "cᴏunt");
            value = value.replaceAll("insert", "ins℮rt");
            value = value.replaceAll("update", "updat℮");
            value = value.replaceAll("delete", "delet℮");
            value = value.replaceAll("script", "sᴄript");
            value = value.replaceAll("cookie", "cᴏᴏkie");
            value = value.replaceAll("iframe", "ifram℮");
            value = value.replaceAll("onmouseover", "onmouseov℮r");
            value = value.replaceAll("onmousemove", "onmousemov℮");
        }
        return value;

    }

}
