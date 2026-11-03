package com.codecool.vizsgaremek.repository;

import com.codecool.vizsgaremek.modell.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    public List<Student> findByGender(String gender);
    List<Student> findByClassNameId(Long classId);

}
