package com.schedule.schedulebackend.dtos;

import java.time.LocalTime;

public record DateDTO(
        LocalTime startTime,
        LocalTime endTime,
        String day,
        Long sectionId,
        int dateNo
) {
}
