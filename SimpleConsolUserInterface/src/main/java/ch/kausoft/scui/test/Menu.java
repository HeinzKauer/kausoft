package ch.kausoft.scui.test;

import ch.kausoft.scui.CUI;
import ch.kausoft.scui.CUIMenu;
import ch.kausoft.scui.CUITabelle;
import ch.kausoft.scui.IMenueAction;
import ch.kausoft.scui.IOElement;
import ch.kausoft.scui.SimpleConsolUserInterface;

public class Menu implements CUI {

	public CUIMenu getUIMenu() {
		CUIMenu menu = new CUIMenu();
		menu.addIOElement(new IOElement("S", "Suchen", new S()));
		menu.addIOElement(new IOElement("E", "Ende", new E()));
		menu.addIOElement(new IOElement("A1", "Edit Name", new E()));
		menu.addIOElement(new IOElement("A2", "Edit Strasse", new E()));
		menu.addIOElement(new IOElement("F", "Finden", new F()));
		menu.addIOElement(new IOElement("A3", "Edit PLZ und Ort", new A()));
		return menu;
	}
	
	public CUITabelle getContentTabelle() {
		CUITabelle t = new CUITabelle();
		t.addZeile(new String[]{"Name","Heinz Kauer"});
		t.addZeile(new String[]{"Strasse","Kesselweg 23"});
		t.addZeile(new String[]{"ort","4711 Liestal"});
		return t;
	}


	class S implements IMenueAction {
		public boolean runMenueAction() {
			// TODO Auto-generated method stub
			return false;
		}
	}

	class E implements IMenueAction {
		public boolean runMenueAction() {
//			getUIMenu().
//			getIoElement().setRueckmeldung("- Ende der Verarbeitung -");
			return true;
		}
	}

	class F implements IMenueAction {
		public boolean runMenueAction() {
			// TODO Auto-generated method stub
			return false;
		}
	}

	class A implements IMenueAction {
		public boolean runMenueAction() {
			SimpleConsolUserInterface scui = new SimpleConsolUserInterface();
			scui.work(new Menu());
			return false;
		}
	}

}
