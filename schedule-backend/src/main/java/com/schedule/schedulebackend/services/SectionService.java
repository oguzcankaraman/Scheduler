package com.schedule.schedulebackend.services;

import com.schedule.schedulebackend.dtos.SectionDTO;
import com.schedule.schedulebackend.entities.Course;
import com.schedule.schedulebackend.entities.Section;
import com.schedule.schedulebackend.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository sectionsRepository;
    private final CourseService coursesService;

    @Autowired
    public SectionService(SectionRepository sectionsRepository, CourseService coursesService) {
        this.sectionsRepository = sectionsRepository;
        this.coursesService = coursesService;
    }

    public List<Section> getAllSections(Optional<Long> courseId) {
        if (courseId.isPresent())
            return sectionsRepository.findByCourse_Id(courseId.get());
        return sectionsRepository.findAll();
    }

    public Section createOneSection(SectionDTO section) {
        Course course = coursesService.getOneCourse(section.courseId());
        if (course == null)
            return null;
        Section sectionToAdd = new Section();
        sectionToAdd.setCourse(course);
        sectionToAdd.setSectionNo(section.sectionNo());
        return sectionsRepository.save(sectionToAdd);
    }

    public Section getOneSection(Long sectionId) {
        return sectionsRepository.findById(sectionId).orElse(null);
    }

    public Section updateOneSection(Long sectionId, SectionDTO section) {
        Section sectionToUpdate = getOneSection(sectionId);
        if (sectionToUpdate == null)
            return null;
        sectionToUpdate.setSectionNo(section.sectionNo());
        Course course = coursesService.getOneCourse(section.courseId());
        if (course != null)
            sectionToUpdate.setCourse(coursesService.getOneCourse(section.courseId()));

        return sectionsRepository.save(sectionToUpdate);
    }

    public void deleteOneSection(Long sectionId) {
        Section sectionToDelete = getOneSection(sectionId);
        if (sectionToDelete == null)
            return;
        sectionsRepository.delete(sectionToDelete);
    }
}
