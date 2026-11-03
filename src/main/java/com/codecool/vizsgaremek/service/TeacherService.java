package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.exception.types.TeacherException;
import com.codecool.vizsgaremek.mapper.TeacherMapper;
import com.codecool.vizsgaremek.modell.Teacher;
import com.codecool.vizsgaremek.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TeacherService {

    private TeacherRepository teacherRepository;


    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacherById(Long id, Teacher teacherUpdate) {
        Teacher teacher = getTeacherById(id);
        teacher.setName(teacherUpdate.getName());
        teacher.setSubject(teacherUpdate.getSubject());
        teacher.setGender(teacherUpdate.getGender());
        return teacherRepository.save(teacher);
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.delete(getTeacherById(id));
    }

    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new TeacherException(id));
    }
}
