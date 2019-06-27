package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class NewGame implements ActionListener{
	/*
	 * This class job is to dispose the old view and create
	 * a new instance of the game.
	 */
	private MainFrame view;
	
	public NewGame(MainFrame view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		view.dispose();
		new MainFrame();
	}	
}