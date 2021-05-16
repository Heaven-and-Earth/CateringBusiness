package com.restManager.operator.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restManager.opeartor.pojo.EnterpriseAccount;
import com.restManager.opeartor.service.EnterpriseAccountService;
import com.restManager.operator.mapper.EnterpriseAccountMapper;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName EnterpriseAccountServiceImpl
 * @date 2021/5/16 16:39
 * @Version 1.0
 * @Author ShyBoy
 */
@Service(version = "1.0.0",protocol = "dubbo")
public class EnterpriseAccountServiceImpl extends ServiceImpl<EnterpriseAccountMapper, EnterpriseAccount> implements EnterpriseAccountService {
    /**
     * 按照企业名称进行模糊查询并分页
     * @param pageNum
     * @param pageSize
     * @param enterpriseName
     * @return
     */
    @Override
    public IPage<EnterpriseAccount> queryPageByName(int pageNum, int pageSize, String enterpriseName) {

        IPage<EnterpriseAccount> page =new Page<>(pageNum,pageSize);
        QueryWrapper<EnterpriseAccount> queryWrapper =new QueryWrapper<>();
        if (StringUtils.isNotEmpty(enterpriseName)){
            queryWrapper.like("enterprise_name",enterpriseName);
        }

        return this.page(page,queryWrapper);
    }

    //新增账号
    @Override
    @Transactional  //添加事务注解
    public boolean add(EnterpriseAccount enterpriseAccount) {
        boolean flag = true;

        try {
            //账号 密码需要特殊处理
            String shopId = getShopId();
            enterpriseAccount.setShopId(shopId);
            //生成密码 6位
            String pwd = RandomStringUtils.randomNumeric(6);
            enterpriseAccount.setPassword(Md5Crypt.md5Crypt(pwd.getBytes()));

            //保存
            this.save(enterpriseAccount);

        }catch (Exception e){
            flag = false;
            throw e;    //由我们定义的统一异常处理器来处理
        }
        return flag;
    }

    //获取shopid  产品要求: 8位 随机数字
    private String getShopId() {
        //随机8位数字
        String shopId = RandomStringUtils.randomNumeric(8);
        //店铺校验
        QueryWrapper<EnterpriseAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId);
        EnterpriseAccount enterpriseAccount = this.getOne(queryWrapper);
        if (enterpriseAccount != null){
            this.getShopId();   //如果shopid重复了,则重新生成
        }
        return shopId;

    }

    //账号还原
    @Override
    @Transactional
    public boolean recovery(String id) {
        return this.getBaseMapper().recovery(id);
    }

    //重置密码
    @Override
    @Transactional
    public boolean resetPwd(String id, String password) {
        boolean flag = true;
        try {
            //查询原有的账户信息
            EnterpriseAccount enterpriseAccount = this.getById(id);
            if (enterpriseAccount == null){ //账户不存在
                return false;
            }
            String newPassword;
            if (StringUtils.isNotEmpty(password)){
                //自定义密码设置
                newPassword = password;
            }else {
                //随机生成 6位密码
                newPassword = RandomStringUtils.randomNumeric(6);
            }
            enterpriseAccount.setPassword(Md5Crypt.md5Crypt(newPassword.getBytes()));
            //执行修改操作
            this.updateById(enterpriseAccount);

        }catch (Exception e){
            e.printStackTrace();
            flag = false;
            throw e;
        }

        return flag;
    }
}










