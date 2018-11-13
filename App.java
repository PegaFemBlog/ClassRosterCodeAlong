/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster;

import com.mycompany.classroster.controller.ClassRosterController;
import com.mycompany.classroster.dao.ClassRosterAuditDao;
import com.mycompany.classroster.dao.ClassRosterAuditDaoImpl;
import com.mycompany.classroster.dao.ClassRosterDao;
import com.mycompany.classroster.dao.ClassRosterPersistenceException;
import com.mycompany.classroster.dao.ClassRosterDaoImpl;
import com.mycompany.classroster.service.ClassRosterDuplicateIdException;
import com.mycompany.classroster.service.ClassRosterService;
import com.mycompany.classroster.service.ClassRosterServiceImpl;
import com.mycompany.classroster.ui.ClassRosterView;
import com.mycompany.classroster.ui.UserIO;
import com.mycompany.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author bacag
 */
public class App {
    
    public static void main(String[] args) throws ClassRosterPersistenceException, ClassRosterDuplicateIdException {
    
    //this is where we instantiate everything
    UserIO myIo = new UserIOConsoleImpl();
    ClassRosterView myView = new ClassRosterView(myIo);
    ClassRosterDao myDao = new ClassRosterDaoImpl();
    ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoImpl();
    ClassRosterService myService = new ClassRosterServiceImpl(myDao, myAuditDao);
    ClassRosterController controller = new ClassRosterController(myService, myView);
    controller.run();
}

}