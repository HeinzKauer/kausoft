/**
 * 
 */
package ch.kausoft.ui;

import ch.kausoft.scui.SimpleConsolUserInterface;


/**
 * @author Heinz
 *
 */
public class Run_ConsolUserInterface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Run_ConsolUserInterface().run();
	}

	private void run() {
		SimpleConsolUserInterface scui = new SimpleConsolUserInterface();
		scui.work(new Menu());
	}

}
