package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    public List<Course> findCourseByCondition(CourseVO courseVo);


    /*
    新增课程信息
     */
    public void saveCourse(Course course);
    /*
    新增讲师信息
     */
    public void saveTeacher(Teacher teacher);

    /*
    查询课程信息及关联的讲师信息
     */
    public CourseVO findCourseById(@Param("id") Integer id);

    /*
    更新课程信息
     */
    public void updateCourse(Course course);

    /*
    更新讲师信息
     */
    public void updateTeacher(Teacher teacher);

    /*
    课程状态管理
     */
    public void updateCourseStatus(Course course);
}
