package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    /*
    多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);
    /*
    用户登入（根据用户名查询具体的用户信息）
     */
    public User login(User user);

    /*
        根据用户id查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
    根据用户id清空中间表
     */
    public void deleteUserContextRole(Integer userId);

    /*
    分配角色
     */
    public void userContextRole(User_Role_relation userRoleRelation);

    /*
    根据多个角色Id，查询角色所拥有的顶级菜单
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> roleIds);

    /*
    根据父菜单，查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /*
    获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> roleIds);
}
