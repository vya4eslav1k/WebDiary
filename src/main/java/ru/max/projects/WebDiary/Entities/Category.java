package ru.max.projects.WebDiary.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "group", schema = "webnotes")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "userId")
    private Integer userId;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "note_id")
//    private List<Note> notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
