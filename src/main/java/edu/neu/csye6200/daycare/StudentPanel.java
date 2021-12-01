package edu.neu.csye6200.daycare;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
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
				"Id", "Age", "Gpa", "RegisterDate", "RenewDate", "Last Name", "First Name",
				"Parent Last Name", "Parent First Name", "Phone", "Address"
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
				
				try (FileUtil fUtility = new FileUtil(new FileWriter("src\\main\\java\\edu\\neu\\csye6200\\daycare\\students.csv", true))) {
					fUtility.write("\n" + Student.serialize(student));
					
				} catch (Exception excpt) {
					excpt.printStackTrace();
				}
				
				for (JTextField field: fields) {
					field.setText("");
				}
			}
			
		});
		
		for (JTextField field: fields) {
			jPanel.add(field);
		}
		
		jPanel.add(registerButton);
		
		return new Item("Student register", jPanel);
	}
}
