package com.schedule.schedulebackend.controllers;

import com.schedule.schedulebackend.entities.Course;
import com.schedule.schedulebackend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService coursesService;

    @Autowired
    public CourseController(CourseService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return coursesService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Long courseId) {
        return coursesService.getOneCourse(courseId);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return coursesService.createOneCourse(course);
    }

    @PutMapping("/{courseId}")
    public Course updateCourse(@PathVariable Long courseId , @RequestBody Course course) {
        return coursesService.updateOneCourse(courseId, course);
    }
    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        coursesService.deleteOneCourse(courseId);
    }

}
