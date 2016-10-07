package ch.kausoft.wbg;

import java.util.List;

import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.basic.RecordID;
import ch.kausoft.context.SessionContext;
import ch.kausoft.controller.useCases.DataPathUC;
import ch.kausoft.controller.useCases.DatenSpeicherUC;
import ch.kausoft.kostenmiete.Investition;
import ch.kausoft.kostenmiete.ZinsCase;
import ch.kausoft.wbg.daten.DataImporter;
import ch.kausoft.wbg.daten.bo.InvestitionBO;
import ch.kausoft.wbg.daten.bo.TilgungBO;
import ch.kausoft.wbg.daten.bo.TilgungWork;
import ch.kausoft.wbg.daten.bo.WohnungBO;

public class Kostenmiete {

	//
	// public static DatenSpeicher getDatenSpeicher() {
	// return datenSpeicher;
	// }
	//
	// public static void setDatenSpeicher(DatenSpeicher datenSpeicher) {
	// Kostenmiete.datenSpeicher = datenSpeicher;
	// }

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DataPathUC dataPath = new DataPathUC("4711");
		SessionContext.getContext().add(dataPath);
		
		
		dataPath.setDataPath("C:\\eclipse2013\\Workspaces\\kausoft\\Java09_Kostenmiete\\daten\\InputDaten.csv");
		System.out.println(dataPath.getDataPath());

		// UseCase DatenSpeicher laden
		DatenSpeicherUC daten = DatenSpeicherUC.getInstance();
		daten.loadFromExcel(dataPath.getDataPath());

		new Kostenmiete().load(DatenSpeicher.getDatenSpeicher());
	}

	public void load(DatenSpeicher datenSpeicher) {
		tilgungsplanRechnen(datenSpeicher);
		listInvest();
		listWohnungTilgung();
	}

	private void listWohnungTilgung() {
		DatenSpeicher datenSpeicher = DatenSpeicher.getDatenSpeicher();

		List<WohnungBO> wohnungen = WohnungBO.loadAll();
		System.out.println("1--------------------------");
		for (WohnungBO wohnungBO : wohnungen) {
			System.out.println("1.1--------------------------");
			wohnungBO.list();

			List<InvestitionBO> investitionen = InvestitionBO.getInvestitionen();
			for (InvestitionBO investitionBO : investitionen) {

				double[] tilgungProWohnung = investitionBO.getInvetition().getTilgungProWohnung();
				for (int k = 0; k < tilgungProWohnung.length; k++) {

					if (tilgungProWohnung[k] > 0d) {
						System.out.println(tilgungProWohnung[k]);
					}
				}
			}
		}
	}

	// private void listWohnungTilgung() {
	// DatenSpeicher datenSpeicher = DatenSpeicher.getDatenSpeicher();
	//
	// WohnungBO.loadAll()
	//
	// WohnungBO[] woh = datenSpeicher.getAllWohnung();
	// System.out.println("1--------------------------");
	// for (int i = 0; i < woh.length; i++) {
	// System.out.println("1.1--------------------------");
	// woh[i].list();
	// InvestitionBO[] inv = datenSpeicher.getAllInvestition();
	// for (int j = 0; j < inv.length; j++) {
	// if (inv[j].invetition. tilgungProWohnung[i] > 0) {
	// Tilgung t = inv[j].invetition.tilgung;
	// t.consolOutDetail(inv[j].invetition,
	// inv[j].invetition.tilgungProWohnung[i]);
	// }
	// }
	// }
	// }

	// private DatenSpeicher loadData() {
	// DataImporter importer = new DataImporter();
	// return importer.load();
	// }

	private void tilgungsplanRechnen(DatenSpeicher datenSpeicher) {
		System.out.println("tpr01: tilgungsplanRechnen()");

		for (Investition inv : datenSpeicher.getInvestitionen().values()) {
			System.out.println("tpr01: "+ inv);

			InvestitionBO invBo = new InvestitionBO(inv);
			invBo.getInvetition().getLebensdauerInJahre();
			TilgungBO.rechnenTilgungsplan(invBo);

			System.out.println("tpr03: ");

			//
			// TilgungBO t = new TilgungBO(invBo, ZinsCase.zinsIst);
			// t.tilgungsPlanRechnen();
			// t.listConsoloutput();
		}
	}

	private void listInvest() {
		// InvestitionBO[] inv = datenSpeicher.getAllInvestition();
		// for (int i = 0; i < inv.length; i++) {
		// InvestitionBO investition = inv[i];
		// TilgungBO t = investition.tilgung;
		// t.listConsoloutput();
		// }
	}

}