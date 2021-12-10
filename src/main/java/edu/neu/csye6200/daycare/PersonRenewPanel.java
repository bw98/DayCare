package edu.neu.csye6200.daycare;


import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView.TableRow;

public class PersonRenewPanel extends JPanel{
	private JTable table;
	private JButton renewButton;
	private Students ss;
	private int renew_period=100;
	public Item toItem() {
		return new Item("renew_person", this);
	}
	public PersonRenewPanel() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		setLayout(new BorderLayout(0,0));
		JPanel panel=new JPanel();
		add(panel,BorderLayout.SOUTH);
		renewButton=new JButton("renew");
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
		ss=new Students("src\\main\\java\\edu\\neu\\csye6200\\daycare\\students.csv");
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
						tableData[i][j]=((Student) a).getFirstName()+((Student) a).getLastName();
					}
					break;
				case 6:
					if(a instanceof Student) {
						tableData[i][j]=((Student) a).getParentFirstName()+((Student) a).getParentLastName();
					}
					
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
		
		String[] titles= {"id","age","gpa","register_date","renew_date","name","parent_name","phone","address"};
		table=new JTable(tableData,titles);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(renewButton);
	}
	
	protected void renew_person(ActionEvent e) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		//DefaultTableModel model=(DefaultTableModel) table.getModel();
		int[] selectedRows=table.getSelectedRows();
		for(int i: selectedRows) {
			
			int id=(Integer)(table.getValueAt(i, 0));
			String date=df.format(new Date());
			table.setValueAt(date, i, 4);
			ss.update_renew_date(id,date);
			ss.write_to_csv("src\\main\\java\\edu\\neu\\csye6200\\daycare\\students.csv");
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
	
}

