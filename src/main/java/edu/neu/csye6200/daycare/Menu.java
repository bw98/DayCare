package edu.neu.csye6200.daycare;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.BorderLayout;

public class Menu {
	
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

		item.setWrapActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mJPanel, item.getTitle());
				if (item.getActionListener() != null) item.getActionListener().actionPerformed(e);
			}

		});

		return items.add(item);
	}
	
	public boolean unregisterItem(Item item) {
		return items.remove(item);
	}

	public void show() {
		JFrame jFrame = new JFrame("demo");
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// add buttons for all items
		for (Item item : items) {
			JButton btn = new JButton(item.getTitle());
			btn.addActionListener(item.getWrapActionListener());
			btnPanel.add(btn);
			mJPanel.add(item.getjPanel(), item.getTitle());
		}
		
		jFrame.add(btnPanel, BorderLayout.NORTH);
		jFrame.add(mJPanel, BorderLayout.CENTER);
		jFrame.setSize(1800, 800);
		jFrame.setVisible(true);
	}
}
