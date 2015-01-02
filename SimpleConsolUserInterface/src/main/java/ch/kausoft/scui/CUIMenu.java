package ch.kausoft.scui;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Console user interface Menu.
 * 
 * @author Heinz kauer
 */
public class CUIMenu   {
	
	private Vector<IOElement> menu = new Vector<IOElement>();

	public void addIOElement(IOElement ioElement) {
		menu.add( ioElement);
	}

	public IOElement[] getIOElements() {

		IOElement element[] = new IOElement[menu.size()];
		int i = 0;

		for (Enumeration<IOElement> e = menu.elements(); e.hasMoreElements();) {
			element[i] = (IOElement) e.nextElement();
			i++;
		}

		return element;
	}

}
