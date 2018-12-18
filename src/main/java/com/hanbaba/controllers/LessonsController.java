package com.hanbaba.controllers;

import com.hanbaba.model.Lesson;
import com.hanbaba.model.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonsController {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonsController(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("/getAll")
    public List<Lesson> getAllLessons() {
        return (List<Lesson>) this.lessonRepository.findAll();
    }


    @GetMapping("/getLessonsFromType")
    public List<Lesson> getLessonsFromType(String type) throws Exception {
        return lessonRepository.findByType(type);
    }

    @GetMapping("/addNewLesson")
    public boolean addNewLesson(Lesson lesson) {
        try {
            this.lessonRepository.save(lesson);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/updateLesson")
    public Lesson updateCustomer(Lesson lesson) throws Exception {
        Lesson updatedLesson = lessonRepository.findById(lesson.getId()).orElseThrow(Exception::new);

        updatedLesson.setName(lesson.getName());
        updatedLesson.setDateAdded(lesson.getDateAdded());
        updatedLesson.setDescription(lesson.getDescription());
        updatedLesson.setPathToFile(lesson.getPathToFile());
        updatedLesson.setRabbi(lesson.getRabbi());
        updatedLesson.setType(lesson.getType());

        lessonRepository.save(updatedLesson);
        return updatedLesson;
    }

    @GetMapping("/deleteLesson")
    public boolean deleteCustomer(Long id) throws Exception {
        lessonRepository.delete(lessonRepository.findById(id).orElseThrow(Exception::new));
        return true;
    }


}
