package edu.neu.csye6200.daycare;

import java.util.ArrayList;

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
		
		JLabel ageLabel = new JLabel("Age");
		ageLabel.setBounds(75,0,200,25);
		JTextField ageField = new JTextField();
		ageField.setBounds(275,0,200,25);
		
		JLabel gpaLabel = new JLabel("Gpa");
		gpaLabel.setBounds(75,30,200,25);
		JTextField gpaField = new JTextField();
		gpaField.setBounds(275,30,200,25);
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setBounds(75,60,200,25);
		JTextField dateField = new JTextField();
		dateField.setBounds(275,60,200,25);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setBounds(75,90,200,25);
		JTextField lastNameField = new JTextField();
		lastNameField.setBounds(275,90,200,25);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(75,120,200,25);
		JTextField firstNameField = new JTextField();
		firstNameField.setBounds(275,120,200,25);
		
		JLabel parentFirstNameLabel = new JLabel("Parent Last Name");
		parentFirstNameLabel.setBounds(75,150,200,25);
		JTextField parentFirstNameField = new JTextField();
		parentFirstNameField.setBounds(275,150,200,25);
		
		JLabel parentLastNameLabel = new JLabel("Parent First Name");
		parentLastNameLabel.setBounds(75,180,200,25);
		JTextField parentLastNameField = new JTextField();
		parentLastNameField.setBounds(275,180,200,25);
		
		JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setBounds(75,210,200,25);
		JTextField phoneField = new JTextField();
		phoneField.setBounds(275,210,200,25);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(75,240,200,25);
		JTextField addressField = new JTextField();
		addressField.setBounds(275,240,200,25);
		
		JButton registerButton = new JButton("register now");
		registerButton.setBounds(275,270,200,25);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Student student = Student.parseStudentFromString(new ArrayList<String>() {
					{
						add(ageField.getText());
						add(gpaField.getText());
						add(dateField.getText());
						add(lastNameField.getText());
						add(firstNameField.getText());
						add(parentLastNameField.getText());
						add(parentFirstNameField.getText());
						add(phoneField.getText());
						add(addressField.getText());
					}
				});
				
				try (FileUtil fUtility = new FileUtil(new FileWriter("src\\main\\java\\edu\\neu\\csye6200\\daycare\\students.csv", true))) {
					fUtility.write("\n" + Student.serialize(student));
					
				} catch (Exception excpt) {
					excpt.printStackTrace();
				}
				
				ageField.setText("");
				gpaField.setText("");
				dateField.setText("");
				lastNameField.setText("");
				firstNameField.setText("");
				parentLastNameField.setText("");
				parentFirstNameField.setText("");
				phoneField.setText("");
				addressField.setText("");
			}
			
		});
		
		jPanel.add(ageLabel);
		jPanel.add(ageField);
		jPanel.add(gpaLabel);
		jPanel.add(gpaField);
		jPanel.add(dateLabel);
		jPanel.add(dateField);
		jPanel.add(lastNameLabel);
		jPanel.add(lastNameField);
		jPanel.add(firstNameLabel);
		jPanel.add(firstNameField);
		jPanel.add(parentLastNameLabel);
		jPanel.add(parentLastNameField);
		jPanel.add(parentFirstNameLabel);
		jPanel.add(parentFirstNameField);
		jPanel.add(phoneLabel);
		jPanel.add(phoneField);
		jPanel.add(addressLabel);
		jPanel.add(addressField);
		
		jPanel.add(registerButton);
		
		return new Item("Student register", jPanel);
	}
}
