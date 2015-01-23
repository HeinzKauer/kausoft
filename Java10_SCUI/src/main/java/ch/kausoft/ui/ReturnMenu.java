/**
 * 
 */
package ch.kausoft.ui;

import ch.kausoft.scui.IMenueAction;
import ch.kausoft.scui.IOElement;

/**
 * @author Heinz
 * 
 */
public class ReturnMenu implements IMenueAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.kausoft.scui.Call#run()
	 */
	@Override
	public boolean runMenueAction() {
		return true;
	}

	public static IOElement getMenue() {
		return new IOElement("<", "<-------", new ReturnMenu());
	}
}
