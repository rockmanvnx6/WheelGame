package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import view.ComboWrapper;
import view.MainFrame;

public class SelectPlayerToBetAction implements ActionListener {
	/*
	 * This class will get the selected player from the JComboBox selector in view.ToolBar.
	 * It then sets the currentPlayer to the selected player.
	 */
	private MainFrame view;
	
	public SelectPlayerToBetAction(MainFrame view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * Triggered when player choose a player from the dropDown menu.
		 * By default it should be the first player from the PlayerList.
		 */
		JComboBox<?> item = (JComboBox<?>)e.getSource();
		ComboWrapper currentPlayer = (ComboWrapper) item.getSelectedItem();
		if(currentPlayer != null)  {
			view.getToolBar().setCurrentPlayer(currentPlayer.getPlayer());
		}
	}
}
