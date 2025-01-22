package com.schedule.schedulebackend.controllers;

import com.schedule.schedulebackend.dtos.SectionDTO;
import com.schedule.schedulebackend.entities.Section;
import com.schedule.schedulebackend.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sections")
public class SectionController {

    private final SectionService sectionsService;

    @Autowired
    public SectionController(SectionService sectionsService) {
        this.sectionsService = sectionsService;
    }

    @GetMapping
    public List<Section> getSection(@RequestParam Optional<Long> courseId) {
        return sectionsService.getAllSections(courseId);
    }

    @GetMapping("/{sectionId}")
    public Section getSection(@PathVariable Long sectionId) {
        return sectionsService.getOneSection(sectionId);
    }

    @PostMapping
    public Section create(@RequestBody SectionDTO section) {
        return sectionsService.createOneSection(section);
    }

    @PutMapping("/{sectionId}")
    public Section update(@PathVariable Long sectionId, @RequestBody SectionDTO section) {
        return sectionsService.updateOneSection(sectionId, section);
    }

    @DeleteMapping("/{sectionId}")
    public void delete(@PathVariable Long sectionId) {
        sectionsService.deleteOneSection(sectionId);
    }
}
