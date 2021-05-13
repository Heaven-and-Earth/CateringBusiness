package com.cbm.utils;

/**
 * @ClassName MD5Util
 * @Description TODO
 * @date 2021/5/13 18:00
 * @Version 1.0
 */

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

import java.util.UUID;

/**
 * 加密工具类
 */
public class MD5Util {
    public static String getSalts(String password) {
        String[] salts = password.split("\\$");
        if (salts.length < 1) {
            return "";
        }
        String mysalt = "";
        for (int i = 1; i < 3; i++) {
            mysalt += "$" + salts[i];
        }
        mysalt += "$";
        return mysalt;
    }

    public static void main(String[] args) {
        //摘要
        System.out.println(DigestUtils.md5Hex("admin"+System.currentTimeMillis()));

        System.out.println(DigestUtils.md5Hex("admin"+System.currentTimeMillis()));

        System.out.println("---------"+ UUID.randomUUID());

        System.out.println(Md5Crypt.md5Crypt("admin".getBytes()));
        System.out.println(Md5Crypt.md5Crypt("admin".getBytes()));

    }
}
