package com.restManager.operator.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restManager.opeartor.pojo.OperatorUser;
import com.restManager.opeartor.service.OperatorUserService;
import com.restManager.operator.mapper.OpeartorUserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

/**
 * @ClassName OperatorUserServiceImpl
 * @Description TODO
 * @date 2021/5/15 15:22
 * @Version 1.0
 */
@Service(version = "1.0.0",protocol = "dubbo")
/**
 * dubbo中支持的协议
 * dubbo 默认
 * rmi
 * hessian
 * http
 * webservice
 * thrift
 * memcached
 * redis
 */
public class OperatorUserServiceImpl extends ServiceImpl<OpeartorUserMapper,OperatorUser> implements OperatorUserService {


    @Override
    public IPage<OperatorUser> queryPageByName(int pageNum, int pageSize, String name) {
        IPage<OperatorUser> page = new Page<>(pageNum,pageSize);

        //查询条件构造器
        QueryWrapper<OperatorUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(name)){
            //对名称进行模糊查询
            queryWrapper.like("loginname",name);
        }

        return this.page(page,queryWrapper);
    }
}
