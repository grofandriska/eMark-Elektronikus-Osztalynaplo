package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.exception.types.MarkException;
import com.codecool.vizsgaremek.exception.types.NoMarksFoundException;
import com.codecool.vizsgaremek.exception.types.SubjectMismatchException;
import com.codecool.vizsgaremek.exception.types.TeacherException;
import com.codecool.vizsgaremek.mapper.MarkMapper;
import com.codecool.vizsgaremek.modell.Mark;
import com.codecool.vizsgaremek.modell.Teacher;

import com.codecool.vizsgaremek.repository.MarkRepository;
import com.codecool.vizsgaremek.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MarkService {

    private MarkRepository markRepository;
    private TeacherRepository teacherRepository;

    public MarkService(MarkRepository markRepository, TeacherRepository teacherRepository) {
        this.markRepository = markRepository;
        this.teacherRepository = teacherRepository;
    }

    public Mark add(Mark mark) {
        Long teacherId = mark.getTeacher().getId();
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new TeacherException(teacherId));
        if (!teacher.getSubject().equals(mark.getSubject())) {
            throw new SubjectMismatchException(teacher.getSubject(), mark.getSubject());
        }
        return markRepository.save(mark);
    }

    public Mark getMarkById(Long id) {
        return markRepository.findById(id).orElseThrow(() -> new MarkException(id));
    }

    public List<Mark> getMarkList() {
        return markRepository.findAll();
    }

    public void deleteMarkByID(Long id) {
        markRepository.delete(getMarkById(id));
    }

    public Mark update(Long id, Mark markUpdate) {
        Mark mark = getMarkById(id);
        mark.setMark(markUpdate.getMark());
        mark.setMonth(markUpdate.getMonth());
        mark.setStudent(markUpdate.getStudent());
        mark.setSubject(markUpdate.getSubject());
        mark.setTeacher(markUpdate.getTeacher());
        return markRepository.save(mark);
    }

    public double getStudentAverage(Long id) {
        return markRepository.findAll().stream()
                .filter(mark -> mark.getStudent().getId().equals(id))
                .mapToDouble(Mark::getMark)
                .average()
                .orElseThrow(() -> new NoMarksFoundException(id));
    }

    public double getStudentAverageBySubject(Long id, String subject) {
        return markRepository.findAll().stream()
                .filter(mark -> mark.getStudent().getId().equals(id))
                .filter(mark -> mark.getSubject().equals(subject))
                .mapToDouble(Mark::getMark)
                .average()
                .orElseThrow(() -> new NoMarksFoundException(id));
    }

    public double getStudentAverageBySubjectAndMonth(Long id, String subject, String month) {
        return markRepository.findAll().stream()
                .filter(mark -> mark.getStudent().getId().equals(id))
                .filter(mark -> mark.getSubject().equals(subject))
                .filter(mark -> mark.getMonth().equals(month))
                .mapToDouble(Mark::getMark)
                .average()
                .orElseThrow(() -> new NoMarksFoundException(id));
    }

}