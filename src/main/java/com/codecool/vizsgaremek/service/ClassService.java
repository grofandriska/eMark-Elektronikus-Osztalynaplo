package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.exception.types.ClassException;
import com.codecool.vizsgaremek.modell.Class;
import com.codecool.vizsgaremek.repository.ClassRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public Class addClass(Class newClass) {
        return classRepository.save(newClass);
    }

    public List<Class> listAllClass() {
        return classRepository.findAll();
    }

    public Class getClassByName(String className) /*throws */{
        Class classResponse = classRepository.findByName(className);
        if (classResponse == null) {
            log.info("  (!getClassByName!)  Class not found by :" + className);
            throw new RuntimeException("Can't get class by this name !"); //placeholder for checked exception
        }
        return classResponse;
    }

    //TODO: IfPresent
    public Class getClassById(Long id) {
        try {
            Class classResponse;
            classResponse = classRepository.findById(id).get();
            return classResponse;
        } catch (RuntimeException e) {
            log.info("Class not found ! "+ e );
        }
        throw new ClassException(id);
    }

    public void deleteClass(long id) {
        try {
            classRepository.deleteById(id);
        } catch (ClassException e) {
            log.info("Can't delete class , with student's inside !yet...");
            log.info(e.getMessage());
            throw new ClassException(id);
        }
    }

    public void updateClass(Long id, Class update) {
        try {
            classRepository.findById(id).map(classE -> {
                classE.setId(update.getId());
                classE.setName(update.getName());
                return classRepository.save(classE);
            });

        } catch (ClassException e) {
            log.info("Couldn't update " + id + "id class , see details.");
            System.out.println(e);
            throw new ClassException(id);
        }
    }

}
