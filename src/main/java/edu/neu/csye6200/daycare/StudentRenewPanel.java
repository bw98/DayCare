package edu.neu.csye6200.daycare;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentRenewPanel {

    public Item toItem() {
        jPanel = new JPanel();
        jPanel.setLayout(null);

        // TODO: 何时获取最新数据，以及何时写入最新数据
        // 目前的想法：点击 StudentRenew 按钮的时候，自动更新 students
        //
        students = new ArrayList<Student>();
        students.add(new Student(1, 30, 3.5, new Date(), "Nana", "O-yang", "baba1", "Yang", "18322215", "Boston Pdt"));
        students.add(new Student(3, 19, 2.0, new Date(), "Jing", "Yang", "baba2", "Y", "110", "CA SD"));
        //

        setTable();

        setInputLabelsAndFields();

        changeBtn = new JButton();
        changeBtn.setBounds(75,550,200,25);
        changeBtn.setText("Change");
        changeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        jPanel.add(changeBtn);

        // Set delete button
        deleteBtn = new JButton();
        deleteBtn.setText("Delete");
        deleteBtn.setBounds(275,550,200,25);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jPanel.add(deleteBtn);

        return new Item("Student Renew", jPanel);
    }

    private void setTable() {
        String[] name = {"studentId", "age", "firstName", "lastName", "registerTime",
                "gpa", "phone", "address", "parentFirstName", "parentLastName"};
        SimpleDateFormat timeFt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int row = 1 + students.size();  // 标题 + 所有对象数据
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

        table.setBounds(75, 0, 500, 180);
        jPanel.add(table);
    }

    private void setInputLabelsAndFields() {
        studentIdLabel = new JLabel();
        studentIdLabel.setText("studentId:");
        studentIdLabel.setBounds(75,190,200,25);
        jPanel.add(studentIdLabel);

        txtStudentId = new JTextField();
        txtStudentId.setBounds(275,190,200,25);
        jPanel.add(txtStudentId);

        ageLabel = new JLabel();
        ageLabel.setText("age:");
        ageLabel.setBounds(75,220,200,25);
        jPanel.add(ageLabel);

        txtAge = new JTextField();
        txtAge.setBounds(275,220,200,25);
        jPanel.add(txtAge);

        firstNameLabel = new JLabel();
        firstNameLabel.setText("firstName:");
        firstNameLabel.setBounds(75,250,200,25);
        jPanel.add(firstNameLabel);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(275,250,200,25);
        jPanel.add(txtFirstName);

        lastNameLabel = new JLabel();
        lastNameLabel.setText("lastName:");
        lastNameLabel.setBounds(75,280,200,25);
        jPanel.add(lastNameLabel);

        txtLastName = new JTextField();
        txtLastName.setBounds(275,280,200,25);
        jPanel.add(txtLastName);

        registerTimeLabel = new JLabel();
        registerTimeLabel.setText("registerTime:");
        registerTimeLabel.setBounds(75,310,200,25);
        jPanel.add(registerTimeLabel);

        txtRegisterTime = new JTextField();
        txtRegisterTime.setBounds(275,310,200,25);
        jPanel.add(txtRegisterTime);

        gpaLabel = new JLabel();
        gpaLabel.setText("gpa:");
        gpaLabel.setBounds(75,340,200,25);
        jPanel.add(gpaLabel);

        txtGpa = new JTextField();
        txtGpa.setBounds(275,340,200,25);
        jPanel.add(txtGpa);

        phoneLabel = new JLabel();
        phoneLabel.setText("phone:");
        phoneLabel.setBounds(75,370,200,25);
        jPanel.add(phoneLabel);

        txtPhone = new JTextField();
        txtPhone.setBounds(275,370,200,25);
        jPanel.add(txtPhone);

        addressLabel = new JLabel();
        addressLabel.setText("address:");
        addressLabel.setBounds(75,400,200,25);
        jPanel.add(addressLabel);

        txtAddress = new JTextField();
        txtAddress.setBounds(275,400,200,25);
        jPanel.add(txtAddress);

        parentFirstNameLabel = new JLabel();
        parentFirstNameLabel.setText("parentFirstName:");
        parentFirstNameLabel.setBounds(75,430,200,25);
        jPanel.add(parentFirstNameLabel);

        txtParentFirstName = new JTextField();
        txtParentFirstName.setBounds(275,430,200,25);
        jPanel.add(txtParentFirstName);

        parentLastNameLabel = new JLabel();
        parentLastNameLabel.setText("parentLastName:");
        parentLastNameLabel.setBounds(75,460,200,25);
        jPanel.add(parentLastNameLabel);

        txtParentLastName = new JTextField();
        txtParentLastName.setBounds(275,460,200,25);
        jPanel.add(txtParentLastName);
    }

    private void updateStudentAndTable(int selectedRow, int studentId) {
        SimpleDateFormat timeFt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Student stu : students) {
            if (stu.getStudentId() == studentId) {
                if (txtStudentId.getText().length() > 0) {
                    stu.setStudentId(Integer.parseInt(txtStudentId.getText()));
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

                if (txtGpa.getText().length() > 0) {
                    stu.setGpa(Double.parseDouble(txtGpa.getText()));
                    table.setValueAt(stu.getGpa(), selectedRow, 5);
                }

                if (txtPhone.getText().length() > 0) {
                    stu.setPhone(txtPhone.getText());
                    table.setValueAt(stu.getPhone(), selectedRow, 6);
                }

                if (txtAddress.getText().length() > 0) {
                    stu.setAddress(txtAddress.getText());
                    table.setValueAt(stu.getAddress(), selectedRow, 7);
                }

                if (txtParentFirstName.getText().length() > 0) {
                    stu.setParentFirstName(txtParentFirstName.getText());
                    table.setValueAt(stu.getParentFirstName(), selectedRow, 8);
                }

                if (txtParentLastName.getText().length() > 0) {
                    stu.setParentLastName(txtParentLastName.getText());
                    table.setValueAt(stu.getParentLastName(), selectedRow, 9);
                }

                break;
            }
        }
    }

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int studentId = (int) model.getValueAt(selectedRow, 0);
        updateStudentAndTable(selectedRow, studentId);

        table.repaint();
    }

    private void deleteStudentByStudentId(int studentId) {
        for (Student stu : students) {
            if (stu.getStudentId() == studentId) {
                students.remove(stu);
                break;
            }
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int studentId = (int) model.getValueAt(selectedRow, 0);
        System.out.println("deleting studentId = " + studentId);
        deleteStudentByStudentId(studentId);

        model.removeRow(selectedRow);
        table.repaint();
    }

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
    private JTextField txtGpa;
    private JTextField txtPhone;
    private JTextField txtAddress;
    private JTextField txtParentFirstName;
    private JTextField txtParentLastName;
}
