/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.service;

import com.mycompany.classroster.dao.ClassRosterDao;
import com.mycompany.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author bacag
 */
public class ClassRosterServiceImpl implements ClassRosterDao {
    
    ClassRosterDao dao;
    
    public ClassRosterServiceImpl(ClassRosterDao injectedDao) {
        this.dao = injectedDao;
    }
    
    @Override
    public Student addStudent(Integer studentId, Student student) {
        return dao.addStudent(studentId, student);
    }

    @Override
    public List<Student> getAllStudents() {
       return dao.getAllStudents();
    }

    @Override
    public Student getStudent(Integer studentId) {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(Integer studentId) {
        return dao.removeStudent(studentId);
    }
    
}
