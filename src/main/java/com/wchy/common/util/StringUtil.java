package com.wchy.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangchangye on 2017/3/9.
 *
 * @Author wangchangye
 */
public class StringUtil {
    /**
     * @Description 判断字符串是否为正整数
     * @author shangpengfei
     * @date 2016年1月15日 下午12:14:24
     * @param str
     * @return boolean
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * @Description 判断字符串是否为空
     * @author jiale
     * @date 2016年3月3日 下午6:06:46
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }
}

