package com.schedule.schedulebackend.services;

import com.schedule.schedulebackend.dtos.ProgramDTO;
import com.schedule.schedulebackend.entities.Program;
import com.schedule.schedulebackend.entities.Section;
import com.schedule.schedulebackend.helpers.ProgramCreator;
import com.schedule.schedulebackend.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProgramService {

    private final ProgramCreator programCreator;
    private final ProgramRepository programRepository;
    private final SectionService sectionService;

    @Autowired
    public ProgramService(ProgramCreator programCreator, ProgramRepository programRepository, SectionService sectionService) {
        this.programCreator = programCreator;
        this.programRepository = programRepository;
        this.sectionService = sectionService;
    }


    public List<Program> getProgram(Optional<Long> id) {
        if (id.isPresent()) {
            Program program = programRepository.findById(id.get()).orElse(null);
            if (program != null) {
                return List.of(Objects.requireNonNull(programRepository.findById(id.get()).orElse(null)));
            }
            return null;
        }
        return programRepository.findAll();
    }

    public Program createOneProgram(ProgramDTO programDTO) {
        List<Section> sections = new ArrayList<>();
        programCreator.createProgram(programDTO.courseIds(), 0);
        List<List<Long>> l = programCreator.getPrograms();//puts all possible section combinations in l

        Program program = new Program();

        for (Long l2 : l.getFirst()) {
            sections.add(sectionService.getOneSection(l2));
        }
        program.setProgramSections(sections);

        return programRepository.save(program);
    }

    public List<Program> createMultiplePrograms(List<Long> courseIds) {
        List<Program> Allprograms = getProgram(Optional.empty());
        Long lastId;
        if (!Allprograms.isEmpty()) {
            lastId = Allprograms.getFirst().getId();
            for (Program p: Allprograms) {
                if (p.getId() > lastId) {
                    lastId = p.getId();
                }
            }
        }else
            lastId = 0L;

        Long id = lastId + 1;
        programCreator.createProgram(courseIds, 0);
        List<List<Long>> l = programCreator.getPrograms();//gets all possible section combinations in l
        List<Program> programs = new ArrayList<>();

        for (List<Long> l1 : l) {
            List<Section> sections = new ArrayList<>();
            Program program = new Program();
            for (Long l2 : l1) {
                sections.add(sectionService.getOneSection(l2));
            }
            program.setProgramSections(sections);
            program.setId(id);
            programs.add(program);
            id++;
        }

        return programs;
    }
}
