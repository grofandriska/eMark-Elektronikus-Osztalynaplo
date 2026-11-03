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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull(message = "Student must have a class!")
    private Class className;
    @NotBlank(message = "Full name is mandatory")
    private String studentName;
    @NotBlank(message = "Gender name is mandatory")
    private String gender;
}
