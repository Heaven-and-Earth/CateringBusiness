package com.restManager.operator.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.restManager.opeartor.pojo.OperatorUser;
import com.restManager.opeartor.service.OperatorUserService;
import com.restManager.operator.mapper.OpeartorUserMapper;
import com.restManager.utils.JWTUtil;
import com.restManager.utils.MD5CryptUtil;
import com.restManager.utils.Result;
import com.restManager.utils.ResultCode;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

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
@RefreshScope   //支持动态刷新
public class OperatorUserServiceImpl extends ServiceImpl<OpeartorUserMapper,OperatorUser> implements OperatorUserService {


    @Value("${gateway.secret}")
    private String secret;

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

    @Override
    public Result login(String loginName, String loginPass) {
        Result result = new Result();
        //参数校验
        if (StringUtils.isEmpty(loginName)){
            result.setStatus(ResultCode.error);
            result.setDesc("用户名为空!");
            return result;
        }
        if (StringUtils.isEmpty(loginPass)){
            result.setStatus(ResultCode.error);
            result.setDesc("密码为空!");
            return result;
        }

        //查询用户是否存在
        QueryWrapper<OperatorUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname",loginName);
        OperatorUser operatorUser = this.getOne(queryWrapper);
        if (operatorUser == null){
            result.setStatus(ResultCode.error);
            result.setDesc("用户不存在!");
            return result;
        }

        //比对密码
        String salts = MD5CryptUtil.getSalts(operatorUser.getLoginpass());
        if (!Md5Crypt.md5Crypt(loginPass.getBytes(),salts).equals(operatorUser.getLoginpass())){
            result.setStatus(ResultCode.error);
            result.setDesc("密码错误!");
            return result;
        }

        //生成jwt令牌
        Map<String,Object> tokenInfo = Maps.newHashMap();
        tokenInfo.put("loginName",operatorUser.getLoginname());
        String token = null;
        try {
            token = JWTUtil.createJWTByObj(tokenInfo,secret);
        } catch (IOException e) {
            e.printStackTrace();
            result.setStatus(ResultCode.error);
            result.setDesc("生成令牌失败!");
            return result;
        }

        //返回结果
        result.setStatus(ResultCode.success);
        result.setDesc("ok");
        result.setData(operatorUser);
        result.setToken(token);

        return result;
    }
}
