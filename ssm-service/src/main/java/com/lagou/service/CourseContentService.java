package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    /*
    根据课程id查询对应的课程内容
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);

    /*
    回显章节对应课程信息
     */
    public Course findCourseByCourseId(int courseId);

    /*
   保存章节
    */
    public void saveSection(CourseSection courseSection);

    void updateSection(CourseSection courseSection);

    void updateSectionStatus(int id,int status);

    public void saveLesson(CourseLesson lesson);

    void updateLesson(CourseLesson lesson);
}

