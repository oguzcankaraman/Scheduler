package com.schedule.schedulebackend.repositories;

import com.schedule.schedulebackend.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findByCourse_Id(Long courseId);
}
