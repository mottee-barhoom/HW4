/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author M M BARHOOM
 */
@RestController
@RequestMapping("/student")
public class studentController {

    private List<Student> students = new ArrayList<>();

    @GetMapping
    public List<Student> findAll() {
        return students;
    }

    @GetMapping("/{id}")
    public Student findById(@RequestParam("id") Long id) {
        return students.stream().filter(it -> it.getId().equals(id)).findFirst().get();
    }

    @GetMapping("/{name}")
    public List<Student> findByName(@RequestParam("name") String name) {
        return students.stream().filter(it -> it.getName().equals(name)).collect(Collectors.toList());
    }

    @PostMapping
    public Student add(@RequestBody Student s) {
        long index;
        if (students.size() > 0) {
            index = students.get(students.size() - 1).getId();
        } else {
            index = 0;
        }
        s.setId(index + 1);
        students.add(s);
        return s;
    }
    
     @DeleteMapping("/{id}")
    public void delete(@RequestParam("id") Long id) {
        List<Student> p = students.stream().filter(it -> it.getId().equals(id)).collect(Collectors.toList());
        students.removeAll(p);
    }

}
