package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
    查询所有菜单信息
     */
    @RequestMapping("findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true,200,"查询所有菜单信息成功",allMenu);
    }

    /*
    回显菜单信息
     */
    @RequestMapping("findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        HashMap<String, Object> map = new HashMap<>();
        if(id == -1){
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"添加回显成功",map);
        }else {
            Menu menu=menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"添加回显成功",map);
        }
    }
}
