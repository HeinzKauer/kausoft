package ch.kausoft.wbg.daten;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import ch.kausoft.kostenmiete.InvestiertesKapital;
import ch.kausoft.kostenmiete.Investition;
import ch.kausoft.kostenmiete.Wohnung;
import ch.kausoft.kostenmiete.KapitalZinssatz;

/**
 * Laden der Daten.
 * 
 * @author Heinz
 * 
 */
public class DataImporter {

	DatenSpeicher ds;

	public DatenSpeicher load() {

		ds = DatenSpeicher.getDatenSpeicher();
		// String name =
		// "C:\\eclipse2013\\git\\github\\Kostenmiete\\src\\ch\\kausoft\\wbg\\daten\\InputDaten.csv";
		String name = "C:\\eclipse2013\\Workspaces\\kausoft\\Java09_Kostenmiete\\daten\\InputDaten.csv";

		File f = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		String zeile;
		try {
			f = new File(name);
			fis = new FileInputStream(f);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			zeile = br.readLine();
			while (zeile != null) {
				System.out.println(zeile);
				parsData(zeile);
				zeile = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		return ds;
	}

	private void parsData(String zeile) {
		String[] d = zeile.split(",");
		if (d != null && d.length <= 3)
			return; // Zeile überlesen
		if (d[0].equalsIgnoreCase("InvestieresKapital")) {
			parsKapital(d);
		} else if (d[0].equalsIgnoreCase("KapitalZinssatz")) {
			parsZinssatz(d);
		} else if (d[0].equalsIgnoreCase("Investition")) {
			parsInvestition(d);
		} else if (d[0].equalsIgnoreCase("Wohnung")) {
			parsWohnung(d);
		}
	}

	private void parsWohnung(String[] d) {
		Long id = new Long(d[1]);
		short hausNr = Short.parseShort(d[2]);
		short wNummer = Short.parseShort(d[3]);
		String berz = d[4];
		String besch = d[5];
		Double flaeche = Double.parseDouble(d[6]);
		Double bewertung = Double.parseDouble(d[7]);
		Wohnung w = new Wohnung(id, hausNr, wNummer, berz, besch, flaeche,
				bewertung);
		ds.getWohnungen().put(id, w);
	}

	private void parsInvestition(String[] d) {
		Long id = new Long(d[1]);
		Long idKapital = new Long(d[2]);
		InvestiertesKapital investiertesKapital = ds.getInvestiertesKapital()
				.get(idKapital);
		String bezeichnung = d[3];
		String beschreibung = d[4];
		int jahr = Integer.parseInt(d[5]);
		int monat = Integer.parseInt(d[6]);
		int betrag = Integer.parseInt(d[7]);
		int lebesdauer = Integer.parseInt(d[8]);
		int betragEFond = Integer.parseInt(d[9]);
		Investition i = new Investition(id, investiertesKapital, bezeichnung,
				beschreibung, jahr, monat, betrag, lebesdauer, betragEFond);
		ds.getInvestitionen().put(id, i);
	}

	//
	// private void parsInvestition(String[] d) {
	// InvestitionBO iv = ds.getInvestition(d[1]); // Bezeichnung
	// if (d[2].equalsIgnoreCase("Beschreibung")) {
	// iv.invetition.setBeschreibung(d[3]);
	// }
	// else if (d[2].equalsIgnoreCase("Kapital")) {
	// iv.invetition.kapital = ds.getKapital(d[3]);
	// } else if (d[2].equalsIgnoreCase("Investition")) {
	// if (d.length >= 7) {
	// try {
	// iv.invetition.setAktivierungsJahr(Integer.parseInt(d[3]));
	// iv.invetition.setInvestitionsBetrag(Integer.parseInt(d[4]));
	// iv.invetition.setTilgungAusErneuerungsFond(Integer
	// .parseInt(d[5]));
	// iv.invetition.setLebensdauerInJahre(Integer.parseInt(d[6]));
	// iv.invetition.setTilgungDurchAmortisation(iv.invetition
	// .getInvestitionsBetrag()
	// - iv.invetition.getTilgungAusErneuerungsFond());
	// } catch (Exception e) {
	// System.out
	// .println("130802-0040 Daten für Investition fehlerhaft");
	// }
	// } else {
	// System.out
	// .println("130802-0033 Daten für Investition fehlerhaft");
	// }
	// } else if (d[2].equalsIgnoreCase("Mietzinsbeitrag")) {
	// if (d.length >= 5) {
	// try {
	// iv.addMietzinsbeitrag(new Mietzinsbeitrag(//
	// Integer.parseInt(d[3]), //
	// Double.parseDouble(d[4])));
	// } catch (Exception e) {
	// System.out
	// .println("130802-0044 Daten für Mietzinsbeitrag fehlerhaft");
	// }
	// } else {
	// System.out
	// .println("130802-0042 Daten für Mietzinsbeitrag fehlerhaft");
	// }
	// } else if (d[2].equalsIgnore Case("Tilgung")) {
	// if (d[3].equalsIgnoreCase("Wohnung")) {
	// short wNummer = Short.parseShort(d[4]);
	// iv.invetition.tilgungProWohnung[wNummer - 1] = Double
	// .parseDouble(d[5]);
	// } else if (d[3].equalsIgnoreCase("Linear")) {
	// for (int i = 0; i < iv.invetition.tilgungProWohnung.length; i++) {
	// iv.invetition.tilgungProWohnung[i] = 100.00d /
	// iv.invetition.tilgungProWohnung.length;
	// }
	// } else if (d[3].equalsIgnoreCase("Wohnflaeche")) {
	// if (ds.wohnungsanteilFlaeche == null) {
	// ds.wohnungsanteilFlaeche = new double[14];
	// double summe = 0;
	// for (int i = 0; i < ds.wohnungen.length; i++) {
	// summe += ds.wohnungen[i].getFlaeche();
	// }
	// for (int i = 0; i < ds.wohnungen.length; i++) {
	// ds.wohnungsanteilFlaeche[i] = (ds.wohnungen[i]
	// .getFlaeche() * 100) / summe;
	// }
	// }
	// iv.invetition.tilgungProWohnung = ds.wohnungsanteilFlaeche;
	//
	// } else if (d[3].equalsIgnoreCase("Bewertungsindex")) {
	// if (ds.wohnungsanteilBewertet == null) {
	// ds.wohnungsanteilBewertet = new double[14];
	// double summe = 0;
	// for (int i = 0; i < ds.wohnungen.length; i++) {
	// summe += ds.wohnungen[i].getBewertung();
	// }
	// for (int i = 0; i < ds.wohnungen.length; i++) {
	// ds.wohnungsanteilBewertet[i] = (ds.wohnungen[i]
	// .getBewertung() * 100) / summe;
	// }
	// }
	// iv.invetition.tilgungProWohnung = ds.wohnungsanteilBewertet;
	// }
	// }
	// }

	private void parsKapital(String[] d) {
		InvestiertesKapital k = //
		new InvestiertesKapital(new Long(d[1]), d[2], d[3]);
		ds.getInvestiertesKapital().put(k.getId(), k);
	}

	/**
	 * @param d
	 */
	private void parsZinssatz(String[] d) {
		KapitalZinssatz kz = new KapitalZinssatz(Integer.parseInt(d[2]),
				Double.parseDouble(d[3]), Double.parseDouble(d[4]),
				Double.parseDouble(d[6]), Double.parseDouble(d[6]));

		InvestiertesKapital kapital = ds.getInvestiertesKapital().get(
				new Long(d[1]));
		List<KapitalZinssatz> zinssatz = kapital.getZinssatz();
		zinssatz.add(kz);

	}
}
