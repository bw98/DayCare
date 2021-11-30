package edu.neu.csye6200.daycare;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentRenewView {

    StudentRenewView() {
        // Read students
        students = new ArrayList<Student>();
        students.add(new Student(1, 3, "Yaaan", "Yang", new Date(), 3.3, 156, "baba1", "mama1"));
        students.add(new Student(2, 4, "Nana", "O-yang", new Date(), 3.8, 157, "baba2", "mama2"));
        students.add(new Student(3, 5, "Mac", "Will", new Date(), 2.0, 158, "baba3", "mama3"));

        // Set frame
        frame = new JFrame("Renewal for Student");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = frame.getContentPane();

        // Set table
        JPanel tablePanel = new JPanel();
        setTable();
        tablePanel.add(table);
        contentPane.add(tablePanel, BorderLayout.NORTH);

        // button Panel
        JPanel btnPanel = new JPanel();

        // Set change button and related label
        setInputLabelsAndFields();

        changeBtn = new JButton();
        // contentPane.add(changeBtn, BorderLayout.AFTER_LAST_LINE);
        btnPanel.add(changeBtn);
        changeBtn.setText("Change");
        changeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        // Set delete button
        deleteBtn = new JButton();
        // contentPane.add(deleteBtn, BorderLayout.EAST);
        btnPanel.add(deleteBtn);
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        contentPane.add(btnPanel, BorderLayout.SOUTH);

        // Frame visible
        frame.setVisible(true);
    }

    private void setTable() {
        int row = 1 + students.size();  // 标题 + 所有对象数据
        int col = 0;
        Class cls = Student.class;
        while (cls != null) {
            col += cls.getDeclaredFields().length;
            cls = cls.getSuperclass();
        }

        Object[][] tableData = new Object[row][col];
        String[] name = {"id", "age", "firstName", "lastName", "RegisterTime", "gpa", "studentId", "fatherName", "motherName"};
        SimpleDateFormat timeFt = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < name.length; i++) {
            tableData[0][i] = name[i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Student stu = students.get(i - 1);
                if (name[j].equals("id")) {
                    tableData[i][j] = stu.getId();
                } else if (name[j].equals("age")) {
                    tableData[i][j] = stu.getAge();
                } else if (name[j].equals("firstName")) {
                    tableData[i][j] = stu.getFirstName();
                } else if (name[j].equals("lastName")) {
                    tableData[i][j] = stu.getLastName();
                } else if (name[j].equals("RegisterTime")) {
                    tableData[i][j] = timeFt.format(stu.getRegisterTime());
                } else if (name[j].equals("gpa")) {
                    tableData[i][j] = stu.getGpa();
                } else if (name[j].equals("studentId")) {
                    tableData[i][j] = stu.getStudentId();
                } else if (name[j].equals("fatherName")) {
                    tableData[i][j] = stu.getFatherName();
                } else if (name[j].equals("motherName")) {
                    tableData[i][j] = stu.getMotherName();
                }
            }
        }

        table = new JTable(tableData, name);
        DefaultTableModel model = new DefaultTableModel(tableData, name);
        table.setModel(model);
    }

    private void setInputLabelsAndFields() {
        JPanel inputPanel = new JPanel(new GridLayout(15, 2,5, 0));

        idLabel = new JLabel();
        idLabel.setText("id:");
        inputPanel.add(idLabel);
        txtId = new JTextField();
        txtId.setPreferredSize(new Dimension(100, 20));
        inputPanel.add(txtId);

        ageLabel = new JLabel();
        ageLabel.setText("age:");
        inputPanel.add(ageLabel);
        txtAge = new JTextField();
        txtAge.setPreferredSize(new Dimension(100, 20));
        inputPanel.add(txtAge);

        firstNameLabel = new JLabel();
        firstNameLabel.setText("firstName:");
        inputPanel.add(firstNameLabel);
        txtFirstName = new JTextField();
        txtFirstName.setPreferredSize(new Dimension(100, 20));
        inputPanel.add(txtFirstName);

        lastNameLabel = new JLabel();
        lastNameLabel.setText("lastName:");
        inputPanel.add(lastNameLabel);
        txtLastName = new JTextField();
        txtLastName.setPreferredSize(new Dimension(100, 20));
        inputPanel.add(txtLastName);

        registerTimeLabel = new JLabel();
        registerTimeLabel.setText("registerTime:");
        inputPanel.add(registerTimeLabel);
        txtRegisterTime = new JTextField();
        txtRegisterTime.setPreferredSize(new Dimension(100, 20));
        inputPanel.add(txtRegisterTime);

        gpaLabel = new JLabel();
        gpaLabel.setText("gpa:");
        inputPanel.add(gpaLabel);
        txtGpa = new JTextField();
        txtGpa.setPreferredSize(new Dimension(100, 20));
        inputPanel.add(txtGpa);

        studentIdLabel = new JLabel();
        studentIdLabel.setText("studentId:");
        inputPanel.add(studentIdLabel);
        txtStudentId = new JTextField();
        txtStudentId.setPreferredSize(new Dimension(100, 20));
        inputPanel.add(txtStudentId);

        fatherNameLabel = new JLabel();
        fatherNameLabel.setText("fatherName:");
        inputPanel.add(fatherNameLabel);
        txtFatherName = new JTextField();
        txtFatherName.setPreferredSize(new Dimension(100, 20));
        inputPanel.add(txtFatherName);

        motherNameLabel = new JLabel();
        motherNameLabel.setText("motherName:");
        inputPanel.add(motherNameLabel);
        txtMotherName = new JTextField();
        txtMotherName.setPreferredSize(new Dimension(100, 20));
        inputPanel.add(txtMotherName);

        contentPane.add(inputPanel, BorderLayout.CENTER);
    }

    private void updateStudentAndTable(int selectedRow, int id) {
        SimpleDateFormat timeFt = new SimpleDateFormat("yyyy-MM-dd");

        for (Student stu : students) {
            if (stu.getId() == id) {
                if (txtId.getText().length() > 0) {
                    stu.setId(Integer.parseInt(txtId.getText()));
                    table.setValueAt(stu.getId(), selectedRow, 0);
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
                if (txtStudentId.getText().length() > 0) {
                    stu.setStudentId(Integer.parseInt(txtStudentId.getText()));
                    table.setValueAt(stu.getStudentId(), selectedRow, 6);
                }
                if (txtFatherName.getText().length() > 0) {
                    stu.setFatherName(txtFatherName.getText());
                    table.setValueAt(stu.getFatherName(), selectedRow, 7);
                }
                if (txtMotherName.getText().length() > 0) {
                    stu.setMotherName(txtMotherName.getText());
                    table.setValueAt(stu.getMotherName(), selectedRow, 8);
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

        int id = (int) model.getValueAt(selectedRow, 0);
        updateStudentAndTable(selectedRow, id);

        table.repaint();
    }

    private void deleteStudentById(int id) {
        for (Student stu : students) {
            if (stu.getId() == id) {
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
        int id = (int) model.getValueAt(selectedRow, 0);
        System.out.println("deleting id = " + id);
        deleteStudentById(id);

        model.removeRow(selectedRow);
        table.repaint();
    }

    private List<Student> students;
    private JFrame frame;
    private Container contentPane;
    private JTable table;
    private JButton changeBtn;
    private JButton deleteBtn;
    private JLabel idLabel;
    private JLabel ageLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel registerTimeLabel;
    private JLabel gpaLabel;
    private JLabel studentIdLabel;
    private JLabel fatherNameLabel;
    private JLabel motherNameLabel;
    private JTextField txtId;
    private JTextField txtAge;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtRegisterTime;
    private JTextField txtGpa;
    private JTextField txtStudentId;
    private JTextField txtFatherName;
    private JTextField txtMotherName;
}
