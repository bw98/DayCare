package edu.neu.csye6200.daycare.vaccineInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;
import java.util.Vector;

public class AlertVaccine extends Thread{

    private Vector<Student> students;

    public AlertVaccine(Vector<Student> students){
        this.students = students;
    }

    @Override
    public void run(){
        init();
    }

    public  void init(){
        JFrame frame = new JFrame("Alert Immunization");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] colName = new String[]{"Name", "Age(months)", "Immunization", "Doses needed"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnCount(4);
        model.setColumnIdentifiers(colName);
        for(Student s : students){
            for(Map.Entry<String, Integer> mp : VaccineNotification.getNotification(s).entrySet()){
                model.addRow(new String[]{s.getName(), String.valueOf(s.getAge()), mp.getKey(), String.valueOf(mp.getValue())});

            }
        }


        JTable table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //JScrollPane scrollPane = new JScrollPane(table);
        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }
}
