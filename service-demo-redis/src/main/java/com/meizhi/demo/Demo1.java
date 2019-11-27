package com.meizhi.demo;

import cn.hutool.core.util.ObjectUtil;
import com.meizhi.entity.User;
import sun.misc.Unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static cn.hutool.crypto.SecureUtil.md5;

public class Demo1 {

    public static void main(String[] args) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("account", "zhangzhang@imagedt.com");
        dataMap.put("password", "123456");
        dataMap.put("timestamp", System.currentTimeMillis());
        dataMap.put("compCode", "test2");
        String secretKey = "ad0234829205b9033196ba818f7a872b";
        String sign = signGenerate(dataMap, secretKey);
        System.out.println("生成的数字签名：sign=" + sign);
    }

    /**
     *      * 生成数字签名
     *      * @param dataMap 加密参数
     *      * @return 32位生成数字签名
     *     
     */
    public static String signGenerate(Map<String, Object> dataMap, String secretKey) {
        TreeMap<String, Object> sortedMap = new TreeMap<>(dataMap);
        StringBuilder stringToSign = new StringBuilder();
        if (sortedMap.size() > 0) {
            for (Map.Entry entry : sortedMap.entrySet()) {
                if (entry.getValue() != null) {
                    stringToSign.append((String) entry.getKey()).append("=").append(String.valueOf(entry.getValue())).append("&");
                    System.err.println(stringToSign);
                }
            }
            //拼接上密钥
            stringToSign.append(secretKey);
            System.err.println(stringToSign);
        }
        return md5(stringToSign.toString());
    }



}
