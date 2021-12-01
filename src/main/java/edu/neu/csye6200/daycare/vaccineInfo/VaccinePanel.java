package edu.neu.csye6200.daycare.vaccineInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

public class VaccinePanel{

    private JPanel panel;
    private JButton alert;
    private JButton show;
    private JTable jTable;
    private DefaultTableModel model;

    public VaccinePanel(){
        panel = new JPanel();
        alert = new JButton("Vaccine alert");
        show = new JButton("Show vaccine record");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                String[] colName = new String[]{"Name", "Age(months)", "Vaccine", "Vaccine age(months)"};
                model.setColumnIdentifiers(colName);
                for(Student s : demo.students){
                    for(Map.Entry<Vaccine, Vector<Integer>> mp : s.getImmuRecord().entrySet()){
                        for(int month : mp.getValue()){
                            model.addRow(new String[]{s.getName(), String.valueOf(s.getAge()), mp.getKey().getName(), String.valueOf(month)});
                        }

                    }
                }
            }
        });
        alert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                String[] colName = new String[]{"Name", "Age(months)", "Vaccine", "Doses need"};
                model.setColumnIdentifiers(colName);
                for(Student s : demo.students){
                    for(Map.Entry<String, Integer> mp : VaccineNotification.getNotification(s).entrySet()){
                        model.addRow(new String[]{s.getName(), String.valueOf(s.getAge()), mp.getKey(), String.valueOf(mp.getValue())});

                    }
                }
            }
        });
        model = new DefaultTableModel();
        model.setColumnCount(4);
        String[] colName = new String[]{"Name", "Age(months)", "Vaccine", "Date(months)/Doses need"};
        model.setColumnIdentifiers(colName);
        jTable = new JTable();
        jTable.setEnabled(false);
        jTable.setModel(model);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        panel.add(alert);
        panel.add(show);
        panel.add(new JScrollPane(jTable));

    }

    public static JPanel getPanel(){
        return new VaccinePanel().panel;
    }

}
