package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.BetAction;
import controller.SelectPlayerToBetAction;
import controller.SpinAction;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class ToolBar extends JPanel{
	/*
	 * This class contains the GUI for user select player to bet.
	 * As well as spin the wheel.
	 * 
	 * It also stores a state if the wheel is spinning in order to
	 * disable the buttons.
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame view;
	private GameEngine model;
	private Player currentPlayer;
	private Border blackBorder = BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK);
	private JComboBox<ComboWrapper> selector;
	private JButton spin;
	private JButton bet;
	private SpinAction spinAction;
	private SelectPlayerToBetAction playerSelector;
	private BetAction betAction;
	private boolean isSpinning = false;

	
	public ToolBar(GameEngine model, MainFrame view) {
		this.model = model;
		this.view = view;
		setLayout(new GridLayout(1,3));
		setBackground(Color.WHITE);
		setBorder(blackBorder);
		
		init();
	}
	
	public void init() {
		// Initialize components
		selector = new JComboBox<ComboWrapper>();
		spin = new JButton("Spin");
		bet = new JButton("Bet");

		// Initialize actions
		spinAction = new SpinAction(model,view);
		playerSelector = new SelectPlayerToBetAction(view);
		betAction = new BetAction(model, view);
		
		spin.addActionListener(spinAction);
		selector.addActionListener(playerSelector);
		bet.addActionListener(betAction);
		
		// add components to tool bar
		add(spin);		
		add(selector);		
		add(bet);
		
		disableAllButtons();
	}
	
	public void setCurrentPlayer(Player player) {
		this.currentPlayer = player;
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public void updateAllPlayers() {
		/*
		 * This class will fill in new instances of
		 * player options to the JComboBox as well as
		 * disable every spin buttons from the application.
		 */
		Collection<Player> allPlayer = model.getAllPlayers();

		selector.removeAllItems();
		spinAction.disableSpinButtons();
		
		// If all of the players have added their bets.
		if(!allPlayer.isEmpty() && betAction.checkPlayersAddBet()) {
			spinAction.spin();
		}
		
		/*
		 * If there are players in the game and the wheel
		 * is not spinning, it then check if at lease one player has
		 * added his/her bet, and then activate the spin button.
		 */
		if(!allPlayer.isEmpty() && !isSpinning) {
			enableBet();
			for(Player player : allPlayer) {
				if(player.getBet() > 0) {
					spinAction.enableSpinButtons();
				}
			}
		} else {
			disableAllButtons();
		}
		
		// Fill in the JComboBox with new player
		for(Player player: allPlayer) {
			ComboWrapper playerItem = new ComboWrapper(player);
			selector.addItem(playerItem);
		}
	}

	public void toggleSpinningStatus() {
		isSpinning = !isSpinning;
	}
	public void disableAllButtons() {
		disableBet();
		disableSpin();
	}
	
	public void disableBet() {
		bet.setEnabled(false);
	}
	
	public void disableSpin() {
		spin.setEnabled(false);
	}
	
	public void enableBet() {
		bet.setEnabled(true);
	}
	
	public void enableSpin() {
		spin.setEnabled(true);
	}
}
