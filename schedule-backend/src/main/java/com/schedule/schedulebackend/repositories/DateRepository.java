package com.schedule.schedulebackend.repositories;

import com.schedule.schedulebackend.entities.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {

    List<Date> findBySection_Id(Optional<Long> sectionId);
}
