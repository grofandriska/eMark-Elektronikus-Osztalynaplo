package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.exception.types.StudentException;
import com.codecool.vizsgaremek.mapper.StudentMapper;
import com.codecool.vizsgaremek.modell.Student;

import com.codecool.vizsgaremek.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService {

    private StudentRepository studentRepository;

    private StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    public Student getStudentById(Long id) {

        Student studentResponse;
        List<Student> studentResponseList;
        studentResponseList = studentRepository.findAll();
        for (Student student : studentResponseList) {
            if (student.getId().equals(id)) {
                studentResponse = student;
                log.info("Student found! id: " + id);
                return studentResponse;
            }
        }
        log.info("(!GetStudentByID!) something went wrong when looking for id:" + id);
        throw new StudentException(id);
    }

    public void deleteStudent(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (StudentException e) {
            throw new StudentException(id);
        }
    }

    public void updateStudent(Long id, Student studentUpdate) {
        try {
            studentRepository.findById(id).map(student -> {
                student.setClassName(studentUpdate.getClassName());
                student.setStudentName(studentUpdate.getStudentName());
                student.setGender(studentUpdate.getGender());
                return studentRepository.save(student);
            });
        } catch (StudentException e) {
            System.out.println(e);
            throw new StudentException(id);
        }
    }

    public List<Student> getStudentsByClassId(Long id) {
        List<Student> list = studentRepository.findAll();
        List<Student> response = new ArrayList<>();
        for (Student student : list) {
            if (student.getClassName().getId().equals(id)) {
                response.add(student);
                log.info(student.getStudentName());
            }
        }
        return response;
    }

    public List<Student> getStudentsByGender(String gender) {
        List<Student> list = studentRepository.findAll();
        List<Student> response = new ArrayList<>();
        for (Student student : list) {
            if (student.getGender().equals(gender)) {
                response.add(student);
            }
        }
        return response;
    }
}


