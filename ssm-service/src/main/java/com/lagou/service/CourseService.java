package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
    多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVO courseVo);

    /*
    添加课程及讲师信息
     */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    public CourseVO findCourseById(Integer id);

    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    public void updateCourseStatus(int courseId,int status);
}
