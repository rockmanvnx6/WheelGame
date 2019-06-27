package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitGame implements ActionListener {	
	/* 
	 * This class job is to safely exit the program.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
