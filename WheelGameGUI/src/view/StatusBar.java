package view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class StatusBar extends JPanel{
	/*
	 * This class shows the game status.
	 */
	private static final long serialVersionUID = 1L;
	private Border blackBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);
	private JLabel message;
	private String systemMsg;
	
	public StatusBar() {
		setLayout(new FlowLayout());
		setBackground(Color.DARK_GRAY);
		setBorder(blackBorder);
		init();
	}
	
	public void showMessage(String information) {
		message.setText(systemMsg + information);
	}
	
	public void init() {
		systemMsg = new String("[System]: ");
		message = new JLabel(systemMsg + "Welcome to WheelGame, please add a player to start");
		message.setForeground(Color.WHITE);
		add(message);
	}
}
