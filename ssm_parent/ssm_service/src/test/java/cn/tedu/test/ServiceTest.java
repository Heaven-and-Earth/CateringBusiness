package cn.tedu.test;

import cn.tedu.dao.ItemsDao;
import cn.tedu.pojo.Items;
import cn.tedu.service.ItemsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class ServiceTest {
    @Test
    public void testService(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-service.xml");

        ItemsService itemsService = (ItemsService)applicationContext.getBean(ItemsService.class);

        System.out.println("商品列表:"+itemsService.findAll());

    }
}
