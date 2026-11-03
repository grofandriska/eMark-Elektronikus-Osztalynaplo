package com.codecool.vizsgaremek.controller;

import com.codecool.vizsgaremek.modell.Mark;
import com.codecool.vizsgaremek.service.MarkService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mark")
public class MarkController {

    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping("getAll")
    public List<Mark> getMarkList() {
        return markService.getMarkList();
    }

    @PostMapping("add")
    public Mark add(@Valid @RequestBody Mark mark) {
        return markService.add(mark);
    }

    @GetMapping("average/{id}")
    public Double getStudentAverage(@PathVariable Long id) {
        return markService.getStudentAverage(id);
    }

    @GetMapping("getMark/{id}")
    public Mark getMarkById(@PathVariable Long id) {
        return markService.getMarkById(id);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        markService.deleteMarkByID(id);
    }

    @PutMapping("update/{id}")
    public Mark update(@PathVariable Long id, @Valid @RequestBody Mark mark) {
        return markService.update(id, mark);
    }

    @GetMapping("get/{id}/{subject}")
    public Double getStudentAverageBySubject(@PathVariable Long id,
                                             @PathVariable String subject) {
        return markService.getStudentAverageBySubject(id, subject);
    }

    @GetMapping("get/{id}/{subject}/{month}")
    public Double getStudentAverageBySubjectAndMonth(@PathVariable Long id,
                                                     @PathVariable String subject,
                                                     @PathVariable String month) {
        return markService.getStudentAverageBySubjectAndMonth(id, subject, month);
    }
}
