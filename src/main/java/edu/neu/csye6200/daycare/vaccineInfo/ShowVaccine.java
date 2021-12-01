package edu.neu.csye6200.daycare.vaccineInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;
import java.util.Vector;

public class ShowVaccine extends Thread{

    private Vector<Student> students;

    public ShowVaccine(Vector<Student> students){
        this.students = students;
    }

    @Override
    public void run(){
        init();
    }

    public void init(){
        JFrame frame = new JFrame("Immunization record");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] colName = new String[]{"Name", "Age(months)", "Immunization", "Date(months)"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnCount(4);
        model.setColumnIdentifiers(colName);
        for(Student s : students){
            for(Map.Entry<Vaccine, Vector<Integer>> mp : s.getImmuRecord().entrySet()){
                for(int month : mp.getValue()){
                    model.addRow(new String[]{s.getName(), String.valueOf(s.getAge()), mp.getKey().getName(), String.valueOf(month)});
                }

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
