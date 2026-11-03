package com.codecool.vizsgaremek.controller;

import com.codecool.vizsgaremek.modell.Class;
import com.codecool.vizsgaremek.service.ClassService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


//TODO: catch exc here
@RestController
@RequestMapping("/class")
public class ClassController {

    ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("getAllClass")
    public List<Class> listAll() {
        return classService.listAllClass();
    }

    @PostMapping("/add")
    public Class add(@Valid @RequestBody Class classEntity, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            bindingResult
                    .getAllErrors()
                    .forEach(errorObj -> System.out.println(errorObj.getDefaultMessage()));
        }
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
    public void update(@PathVariable Long id,
                       @Valid @RequestBody Class classEntity,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult
                    .getAllErrors()
                    .forEach(errorObj -> System.out.println(errorObj.getDefaultMessage()));
        }
        classService.updateClass(id, classEntity);
    }
}
