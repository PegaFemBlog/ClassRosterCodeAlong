/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.controller;

import com.mycompany.classroster.dao.ClassRosterPersistenceException;
import com.mycompany.classroster.dto.Student;
import com.mycompany.classroster.service.ClassRosterDataValidationException;
import com.mycompany.classroster.service.ClassRosterDuplicateIdException;
import com.mycompany.classroster.service.ClassRosterService;
import com.mycompany.classroster.ui.ClassRosterView;
import com.mycompany.classroster.ui.UserIO;
import com.mycompany.classroster.ui.UserIOConsoleImpl;
import java.util.List;



//The controller doesn't do any of this work - it will have the view and
//the DAO do all the heavy lilfting
/**
 *
 * @author bacag
 */
public class ClassRosterController {
    //declarations
    private ClassRosterService service;
    private ClassRosterView view;
    
    //constructor that initializaes the dao and view
    public ClassRosterController(ClassRosterService service, ClassRosterView view) {
        this.service = service;
        this.view = view;
    }
 
    private final UserIO io = new UserIOConsoleImpl();

    //method to "run" my menu selection  
    public void run() throws ClassRosterPersistenceException, ClassRosterDuplicateIdException {
	        boolean keepGoing = true;
	        int menuSelection = 0;
                try {
	        while (keepGoing) {
                    menuSelection = getMenuSelection();//added this
	            switch (menuSelection) {
	                case 1:
	                    listStudents();
	                    break;
	                case 2:
	                    createStudent();
	                    break;
	                case 3:
	                    viewStudent();
	                    break;
	                case 4:
	                    removeStudent();
	                    break;
	                case 5:
	                    keepGoing = false;
	                    break;
	                default:
	                    unknownCommand();
                    }
	            }
                    exitMessage();
	        } 
            catch (ClassRosterPersistenceException e) {
                view.displayErrorMessage(e.getMessage());  
	    } 
    }
     private int getMenuSelection() {
	return view.printMenuAndGetSelection();
	    }
     
     //Method to Create a student
     private void createStudent() throws ClassRosterPersistenceException, ClassRosterDuplicateIdException{   
         view.displayCreateStudentBanner();
         boolean hasErrors = false;
         
         do {
             Student currentStudent = view.getNewStudentInfo();
             try {
                 service.createStudent(currentStudent); 
                 view.displayCreateSuccessBanner();
                 hasErrors = false;
             } catch(ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {        
                 hasErrors = true;
                 view.displayErrorMessage(e.getMessage()); 
             }  
        }while (hasErrors); 
     }
     
      private void listStudents() throws ClassRosterPersistenceException {
          view.displayDisplayAllBanner();
          List<Student> studentList = service.getAllStudents();
          view.displayStudentList(studentList); 
      }
      private void viewStudent() throws ClassRosterPersistenceException {
          view.displayDisplayStudentBanner();
          String studentId = view.getStudentIdChoice();
          Student student = service.getStudent(studentId);
          view.displayStudent(student);
      }
      private void removeStudent() throws ClassRosterPersistenceException {
          view.displayRemoveStudentBanner();
          String studentId = view.getStudentIdChoice();
          service.removeStudent(studentId);
          view.displayRemoveSuccessBanner();
      }
      private void unknownCommand() throws ClassRosterPersistenceException {
          view.displayUnknownCommandBanner();
      }
      private void exitMessage() throws ClassRosterPersistenceException {
          view.displayExitBanner();
      }
}
