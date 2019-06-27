package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.interfaces.Player;

public class PlayerPanel extends JPanel{
	/* 
	 * This class is an instance of summary panel.
	 * its job is to display the player details such as:
	 * - Player ID
	 * - Player Points
	 * - Player Bet
	 * - Player BetType
	 */
	private static final long serialVersionUID = 1L;
	private Border blackBorder = BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK);
	private Player player;
	private JLabel playerNameID;
	private JLabel playerPoints;
	private JLabel playerBet;
	private JLabel playerBetType;
	private Dimension initialDimension;
	
	public PlayerPanel(Player player, MainFrame view) {
		this.player = player;
		
		// Set border and layout.
		setLayout(new GridLayout(4,1));
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createCompoundBorder(blackBorder,new EmptyBorder(10, 10, 10, 10)));
		
		// Set the size of each panel relative to the size of the application.
		Rectangle parentSize = view.getSummaryPanel().getBounds();
		int parentHeight = (int) parentSize.getHeight();
		int parentWidth = (int) parentSize.getWidth();
		initialDimension = new Dimension(parentWidth-parentWidth/5, parentHeight/6);
		
		setPreferredSize(initialDimension);
		setMaximumSize(this.getPreferredSize());
		
		init();
	}
	
	public void init() {
		/* 
		 * This function initializes all the labels with
		 * player's information.
		 */
		playerNameID = new JLabel(String.format("#%s %s",player.getPlayerId(),player.getPlayerName()));
		playerPoints = new JLabel(String.format("Points: %d", player.getPoints()));
		playerBet = new JLabel(String.format("Bet: %d", player.getBet()));
		playerBetType = new JLabel(String.format("Bet Type: %s", 
				player.getBetType() != null ? player.getBetType() : "Hasn't bet"));
		
		// Add the labels to Panel.
		add(playerNameID);
		add(playerPoints);
		add(playerBet);
		add(playerBetType);
	}
}
