package ch.kausoft.scui;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Console user interface Menu.
 * 
 * Ein CUIMenu besteht aus einer Menge von Iinput Output Elementen
 * Dem CUIMenu könne IOElement hinzugefügt werden
 * 
 * @author Heinz kauer
 */
public class CUIMenu {

	private Vector<IOElement> menu = new Vector<IOElement>();

	/**
	 * Dem CUIMenu könne IOElement hinzugefügt werden
	 * 
	 * @since created at 16.01.2015.23:20:09
	 * @param ioElement
	 *          void
	 */
	public void addIOElement(IOElement ioElement) {
		menu.add(ioElement);
	}

	/**
	 * liefert alle Menue Elemente zurück
	 * 
	 * @since created at 16.01.2015.23:23:44
	 * @return IOElement []
	 */
	public IOElement[] getIOElements() {
		IOElement element[] = new IOElement[menu.size()];
		int i = 0;
		for (Enumeration<IOElement> e = menu.elements(); e
				.hasMoreElements();) {
			element[i] = (IOElement) e.nextElement();
			i++;
		}
		return element;
	}

}
