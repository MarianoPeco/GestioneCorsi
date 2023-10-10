package com.idea.gestioneCorsi.utils;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(int courseId) {
        super("Course with ID " + courseId + " was not found.");
    }
}