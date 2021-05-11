package cn.tedu.controller;

import cn.tedu.pojo.Items;
import cn.tedu.service.ItemsService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    //@RequestMapping(value = "/list",method = RequestMethod.GET)
    @GetMapping("/list")
    public String list(Model model){
        List<Items> list = itemsService.findAll();
        //ModelAndView
        /*ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items");
        modelAndView.addObject("list",list);
        return modelAndView;*/


        //塞给页面
        model.addAttribute("items",list);
        //返回视图
        return "items";
    }

    /*@GetMapping("/findById" + "/{id}")
    public String findById(@PathVariable("id") Integer id, Model model){
        // model 数据模型
        // modelAndView 数据与视图 基本不用
        Items items = itemsService.findById(id);
        //塞给页面
        model.addAttribute("items",items);
        //返回视图
        return "items";
    }*/


    /**
     * 事务测试
     * 增加商品
     * @return
     */
    @RequestMapping("/save")
    public String save(Items items){

        int res = itemsService.save(items);
        System.out.println(res);
        //集合列表跳转
        return "redirect:/items/list";
    }


}
