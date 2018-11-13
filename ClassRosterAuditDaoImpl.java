/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bacag
 */
public class ClassRosterAuditDaoImpl implements ClassRosterAuditDao {
    
    public static final String AUDIT_FILE = "audit.txt";

    /**
     *
     * @param entry
     * @throws ClassRosterPersistenceException
     */
    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException ex) {
            throw new ClassRosterPersistenceException("Could not persist audit information", ex);//ex == is the cause of the problem (the explanation)
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString()+ " : "+ entry);
        out.flush();
    }
    
}
