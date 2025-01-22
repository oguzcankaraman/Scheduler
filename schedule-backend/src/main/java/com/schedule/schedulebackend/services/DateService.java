package com.schedule.schedulebackend.services;

import com.schedule.schedulebackend.dtos.DateDTO;
import com.schedule.schedulebackend.entities.Date;
import com.schedule.schedulebackend.entities.Section;
import com.schedule.schedulebackend.repositories.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DateService {

    private final DateRepository datesRepository;
    private final SectionService sectionsService;

    @Autowired
    public DateService(DateRepository datesRepository, SectionService sectionsService) {
        this.datesRepository = datesRepository;
        this.sectionsService = sectionsService;
    }

    public List<Date> getAllDates(Optional<Long> sectionId) {
        if (sectionId.isPresent()) {
            return datesRepository.findBySection_Id(sectionId);
        }
        return datesRepository.findAll();
    }

    public Date getOneDate(Long dateId) {
        return datesRepository.findById(dateId).orElse(null);
    }

    public Date createOneDate(DateDTO dates) {
        Section section = sectionsService.getOneSection(dates.sectionId());
        if (section == null) {
            return null;
        }
        Date newDate = new Date();
        newDate.setStartTime(dates.startTime());
        newDate.setDay(dates.day());
        newDate.setEndTime(dates.endTime());
        newDate.setSection(section);
        newDate.setDateNo(dates.dateNo());
        return datesRepository.save(newDate);
    }

    public Date updateOneDate(Long dateId, DateDTO date) {
        Date newDate = getOneDate(dateId);
        if (newDate == null) {
            return null;
        }
        newDate.setStartTime(date.startTime());
        newDate.setEndTime(date.endTime());
        newDate.setDateNo(date.dateNo());
        Section section = sectionsService.getOneSection(date.sectionId());
        if (section != null)
            newDate.setSection(section);

        return datesRepository.save(newDate);
    }
}
