package edu.neu.csye6200.daycare.vaccineInfo;

import edu.neu.csye6200.daycare.Item;
import edu.neu.csye6200.daycare.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;



public class VaccinePanel{

    private JPanel panel;
    private JButton alert;
    private JButton show;
    private JTable jTable;
    private DefaultTableModel model;

    public Item toItem(){
        return new Item("Vaccine", VaccinePanel.getPanel());
    }

    public VaccinePanel(){

        panel = new JPanel();
        panel.setLayout(null);
        alert = new JButton("Vaccine alert");
        show = new JButton("Show vaccine record");

        MouseAdapter doubleClick =new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                List<Student> students = GetStudents.get();
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
                        for(edu.neu.csye6200.daycare.Student s : students){
                            for(Map.Entry<String, Integer> mp : VaccineNotification.getNotification(s).entrySet()){
                                model.addRow(new String[]{String.valueOf(s.getStudentId()), s.getFirstName(), s.getLastName(), String.valueOf(s.getAge()), mp.getKey(), String.valueOf(mp.getValue())});

                            }
                        }
                        alert.doClick();
                    }


                }
            }
        };
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> students = GetStudents.get();
                model.setRowCount(0);
                String[] colName = new String[]{"Student ID", "FirstName", "LastName", "Age", "Vaccine", "Vaccine age"};
                model.setColumnIdentifiers(colName);
                for(edu.neu.csye6200.daycare.Student s : students){
                    for(Map.Entry<Vaccine, Vector<Integer>> mp : s.getImmuRecord().entrySet()){
                        for(int month : mp.getValue()){
                            model.addRow(new String[]{String.valueOf(s.getStudentId()), s.getFirstName(), s.getLastName(), String.valueOf(s.getAge()), mp.getKey().getName(), String.valueOf(month/12)});
                        }

                    }
                }

            }
        });
        alert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Student> students = GetStudents.get();

                model.setRowCount(0);
                String[] colName = new String[]{"Student ID", "FirstName", "LastName", "Age", "Vaccine", "Doses need"};
                model.setColumnIdentifiers(colName);
                for(edu.neu.csye6200.daycare.Student s : students){
                    for(Map.Entry<String, Integer> mp : VaccineNotification.getNotification(s).entrySet()){
                        model.addRow(new String[]{String.valueOf(s.getStudentId()), s.getFirstName(), s.getLastName(), String.valueOf(s.getAge()), mp.getKey(), String.valueOf(mp.getValue())});

                    }
                }

            }
        });
        model = new DefaultTableModel();
        model.setColumnCount(5);
        String[] colName = new String[]{"Student ID", "FirstName", "LastName",  "Age", "Vaccine", "Date/Doses need"};
        model.setColumnIdentifiers(colName);
        jTable = new JTable(model){public boolean isCellEditable(int row, int column) { return false; }};
        jTable.addMouseListener(doubleClick);
        //jTable.setModel(model);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //jTable.setEnabled(false);
        panel.add(alert);
        panel.add(show);
        JScrollPane jsp = new JScrollPane(jTable);
        jsp.setBounds(300, 10, 600, 700);
        jTable.setBounds(300, 100, 600, 700);
        panel.add(jsp);
        alert.setBounds(30, 40, 200, 50);
        show.setBounds(30, 100, 200, 50);


    }

    public static void writeVaccine(int id, String vaccine) throws IOException {
        String sep = File.separator;
        List<Student> students = GetStudents.get();
        int row = 0;
        File file = new File("src"+ sep +"main" + sep+ "java" + sep + "edu" + sep + "neu" + sep + "csye6200" + sep + "daycare" + sep + "vaccineInfo" + sep + "vaccine.csv");
        Student s = null;
        for(edu.neu.csye6200.daycare.Student ss : students){
            if(ss.getStudentId() == id){
                s = ss;
            }
        }
        Vaccine cur = VaccineFactory.getVaccine(vaccine);
        System.out.println(cur.getName());
        s.getImmuRecord().get(cur).add(s.getAge()*12);
        try(BufferedReader inLine = new BufferedReader(new FileReader(file));){
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
        List<String> lines = Files.readAllLines(file.toPath());
        String newLine = lines.get(row) + "," + vaccine + "," + s.getAge()*12;
        lines.set(row, newLine);
        Files.write(file.toPath(), lines);

    }

    public static JPanel getPanel(){

            return new VaccinePanel().panel;

    }

}
