package com.idea.gestioneCorsi.controller;

import com.idea.gestioneCorsi.entity.Corsi;
import com.idea.gestioneCorsi.service.CorsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CorsiController {

    private final CorsiService corsiService;

    @Autowired
    public CorsiController(CorsiService corsiService) {
        this.corsiService = corsiService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Corsi>> getAllCourses(@RequestParam(required = false) String orderBy) {
        List<Corsi> courses;
        if (orderBy != null) {
            courses = corsiService.getAllCoursesOrderedBy(orderBy);
        } else {
            courses = corsiService.getAllCourses();
        }
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/{teacherId}")
    public ResponseEntity<Corsi> createCourseWithTeacher(@RequestBody Corsi course, @PathVariable Long teacherId) {
        Corsi createdCourse = corsiService.createCourseWithTeacher(course, teacherId);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}/assign-teacher/{teacherId}")
    public ResponseEntity<Corsi> assignTeacherToCourse(@PathVariable int courseId, @PathVariable Long teacherId) {
        Corsi updatedCourse = corsiService.assignTeacherToCourse(courseId, teacherId);
        return ResponseEntity.ok(updatedCourse);
    }

    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<String> enrollStudentInCourse(@PathVariable Long studentId, @PathVariable int courseId) {
        corsiService.enrollStudentInCourse(studentId, courseId);
        return new ResponseEntity<>("Enrollment successful", HttpStatus.OK);
    }
}
