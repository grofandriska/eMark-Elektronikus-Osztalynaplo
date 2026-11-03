package com.codecool.vizsgaremek.modell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double mark;
    @NotBlank(message = "Subject name is mandatory and must match with the teacher's subject")
    private String subject;
    @NotBlank(message = "Month name is mandatory")
    private String month;
    @ManyToOne
    @NotNull(message = "Student id must be given ")
    private Student student;
    @ManyToOne
    @NotNull(message = "Please add teacher ,and check it's subject too ")
    private Teacher teacher;
}
