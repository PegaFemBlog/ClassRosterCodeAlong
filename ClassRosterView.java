/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.ui;

/**
 *
 * @author bacag
 */
public class ClassRosterView {
/**************************************************************************************************************************
                The view is what the user sees and what 
                    gives our program functionality
    
**************************************************************************************************************************/    
    UserIO io = new UserIOConsoleImpl();

                public int printMenuAndGetSelection() {
	            io.print("Main Menu");
	            io.print("1. List Student IDs");
	            io.print("2. Create New Student");
	            io.print("3. View a Student");
	            io.print("4. Remove a Student");
	            io.print("5. Exit");
	            
	           return io.readInt("Please select from the choices.", 1, 5);    
        }
    }

            

//Page #6 of the assignment io.print(student.getStudent() + " ");