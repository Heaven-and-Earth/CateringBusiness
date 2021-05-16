package com.restManager.opeartor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.restManager.opeartor.pojo.EnterpriseAccount;

/**
 * @ClassName EnterpriseAccountService
 * @date 2021/5/16 16:34
 * @Version 1.0
 * @Author ShyBoy
 */
public interface EnterpriseAccountService extends IService<EnterpriseAccount> {

    //数据分页查询(按照企业名称进行模糊查询)
    IPage<EnterpriseAccount> queryPageByName(int pageNum, int pageSize, String enterpriseName);

    //新增账号
    boolean add(EnterpriseAccount enterpriseAccount);

    //账号还原
    boolean recovery(String id);

    //重置密码
    boolean resetPwd(String id,String password);
}
