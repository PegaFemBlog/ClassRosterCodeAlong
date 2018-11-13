/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.dao;

import com.mycompany.classroster.dto.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author bacag
 */
public class ClassRosterDaoImpl implements ClassRosterDao {
    
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";
    private Map<String, Student> students = new HashMap<>();//this will change for movie library project
    
    //Here we implement all the methods
    @Override
    public Student createStudent(String studentId, Student student) throws ClassRosterPersistenceException {
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
    }
    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        loadRoster();
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        loadRoster();
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }
    
    private void loadRoster() throws ClassRosterPersistenceException {// removed FileNotFoundException 
        Scanner scanner;
        try {
            //Create a scanner for reading the file 
            scanner = new Scanner (new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterPersistenceException("-_- Could not load roster data into memory.", e);
        }
        //currentLine holds the most recent line read from the file
        String currentLine;
        //curentTokens holds each of the parts of the currentLine after it has
        //been split on our DELIMITER
        //NOTE FOR APPRENTICES: In our case we use :: as our delimiter. If 
        //currentLine looks like this:
        //1234::Joe::Smith::Java-September2013
        //then currentTokens will be a string array that looks like this:
        //
	// ___________________________________
	// |    |   |     |                  |
	// |1234|Joe|Smith|Java-September2013|
	// |    |   |     |                  |
	// -----------------------------------
	//  [0]  [1]  [2]         [3]
        String[] currentTokens;
        //Go through ROSTER_FILE line by line, decoding each line into a 
        //Student object
        //Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            //get the next lien in the file
            currentLine = scanner.nextLine();
            //break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            //Create a new Student object and put it into the map of students
            //NOTE FOR APPRENTICICES: We are going to use the sutdent id
            //which is curentTokens[0] as the map keyfor our student object
            //We also have to pass the student id into the Student constructor
            Student currentStudent = new Student(currentTokens[0]);
            //Set the remianing values on the current student manually
            currentStudent.setFirstName(currentTokens[1]);
            currentStudent.setLastName(currentTokens[2]);
            currentStudent.setCohort(currentTokens[3]); 
            //Put currentStudent into themap using the studentID as the key
            students.put(currentStudent.getStudentId(), currentStudent);
        }
        //close scanner
        scanner.close();
    }
        //This method writes all students in the roster out to a ROSTER_FILE.
        //@throws ClassRosterDAOException  isf an error occurs in the writing to the file
        private void writeRoster() throws ClassRosterPersistenceException {
            //NOTE FOR APPRENTICES: we are not handing the IOException, but 
            //translating it into an applicaiton specific exception and
            //then simply throwing it (i.e. 'reporting" it) to the code that
            //called us. It is the responsibility of the calling code to 
            //handle any arrors that occur.
            PrintWriter out;
            
            try {
                out = new PrintWriter(new FileWriter(ROSTER_FILE));
            } catch (IOException e) {
                throw new ClassRosterPersistenceException("Could not save student data.", e);
            }
            //Write out hte Student objects to the roster file.
            //NOTE TO THE APPRENTICES: We could just grab the student map,
            //get the Collection of Students and iterate over them but we've
            //already created a method that gets a List of Students so 
            //we'll reuse it!
            List<Student> studentList = this.getAllStudents();
                for (Student currentStudent : studentList) {
                    //write the Studentn object to the file
                    out.println(currentStudent.getStudentId()+DELIMITER + currentStudent.getFirstName() + DELIMITER + currentStudent.getLastName()+DELIMITER+currentStudent.getCohort());
                    //force the PrintWriter to write line to the file
                    out.flush();
                }
                //clean up
                out.close();
        }
    }
