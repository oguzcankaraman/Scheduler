package com.schedule.schedulebackend.controllers;

import com.schedule.schedulebackend.dtos.DateDTO;
import com.schedule.schedulebackend.entities.Date;
import com.schedule.schedulebackend.services.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dates")
public class DateController {

    private final DateService datesService;

    @Autowired
    public DateController(DateService datesService) {
        this.datesService = datesService;
    }

    @GetMapping
    public List<Date> getDates(@RequestParam Optional<Long> sectionId) {
        return datesService.getAllDates(sectionId);
    }

    @GetMapping("/{dateId}")
    public Date getDate(@PathVariable Long dateId) {
        return datesService.getOneDate(dateId);
    }

    @PostMapping
    public Date addDate(@RequestBody DateDTO dates) {
        return datesService.createOneDate(dates);
    }

    @PutMapping("/{dateId}")
    public Date updateDate(@PathVariable Long dateId, @RequestBody DateDTO date) {
        return datesService.updateOneDate(dateId, date);
    }
}
