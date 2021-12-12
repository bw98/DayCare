package edu.neu.csye6200.daycare;

//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

import edu.neu.csye6200.daycare.vaccineInfo.VaccinePanel;

import javax.swing.*;

public class Driver {
    public static void main(String[] args) {
        System.out.println("driver demo");
        
        Menu menu = new Menu();
        menu.registerItem((new StudentPanel()).toItem());
//        menu.registerItem(new TeacherPanel().toItem());
        menu.registerItem((new StudentRenewPanel()).toItem());
        menu.registerItem((new PersonRenewPanel()).toItem());
        menu.registerItem(new VaccinePanel().toItem());
        menu.registerItem(new GroupPanel().toItem());
        menu.registerItem(new ClassPanel().toItem());

        menu.show();
    }
}
