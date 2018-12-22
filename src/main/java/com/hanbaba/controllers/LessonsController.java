package com.hanbaba.controllers;

import com.hanbaba.model.Lesson;
import com.hanbaba.model.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        List<Lesson> all = (List<Lesson>) this.lessonRepository.findAll();
        return resolveLessonsPaths(all);
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


    private List<Lesson> resolveLessonsPaths(List<Lesson> list) {
        List<Lesson> newLessons = new ArrayList<>();
        list.forEach(lesson -> {
            try {
                newLessons.add(new Lesson(lesson.getType(), lesson.getName(), lesson.getDescription(), lesson.getRabbi(), lesson.getDateAdded(), lesson.getPathToFile()));
            } catch (Exception e) {
                newLessons.add(lesson);
            }
        });
        return newLessons;
    }
}


