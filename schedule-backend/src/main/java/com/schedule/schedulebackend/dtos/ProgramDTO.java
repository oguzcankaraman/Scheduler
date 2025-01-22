package com.schedule.schedulebackend.dtos;

import java.util.List;

public record ProgramDTO(
        List<Long> courseIds
        ) {
}
