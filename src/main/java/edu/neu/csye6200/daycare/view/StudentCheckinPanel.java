package edu.neu.csye6200.daycare.view;


import edu.neu.csye6200.daycare.model.Person;
import edu.neu.csye6200.daycare.model.Student;
import edu.neu.csye6200.daycare.model.Students;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class StudentCheckinPanel extends JPanel{
	private JScrollPane sp=null;
	private JTable table=null;
	String[] titles= {"id","age","gpa","register_date","renew_date","name","parent_name","phone","address"};
	private JButton renewButton;
	private JButton refreshButton;
	private Students ss;
	private int renew_period=100;
	public Item toItem() {
		return new Item("Student Check In", this,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("11111111111111111111111111111111111111");
				refresh_data();
			}
		});
	}
	public StudentCheckinPanel() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		setLayout(new BorderLayout(0,0));
		JPanel panel=new JPanel();
		add(panel,BorderLayout.SOUTH);
		renewButton=new JButton("renew");
		//refreshButton=new JButton("refresh");
		renewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					renew_person(e);
				} 
				catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refresh_data();
//		
//		ss=new Students("src"+ File.separator+"main" + File.separator + "java" +File.separator+"edu" + File.separator+"neu" + File.separator+"csye6200"+File.separator+"daycare"+File.separator+"students.csv");
//		Object[][] tableData=new Object[ss.getNumber()][9];
//		Iterator<Person> iter=ss.iterator();
//		for(int i=0;i<ss.getNumber();i++) {
//			Person a=iter.next();
//			for(int j=0;j<9;j++) {
//				switch (j) {
//				case 0:
//					if(a instanceof Student) {
//						tableData[i][j]=((Student) a).getStudentId();
//					}
//					break;
//				case 1:
//					if(a instanceof Student) {
//						tableData[i][j]=((Student) a).getAge();
//					}
//					break;
//				case 2:
//					if(a instanceof Student) {
//						tableData[i][j]=((Student) a).getGpa();
//					}
//					break;
//				case 3:
//					if(a instanceof Student) {
//						tableData[i][j]=df.format(((Student) a).getRegisterTime());
//					}
//					break;
//				case 4:
//					if(a instanceof Student) {
//						if(judge_alert(((Student) a).getRenewDate())) {
//							tableData[i][j]="Need to renew!";
//						}
//						else {
//							tableData[i][j]=df.format(((Student) a).getRenewDate());
//						}
//					}
//					break;
//				case 5:
//					if(a instanceof Student) {
//						tableData[i][j]=((Student) a).getFirstName()+((Student) a).getLastName();
//					}
//					break;
//				case 6:
//					if(a instanceof Student) {
//						tableData[i][j]=((Student) a).getParentFirstName()+((Student) a).getParentLastName();
//					}
//					
//				case 7:
//					if(a instanceof Student) {
//						tableData[i][j]=((Student) a).getPhone();
//					}
//					break;
//					
//				case 8:
//					if(a instanceof Student) {
//						tableData[i][j]=((Student) a).getAddress();
//					}
//					break;
//			}
//				
//		}
//		}
//		
//		table=new JTable(tableData,titles);
//		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		
//		add(new JScrollPane(table),BorderLayout.CENTER);
		
		panel.add(renewButton);
		
	}
	
	protected void renew_person(ActionEvent e) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//DefaultTableModel model=(DefaultTableModel) table.getModel();
		int[] selectedRows=table.getSelectedRows();
		for(int i: selectedRows) {
			
			int id=(Integer)(table.getValueAt(i, 0));
			String date=df.format(new Date());
			table.setValueAt(date, i, 4);
			ss.update_renew_date(id,date);
			String sep=File.separator;
			ss.write_to_csv("src"+sep+"main"+sep+"resources"+sep+"students.csv");
		}
	}
	
	public static int distance_days(Date d) {
		int distance=0;
		Date now=new Date();
		try {
			
			long diff=now.getTime()-d.getTime();
			distance=(int)(diff/(1000*60*60*24));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return distance;
	}
	
	public boolean judge_alert(Date d) {
		if(distance_days(d)>renew_period) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void refresh_data() {
		if(table!=null) {
			remove(table);
		}
		if(sp!=null) {
			remove(sp);
		}
		table=new JTable(get_data(),titles);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		sp=new JScrollPane(table);
		add(sp,BorderLayout.CENTER);
	}
	
	public Object[][] get_data() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sep=File.separator;
		ss=new Students("src"+sep+"main"+sep+"resources"+sep+"students.csv");
		Object[][] tableData=new Object[ss.getNumber()][9];
		Iterator<Person> iter=ss.iterator();
		for(int i=0;i<ss.getNumber();i++) {
			Person a=iter.next();
			for(int j=0;j<9;j++) {
				switch (j) {
				case 0:
					if(a instanceof Student) {
						tableData[i][j]=((Student) a).getStudentId();
					}
					break;
				case 1:
					if(a instanceof Student) {
						tableData[i][j]=((Student) a).getAge();
					}
					break;
				case 2:
					if(a instanceof Student) {
						tableData[i][j]=((Student) a).getGpa();
					}
					break;
				case 3:
					if(a instanceof Student) {
						tableData[i][j]=df.format(((Student) a).getRegisterTime());
					}
					break;
				case 4:
					if(a instanceof Student) {
						if(judge_alert(((Student) a).getRenewDate())) {
							tableData[i][j]="Need to renew!";
						}
						else {
							tableData[i][j]=df.format(((Student) a).getRenewDate());
						}
					}
					break;
				case 5:
					if(a instanceof Student) {
						tableData[i][j]=((Student) a).getFirstName()+" "+((Student) a).getLastName();
					}
					break;
				case 6:
					if(a instanceof Student) {
						tableData[i][j]=((Student) a).getParentFirstName()+" "+((Student) a).getParentLastName();
					}
					break;
					
				case 7:
					if(a instanceof Student) {
						tableData[i][j]=((Student) a).getPhone();
					}
					break;
					
				case 8:
					if(a instanceof Student) {
						tableData[i][j]=((Student) a).getAddress();
					}
					break;
			}
	}
}
		return tableData;
	}
}
	

