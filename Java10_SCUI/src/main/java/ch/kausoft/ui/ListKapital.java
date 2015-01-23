/**
 * 
 */
package ch.kausoft.ui;

import java.util.List;

import ch.kausoft.scui.CUI;
import ch.kausoft.scui.CUIMenu;
import ch.kausoft.scui.CUITabelle;
import ch.kausoft.scui.IMenueAction;
import ch.kausoft.scui.IOElement;
import ch.kausoft.wbg.daten.bo.InvestiertesKapitalBO;

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
