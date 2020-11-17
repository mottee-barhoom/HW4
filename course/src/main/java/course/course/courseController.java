/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author M M BARHOOM
 */
@RestController
@RequestMapping("/course")

public class courseController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    List <Course> courses = new ArrayList<>();
    
    @GetMapping
    public List<Course> findAll() {
        return courses;
    }
    
    @PostMapping
    public Course add(@RequestBody Course c) {
        long index;
        if (courses.size() > 0) {
            index = courses.get(courses.size() - 1).getId();
        } else {
            index = 0;
        }
        c.setId(index + 1);
        courses.add(c);
        return c;
    }
    @GetMapping("/{course-name}")
    public List<Student> findStudentsByCoursename(@RequestParam("course-name") String name) {
        List<Student> students = new ArrayList<>();
        List <Course> course_s = courses.stream().filter(co -> co.getName().equals(name)).collect(Collectors.toList()); 
        course_s.stream().forEach(co -> co.getStudentid().forEach((id) -> students.add(restTemplate.getForObject("http://student-service/student/{id}?id="+id, Student.class))));
        return students;
    }
    
    @GetMapping("/{student-name}")
    public List<Course> findCoursenameByStudent(@RequestParam("student-name") String name) {
        Student[] student_s = restTemplate.getForObject("http://student-service/student/{name}?name="+name, Student[].class);
        List <Course> course_s = new ArrayList<>();
        for (Student s : student_s)
        {
            course_s.addAll(courses.stream().filter(it -> it.getStudentid().contains(s.getId())).collect(Collectors.toList()));
        }
        List <Course> coursesWithoutFrequent = course_s.stream().distinct().collect(Collectors.toList());
        return coursesWithoutFrequent;
    }
    
    
}
