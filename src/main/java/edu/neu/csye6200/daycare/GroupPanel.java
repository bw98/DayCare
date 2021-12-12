package edu.neu.csye6200.daycare;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;
import java.util.Map;


public class GroupPanel {
    GroupRule rule;
    public Item toItem() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JButton generateButton = new JButton("Generate Groups");
        generateButton.setBounds(30,40,200,50);
        JButton showButton = new JButton("Show Groups");
        showButton.setBounds(30,100,200,50);

        rule = new GroupRule();

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rule = new GroupRule();
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
                model.setRowCount(0);
                Map<Integer, List<Group>>  groupMap = rule.getGroupMap();
//                model.setRowCount(0);
//                model.setColumnIdentifiers(colName);
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
        TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = jTable.getColumnModel().getColumn(i);
            if (i == 4) {
                column.setPreferredWidth(400);
            } else {
                column.setPreferredWidth(1);
            }
        }

//        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane jsp = new JScrollPane(jTable);

        jsp.setBounds(300, 10, 1000, 700);
        jTable.setBounds(300, 100, 1000, 700);
        jPanel.add(jsp);



        jTable.addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub
                    Point mousepoint;
                    mousepoint =e.getPoint();
                    // System.out.println(jTable.rowAtPoint(mousepoint)+1);

                }
            });

        return new Item("Group Assignment", jPanel);

    }

}
