package com.schedule.schedulebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalTime;

@Entity(name = "dates")
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private String day;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    Section section;
    int dateNo;

    public Date() {
    }

    public Date(LocalTime startTime, LocalTime endTime, Section section, int dateNo, String day) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.section = section;
        this.dateNo = dateNo;
        this.day = day;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public int getDateNo() {
        return dateNo;
    }

    public void setDateNo(int dateNo) {
        this.dateNo = dateNo;
    }
}
