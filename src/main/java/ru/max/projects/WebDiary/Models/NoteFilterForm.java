package ru.max.projects.WebDiary.Models;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteFilterForm {
    private Integer categoryId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String sortOrder;

    @Override
    public String toString() {
        return "NoteFilterForm{" +
                "categoryId=" + categoryId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", sortOrder='" + sortOrder + '\'' +
                '}';
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public void setStartDate(String startDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.startDate = formatter.parse(startDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEndDate(String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.endDate = formatter.parse(endDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
