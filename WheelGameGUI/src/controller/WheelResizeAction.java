package controller;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import view.MainFrame;

public class WheelResizeAction extends ComponentAdapter{
	/* 
	 * This class detects when user resize and tell the
	 * Wheel Panel to update with the new size.
	 */
	private MainFrame view;
	
	public WheelResizeAction(MainFrame view) {
		this.view = view;
	}
	
	public void componentResized(ComponentEvent e) {
		Component c = (Component)e.getSource();
		view.getWheelPanel().setDimension(c.getHeight());
	}
}
 