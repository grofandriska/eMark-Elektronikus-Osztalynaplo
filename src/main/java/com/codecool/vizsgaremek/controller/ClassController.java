package com.codecool.vizsgaremek.controller;

import com.codecool.vizsgaremek.modell.Class;
import com.codecool.vizsgaremek.service.ClassService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("getAllClass")
    public List<Class> listAll() {
        return classService.listAllClass();
    }

    @PostMapping("/add")
    public Class add(@Valid @RequestBody Class classEntity)  {
        return classService.addClass(classEntity);
    }

    @DeleteMapping("deleteClass/{id}")
    public void delete(@PathVariable("id") Long id) {
        classService.deleteClass(id);
    }

    @GetMapping("getClassById/{id}")
    public Class getClassById (@PathVariable Long id) {
        return classService.getClassById(id);
    }

    @GetMapping("getClassByName/{name}")
    public Class getClassByName(@PathVariable("name") String name) {
        return classService.getClassByName(name);
    }

    @PutMapping("/updateClass/{id}")
    public Class update(@PathVariable Long id, @Valid @RequestBody Class classEntity) {
        return classService.updateClass(id, classEntity);
    }
}
