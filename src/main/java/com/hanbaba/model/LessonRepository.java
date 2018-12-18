package com.hanbaba.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface LessonRepository extends CrudRepository<Lesson, Long> {
    List<Lesson> findByType(String type);
}