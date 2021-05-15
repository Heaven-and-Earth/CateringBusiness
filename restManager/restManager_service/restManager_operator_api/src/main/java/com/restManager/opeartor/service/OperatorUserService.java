package com.restManager.opeartor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.restManager.opeartor.pojo.OperatorUser;
import com.restManager.utils.Result;

/**
 * @ClassName OperatorUserService
 * @Description TODO
 * @date 2021/5/15 15:21
 * @Version 1.0
 */
public interface OperatorUserService extends IService<OperatorUser> {

    IPage<OperatorUser> queryPageByName(int pageNum, int pageSize, String name);

    //登录
    Result login(String loginName,String loginPass);
}
