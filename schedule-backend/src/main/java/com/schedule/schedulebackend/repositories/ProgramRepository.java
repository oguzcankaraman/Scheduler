package com.schedule.schedulebackend.repositories;

import com.schedule.schedulebackend.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProgramRepository extends JpaRepository<Program, Long> {
}
