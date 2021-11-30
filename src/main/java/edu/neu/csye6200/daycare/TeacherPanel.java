package edu.neu.csye6200.daycare;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class TeacherPanel {
	
	public Item toItem() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		
		JLabel idLabel = new JLabel("id");
		idLabel.setBounds(75,0,200,25);
		JTextField idField = new JTextField();
		idField.setBounds(275,0,200,25);
		
		JLabel ageLabel = new JLabel("Age");
		ageLabel.setBounds(75,30,200,25);
		JTextField ageField = new JTextField();
		ageField.setBounds(275,30,200,25);
		
		JLabel creditsLabel = new JLabel("Gpa");
		creditsLabel.setBounds(75,60,200,25);
		JTextField creditsField = new JTextField();
		creditsField.setBounds(275,60,200,25);
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setBounds(75,90,200,25);
		JTextField dateField = new JTextField();
		dateField.setBounds(275,90,200,25);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setBounds(75,120,200,25);
		JTextField lastNameField = new JTextField();
		lastNameField.setBounds(275,120,200,25);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(75,150,200,25);
		JTextField firstNameField = new JTextField();
		firstNameField.setBounds(275,150,200,25);
		
		JLabel classRoomNumLabel = new JLabel("Parent Last Name");
		classRoomNumLabel.setBounds(75,180,200,25);
		JTextField classRoomNumField = new JTextField();
		classRoomNumField.setBounds(275,180,200,25);
		
		JLabel groupNumLabel = new JLabel("Parent First Name");
		groupNumLabel.setBounds(75,210,200,25);
		JTextField groupNumField = new JTextField();
		groupNumField.setBounds(275,210,200,25);
		
		JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setBounds(75,240,200,25);
		JTextField phoneField = new JTextField();
		phoneField.setBounds(275,240,200,25);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(75,270,200,25);
		JTextField addressField = new JTextField();
		addressField.setBounds(275,270,200,25);
		
		JButton registerButton = new JButton("register now");
		registerButton.setBounds(275,300,200,20);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Teacher teacher = Teacher.parseTeacherFromString(new ArrayList<String>() {
					{
						add(idField.getText());
						add(ageField.getText());
						add(creditsField.getText());
						add(dateField.getText());
						add(lastNameField.getText());
						add(firstNameField.getText());
						add(classRoomNumField.getText());
						add(groupNumField.getText());
						add(phoneField.getText());
						add(addressField.getText());
					}
				});
				
				try (FileUtil fUtility = new FileUtil(new FileWriter("src\\main\\java\\edu\\neu\\csye6200\\daycare\\teachers.csv", true))) {
					fUtility.write("\n" + Teacher.serialize(teacher));
					
				} catch (Exception excpt) {
					excpt.printStackTrace();
				}
				
				idField.setText("");
				ageField.setText("");
				creditsField.setText("");
				dateField.setText("");
				lastNameField.setText("");
				firstNameField.setText("");
				classRoomNumField.setText("");
				groupNumField.setText("");
				phoneField.setText("");
				addressField.setText("");
			}
			
		});
		
		jPanel.add(idLabel);
		jPanel.add(idField);
		jPanel.add(ageLabel);
		jPanel.add(ageField);
		jPanel.add(creditsLabel);
		jPanel.add(creditsField);
		jPanel.add(dateLabel);
		jPanel.add(dateField);
		jPanel.add(lastNameLabel);
		jPanel.add(lastNameField);
		jPanel.add(firstNameLabel);
		jPanel.add(firstNameField);
		jPanel.add(groupNumLabel);
		jPanel.add(classRoomNumField);
		jPanel.add(classRoomNumLabel);
		jPanel.add(groupNumField);
		jPanel.add(phoneLabel);
		jPanel.add(phoneField);
		jPanel.add(addressLabel);
		jPanel.add(addressField);
		
		jPanel.add(registerButton);
		
		return new Item("Teacher register", jPanel);
	}
}
