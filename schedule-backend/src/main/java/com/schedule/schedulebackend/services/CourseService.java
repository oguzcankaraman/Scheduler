package com.schedule.schedulebackend.services;

import com.schedule.schedulebackend.entities.Course;
import com.schedule.schedulebackend.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository coursesRepository;

    @Autowired
    public CourseService(CourseRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public List<Course> getAllCourses() {
        return coursesRepository.findAll();
    }

    public Course getOneCourse(Long courseId) {
        return coursesRepository.findById(courseId).orElse(null);
    }

    public Course createOneCourse(Course course) {
        return coursesRepository.save(course);
    }

    public Course updateOneCourse(Long courseId, Course newCourse) {
        Optional<Course> course = coursesRepository.findById(courseId);
        if (course.isPresent()) {
            Course updatedCourse = course.get();
            updatedCourse.setName(newCourse.getName());
            return coursesRepository.save(updatedCourse);
        }
        else
            return null;
    }

    public void deleteOneCourse(Long courseId) {
        coursesRepository.deleteById(courseId);
    }
}
