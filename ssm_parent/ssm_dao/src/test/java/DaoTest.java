import cn.tedu.dao.ItemsDao;
import cn.tedu.pojo.Items;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class DaoTest {
    @Test
    public void testDao(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        //ItemsDao itemsDao = (ItemsDao)applicationContext.getBean("itemsDao");
        ItemsDao itemsDao = (ItemsDao)applicationContext.getBean(ItemsDao.class);
        System.out.println("商品列表:"+itemsDao.findAll());

        Items items = new Items();
        items.setName("商品名称");
        items.setPrice(1666f);
        items.setCreatetime(new Date());
        itemsDao.save(items);
    }
}
