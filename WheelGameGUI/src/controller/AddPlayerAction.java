package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.PopupPanel;

public class AddPlayerAction implements ActionListener {
	/* 
	 * This class manage add player action. It will call the
	 * responsible views to update and pass information to model
	 * 
	 */
	private MainFrame view;
	private GameEngine model;
	private int ID = 0; // Store the ID of the player, this will automatically increment.
	
	public AddPlayerAction(GameEngine model, MainFrame view) {
		this.view = view;
		this.model = model;
	}
	
	public void updateViews() {
		view.getSummaryPanel().updateAllPlayers();
		view.getToolBar().updateAllPlayers();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/* 
		 * Triggered when player press add player action.
		 * Add the player to the model and update view for Tool bar, Summary panel and status bar.
		 */
		PopupPanel popup = view.getPopupPanel();
		try {
			if(popup.addPlayer() == JOptionPane.OK_OPTION) {
				String playerID = String.valueOf(ID++);
				Player thisPlayer = new SimplePlayer(playerID, popup.getPlayerName(), popup.getPoints());
				
				// Add Selected Player to GameEngine.
				model.addPlayer(thisPlayer);
				
				// Show feedback and update views.
				popup.showSuccessfulMessage("Player successfully added!");
				view.getStatusBar().showMessage(String.format("%s has joined the game", popup.getPlayerName()));
				updateViews();
			} else {
				popup.showErrorMessage("Cancelled add player action.");
			}
		} catch (Exception err) {
			popup.showErrorMessage("Invalid information, player is not added.");
		}
	}
	
}
