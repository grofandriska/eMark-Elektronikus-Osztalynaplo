package com.codecool.vizsgaremek.IT;

import com.codecool.vizsgaremek.modell.Class;
import com.codecool.vizsgaremek.modell.Mark;
import com.codecool.vizsgaremek.modell.Student;
import com.codecool.vizsgaremek.modell.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class MarkTest {

    @LocalServerPort
    private int port;
    private String baseUrl;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/mark";
    }

    @Test
    public void addNewMark() {
        Class testClass = new Class(1L, "1.A");
        testRestTemplate.postForObject("http://localhost:" + port + "/class/add", testClass, Class.class);

        Student studentTest = new Student(1L, testClass, "Kis Géza", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/student/add", studentTest, Student.class);

        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/teacher/add", testTeacher, Teacher.class);

        Mark mark = new Mark(null, 3.0, "JAVA", "June", studentTest, testTeacher);
        Mark result = testRestTemplate.postForObject(baseUrl + "/add", mark, Mark.class);

        assertNotNull(result.getId());
        assertEquals(3.0, result.getMark());
        assertEquals("JAVA", result.getSubject());
    }

    @Test
    public void addMarkWithWrongSubjectReturnsBadRequest() {
        Class testClass = new Class(1L, "1.A");
        testRestTemplate.postForObject("http://localhost:" + port + "/class/add", testClass, Class.class);

        Student studentTest = new Student(1L, testClass, "Kis Géza", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/student/add", studentTest, Student.class);

        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/teacher/add", testTeacher, Teacher.class);

        Mark mark = new Mark(null, 3.0, "MATH", "June", studentTest, testTeacher);
        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl + "/add", mark, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getMarkById() {
        Class testClass = new Class(1L, "1.A");
        testRestTemplate.postForObject("http://localhost:" + port + "/class/add", testClass, Class.class);

        Student studentTest = new Student(1L, testClass, "Kis Géza", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/student" + "/add", studentTest, Student.class);

        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/teacher" + "/add", testTeacher, Teacher.class);

        Mark mark = new Mark(4L, 3.0, "JAVA", "June", studentTest, testTeacher);
        testRestTemplate.postForObject(baseUrl + "/add", mark, Mark.class);
        Mark temp = testRestTemplate.getForObject(baseUrl + "/getMark/" + mark.getId(), Mark.class);
        assertEquals(temp.getId(), temp.getId());

    }

    @Test
    public void update() {
        Class testClass = new Class(1L, "1.A");
        testRestTemplate.postForObject("http://localhost:" + port + "/class/add", testClass, Class.class);

        Student studentTest = new Student(1L, testClass, "Kis Géza", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/student" + "/add", studentTest, Student.class);

        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/teacher" + "/add", testTeacher, Teacher.class);

        Mark mark = new Mark(null, 3.0, "JAVA", "June", studentTest, testTeacher);
        mark = testRestTemplate.postForObject(baseUrl + "/add", mark, Mark.class);

        mark.setMonth("December");
        testRestTemplate.put(baseUrl + "/update/" + mark.getId(), mark);

        Mark temp = testRestTemplate.getForObject(baseUrl + "/getMark/" + mark.getId(), Mark.class);
        assertEquals("December", temp.getMonth());
    }

    @Test
    public void delete() {
        Class testClass = new Class(1L, "1.A");
        testRestTemplate.postForObject("http://localhost:" + port + "/class/add", testClass, Class.class);
        Student studentTest = new Student(1L, testClass, "Kis Géza", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/student" + "/add", studentTest, Student.class);
        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/teacher" + "/add", testTeacher, Teacher.class);

        Mark mark = new Mark(null, 3.0, "JAVA", "June", studentTest, testTeacher);
        mark = testRestTemplate.postForObject(baseUrl + "/add", mark, Mark.class);
        Mark mark2 = new Mark(null, 3.0, "JAVA", "June", studentTest, testTeacher);
        mark = testRestTemplate.postForObject(baseUrl + "/add", mark, Mark.class);


        List<Mark> markList = new ArrayList<>();

        markList.add(mark);
        markList.add(mark2);

        markList.forEach(markX -> markX.setId(testRestTemplate.postForObject(baseUrl + "/add", markX, Mark.class).getId())
        );

        testRestTemplate.delete(baseUrl + "/delete/" + mark2.getId());
        markList.remove(mark2);

        List<Mark> remainingMarks = List.of(testRestTemplate.getForObject(baseUrl + "/getAll", Mark[].class));

        assertEquals(markList.size(), remainingMarks.size());

        for (int i = 0; i < markList.size(); i++) {
            assertEquals(markList.get(i).getMark(), remainingMarks.get(i).getMark());
        }
    }

    @Test
    public void getAverage() {
        Class testClass = new Class(1L, "1.A");
        testRestTemplate.postForObject("http://localhost:" + port + "/class/add", testClass, Class.class);

        Student studentTest = new Student(1L, testClass, "Kis Géza", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/student" + "/add", studentTest, Student.class);

        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/teacher" + "/add", testTeacher, Teacher.class);

        Mark mark = new Mark(null, 5.0, "JAVA", "June", studentTest, testTeacher);
        testRestTemplate.postForObject(baseUrl + "/add", mark, Mark.class);

        Mark markTwo = new Mark(null, 3.0, "JAVA", "June", studentTest, testTeacher);
        testRestTemplate.postForObject(baseUrl + "/add", markTwo, Mark.class);

        Double temp = testRestTemplate.getForObject(baseUrl + "/average/" + studentTest.getId(), Double.class);
        assertEquals(4, temp);
    }@Test

    public void getAverageBySubject() {
        Class testClass = new Class(1L, "1.A");
        testRestTemplate.postForObject("http://localhost:" + port + "/class/add", testClass, Class.class);

        Student studentTest = new Student(1L, testClass, "Kis Géza", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/student" + "/add", studentTest, Student.class);

        Teacher testTeacher = new Teacher(1L, "Erős Pista", "JAVA", "Male");
        testRestTemplate.postForObject("http://localhost:" + port + "/teacher" + "/add", testTeacher, Teacher.class);

        Mark mark = new Mark(null, 5.0, "JAVA", "June", studentTest, testTeacher);
        testRestTemplate.postForObject(baseUrl + "/add", mark, Mark.class);

        Mark markTwo = new Mark(null, 3.0, "JAVA", "June", studentTest, testTeacher);
        testRestTemplate.postForObject(baseUrl + "/add", markTwo, Mark.class);

        Double temp = testRestTemplate.getForObject(baseUrl + "/get/"+studentTest.getId()+"/"+testTeacher.getSubject() , Double.class);
        assertEquals(4, temp);
    }


}
