package ru.max.projects.WebDiary.Models;

import ru.max.projects.WebDiary.Entities.Group;

import java.util.Date;

public class NoteFilter {
    private Group group;
    private Date startDate;
    private Date endDate;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
