package com.restManager.response.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PageVO
 * @Description TODO
 * @date 2021/5/15 18:23
 * @Version 1.0
 */
@Data
public class PageVO<T> {
    private  long counts; //总个数

    private  long pagesize; //每页个数

    private  long pages; //总页数

    private  long page; //当前页

    private List<T> items; //数据记录

    public PageVO(IPage page) {
        this.pagesize = page.getSize();
        this.counts = page.getTotal();
        this.page = page.getCurrent();
        this.pages = page.getPages();
        this.items = page.getRecords();
    }
}
