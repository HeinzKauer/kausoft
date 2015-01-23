/*
 * @author Heinz Kauer 22.01.2015.23:57:33 <br/>
 * Copyright 2015 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.ui;

import ch.kausoft.scui.IMenueAction;
import ch.kausoft.scui.SimpleConsolUserInterface;

public class KapitalList implements IMenueAction {
	public boolean runMenueAction() {
		SimpleConsolUserInterface scui = new SimpleConsolUserInterface();
		scui.work(new ListKapital());
		return false;
	}
}




