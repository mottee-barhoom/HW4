/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.course;

import java.util.List;

/**
 *
 * @author M M BARHOOM
 */
public class Course {
     private Long id;
    private String name;
    private List<Long> studentIDs;

    public Course() {
    }

    public Course(Long id, String name, List<Long> studentid) {
        this.id = id;
        this.name = name;
        this.studentIDs = studentid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getStudentid() {
        return studentIDs;
    }

    public void setStudentid(List<Long> studentid) {
        this.studentIDs = studentid;
    }

    
}
