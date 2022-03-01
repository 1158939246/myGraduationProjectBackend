package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id) {
        List<CourseSection> sectionAndLessonByCourseId = courseContentMapper.findSectionAndLessonByCourseId(id);
        return sectionAndLessonByCourseId;
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        return courseContentMapper.findCourseByCourseId(courseId);
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        courseContentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(int id, int status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);
        courseSection.setStatus(status);

        courseContentMapper.updateSectionStatus(courseSection);
    }

    @Override
    public void saveLesson(CourseLesson lesson) {
        //补全信息
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);
        courseContentMapper.saveLesson(lesson);
    }

    @Override
    public void updateLesson(CourseLesson lesson) {
        Date date = new Date();
        lesson.setUpdateTime(date);
        courseContentMapper.updateLesson(lesson);
    }
}
