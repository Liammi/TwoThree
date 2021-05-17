package com.example.video.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

public class Toolbox {

    private static final String salt = "这是啥毕业设计";

    public static String md5(String str) {
        if (StringUtils.isEmpty(str)) {
            return  " ";
        }
        return DigestUtils.md5DigestAsHex((str + salt).getBytes());
    }

}
