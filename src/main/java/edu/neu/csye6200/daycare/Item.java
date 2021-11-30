package edu.neu.csye6200.daycare;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Item {
	private String title;
	private JPanel jPanel;
	private ActionListener actionListener;
	
	Item(String title, JPanel jPanel) {
		this.setTitle(title);
		this.setjPanel(jPanel);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JPanel getjPanel() {
		return jPanel;
	}

	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}
}
