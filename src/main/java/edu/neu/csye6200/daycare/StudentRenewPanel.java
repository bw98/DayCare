package edu.neu.csye6200.daycare;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentRenewPanel {

    public Item toItem() {
        jPanel = new JPanel();
        jPanel.setLayout(null);

        students = new ArrayList<Student>();
        readStudentsCSV();

        setTable();

        setInputLabelsAndFields();

        changeBtn = new JButton();
        changeBtn.setBounds(75, 580, 200, 25);
        changeBtn.setText("Change");
        changeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnChangeActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        jPanel.add(changeBtn);

        // Set delete button
        deleteBtn = new JButton();
        deleteBtn.setText("Delete");
        deleteBtn.setBounds(275, 580, 200, 25);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnDeleteActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        jPanel.add(deleteBtn);

        return new Item("Student Update", jPanel, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                readStudentsCSV();
                updateTable();

			}

        });
    }

    private void readStudentsCSV() {
        students.clear();
        SimpleDateFormat timeFt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Scanner inLine = new Scanner(new BufferedReader(new FileReader(studentCSVFile)));

            while (inLine.hasNextLine()) {
                String inputLine = inLine.nextLine();
                Scanner in = new Scanner(inputLine);
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

            inLine.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeStudentsCSV() {
        SimpleDateFormat timeFt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
              FileWriter fw = new FileWriter(studentCSVFile);
              BufferedWriter out = new BufferedWriter(fw);

            for (Student stu : students) {
                out.write(Integer.toString(stu.getStudentId()));
                out.write(',');
                out.write(Integer.toString(stu.getAge()));
                out.write(',');
                out.write(Double.toString(stu.getGpa()));
                out.write(',');
                out.write(timeFt.format(stu.getRegisterTime()));
                out.write(',');
                out.write(timeFt.format(stu.getRenewDate()));
                out.write(',');
                out.write(stu.getFirstName());
                out.write(',');
                out.write(stu.getLastName());
                out.write(',');
                out.write(stu.getParentFirstName());
                out.write(',');
                out.write(stu.getParentLastName());
                out.write(',');
                out.write(stu.getPhone());
                out.write(',');
                out.write(stu.getAddress());

                out.newLine();
            }

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTable() {
        String[] name = {"studentId", "age", "firstName", "lastName", "registerTime",
                "renewDate", "gpa", "phone", "address", "parentFirstName", "parentLastName"};
        SimpleDateFormat timeFt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int row = 1 + students.size();  // 鏍囬 + 鎵�鏈夊璞℃暟鎹�
        int col = name.length;

        Object[][] tableData = new Object[row][col];

        for (int i = 0; i < name.length; i++) {
            tableData[0][i] = name[i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Student stu = students.get(i - 1);
                if (name[j].equals("studentId")) {
                    tableData[i][j] = stu.getStudentId();
                } else if (name[j].equals("age")) {
                    tableData[i][j] = stu.getAge();
                } else if (name[j].equals("firstName")) {
                    tableData[i][j] = stu.getFirstName();
                } else if (name[j].equals("lastName")) {
                    tableData[i][j] = stu.getLastName();
                } else if (name[j].equals("registerTime")) {
                    tableData[i][j] = timeFt.format(stu.getRegisterTime());
                } else if (name[j].equals("renewDate")) {
                    String renewDateStr = timeFt.format(stu.getRenewDate());
                    String defaultRenewDateStr = timeFt.format(new Date(0));
                    if (renewDateStr.equals(defaultRenewDateStr)) {
                        tableData[i][j] = "";
                    } else {
                        tableData[i][j] = renewDateStr;
                    }
                } else if (name[j].equals("gpa")) {
                    tableData[i][j] = stu.getGpa();
                } else if (name[j].equals("phone")) {
                    tableData[i][j] = stu.getPhone();
                } else if (name[j].equals("address")) {
                    tableData[i][j] = stu.getAddress();
                } else if (name[j].equals("parentFirstName")) {
                    tableData[i][j] = stu.getParentFirstName();
                } else if (name[j].equals("parentLastName")) {
                    tableData[i][j] = stu.getParentLastName();
                }
            }
        }

        table = new JTable(tableData, name);
        DefaultTableModel model = new DefaultTableModel(tableData, name);
        table.setModel(model);

        table.setBounds(75, 0, 1500, 180);

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(50, 0, 850, 185);

        // jPanel.add(table);
        jPanel.add(jsp);
    }

    private void updateTable() {
        String[] name = {"studentId", "age", "firstName", "lastName", "registerTime",
                "renewDate", "gpa", "phone", "address", "parentFirstName", "parentLastName"};
        SimpleDateFormat timeFt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int row = 1 + students.size();  // 鏍囬 + 鎵�鏈夊璞℃暟鎹�
        int col = name.length;

        Object[][] tableData = new Object[row][col];

        for (int i = 0; i < name.length; i++) {
            tableData[0][i] = name[i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Student stu = students.get(i - 1);
                if (name[j].equals("studentId")) {
                    tableData[i][j] = stu.getStudentId();
                } else if (name[j].equals("age")) {
                    tableData[i][j] = stu.getAge();
                } else if (name[j].equals("firstName")) {
                    tableData[i][j] = stu.getFirstName();
                } else if (name[j].equals("lastName")) {
                    tableData[i][j] = stu.getLastName();
                } else if (name[j].equals("registerTime")) {
                    tableData[i][j] = timeFt.format(stu.getRegisterTime());
                } else if (name[j].equals("renewDate")) {
                    String renewDateStr = timeFt.format(stu.getRenewDate());
                    String defaultRenewDateStr = timeFt.format(new Date(0));
                    if (renewDateStr.equals(defaultRenewDateStr)) {
                        tableData[i][j] = "";
                    } else {
                        tableData[i][j] = renewDateStr;
                    }
                } else if (name[j].equals("gpa")) {
                    tableData[i][j] = stu.getGpa();
                } else if (name[j].equals("phone")) {
                    tableData[i][j] = stu.getPhone();
                } else if (name[j].equals("address")) {
                    tableData[i][j] = stu.getAddress();
                } else if (name[j].equals("parentFirstName")) {
                    tableData[i][j] = stu.getParentFirstName();
                } else if (name[j].equals("parentLastName")) {
                    tableData[i][j] = stu.getParentLastName();
                }
            }
        }

        // table = new JTable(tableData, name);
        DefaultTableModel model = new DefaultTableModel(tableData, name);
        table.setModel(model);

        // table.setBounds(75, 0, 800, 180);
        // jPanel.add(table);
    }

    private void setInputLabelsAndFields() {
        studentIdLabel = new JLabel();
        studentIdLabel.setText("studentId:");
        studentIdLabel.setBounds(75, 190, 200, 25);
        jPanel.add(studentIdLabel);

        txtStudentId = new JTextField();
        txtStudentId.setBounds(275, 190, 200, 25);
        jPanel.add(txtStudentId);

        ageLabel = new JLabel();
        ageLabel.setText("age:");
        ageLabel.setBounds(75, 220, 200, 25);
        jPanel.add(ageLabel);

        txtAge = new JTextField();
        txtAge.setBounds(275, 220, 200, 25);
        jPanel.add(txtAge);

        firstNameLabel = new JLabel();
        firstNameLabel.setText("firstName:");
        firstNameLabel.setBounds(75, 250, 200, 25);
        jPanel.add(firstNameLabel);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(275, 250, 200, 25);
        jPanel.add(txtFirstName);

        lastNameLabel = new JLabel();
        lastNameLabel.setText("lastName:");
        lastNameLabel.setBounds(75, 280, 200, 25);
        jPanel.add(lastNameLabel);

        txtLastName = new JTextField();
        txtLastName.setBounds(275, 280, 200, 25);
        jPanel.add(txtLastName);

        registerTimeLabel = new JLabel();
        registerTimeLabel.setText("registerTime:");
        registerTimeLabel.setBounds(75, 310, 200, 25);
        jPanel.add(registerTimeLabel);

        txtRegisterTime = new JTextField();
        txtRegisterTime.setBounds(275, 310, 200, 25);
        jPanel.add(txtRegisterTime);

        renewTimeLabel = new JLabel();
        renewTimeLabel.setText("renewTime:");
        renewTimeLabel.setBounds(75, 340, 200, 25);
        jPanel.add(renewTimeLabel);

        txtRenewTime = new JTextField();
        txtRenewTime.setBounds(275, 340, 200, 25);
        jPanel.add(txtRenewTime);

        gpaLabel = new JLabel();
        gpaLabel.setText("gpa:");
        gpaLabel.setBounds(75, 370, 200, 25);
        jPanel.add(gpaLabel);

        txtGpa = new JTextField();
        txtGpa.setBounds(275, 370, 200, 25);
        jPanel.add(txtGpa);

        phoneLabel = new JLabel();
        phoneLabel.setText("phone:");
        phoneLabel.setBounds(75, 400, 200, 25);
        jPanel.add(phoneLabel);

        txtPhone = new JTextField();
        txtPhone.setBounds(275, 400, 200, 25);
        jPanel.add(txtPhone);

        addressLabel = new JLabel();
        addressLabel.setText("address:");
        addressLabel.setBounds(75, 430, 200, 25);
        jPanel.add(addressLabel);

        txtAddress = new JTextField();
        txtAddress.setBounds(275, 430, 200, 25);
        jPanel.add(txtAddress);

        parentFirstNameLabel = new JLabel();
        parentFirstNameLabel.setText("parentFirstName:");
        parentFirstNameLabel.setBounds(75, 460, 200, 25);
        jPanel.add(parentFirstNameLabel);

        txtParentFirstName = new JTextField();
        txtParentFirstName.setBounds(275, 460, 200, 25);
        jPanel.add(txtParentFirstName);

        parentLastNameLabel = new JLabel();
        parentLastNameLabel.setText("parentLastName:");
        parentLastNameLabel.setBounds(75, 490, 200, 25);
        jPanel.add(parentLastNameLabel);

        txtParentLastName = new JTextField();
        txtParentLastName.setBounds(275, 490, 200, 25);
        jPanel.add(txtParentLastName);


    }

    public void updateStudentId(Student s, int id) throws IOException {
        String sep = File.separator;
        File file = new File("src"+ sep +"main" + sep+ "java" + sep + "edu" + sep + "neu" + sep + "csye6200" + sep + "daycare" + sep + "vaccineInfo" + sep + "vaccine.csv");
        int row = 0;
        try(BufferedReader inLine = new BufferedReader(new FileReader(file));){
            String in = null;
            while((in = inLine.readLine()) != null) {
                String[] temp = in.split(",");
                if(Integer.valueOf(temp[0]) == s.getStudentId()){
                    break;
                }
                row++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        List<String> lines = Files.readAllLines(file.toPath());
        String newLine = lines.get(row);
        newLine = newLine.replace(String.valueOf(s.getStudentId()), String.valueOf(id));
        lines.set(row, newLine);
        Files.write(file.toPath(), lines);
    }

    public void deleteStudent(Student s) throws IOException {
        String sep = File.separator;
        File file = new File("src"+ sep +"main" + sep+ "java" + sep + "edu" + sep + "neu" + sep + "csye6200" + sep + "daycare" + sep + "vaccineInfo" + sep + "vaccine.csv");
        int row = 0;
        try(BufferedReader inLine = new BufferedReader(new FileReader(file));){
            String in = null;
            while((in = inLine.readLine()) != null) {
                String[] temp = in.split(",");
                if(Integer.valueOf(temp[0]) == s.getStudentId()){
                    break;
                }
                row++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        List<String> lines = Files.readAllLines(file.toPath());
        lines.remove(row);
        Files.write(file.toPath(), lines);
    }

    private void updateStudentAndTable(int selectedRow, int studentId) throws IOException {
        SimpleDateFormat timeFt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Student stu : students) {
            if (stu.getStudentId() == studentId) {
                if (txtStudentId.getText().length() > 0) {
                    int newId = Integer.parseInt(txtStudentId.getText());
                    updateStudentId(stu, newId);
                    stu.setStudentId(newId);
                    table.setValueAt(stu.getStudentId(), selectedRow, 0);
                }

                if (txtAge.getText().length() > 0) {
                    stu.setAge(Integer.parseInt(txtAge.getText()));
                    table.setValueAt(stu.getAge(), selectedRow, 1);
                }

                if (txtFirstName.getText().length() > 0) {
                    stu.setFirstName(txtFirstName.getText());
                    table.setValueAt(stu.getFirstName(), selectedRow, 2);
                }

                if (txtLastName.getText().length() > 0) {
                    stu.setLastName(txtLastName.getText());
                    table.setValueAt(stu.getLastName(), selectedRow, 3);
                }

                if (txtRegisterTime.getText().length() > 0) {
                    try {
                        stu.setRegisterTime(timeFt.parse(txtRegisterTime.getText()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    table.setValueAt(timeFt.format(stu.getRegisterTime()), selectedRow, 4);
                }

                if (txtRenewTime.getText().length() > 0) {
                    try {
                        stu.setRenewDate(timeFt.parse(txtRenewTime.getText()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    table.setValueAt(timeFt.format(stu.getRenewDate()), selectedRow, 5);
                }

                if (txtGpa.getText().length() > 0) {
                    stu.setGpa(Double.parseDouble(txtGpa.getText()));
                    table.setValueAt(stu.getGpa(), selectedRow, 6);
                }

                if (txtPhone.getText().length() > 0) {
                    stu.setPhone(txtPhone.getText());
                    table.setValueAt(stu.getPhone(), selectedRow, 7);
                }

                if (txtAddress.getText().length() > 0) {
                    stu.setAddress(txtAddress.getText());
                    table.setValueAt(stu.getAddress(), selectedRow, 8);
                }

                if (txtParentFirstName.getText().length() > 0) {
                    stu.setParentFirstName(txtParentFirstName.getText());
                    table.setValueAt(stu.getParentFirstName(), selectedRow, 9);
                }

                if (txtParentLastName.getText().length() > 0) {
                    stu.setParentLastName(txtParentLastName.getText());
                    table.setValueAt(stu.getParentLastName(), selectedRow, 10);
                }

                break;
            }
        }

        writeStudentsCSV();
    }

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int studentId = (int) model.getValueAt(selectedRow, 0);
        updateStudentAndTable(selectedRow, studentId);

        table.repaint();
    }

    private void deleteStudentByStudentId(int studentId) throws IOException {
        for (Student stu : students) {
            if (stu.getStudentId() == studentId) {
                students.remove(stu);
                deleteStudent(stu);
                break;
            }
        }

        writeStudentsCSV();
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int studentId = (int) model.getValueAt(selectedRow, 0);
        // System.out.println("deleting studentId = " + studentId);
        deleteStudentByStudentId(studentId);
        model.removeRow(selectedRow);
        table.repaint();
    }

    // private static String studentCSVFile = "src/main/java/edu/neu/csye6200/daycare/students.csv";
    private static String studentCSVFile = "src" + File.separator + "main" + File.separator + "java" + File.separator + "edu" + File.separator + "neu" + File.separator + "csye6200" + File.separator + "daycare" + File.separator + "students.csv";
    private List<Student> students;
    private JPanel jPanel;
    private JTable table;
    private JButton changeBtn;
    private JButton deleteBtn;
    private JLabel studentIdLabel;
    private JLabel ageLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel registerTimeLabel;
    private JLabel renewTimeLabel;
    private JLabel gpaLabel;
    private JLabel phoneLabel;
    private JLabel addressLabel;
    private JLabel parentFirstNameLabel;
    private JLabel parentLastNameLabel;
    private JTextField txtStudentId;
    private JTextField txtAge;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtRegisterTime;
    private JTextField txtRenewTime;
    private JTextField txtGpa;
    private JTextField txtPhone;
    private JTextField txtAddress;
    private JTextField txtParentFirstName;
    private JTextField txtParentLastName;
}
