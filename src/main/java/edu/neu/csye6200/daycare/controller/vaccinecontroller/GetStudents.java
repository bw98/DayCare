package edu.neu.csye6200.daycare.controller.vaccinecontroller;

import edu.neu.csye6200.daycare.model.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GetStudents {

    public static List<Student> get(){
        List<Student> students = new ArrayList<>();
        SimpleDateFormat timeFt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sep = File.separator;
        try(BufferedReader inLine = new BufferedReader(new FileReader("src"+sep+"main"+sep+"resources"+sep+"students.csv"))){
            String inline = null;
            while((inline = inLine.readLine()) != null) {
                Scanner in = new Scanner(inline);
                in.useDelimiter(",");
                int studentId = in.nextInt();
                int age = in.nextInt();
                double gpa = in.nextDouble();
                Date registerDate = timeFt.parse(in.next());
                Date renewDate = timeFt.parse(in.next());
                String firstName = in.next();
                String lastName = in.next();
                String parentFirstName = in.next();
                String parentLastName = in.next();
                String phone = in.next();
                String address = in.next();
                students.add(new Student(studentId, age, gpa, registerDate, renewDate, firstName, lastName, parentFirstName, parentLastName, phone, address));
                in.close();

            }
        }catch(Exception ee) {
            // System.out.println("Caught an ERROR!");
            ee.printStackTrace();
        }
        return students;
    }
}
