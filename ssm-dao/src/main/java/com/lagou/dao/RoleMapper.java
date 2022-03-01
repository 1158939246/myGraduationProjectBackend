package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    /*
    查询所有角色&根据角色进行查询
     */
    public List<Role> findAllRole(Role role);

    /*
    根据角色id查询角色关联的菜单信息id
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
    根据roleId清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer rid);

    /*
    为角色分配菜单信息
     */
    public void roleContextMenu(List<Role_menu_relation> roleMenuRelation);

    /*
    删除角色
     */
    public void deleteRole(Integer roleId);
}