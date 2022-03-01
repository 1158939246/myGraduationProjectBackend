package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        Date date = new Date();

        if (roleMenuVo.getMenuIdList().size()!=0) {
            LinkedList<Role_menu_relation> list = new LinkedList<>();
            for (Integer index : roleMenuVo.getMenuIdList()) {
                Role_menu_relation relation = new Role_menu_relation();
                relation.setCreatedTime(date);
                relation.setUpdatedTime(date);
                relation.setMenuId(index);
                relation.setRoleId(roleMenuVo.getRoleId());
                relation.setCreatedBy("system");
                relation.setUpdatedby("system");
                list.push(relation);
            }
            roleMapper.roleContextMenu(list);
        }
    }

    @Override
    public void deleteRole(Integer rid) {
        roleMapper.deleteRoleContextMenu(rid);
        roleMapper.deleteRole(rid);
    }
}
