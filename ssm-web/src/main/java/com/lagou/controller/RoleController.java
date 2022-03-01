package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /*
    查询所有角色
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true,200,"查询所有角色成功",allRole);
    }
    /*
    查询所有的父子菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        return new ResponseResult(true,200,"响应成功",map);
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true,200,"响应成功",menuByRoleId);
    }

    /*
    为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);
        return new ResponseResult(true,200,"响应成功",null);
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer roleId){
        roleService.deleteRole(roleId);
        return new ResponseResult(true,200,"响应成功",null);
    }
}
