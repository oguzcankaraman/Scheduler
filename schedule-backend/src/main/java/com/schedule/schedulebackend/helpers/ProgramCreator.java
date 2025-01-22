package com.schedule.schedulebackend.helpers;

import com.schedule.schedulebackend.entities.Date;
import com.schedule.schedulebackend.entities.Section;
import com.schedule.schedulebackend.services.DateService;
import com.schedule.schedulebackend.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProgramCreator {
    private final int[][] week;
    private final Stack<Long> sectionIdStack;
    private final List<List<Long>> programs;

    private final DateService dateService;
    private final SectionService sectionService;

    @Autowired
    public ProgramCreator(DateService dateService, SectionService sectionService) {
        this.dateService = dateService;
        this.sectionService = sectionService;
        week = new int[6][9];
        sectionIdStack = new Stack<>();
        programs = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                week[i][j] = 0;
            }// end inner for
        }// end outer for
    }

    public List<List<Long>> getPrograms() {
        List<List<Long>> programsToReturn = new ArrayList<>(programs);
        programs.clear();
        return programsToReturn;
    }

    /**
     *
     * @param day param to turn into integer
     * @return integer value of day
     */
    public int dayToInt(String day) {
        String dayString = day.toUpperCase();
        return switch (dayString) {
            case "TUESDAY" -> 1;
            case "WEDNESDAY" -> 2;
            case "THURSDAY" -> 3;
            case "FRIDAY" -> 4;
            case "SATURDAY" -> 5;
            default -> 0;
        };
    }

    /**
     * modifies sections dates to be able to use them in other methods
     * @param sectionId id of section to modify its dates hours
     */
    public void modifyHours(Long sectionId) {
        List<Date> dates = dateService.getAllDates(Optional.of(sectionId));
        for (Date date : dates) {
            int DayValue = dayToInt(date.getDay());
            int startHour = date.getStartTime().getHour() - 9;
            int endHour = date.getEndTime().getHour() - 9;

            for (int i = startHour; i < endHour; i++) {
                if (week[DayValue][i] == 0) {
                    week[DayValue][i] = 1;
                } else if (week[DayValue][i] == 1) {
                    week[DayValue][i] = 0;
                }
            }// end inner for
        }// end outer for
    }

    /**
     * checks if any hour overlaps with given sectionId
     * @param sectionId id of section to check
     * @return true if sections dates doesn't overlap with other dates in current program
     */
    public boolean doesntOverlap(Long sectionId) {
        List<Date> dates = dateService.getAllDates(Optional.of(sectionId));
        for (Date date : dates) {
            int DayValue = dayToInt(date.getDay());
            int startHour = date.getStartTime().getHour() - 9;
            int endHour = date.getEndTime().getHour() - 9;
            for (int i = startHour; i < endHour; i++) {
                if (week[DayValue][i] == 1) {
                    return false;
                }
            }// end inner for
        }// end outer for
        return true;
    }

    /**
     * fills List<List<Long>> programs with lists of sectionId's
     * @param list list of courseId's
     * @param size should be 0 by default
     */
    public void createProgram(List<Long> list, int size) {
        if (list.size() == size) {
            if (sectionIdStack.size() == list.size()) {
                programs.add(new ArrayList<>(sectionIdStack));
            }// end if
            return;
        }// end if

        //getting all the sections of the course and putting their ids to a list
        List<Section> sections = sectionService.getAllSections(Optional.of(list.get(size)));
        for (Section section : sections) {
            Long sectionId = section.getId();

            if (doesntOverlap(sectionId)) {
                modifyHours(sectionId);
                sectionIdStack.push(sectionId);
                createProgram(list, size+1);
                modifyHours(sectionIdStack.pop());
            }
        }
    }
}

