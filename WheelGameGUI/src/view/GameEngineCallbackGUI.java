package view;

import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback{
	/* 
	 * This class will call the WheelPanel to render a spinning ball
	 * on the wheel. 
	 * 
	 * It also displays the status of what the wheel is doing on the status bar.
	 */
	private MainFrame view;
	
	public GameEngineCallbackGUI(MainFrame view) {
		this.view = view;
	}
	@Override
	public void nextSlot(Slot slot, GameEngine engine) {
		view.getWheelPanel().renderSlot(slot);
		view.getStatusBar().showMessage(String.format("Spinning...(%s, %s)", slot.getNumber(), slot.getColor()));
	}

	@Override
	public void result(Slot winningSlot, GameEngine engine) {
		/*
		 * This functions does the following:
		 * 1. Render the winning slot.
		 * 2. Enable the bet buttons (Since it is disabled when spin action is called).
		 * 3. Toggle the spinning status in tool bar since the spinning action should stop here.
		 * 4. Update all players' state on both tool bar and summary panel.
		 * 5. Announce the winning slot in the status bar.
		 * 6. Disable spin button on both tool bar and menu (Since at least one player needs to make bet to spin).
		 * 7. Re-enable the action menu (Which was disabled from spin action).
		 */
		view.getWheelPanel().renderSlot(winningSlot);
		
		view.getToolBar().enableBet();
		view.getToolBar().toggleSpinningStatus();
		
		view.getSummaryPanel().updateAllPlayers();
		view.getToolBar().updateAllPlayers();
		
		view.getStatusBar().showMessage(String.format("Winning slot is (%s, %s).", winningSlot.getNumber(), winningSlot.getColor()));
		
		view.getToolBar().disableSpin();
		view.getMenu().disableSpin();
		
		view.getMenu().enableAction();
	}
}
