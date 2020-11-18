package io.x16fd16b.assignment03.school.starter;

import io.x16fd16b.assignment03.school.starter.service.SchoolService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class SchoolAutoConfigurationTest {
    @Autowired
    private School school;
    @Autowired
    private SchoolService schoolService;

    @Test
    public void testSchoolWire() {
        School expect = new School();
        Klass expectClass = new Klass();
        Student classStudent = new Student();
        classStudent.setId(1);
        classStudent.setName("David Liu");
        expectClass.setStudents(Collections.singletonList(classStudent));
        expectClass.setId(1);
        expectClass.setName("class 01");
        expect.setName("Java Enhancement");
        expect.setKlasses(Collections.singletonList(expectClass));
        Assert.assertEquals(expect, school);
    }

    @Test
    public void testSchoolService() {
        schoolService.print(school);
    }
}