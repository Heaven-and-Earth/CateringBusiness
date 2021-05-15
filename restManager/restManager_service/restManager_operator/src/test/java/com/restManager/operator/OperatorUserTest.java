package com.restManager.operator;

import com.restManager.opeartor.pojo.OperatorUser;
import com.restManager.opeartor.service.OperatorUserService;
import com.restManager.utils.MD5CryptUtil;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName OperatorUserTest
 * @Description TODO
 * @date 2021/5/15 15:29
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OperatorUserTest {
    @Reference(version = "1.0.0",check = false)
    private OperatorUserService operatorUserService;

    //新增用户
    @Test
    @Rollback(false)
    public void addTest(){
        OperatorUser operatorUser = new OperatorUser();
        operatorUser.setLoginname("小昭");
        //operatorUser.setLoginpass("123456");
        String crypt = Md5Crypt.md5Crypt("123456".getBytes());
        operatorUser.setLoginpass(crypt);
        operatorUserService.save(operatorUser);
    }

}
