/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.service;

import com.mycompany.classroster.dao.ClassRosterAuditDao;
import com.mycompany.classroster.dao.ClassRosterDao;
import com.mycompany.classroster.dao.ClassRosterPersistenceException;
import com.mycompany.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author bacag
 */
public class ClassRosterServiceImpl implements ClassRosterService {

    private ClassRosterDao dao;
    private ClassRosterAuditDao auditDao;
    
    public ClassRosterServiceImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    
    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, 
            ClassRosterDataValidationException, ClassRosterPersistenceException { 
        //THIS METHOD ALLOWS ME TO CHECK IF THE STUDENT DOESN'T ALREADY EXIST
        //USING THE MAP KEY --> STUDENT ID
        //IF IT DOES, THROW THE EXCEPTION AND DONE
        //IF NOT...
        if (dao.getStudent(student.getStudentId()) != null) {
        throw new ClassRosterDuplicateIdException ("ERROR: Could not create student. Student Id "
        + student.getStudentId()
        + " already exists.");
    }
        //THEN VALIDATE STUDENT DATA 
        validateStudentData(student);
        dao.createStudent(student.getStudentId(), student);
        
        //WHEN STUDENT IS ADDED
        auditDao.writeAuditEntry("Student " + student.getStudentId() + " has been created.");
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
          Student removedStudent = dao.removeStudent(studentId);
          auditDao.writeAuditEntry("Student "+ studentId + "REMOVED");
          return removedStudent;
    }
    
    private void validateStudentData(Student student) throws ClassRosterDataValidationException {
        if (student.getFirstName() == null 
                || student.getFirstName().trim().length() == 0
                || student.getLastName() == null
                || student.getLastName().trim().length() == 0
                || student.getCohort() == null
                || student.getCohort().trim().length() == 0) {
            throw new ClassRosterDataValidationException("ERROR: All fields [First name, Last name, and Cohort] are required.");
        }
    }

   

}
