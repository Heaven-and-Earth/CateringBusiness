package com.restManager.operator.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restManager.opeartor.pojo.OperatorUser;
import com.restManager.opeartor.service.OperatorUserService;
import com.restManager.operator.mapper.OpeartorUserMapper;
import org.apache.dubbo.config.annotation.Service;

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


}
