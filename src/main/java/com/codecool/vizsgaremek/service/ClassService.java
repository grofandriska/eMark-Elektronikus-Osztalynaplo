package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.exception.types.ClassException;
import com.codecool.vizsgaremek.modell.Class;
import com.codecool.vizsgaremek.repository.ClassRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public Class addClass(Class newClass) {
        return classRepository.save(newClass);
    }

    public List<Class> listAllClass() {
        return classRepository.findAll();
    }

    public Class getClassByName(String className) {
        return classRepository.findByName(className).orElseThrow(() -> new ClassException(className));
    }

    public Class getClassById(Long id) {
        return classRepository.findById(id).orElseThrow(() -> new ClassException(id));
    }

    public void deleteClass(Long id) {
        classRepository.delete(getClassById(id));
    }

    public Class updateClass(Long id, Class update) {
        Class current = getClassById(id);
        current.setName(update.getName());
        return classRepository.save(current);
    }
}
