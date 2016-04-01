package com.opensource.leo.hash;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class MD5 implements HashAlgo {

    @Override
    public long hash(String str) {
        if (StringUtils.isBlank(str)) {
            return -1L;
        }
        String md5 = DigestUtils.md5Hex(str);
        if (StringUtils.isBlank(md5)) {
            return -1L;
        }
        long hash = Long.parseLong(md5.substring(md5.length() - 16, md5.length() - 1), 16);
        if (hash < 0) {
            hash = hash * (-1);
        }
        return hash;
    }

    @Override
    public String name() {
        return "MD5";
    }
}
