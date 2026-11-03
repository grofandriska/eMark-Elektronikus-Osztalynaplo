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

    private TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public Teacher saveTeacher(Teacher teacher) {
        Teacher teacherSave ;
        try {
            teacherSave = teacherRepository.save(teacher);
        } catch (RuntimeException e) {
            log.info("Teacher can not be added" + e.getMessage());
            throw new RuntimeException("Can't save teacher !");
        }
        return teacherSave;
    }

    public void updateTeacherById(Long id, Teacher teacherUpdate) {
        try {
            Teacher teacher = teacherRepository.findById(id).get();
            teacher.setName(teacherUpdate.getName());
            teacher.setSubject(teacherUpdate.getSubject());
            teacher.setGender(teacherUpdate.getGender());
            teacherRepository.save(teacher);
        } catch (TeacherException e) {
            log.info("Something went wrong when updating Teacher by id :" + id);
            log.info("See details :" + e);
            throw new TeacherException(id);
        }
    }

    public void deleteTeacherById(Long id) {
        try {
            teacherRepository.deleteById(id);
        } catch (TeacherException e) {
            log.info("Something went wrong when deleting Teacher by id :" + id);
            log.info("See details :" + e);
            throw new TeacherException(id);
        }
    }

    public List<Teacher> getAllTeacher() {
        List<Teacher> teacherListResponse = new ArrayList<>();
        try {
            List<Teacher> teacherList = teacherRepository.findAll();
            for (Teacher teacher : teacherList) {
                teacherListResponse.add(teacher);
            }
            return teacherListResponse;
        } catch (RuntimeException e) {
            log.info("Can't access teachers. See details: " + e);
            log.info(e.getMessage());
        }
        return teacherListResponse;
    }

    public Teacher getTeacherById(Long id) {
        Teacher teacherResponse;
        List<Teacher> teacherResponseList;
        teacherResponseList = teacherRepository.findAll();
        for (Teacher teacher : teacherResponseList) {
            if (teacher.getId().equals(id)) {
                teacherResponse = teacher;
                log.info("Class Found! id: " + id);
                return teacherResponse;
            }
        }
        log.info("(!GetTeacherByID!) something went wrong when looking for id:" + id);
        throw new TeacherException(id);
    }
}
