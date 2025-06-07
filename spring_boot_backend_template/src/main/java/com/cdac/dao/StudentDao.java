package com.cdac.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.entity.Student;

public interface StudentDao extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    @Query("SELECT s FROM Student s JOIN s.enrolledCourses c WHERE c.id = :courseId")
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
    
    @Query("SELECT s FROM Student s JOIN s.enrolledCourses c WHERE c.staff.id = :staffId")
    List<Student> findStudentsByStaffId(@Param("staffId") Long staffId);
	Optional<Student> findByEmailAndPassword(String email, String password);
} 
