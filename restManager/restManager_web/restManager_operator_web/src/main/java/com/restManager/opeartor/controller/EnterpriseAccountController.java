package com.restManager.opeartor.controller;

import com.restManager.opeartor.pojo.EnterpriseAccount;
import com.restManager.opeartor.service.EnterpriseAccountService;
import com.restManager.opeartor.vo.AddEnterpriseAccountVO;
import com.restManager.opeartor.vo.ResetPwdVO;
import com.restManager.opeartor.vo.UpdateEnterpriseAccountVO;
import com.restManager.response.vo.PageVO;
import com.restManager.utils.AccountStatus;
import com.restManager.utils.Result;
import com.restManager.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @ClassName EnterpriseAccountController
 * @date 2021/5/16 17:03
 * @Version 1.0
 * @Author ShyBoy
 */
@Api(tags = {"企业账号管理"})
@RestController
@RequestMapping("/enterprise")
public class EnterpriseAccountController {
    @Reference(version = "1.0.0",check = false) //基于dubbo的分布式远程服务注入方式
    private EnterpriseAccountService enterpriseAccountService;

    @ApiOperation("查询企业账号列表")
    @GetMapping("/pageList/{page}/{pageSize}")
    public PageVO<EnterpriseAccount> findListByPage(@PathVariable("page") int page,
                                                    @PathVariable("pageSize") int pageSize,
                                                    @RequestParam(value = "enterpriseName",required = false) String enterpriseName){

        return new PageVO<EnterpriseAccount>(enterpriseAccountService.queryPageByName(page,pageSize,enterpriseName));
    }

    @ApiOperation("新增账号")
    @PostMapping("/add")
    public boolean add(@RequestBody AddEnterpriseAccountVO addEnterpriseAccountVO){
        //bean拷贝
        EnterpriseAccount enterpriseAccount = new EnterpriseAccount();
        BeanUtils.copyProperties(addEnterpriseAccountVO,enterpriseAccount);

        //设置时间
        LocalDateTime localDateTime = LocalDateTime.now();
        enterpriseAccount.setApplicationTime(localDateTime);

        //设置过期时间
        LocalDateTime expireTime = null;
        //试用期默认七天
        if (addEnterpriseAccountVO.getStatus() == 0){
            //表示试用账号
            expireTime = localDateTime.plusDays(7);
        }
        if (addEnterpriseAccountVO.getStatus() == 1){
            //设置到期时间
            expireTime = localDateTime.plusDays(addEnterpriseAccountVO.getValidityDay());
        }
        if (expireTime != null){
            enterpriseAccount.setExpireTime(expireTime);
        }else{
            throw new RuntimeException("账号类型信息有误");
        }
        return enterpriseAccountService.add(enterpriseAccount);
    }

    //根据id查询账号信息
    @ApiOperation("账号查询")
    @GetMapping("/getById/{id}")
    public EnterpriseAccount getById(@PathVariable("id") String id){
        return enterpriseAccountService.getById(id);
    }

    //账号编辑
    @ApiOperation("账号编辑")
    @PutMapping("/update")
    public Result update(@RequestBody UpdateEnterpriseAccountVO updateEnterpriseAccountVO){
        Result result = new Result();

        //查询原有企业账户信息'
        EnterpriseAccount enterpriseAccount = enterpriseAccountService.getById(updateEnterpriseAccountVO.getEnterpriseId());
        if (enterpriseAccount == null){
            result.setStatus(ResultCode.error);
            result.setDesc("所要修改的账户不存在");
            return result;
        }
        //修改状态信息
        if (updateEnterpriseAccountVO.getStatus() != null){
            //正式期不能修改为试用期
            if (updateEnterpriseAccountVO.getStatus() == 0 && enterpriseAccount.getStatus() == 1){
                result.setStatus(ResultCode.error);
                result.setDesc("不能将正式账号修改为试用账号");
                return result;
            }
            //试用期改为正式
            if (updateEnterpriseAccountVO.getStatus() == 1 && enterpriseAccount.getStatus() == 0){
                //到期时间
                LocalDateTime localDateTime = LocalDateTime.now();
                LocalDateTime expireTime = localDateTime.plusDays(updateEnterpriseAccountVO.getValidityDay());

                enterpriseAccount.setApplicationTime(localDateTime);
                enterpriseAccount.setExpireTime(expireTime);

            }
            //正式添加延期
            if (updateEnterpriseAccountVO.getStatus() == 1 && enterpriseAccount.getStatus() == 1){
                LocalDateTime localDateTime = LocalDateTime.now();
                /////设置到期时间
                LocalDateTime expireTime = localDateTime.plusDays(updateEnterpriseAccountVO.getValidityDay());
                enterpriseAccount.setExpireTime(expireTime);

            }
        }

        //其他字段 bean拷贝
        BeanUtils.copyProperties(updateEnterpriseAccountVO,enterpriseAccount);
        //执行修改
        boolean flag = enterpriseAccountService.updateById(enterpriseAccount);
        if (flag){
            //修改成功
            result.setStatus(ResultCode.success);
            result.setDesc("修改成功");
            return result;
        }else{
            //修改失败
            result.setStatus(ResultCode.error);
            result.setDesc("修改失败");
            return result;
        }
    }

    //账号删除
    @ApiOperation("账号删除")
    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable("id") String id){
        return enterpriseAccountService.removeById(id);
    }

    //账号删除后还原
    @ApiOperation("账号删除后还原")
    @PutMapping("/recovery/{id}")
    public boolean recovery(@PathVariable("id") String id){
        return enterpriseAccountService.recovery(id);
    }

    //账号禁用
    @ApiOperation("账号禁用")
    @PutMapping("/forbidden/{id}")
    public boolean forbidden(@PathVariable("id") String id){
        //查询原有账户信息
        EnterpriseAccount enterpriseAccount = enterpriseAccountService.getById(id);
        enterpriseAccount.setStatus(AccountStatus.Forbidden.getStatus());
        return enterpriseAccountService.updateById(enterpriseAccount);
    }

    //重置密码
    @ApiOperation("重置密码")
    @PutMapping("/resetPwd")
    public boolean resetPwd(@RequestBody ResetPwdVO resetPwdVO){
        return enterpriseAccountService.resetPwd(resetPwdVO.getId(),resetPwdVO.getPwd());
    }

}
