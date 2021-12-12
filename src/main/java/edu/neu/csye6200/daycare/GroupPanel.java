package edu.neu.csye6200.daycare;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;


public class GroupPanel {
    public Item toItem() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JButton generateButton = new JButton("Generate Groups");
        generateButton.setBounds(30,40,200,50);
        JButton showButton = new JButton("Show Groups");
        showButton.setBounds(30,100,200,50);

        GroupRule rule = new GroupRule();

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sep = File.separator;
                Students ss = Students.parseStudents("src"+sep+"main"+sep+"java"+sep+"edu"+sep+"neu"+sep+"csye6200"+sep+"daycare"+sep+"students.csv");
                Teachers ts = Teachers.parseTeachers("src"+sep+"main"+sep+"java"+sep+"edu"+sep+"neu"+sep+"csye6200"+sep+"daycare"+sep+"teachers.csv");
                try {
                    rule.generateGroups(ts, ss);
                } catch (Group.BadGroupSizeException badGroupSizeException) {
                    badGroupSizeException.printStackTrace();
                }
            }
        });

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnCount(5);
        String[] colName = new String[]{"Group ID", "Group Capacity", "Current Group Size", "Teacher", "Students"};
        model.setColumnIdentifiers(colName);
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Integer, List<Group>>  groupMap = rule.getGroupMap();
                model.setRowCount(0);
                model.setColumnIdentifiers(colName);

                for (Integer key : groupMap.keySet()) {
                    for (Group group : groupMap.get(key)) {
                        List<Person> studentList= group.getStudents().getStudents();
                        StringBuilder sb = new StringBuilder();
                        for (Person p : studentList) {
                            sb.append(p.getFirstName()+" ");
                            sb.append(p.getLastName()+", ");
                        }

                        String studentsName = sb.toString();
                        model.addRow(new String[]{
                                       String.valueOf(group.getGroupId()),
                                       String.valueOf(group.getCapacity()),
                                       String.valueOf(group.getCurGroupSize()),
                                       group.getTeacher().getFirstName() + " " + group.getTeacher().getLastName(),
                                       studentsName,
                        });
                    }
                }

            }
        });


        jPanel.add(generateButton);
        jPanel.add(showButton);
        JTable jTable = new JTable(model){public boolean isCellEditable(int row, int column) { return false; }};
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane jsp = new JScrollPane(jTable);
        jsp.setBounds(300, 10, 600, 700);
        jTable.setBounds(300, 100, 600, 700);
        jPanel.add(jsp);

        return new Item("Group", jPanel);

    }

}
