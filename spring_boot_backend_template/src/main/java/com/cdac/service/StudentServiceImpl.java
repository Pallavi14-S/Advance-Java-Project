package com.cdac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dao.CourseDao;
import com.cdac.dao.StudentDao;
import com.cdac.dto.CourseRespDTO;
import com.cdac.dto.StudentLoginRequestDTO;
import com.cdac.dto.StudentRequestDTO;
import com.cdac.dto.StudentResponseDTO;
import com.cdac.entity.Course;
import com.cdac.entity.Student;
import com.cdac.entity.UserRole;
import com.cdac.custom_exceptions.AuthenticationException;
import com.cdac.custom_exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final ModelMapper modelMapper;
    

    @Override
    public StudentResponseDTO registerStudent(StudentRequestDTO studentDTO) {
        if (studentDao.existsByEmail(studentDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        Student student = modelMapper.map(studentDTO, Student.class);
        UserRole r=null;
        student.setRole(r.STUDENT);
        Student savedStudent = studentDao.save(student);
        return modelMapper.map(savedStudent, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO getStudentDetails(Long studentId) {
        Student student = studentDao.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO updateStudentDetails(Long studentId, StudentRequestDTO studentDTO) {
        Student student = studentDao.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        
        // Check if email is being changed and if it already exists
        if (!student.getEmail().equals(studentDTO.getEmail()) && 
            studentDao.existsByEmail(studentDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        modelMapper.map(studentDTO, student);
        Student updatedStudent = studentDao.save(student);
        return modelMapper.map(updatedStudent, StudentResponseDTO.class);
    }

    @Override
    public List<CourseRespDTO> getEnrolledCourses(Long studentId) {
        Student student = studentDao.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        
        return student.getEnrolledCourses().stream()
                .map(course -> modelMapper.map(course, CourseRespDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO enrollInCourse(Long studentId, Long courseId) {
        Student student = studentDao.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        
        Course course = courseDao.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        
        student.addCourse(course);
        Student updatedStudent = studentDao.save(student);
        return modelMapper.map(updatedStudent, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO unenrollFromCourse(Long studentId, Long courseId) {
        Student student = studentDao.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        
        Course course = courseDao.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        
        student.removeCourse(course);
        Student updatedStudent = studentDao.save(student);
        return modelMapper.map(updatedStudent, StudentResponseDTO.class);
    }

    @Override
    public List<StudentResponseDTO> getEnrollStudent(Long courseId) {
        List<Student> students = studentDao.findStudentsByCourseId(courseId);
        return students.stream()
                       .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                       .toList();
    }

    @Override
    public StudentResponseDTO loginStudent(StudentLoginRequestDTO loginDTO) {
        Student student = studentDao.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword())
                .orElseThrow(() -> new AuthenticationException("Invalid email or password"));
        return modelMapper.map(student, StudentResponseDTO.class);
    }
} 
