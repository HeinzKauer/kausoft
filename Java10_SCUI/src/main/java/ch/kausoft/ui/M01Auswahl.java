/**
 * 
 */
package ch.kausoft.ui;

import ch.kausoft.scui.CUI;
import ch.kausoft.scui.CUIMenu;
import ch.kausoft.scui.CUITabelle;
import ch.kausoft.scui.IMenueAction;
import ch.kausoft.scui.IOElement;
import ch.kausoft.scui.SimpleConsolUserInterface;

/**
 * @author Heinz
 * 
 */
public class M01Auswahl implements CUI {

	private SimpleConsolUserInterface scui = new SimpleConsolUserInterface();

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.kausoft.scui.CUI#getUIMenu()
	 */
	@Override
	public CUIMenu getUIMenu() {
		CUIMenu menu = new CUIMenu();
		menu.addIOElement(new IOElement(null, "M01Auswahl",null));
		menu.addIOElement(new IOElement("K", "Invetiertes Kapital", new KapitalList()));
		menu.addIOElement(new IOElement("K1", "Invetiertes Kapital", new Kapital()));
		menu.addIOElement(new IOElement("I", "Investitionen", new Investition()));
		menu.addIOElement(new IOElement("W", "Wohnungen", new Wohnungen()));
		menu.addIOElement(ReturnMenu.getMenue());
		return menu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.kausoft.scui.CUI#getContentTabelle()
	 */
	@Override
	public CUITabelle getContentTabelle() {
		CUITabelle t = new CUITabelle();
		return t;
	}

	
	private class Wohnungen implements IMenueAction {
		public boolean runMenueAction() {
			scui.inputText("------------------> etwas eingeben");
			scui.work(new ListWohnungen());
			return false;
		}
	}

	private class Investition implements IMenueAction {
		public boolean runMenueAction() {
			return false;
		}
	}

	private class Kapital implements IMenueAction {
		public boolean runMenueAction() {
			SimpleConsolUserInterface scui = new SimpleConsolUserInterface();
			scui.work(new ListKapital());
			return false;
		}
	}


}
