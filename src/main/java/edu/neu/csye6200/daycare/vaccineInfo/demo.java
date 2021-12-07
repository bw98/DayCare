package edu.neu.csye6200.daycare.vaccineInfo;

import javax.swing.*;
import java.util.Vector;

public class demo {

    public static Vector<Student> students = new Vector<>();
    public static void main(String[] args) {

        Student s1 = new Student(1, "s1f", "s1l", 62);
        Student s2 = new Student(2, "s2f", "s2l", 40);

        students.add(s1);
        students.add(s2);
        new Menu().show();
    }


}
