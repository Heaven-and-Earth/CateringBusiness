package cn.tedu.service;

import cn.tedu.pojo.Items;

import java.util.List;

public interface ItemsService {

    /**
     * 查询所有
     * @return
     */
    List<Items> findAll();

    int save(Items items);

    Items findById(Integer id);
}
