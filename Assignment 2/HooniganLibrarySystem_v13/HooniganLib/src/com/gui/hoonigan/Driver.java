package com.gui.hoonigan;


import java.awt.EventQueue;

/**HooniganGUIDriver class
 * This class instantiates the GUI for the Hoonigan Library system.
 * @author Hoonigan
 *
 */
public class Driver {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpeningFrame frame = new OpeningFrame();
					frame.setVisible();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
