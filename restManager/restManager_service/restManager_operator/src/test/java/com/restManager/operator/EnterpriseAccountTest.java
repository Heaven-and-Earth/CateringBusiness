package com.restManager.operator;

import com.restManager.opeartor.service.EnterpriseAccountService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EnterpriseAccountTest {

    @Reference(version = "1.0.0",check = false)
    private EnterpriseAccountService enterpriseAccountService;

    @Test
    @Rollback(false)
    public void delTest(){
        enterpriseAccountService.removeById("1393886549093269505");
    }
}
