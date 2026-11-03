package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.exception.types.StudentException;
import com.codecool.vizsgaremek.mapper.StudentMapper;
import com.codecool.vizsgaremek.modell.Student;

import com.codecool.vizsgaremek.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentException(id));
    }

    public void deleteStudent(Long id) {
        studentRepository.delete(getStudentById(id));
    }

    public Student updateStudent(Long id, Student studentUpdate) {
        Student student = getStudentById(id);
        student.setClassName(studentUpdate.getClassName());
        student.setStudentName(studentUpdate.getStudentName());
        student.setGender(studentUpdate.getGender());
        return studentRepository.save(student);

    }

    public List<Student> getStudentsByClassId(Long id) {
        return studentRepository.findByClassNameId(id);
    }

    public List<Student> getStudentsByGender(String gender) {
        return studentRepository.findByGender(gender);
    }
}


