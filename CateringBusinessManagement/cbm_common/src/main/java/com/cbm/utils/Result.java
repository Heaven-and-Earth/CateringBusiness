package com.cbm.utils;

/**
 * @ClassName Result
 * @Description TODO
 * @date 2021/5/13 16:25
 * @Version 1.0
 */

import lombok.Data;

/**
 * 返回结果的通用封装
 */
@Data
public class Result {
    // 返回状态
    private int status;
    // 状态描述
    private String desc;
    // 返回数据
    private Object data;

    private String token;
}
