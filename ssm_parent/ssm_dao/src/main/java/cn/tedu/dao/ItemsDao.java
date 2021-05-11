package cn.tedu.dao;

import cn.tedu.pojo.Items;

import java.util.List;

public interface ItemsDao {
    /***
     * 查询所有
     * @return
     */
    List<Items> findAll();

    /***
     * 保存操作
     * @param items
     * @return
     */
    int save(Items items);

    Items findById(Integer id);
}
