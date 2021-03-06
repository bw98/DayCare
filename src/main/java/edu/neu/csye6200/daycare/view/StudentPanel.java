package edu.neu.csye6200.daycare.view;

import edu.neu.csye6200.daycare.controller.FileUtil;
import edu.neu.csye6200.daycare.model.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class StudentPanel {
	
	public Item toItem() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		
		String[] labelStrings = new String[] {
				"Id", "Age", "Gpa", "RegisterDate", "RenewDate", "First Name", "Last Name",
				"Parent First Name", "Parent Last Name", "Phone", "Address"
		};
		List<JTextField> fields = new ArrayList<>();
		
		int delta = 25;
		
		for (int i = 0; i < labelStrings.length; i++) {
			
			JLabel label = new JLabel(labelStrings[i]);
			label.setBounds(75,delta * i,200,20);
			jPanel.add(label);
			
			JTextField field = new JTextField();
			field.setBounds(275,delta * i,200,20);
			fields.add(field);
		}
		
		JButton registerButton = new JButton("register now");
		registerButton.setBounds(275,delta * labelStrings.length,200,30);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Student student = Student.parseStudentFromString(new ArrayList<String>() {
					{
						for (JTextField field: fields) {
							add(field.getText());
						}
					}
				});

				String sep = File.separator;
				try (FileUtil fUtility = new FileUtil(new FileWriter("src"+sep+"main"+sep+"resources"+sep+"students.csv", true))) {
					fUtility.write(Student.serialize(student));
					
				} catch (Exception excpt) {
					excpt.printStackTrace();
				}
				try (BufferedWriter bw = new BufferedWriter(new FileWriter("src"+sep+"main"+sep+"resources"+sep+"vaccine.csv", true))) {
					bw.write(String.valueOf(student.getStudentId()));
					bw.newLine();
					bw.close();
					// System.out.println(student.getStudentId());

				} catch (Exception excpt) {
					excpt.printStackTrace();
				}
				
				for (JTextField field: fields) {
					field.setText("");
				}
				JOptionPane.showMessageDialog(null, "Register successful");
			}
			
		});
		
		for (JTextField field: fields) {
			jPanel.add(field);
		}
		
		jPanel.add(registerButton);
		
		return new Item("Student Register", jPanel);
	}
}
