package com.idea.gestioneCorsi.service;

import com.idea.gestioneCorsi.entity.Corsi;
import com.idea.gestioneCorsi.entity.Utente;
import com.idea.gestioneCorsi.repository.CorsiRepository;
import com.idea.gestioneCorsi.repository.UtenteRepository;
import com.idea.gestioneCorsi.utils.CourseNotFoundException;
import com.idea.gestioneCorsi.utils.UserNotFoundException;
import com.idea.gestioneCorsi.utils.ValidationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsiService {
    private final CorsiRepository corsiRepository;
    private final UtenteRepository userRepository;

    @Autowired
    public CorsiService(CorsiRepository corsiRepository, UtenteRepository userRepository) {
        this.corsiRepository = corsiRepository;
        this.userRepository = userRepository;
    }

    public List<Corsi> getAllCourses() {
        return corsiRepository.findAll();
    }

    public List<Corsi> getAllCoursesOrderedBy(String orderBy) {
        Sort sort = Sort.by(orderBy);
        return corsiRepository.findAll(sort);
    }
    public Corsi createCourseWithTeacher(Corsi course, Long teacherId) {
        Utente teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new UserNotFoundException(teacherId));

        // Check if user is an 'Insegnante'
        if (!teacher.getType().equals(Utente.UserType.INSEGNANTE)) {
            throw new ValidationErrorException("User is not a teacher");
        }

        // Check if teacher is already managing 3 courses
        long count = corsiRepository.countByTeacher(teacher);
        if (count >= 3) {
            throw new ValidationErrorException("A teacher can manage up to 3 courses only");
        }

        course.setTeacher(teacher);
        return corsiRepository.save(course);
    }

    public Corsi assignTeacherToCourse(int courseId, Long userId) {
        Corsi course = corsiRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Utente teacher = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Teacher not found"));

        course.setTeacher(teacher);
        return corsiRepository.save(course);
    }
    public void enrollStudentInCourse(Long studentId, int courseId) {
        Utente student = userRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException(studentId));
        Corsi course = corsiRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        // Check if student is already enrolled in the course
        if(student.getCourses().contains(course)) {
            throw new ValidationErrorException("Student is already enrolled in this course");
        }
        if(course.getTeacher() == null) {
            throw new ValidationErrorException("Cannot enroll in a course without an assigned teacher");
        }
        int totalCourses = corsiRepository.findAll().size();
        int coursesEnrolled = student.getCourses().size();

        if (totalCourses > 6 && coursesEnrolled < 5) {
            student.getCourses().add(course);
        } else if (coursesEnrolled < 3) {
            student.getCourses().add(course);
        } else {
            throw new ValidationErrorException("Course enrollment limit reached");
        }

        userRepository.save(student);
    }
}
