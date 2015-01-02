package ch.kausoft.scui.test;

import ch.kausoft.scui.SimpleConsolUserInterface;



public class CUITest {

	public static void main(String args[]) {
		new CUITest().run();
	}

	private void run() {
		SimpleConsolUserInterface scui = new SimpleConsolUserInterface();
		scui.work(new Menu());
	}

}
