package view;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import model.CRM;

public class CRMFrame extends JFrame {
	private CRMPanel mainPane;
	public CRMFrame() {
		this.mainPane = new CRMPanel();
		this.setIcon();
		this.display();
	}
	
	/**
	 * setIcon: cai dat icon cho frame
	 */
	private void setIcon() {
		// TODO Auto-generated method stub
		ImageIcon crmIcon = new ImageIcon("src//image//cashicon.png");
		this.setIconImage(crmIcon.getImage());
	}

	/*
	 * display: cai dat thong tin hien thi
	 */
	private void display() {
		this.setTitle("CRM-Simulate");
		this.setSize(600, 520);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setResizable(false);
		this.setContentPane(mainPane);
		this.setVisible(true);
	}
}
