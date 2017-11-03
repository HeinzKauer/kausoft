/**
 * 
 */
package ch.kausoft.ui;

import ch.kausoft.scui.*;
import ch.kausoft.wbg.daten.bo.WohnungBO;

import java.util.List;

/**
 * @author Heinz
 * 
 */
public class ListWohnungen implements CUI {

	CUITabelle t = new CUITabelle();

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.kausoft.scui.CUI#getUIMenu()
	 */
	@Override
	public CUIMenu getUIMenu() {
		CUIMenu menu = new CUIMenu();

		menu.addIOElement(new IOElement("", "Edit Wohnung", null));
		menu.addIOElement(new IOElement("----", "------------", null));
		t.addZeile(new String[] { "WohnungID", "Nr", "Bezeichnung" });
		t.addZeile(new String[] { "---------", "--", "-----------" });

		List<WohnungBO> wohnungen = WohnungBO.loadAll();

		int i = 0;
		for (WohnungBO wohnungBO : wohnungen) {
			// System.out.println("140131-2214 "+wohnungBO.getBezeichnung());
			String id = wohnungBO.getId().toString();
			String nr = new Short(wohnungBO.getNummer()).toString();
			String bez = wohnungBO.getBezeichnung();
			String bsch = wohnungBO.getBeschreibung();
			String berw =  Double.toString(wohnungBO.getBewertung());
			String flae = Double.toString(wohnungBO.getFlaeche());
			String hNr = Double.toString(wohnungBO.getHausnummer());
 
			t.addZeile(new String[] { id, nr, bez, bsch, berw, flae, hNr});
			menu.addIOElement(new IOElement("w" + ++i, "Wohnung Nr " + nr,   new CallWohnung(wohnungBO)));

		}
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
		return t;
	}

	private class Menu implements IMenueAction {
		public boolean runMenueAction() {
			return false;
		}
	}

	private class CallWohnung implements IMenueAction {
		WohnungBO wo;

		CallWohnung(WohnungBO wo) {
			this.wo = wo;
		}

		public boolean runMenueAction() {
	   	wo.list();
			return false;
		}
	}

}
