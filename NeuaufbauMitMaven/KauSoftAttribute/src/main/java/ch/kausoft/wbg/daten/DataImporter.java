package ch.kausoft.wbg.daten;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import ch.kausoft.basic.DatenSpeicher;
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

	public DatenSpeicher load(String path) {

		ds = DatenSpeicher.getDatenSpeicher();

		
		File f = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		String zeile;
		try {
			f = new File(path);
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
			DataParser.parsInvestiertesKapital(d);
		} else if (d[0].equalsIgnoreCase("KapitalZinssatz")) {
			DataParser.parsKapitalZinssatz(d);
		} else if (d[0].equalsIgnoreCase("Investition")) {
			DataParser.parsInvestition(d);
		} else if (d[0].equalsIgnoreCase("Wohnung")) {
			DataParser.parsWohnung(d);
		}
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



//	/**
//	 * @param d
//	 */
//	private void parsZinssatz(String[] d) {
//		KapitalZinssatz kz = new KapitalZinssatz(Integer.parseInt(d[2]),
//				Double.parseDouble(d[3]), Double.parseDouble(d[4]),
//				Double.parseDouble(d[6]), Double.parseDouble(d[6]));
//
//		InvestiertesKapital kapital = ds.getInvestiertesKapital().get(
//				new Long(d[1]));
//		List<KapitalZinssatz> zinssatz = kapital.getZinssatz();
//		zinssatz.add(kz);
//	}
}
