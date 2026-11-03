package com.codecool.vizsgaremek.IT;

import com.codecool.vizsgaremek.modell.Class;
import com.codecool.vizsgaremek.modell.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class TeacherTest {

    @LocalServerPort
    private int port;
    private String baseUrl;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/teacher";
    }

    @Test
    public void addNewTeacher() {
        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        Teacher result = testRestTemplate.postForObject(baseUrl + "/add", testTeacher, Teacher.class);
        assertEquals(testTeacher.getName(), result.getName());
    }

    @Test
    public void getTeachers() {
        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        testRestTemplate.postForObject(baseUrl + "/add", testTeacher, Teacher.class);
        List<Teacher> teacherList = List.of(testRestTemplate.getForObject(baseUrl + "/getAll", Teacher[].class));
        assertEquals(1, teacherList.size());
    }

    @Test
    public void getTeacherById() {
        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        testTeacher = testRestTemplate.postForObject(baseUrl + "/add", testTeacher, Teacher.class);
        Teacher result = testRestTemplate.getForObject(baseUrl + "/" + testTeacher.getId(), Teacher.class);
        assertEquals(testTeacher.getId(), result.getId());
    }

    @Test
    public void update() {
        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        testTeacher = testRestTemplate.postForObject(baseUrl + "/add", testTeacher, Teacher.class);
        testTeacher.setName("Updated name");
        testRestTemplate.put(baseUrl + "/update/" + testTeacher.getId(), testTeacher);
        Class updatedClass = testRestTemplate.getForObject(baseUrl +"/"+ testTeacher.getId(), Class.class);
        assertEquals("Updated name", updatedClass.getName());
    }

    @Test
    public void delete() {
        Teacher testTeacher1 = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        Teacher testTeacher2 = new Teacher(2L, "Erős Béla", "SQL", "Male");
        Teacher testTeacher3 = new Teacher(3L, "Erős Géza", "SPRING", "Male");

        List<Teacher> testTeachers = new ArrayList<>();

        testTeachers.add(testTeacher1);
        testTeachers.add(testTeacher2);
        testTeachers.add(testTeacher3);

        testTeachers.forEach(testTeacher -> testTeacher.setId(testRestTemplate.postForObject(baseUrl + "/add", testTeacher, Teacher.class).getId())
        );

        testRestTemplate.delete(baseUrl + "/delete/" + testTeacher2.getId());
        testTeachers.remove(testTeacher2);

        List<Teacher> remainingTeachers = List.of(testRestTemplate.getForObject(baseUrl + "/getAll", Teacher[].class));

        assertEquals(testTeachers.size(), remainingTeachers.size());

        for (int i = 0; i < testTeachers.size(); i++) {
            assertEquals(testTeachers.get(i).getName(), remainingTeachers.get(i).getName());
        }
    }
}
