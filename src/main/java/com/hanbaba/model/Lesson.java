package com.hanbaba.model;


import com.hanbaba.enteties.LessonType;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column
    private String type;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String rabbi;
    @Column(name = "dateAdded")
    private String dateAdded;
    @Column(name = "pathToFile")
    private String pathToFile;

    public Lesson() {
    }

    public Lesson(String type, String name, String description, String rabbi, String dateAdded, String pathToFile) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.rabbi = rabbi;
        this.dateAdded = dateAdded;
        this.pathToFile = pathToFile;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRabbi() {
        return rabbi;
    }

    public String getRowId() {
        return "row_id_" + getId();
    }

    public String getRecordId() {
        return "record_id_" + getId();
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getPathToFile() throws Exception {
        if (pathToFile.startsWith("/")) {
            return pathToFile;
        } else {
            LessonType lessonType = LessonType.getLessonFromString(this.type);
            return LessonType.getPathToFileFromType(lessonType) + pathToFile;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRabbi(String rabbi) {
        this.rabbi = rabbi;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
}
