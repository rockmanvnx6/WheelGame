package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

public class MainFrame extends JFrame {
	/*
	 * This class initializes the Game Engine model. 
	 * It also creates the Game Engine Call Back console and 
	 * Game Engine Call Back GUI as well as all other view
	 * such as Summary Panel, Wheel Panel, Menu, Tool Bar, Status Bar
	 * and PopupPanel.
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int Fwidth;
	private int Fheight;
	private int hgap = 0;
	private int vgap = 0;

	private GameEngine model;
	private SummaryPanel summaryPanel;
	private WheelPanel wheelPanel;
	private MenuBar menu;
	private ToolBar toolBar;
	private StatusBar statusBar;
	private PopupPanel popup;

	public MainFrame() {
		// Set title.
		super("Wheel Game");
		
		// Width and height has to be half of the user's screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Fwidth = screenSize.width/2;
		Fheight = screenSize.height/2;
		
		// Launch in the center of user's screen.
		x = screenSize.width/2 - Fwidth/2;
		y = screenSize.height/2 - Fheight/2;
		setBounds(x, y, Fwidth, Fheight);
		
		// Set layout and minimum size to be half screen width and height.
		setLayout(new BorderLayout(vgap, hgap));
		setMinimumSize(new Dimension(Fwidth, Fheight));
		setResizable(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
	}

	public void init() {
		/* 
		 * Initialize all the main view classes. 
		 * as well as add call backs to the Game Engine. 
		 */
		model = new GameEngineImpl();
		model.addGameEngineCallback(new GameEngineCallbackImpl());
		model.addGameEngineCallback(new GameEngineCallbackGUI(this));

		menu = new MenuBar(model, this);
		summaryPanel = new SummaryPanel(model, this);
		wheelPanel = new WheelPanel(model, this);
		toolBar = new ToolBar(model, this);
		statusBar = new StatusBar();
		popup = new PopupPanel(this);
		
		getContentPane().add(wheelPanel, BorderLayout.CENTER);
		getContentPane().add(summaryPanel, BorderLayout.WEST);
		getContentPane().add(toolBar, BorderLayout.NORTH);
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		setJMenuBar(menu);

		setVisible(true);
	}
	
	public PopupPanel getPopupPanel() {
		return popup;
	}

	public SummaryPanel getSummaryPanel() {
		return summaryPanel;
	}

	public WheelPanel getWheelPanel() {
		return wheelPanel;
	}

	public MenuBar getMenu() {
		return menu;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}
}
