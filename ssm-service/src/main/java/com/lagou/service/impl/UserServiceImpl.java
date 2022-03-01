package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Md5;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> list = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public User login(User user) {

        User loginUser = userMapper.login(user);
        if(loginUser!=null && Md5.verify(user.getPassword(), "lagou", loginUser.getPassword())){
             return loginUser;
        }
        return null;
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        userMapper.deleteUserContextRole(userVo.getUserId());
        Date date = new Date();
        User_Role_relation relation = new User_Role_relation();
        relation.setCreatedBy("system");
        relation.setUpdatedby("system");
        relation.setUserId(userVo.getUserId());
        relation.setCreatedTime(date);
        relation.setUpdatedTime(date);
        for (Integer integer : userVo.getRoleIdList()) {
            relation.setRoleId(integer);
            userMapper.userContextRole(relation);
        }
    }

    @Override
    public ResponseResult getUserPermission(Integer userId) {

        //1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);
        List<Integer> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(role.getId());
        }
        System.out.println(list);
        List<Menu> parentMenuList = null;
        List<Resource> resourceList = null;

        if (list.size()!=0) {
            //根据角色id查询父菜单
            parentMenuList = userMapper.findParentMenuByRoleId(list);

            //查询封装父菜单关联的子菜单
            for (Menu menu : parentMenuList) {
                List<Menu> subMenuByPid = userMapper.findSubMenuByPid(menu.getId());
                menu.setSubMenuList(subMenuByPid);
            }

            resourceList = userMapper.findResourceByRoleId(list);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenuList);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }

}
