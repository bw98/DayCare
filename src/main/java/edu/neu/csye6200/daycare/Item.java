package edu.neu.csye6200.daycare;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Item {
	private String title;
	private JPanel jPanel;
	private ActionListener actionListener;
	private ActionListener wrapActionListener;
	
	Item(String title, JPanel jPanel) {
		this.setTitle(title);
		this.setjPanel(jPanel);
	}
	
	Item(String title, JPanel jPanel, ActionListener actionListener) {
		this.setTitle(title);
		this.setjPanel(jPanel);
		this.setActionListener(actionListener);
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

	public ActionListener getWrapActionListener() {
		return wrapActionListener;
	}

	public void setWrapActionListener(ActionListener wrapActionListener) {
		this.wrapActionListener = wrapActionListener;
	}
}
