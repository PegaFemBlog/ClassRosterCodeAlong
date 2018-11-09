/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster;

import com.mycompany.classroster.controller.ClassRosterController;

/**
 *
 * @author bacag
 */
public class App {
    
    public static void main(String[] args) {
        
        //this is where we instantiate everything
        
    ClassRosterController controller = new ClassRosterController(/*view, service*/);
    controller.run();
}

}