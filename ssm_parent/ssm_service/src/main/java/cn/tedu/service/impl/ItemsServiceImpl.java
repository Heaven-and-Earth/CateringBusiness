package cn.tedu.service.impl;

import cn.tedu.dao.ItemsDao;
import cn.tedu.pojo.Items;
import cn.tedu.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDao ItemsDao;

    @Override
    public List<Items> findAll() {
        return ItemsDao.findAll();
    }

    @Override
    public int save(Items items) {
        return ItemsDao.save(items);
    }

    @Override
    public Items findById(Integer id) {
        return ItemsDao.findById(id);
    }
}
