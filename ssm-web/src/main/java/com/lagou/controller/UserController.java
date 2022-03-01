package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
    用户分页  多条件查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo pageInfo = userService.findAllUserByPage(userVo);
        return new ResponseResult(true,200,"success",pageInfo);
    }

    /*
    用户登入
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request){
        User loginUser = userService.login(user);
        if(loginUser!=null){
            HttpSession session = request.getSession();
            String s = UUID.randomUUID().toString();
            session.setAttribute("access_token",s);
            session.setAttribute("user_id",loginUser.getId());

            Map<String, Object> map = new HashMap<>();
            map.put("access_token",s);
            map.put("user_id",loginUser.getId());
            map.put("user",loginUser);
            return new ResponseResult(true,1,"登入成功",map);
        }else {
            return new ResponseResult(true,400,"用户名密码错误",null);
        }
    }

    /*
    分配角色回显
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> list = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"分配角色回显成功",list);
    };

    /*
    分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true,200,"分配角色成功",null);
    }

    /*
    获取用户权限，进行菜单动态展示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermission(HttpServletRequest request){
        String header_token = request.getHeader("Authorization");
        HttpSession session = request.getSession();
        String session_token = (String) session.getAttribute("access_token");

        if (header_token!=null&&header_token.equalsIgnoreCase(session_token)){
            Integer user_id = (Integer) session.getAttribute("user_id");
            return userService.getUserPermission(user_id);
        }

        return new ResponseResult(false,400,"获取菜单信息失败",null);
    }
}
