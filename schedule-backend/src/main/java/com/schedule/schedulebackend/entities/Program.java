package com.schedule.schedulebackend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "program")
public class Program {
    @Id
    private Long id;

    @OneToMany
    private List<Section> programSections;
    //TODO: student entity

    public Program() {
    }

    public Program(List<Section> programSections) {
        this.programSections = programSections;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Section> getProgramSections() {
        return programSections;
    }
    public void setProgramSections(List<Section> programSections) {
        this.programSections = programSections;
    }

}
