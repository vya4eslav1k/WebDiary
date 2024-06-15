package ru.max.projects.WebDiary.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "note", schema = "webnotes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "lastUpdate", nullable = false)
    private Date lastUpdate;
    @Column(name = "userId", nullable = false)
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
