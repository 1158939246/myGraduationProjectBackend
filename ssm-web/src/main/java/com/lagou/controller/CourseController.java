package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> courseList = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",courseList);
        return responseResult;
    }

    /*
    课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //判断文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }

        //获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String webappPath = realPath.substring(0, realPath.indexOf("ssm-web"));

        //获取原文件名
        String originalFilename = file.getOriginalFilename();

        //生成新文件名
        String newFileName=System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //文件上传
        String uploadPath = webappPath+"upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在 创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录"+filePath);
        }

        file.transferTo(filePath);

        //将文件名和文件路径返回
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        return new ResponseResult(true,200,"上传成功",map);
    }

    /*
    新增课程信息及讲师信息
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        if(courseVO.getId() != null){
            courseService.updateCourseOrTeacher(courseVO);
            return new ResponseResult(true,200,"修改成功",null);
        }else {
            courseService.saveCourseOrTeacher(courseVO);
            return new ResponseResult(true,200,"新增成功",null);
        }
    }

    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseById = courseService.findCourseById(id);

        return new ResponseResult(true,200,"响应成功",courseById);
    }

    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        courseService.updateCourseStatus(id, status);

        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"课程状态变更成功",map);
    }
}
