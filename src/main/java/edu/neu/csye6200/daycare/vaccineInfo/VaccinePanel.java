package edu.neu.csye6200.daycare.vaccineInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
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

        MouseAdapter doubleClick =new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int row = jTable.rowAtPoint(e.getPoint());
                    int id =  Integer.valueOf(jTable.getValueAt(row, 0).toString());
                    String vaccine = (String)jTable.getValueAt(row, 4);
                    if(JOptionPane.showConfirmDialog(null, "Do you want to vaccine " + vaccine + " on student ID: " + id + "?", "Vaccine Confirm", JOptionPane.YES_NO_OPTION) == 0){
                        try {
                            writeVaccine(id, vaccine);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        //alert.doClick();
                        model.setRowCount(0);
                        for(Student s : demo.students){
                            for(Map.Entry<String, Integer> mp : VaccineNotification.getNotification(s).entrySet()){
                                model.addRow(new String[]{String.valueOf(s.getStudentId()), s.getFirstName(), s.getLastName(), String.valueOf(s.getAge()), mp.getKey(), String.valueOf(mp.getValue())});

                            }
                        }
                    }


                }
            }
        };
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                String[] colName = new String[]{"Student ID", "FirstName", "LastName", "Age(months)", "Vaccine", "Vaccine age(months)"};
                model.setColumnIdentifiers(colName);
                for(Student s : demo.students){
                    for(Map.Entry<Vaccine, Vector<Integer>> mp : s.getImmuRecord().entrySet()){
                        for(int month : mp.getValue()){
                            model.addRow(new String[]{String.valueOf(s.getStudentId()), s.getFirstName(), s.getLastName(), String.valueOf(s.getAge()), mp.getKey().getName(), String.valueOf(month)});
                        }

                    }
                }

                jTable.removeMouseListener(doubleClick);
            }
        });
        alert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                String[] colName = new String[]{"Student ID", "FirstName", "LastName", "Age(months)", "Vaccine", "Doses need"};
                model.setColumnIdentifiers(colName);
                for(Student s : demo.students){
                    for(Map.Entry<String, Integer> mp : VaccineNotification.getNotification(s).entrySet()){
                        model.addRow(new String[]{String.valueOf(s.getStudentId()), s.getFirstName(), s.getLastName(), String.valueOf(s.getAge()), mp.getKey(), String.valueOf(mp.getValue())});

                    }
                }
                jTable.addMouseListener(doubleClick);
            }
        });
        model = new DefaultTableModel();
        model.setColumnCount(5);
        String[] colName = new String[]{"Student ID", "FirstName", "LastName",  "Age(months)", "Vaccine", "Date(months)/Doses need"};
        model.setColumnIdentifiers(colName);
        jTable = new JTable(model){public boolean isCellEditable(int row, int column) { return false; }};

        //jTable.setModel(model);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //jTable.setEnabled(false);
        panel.add(alert);
        panel.add(show);
        panel.add(new JScrollPane(jTable));

    }

    public static void writeVaccine(int id, String vaccine) throws IOException {
        int row = 0;
        File file = new File("src/main/java/edu/neu/csye6200/daycare/vaccineInfo/vaccine.txt");
        Student s = new Student(-1, "first", "last",  -1);
        for(Student ss : demo.students){
            if(ss.getStudentId() == id){
                s = ss;
            }
        }
        Vaccine cur = VaccineFactory.getVaccine(vaccine);
        System.out.println(cur.getName());
        s.getImmuRecord().get(cur).add(s.getAge());
        try(BufferedReader inLine = new BufferedReader(new FileReader("src/main/java/edu/neu/csye6200/daycare/vaccineInfo/vaccine.txt"));){
            String in = null;
            while((in = inLine.readLine()) != null) {
                String[] temp = in.split(",");
                if(Integer.valueOf(temp[0]) == id){

                    break;
                }
                row++;
            }
        }catch(Exception e) {
            System.out.println("Caught an ERROR!");
            e.printStackTrace();
        }
        List<String> lines = Files.readAllLines(new File("src/main/java/edu/neu/csye6200/daycare/vaccineInfo/vaccine.txt").toPath());
        String newLine = lines.get(row) + "," + vaccine + "," + s.getAge();
        lines.set(row, newLine);
        Files.write(file.toPath(), lines);

    }

    public static JPanel getPanel(){

            return new VaccinePanel().panel;

    }

}
