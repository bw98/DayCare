package edu.neu.csye6200.daycare;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.BorderLayout;

public class Menu {
	
	public class Item {
		private String title;
		private JPanel jPanel;
		private ActionListener actionListener;
		
		Item(String title, JPanel jPanel) {
			this.title = title;
			this.jPanel = jPanel;
		}
		
		public void setActionListener(ActionListener actionListener) {
			this.actionListener = actionListener;
		}
	};
	
	private CardLayout cardLayout;
	private JPanel mJPanel;
	private JPanel btnPanel;
	private Vector<Item> items;
	
	public Menu() {
		this.items = new Vector<>();
		this.cardLayout = new CardLayout();
		this.mJPanel = new JPanel(cardLayout);
		this.btnPanel = new JPanel();
	}
	
	public boolean registerItem(Item item) {
		if (item.actionListener == null) {
			item.setActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					cardLayout.show(mJPanel, item.title);
				}
				
			});
		}

		return items.add(item);
	}
	
	public boolean unregisterItem(Item item) {
		return items.remove(item);
	}

	public void show() {
		JFrame jFrame = new JFrame("demo");
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		panel1.add(new JButton("first panel content"));
		registerItem(new Item("first panel", panel1));
		
		JPanel panel2 = new JPanel();
		panel2.add(new JButton("second panel content"));
		registerItem(new Item("second panel", panel2));
		
		// add buttons for all items
		for (Item item : items) {
			JButton btn = new JButton(item.title);
			btn.addActionListener(item.actionListener);
			btnPanel.add(btn);
			mJPanel.add(item.jPanel, item.title);
		}
		
		jFrame.add(btnPanel, BorderLayout.NORTH);
		jFrame.add(mJPanel, BorderLayout.CENTER);
		jFrame.setSize(600, 400);
		jFrame.setVisible(true);
	}
}
