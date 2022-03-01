package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> sectionAndLessonByCourseId = courseContentService.findSectionAndLessonByCourseId(courseId);
        return new ResponseResult(true, 200, "查询成功", sectionAndLessonByCourseId);
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);
        return new ResponseResult(true, 200, "查询成功", course);
    }

    /*
    新增或者更新课程信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {
        //判断是否携带了章节id
        if (courseSection.getId() == null) {
            courseContentService.saveSection(courseSection);
        } else {
            courseContentService.updateSection(courseSection);
        }
        return new ResponseResult(true, 200, "响应成功", null);
    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status) {
        courseContentService.updateSectionStatus(id, status);
        Map<Object, Object> map = new HashMap<>();
        map.put("status", status);
        return new ResponseResult(true, 200, "响应成功", map);
    }

    /**
     * 保存&修改课时
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson) {
        try {
            if (lesson.getId() == null) {
                courseContentService.saveLesson(lesson);
                return new ResponseResult(true, 200, "响应成功", null);
            } else {
                courseContentService.updateLesson(lesson);
                return new ResponseResult(true, 200, "响应成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
