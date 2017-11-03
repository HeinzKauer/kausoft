/**
 * 
 */
package ch.kausoft.ui;

import ch.kausoft.scui.*;
import ch.kausoft.wbg.daten.bo.InvestiertesKapitalBO;

import java.util.List;

/**
 * @author Heinz
 * 
 */
public class ListKapital implements CUI {

	CUITabelle t = new CUITabelle();

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.kausoft.scui.CUI#getUIMenu()
	 */
	@Override
	public CUIMenu getUIMenu() {
		CUIMenu menu = new CUIMenu();

		menu.addIOElement(new IOElement("Edit", "ID", null));
		menu.addIOElement(new IOElement("----", "------------", null));

		t.addZeile(new String[] { "Bezeichnung", "Beschreibung" });
		t.addZeile(new String[] { "--", "-----------" });


		List<InvestiertesKapitalBO> investKapital = InvestiertesKapitalBO.loadAll();
		int i = 0;
		for (InvestiertesKapitalBO bo : investKapital) {
			
			System.out.println(bo.getId());
			
			t.addZeile(new String[] { bo.getBezeichnung(), bo.getBeschreibung().getString() });
			menu.addIOElement(new IOElement("e" + ++i, bo.getId().toString(), new CallKapital(bo)));
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
		// TODO Auto-generated method stub
		return t;
	}

	private class CallKapital implements IMenueAction {
		InvestiertesKapitalBO ka;

		CallKapital(InvestiertesKapitalBO ka) {
			this.ka = ka;
		}

		public boolean runMenueAction() {
			return true;
		}
	}

}
