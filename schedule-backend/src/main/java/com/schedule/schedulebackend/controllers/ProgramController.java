package com.schedule.schedulebackend.controllers;

import com.schedule.schedulebackend.dtos.ProgramDTO;
import com.schedule.schedulebackend.entities.Program;
import com.schedule.schedulebackend.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

//    @GetMapping
//    public List<Program> getProgram(@RequestParam Optional<Long> id) {
//        return programService.getProgram(id);
//    }

    @PostMapping
    public Program createProgram(@RequestBody ProgramDTO programDTO) {
        System.out.println("Recieved ProgramDTO: " + programDTO.courseIds());
        return programService.createOneProgram(programDTO);
    }
//programlar save edilcek
    @PostMapping("/all")
    public List<Program> createPrograms(@RequestBody ProgramDTO programDTOList) {
        List<Long> sectionIds = programDTOList.courseIds();
        return programService.createMultiplePrograms(sectionIds);
    }
}
