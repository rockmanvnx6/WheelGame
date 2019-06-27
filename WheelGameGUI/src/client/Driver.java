package client;

import javax.swing.SwingUtilities;

import view.MainFrame;

public class Driver {
	public static void main(String[] args) {
		/* 
		 * Main driver manages to call the view class.
		 * This runs in a separated thread to synchronize with the console Game Engine.
		 */
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}
}
