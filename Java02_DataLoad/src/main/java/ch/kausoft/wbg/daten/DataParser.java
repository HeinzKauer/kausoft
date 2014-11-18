/**
 * @author Heinz Kauer 06.01.2014.21:43:19 <br/>
 * Copyright 2014 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.wbg.daten;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.kostenmiete.InvestiertesKapital;
import ch.kausoft.kostenmiete.Investition;
import ch.kausoft.kostenmiete.KapitalZinssatz;
import ch.kausoft.kostenmiete.Liegenschaft;
import ch.kausoft.kostenmiete.Siedlung;
import ch.kausoft.kostenmiete.Tilgung;
import ch.kausoft.kostenmiete.Tilgung.TilgungStrategie;
import ch.kausoft.kostenmiete.TilgungWert;
import ch.kausoft.kostenmiete.TilgungWert.Typ;
import ch.kausoft.kostenmiete.Wohnung;

/**
 * 
 */
public class DataParser {

	public static void parsInvestiertesKapital(String[] d) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		Long pId = new Long(d[1]);
		String pBezeichnung = d[2];
		String pBeschreibung = d[3];
		InvestiertesKapital k = new InvestiertesKapital(pId, pBezeichnung, pBeschreibung);
		ds.getInvestiertesKapital().put(k.getId(), k);
	}

	/**
	 * @since created at 06.01.2014.21:37:09
	 * @param rType
	 * @param row
	 *          void
	 */
	public static void parsInvestiertesKapital(Row rType, Row row) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		Long pId = new Long(-1);
		String pBezeichnung = "";
		String pBeschreibung = "";
		for (int i_cell = 2; i_cell < rType.getLastCellNum(); i_cell++) {
			Cell cell = rType.getCell(i_cell);
			Cell cellData = row.getCell(i_cell);
			String stringCellValue = cell.getStringCellValue();
			System.out.println(stringCellValue);
			if (stringCellValue.equalsIgnoreCase("ID")) {
				pId = (long) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Bezeichnung")) {
				pBezeichnung = cellData.getStringCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Beschreibung")) {
				pBeschreibung = cellData.getStringCellValue();
			}
		}
		InvestiertesKapital k = new InvestiertesKapital(pId, pBezeichnung, pBeschreibung);
		ds.getInvestiertesKapital().put(k.getId(), k);
	}

	/**
	 * @param d
	 */
	public static void parsKapitalZinssatz(String[] d) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		int abJahr = Integer.parseInt(d[2]);
		double zinsOptimistisch = Double.parseDouble(d[3]);
		double zinsPessimistisch = Double.parseDouble(d[4]);
		double zinsIst = Double.parseDouble(d[5]);
		double zinsRendite = Double.parseDouble(d[6]);
		KapitalZinssatz kz = new KapitalZinssatz(abJahr, zinsOptimistisch, zinsPessimistisch, zinsIst, zinsRendite);
		InvestiertesKapital kapital = ds.getInvestiertesKapital().get(new Long(d[1]));
		List<KapitalZinssatz> zinssatz = kapital.getZinssatz();
		zinssatz.add(kz);
	}

	/**
	 * @since created at 06.01.2014.21:37:12
	 * @param rType
	 * @param row
	 *          void
	 */
	public static void parsKapitalZinssatz(Row rType, Row row) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		long investID = -1;
		int abJahr = -1;
		int abMonat = 1;
		double zinsOptimistisch = Double.parseDouble("-1");
		double zinsPessimistisch = Double.parseDouble("-1");
		double zinsIst = Double.parseDouble("-1");
		double zinsRendite = Double.parseDouble("-1");

		for (int i = 2; i < rType.getLastCellNum(); i++) {
			Cell cell = rType.getCell(i);
			if (cell == null)
				continue;
			Cell cellData = row.getCell(i);
			String stringCellValue = cell.getStringCellValue();
			System.out.println(stringCellValue);
			if (stringCellValue.equalsIgnoreCase("IKID")) {
				investID = (long) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("abJahr")) {
				abJahr = (int) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("abMonat")) {
				abMonat = (int) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("optimistisch")) {
				zinsOptimistisch = cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("pessimistisch")) {
				zinsPessimistisch = cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Real ist")) {
				zinsIst = cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("rendite")) {
				zinsRendite = cellData.getNumericCellValue();
			}
		}
		KapitalZinssatz kz = new KapitalZinssatz(abJahr, abMonat, zinsOptimistisch, zinsPessimistisch, zinsIst, zinsRendite);
		InvestiertesKapital kapital = ds.getInvestiertesKapital().get(investID);
		List<KapitalZinssatz> zinssatz = kapital.getZinssatz();
		zinssatz.add(kz);
	}

	/**
	 * 
	 * @since created at 17.01.2014.23:21:46
	 * @param rType
	 * @param row
	 *          void
	 */
	public static void parsWohnung(Row rType, Row row) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		long id = -1;
		long liegenschaftID = -1;
		short hausNr = -1;
		short wNummer = -1;
		String berz = "";
		String besch = "";
		double flaeche = -1;
		double bewertung = -1;

		for (int i = 2; i < rType.getLastCellNum(); i++) {
			Cell cell = rType.getCell(i);
			if (cell == null)
				continue;
			Cell cellData = row.getCell(i);
			String stringCellValue = cell.getStringCellValue();
			System.out.println(stringCellValue);
			if (stringCellValue.equalsIgnoreCase("ID")) {
				id = (long) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("LiegenschaftID")) {
				liegenschaftID = (long) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Bezeichnung")) {
				berz = cellData.getStringCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Beschreibung")) {
				besch = cellData.getStringCellValue();
			} else if (stringCellValue.equalsIgnoreCase("WohnungNr")) {
				wNummer = (short) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("HausNr")) {
				hausNr = (short) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Flaeche")) {
				flaeche = cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Bewertung")) {
				bewertung = cellData.getNumericCellValue();
			}
		}

		Liegenschaft liegenschaft = ds.getLiegenschaft(liegenschaftID);
		Wohnung w = new Wohnung(id, liegenschaft, hausNr, wNummer, berz, besch, flaeche, bewertung);
		ds.getWohnungen().put(id, w);
		liegenschaft.getWohnungen().add(w);
	}

	/**
	 * @since created at 06.01.2014.21:37:21
	 * @param rType
	 * @param row
	 *          void
	 */
	public static void parsWohnung(String[] d) {

		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		Long id = new Long(d[1]);
		short hausNr = Short.parseShort(d[2]);
		short wNummer = Short.parseShort(d[3]);
		String berz = d[4];
		String besch = d[5];
		Double flaeche = Double.parseDouble(d[6]);
		Double bewertung = Double.parseDouble(d[7]);

		Wohnung w = new Wohnung(id, null, hausNr, wNummer, berz, besch, flaeche, bewertung);
		ds.getWohnungen().put(id, w);

	}

	/**
	 * @since created at 06.01.2014.21:37:15
	 * @param rType
	 * @param row
	 *          void
	 */
	public static void parsInvestition(Row rType, Row row) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();

		Long id = null;
		Long idKapital = null;
		String bezeichnung = "";
		String beschreibung = "";
		int jahr = -1;
		int monat = -1;
		int betrag = -1;
		int dauer = -1;
		int betragEFond = -1;

		for (int i_cell = 2; i_cell < rType.getLastCellNum(); i_cell++) {
			Cell cell = rType.getCell(i_cell);
			if (cell == null)
				continue;
			Cell cellData = row.getCell(i_cell);
			String stringCellValue = cell.getStringCellValue();
			System.out.println(stringCellValue);
			if (stringCellValue.equalsIgnoreCase("ID")) {
				id = (long) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("InvestieresKapitalID")) {
				idKapital = (long) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("jahr")) {
				jahr = (int) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Monat")) {
				monat = (int) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("LebensdauerJahre")) {
				dauer = (int) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Betrag")) {
				betrag = (int) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("AmortiasationDurchErneuerungsfon")) {
				betragEFond = (int) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Bezeichnung")) {
				bezeichnung = cellData.getStringCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Beschreibung")) {
				beschreibung = cellData.getStringCellValue();
			}
		}

		InvestiertesKapital investiertesKapital = ds.getInvestiertesKapital().get(idKapital);

		Investition i = new Investition(id, investiertesKapital, bezeichnung, beschreibung, jahr, monat, betrag, dauer,
				betragEFond);
		ds.getInvestitionen().put(id, i);
	}

	public static void parsInvestition(String[] d) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();

		Long id = new Long(d[1]);
		Long idKapital = new Long(d[2]);
		InvestiertesKapital investiertesKapital = ds.getInvestiertesKapital().get(idKapital);
		String bezeichnung = d[3];
		String beschreibung = d[4];
		int jahr = Integer.parseInt(d[5]);
		int monat = Integer.parseInt(d[6]);
		int betrag = Integer.parseInt(d[7]);
		int lebesdauer = Integer.parseInt(d[8]);
		int betragEFond = Integer.parseInt(d[9]);

		Investition i = new Investition(id, investiertesKapital, bezeichnung, beschreibung, jahr, monat, betrag,
				lebesdauer, betragEFond);
		ds.getInvestitionen().put(id, i);
	}

	/**
	 * @since created at 18.01.2014.00:33:52
	 * @param rType
	 * @param row
	 *          void
	 */
	public static void parsLiegenschaft(Row rType, Row row) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		long id = -1;
		long siedlungID = -1;
		String berz = "";
		String besch = "";
		double bewertung = 0;

		for (int i = 2; i < rType.getLastCellNum(); i++) {
			Cell cell = rType.getCell(i);
			if (cell == null)
				continue;
			Cell cellData = row.getCell(i);
			String stringCellValue = cell.getStringCellValue();
			System.out.println(stringCellValue);

			if (stringCellValue.equalsIgnoreCase("ID")) {
				id = (long) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("SiedlungID")) {
				siedlungID = (long) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Bezeichnung")) {
				berz = cellData.getStringCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Beschreibung")) {
				besch = cellData.getStringCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Bewertung")) {
				bewertung = cellData.getNumericCellValue();
			}
		}
		Siedlung siedlung = ds.getSiedlung(siedlungID);
		Liegenschaft liegenschaft = new Liegenschaft(id, siedlung, berz, besch, bewertung);
		ds.getLiegenschaft().put(id, liegenschaft);
		siedlung.getLiegenschaften().add(liegenschaft);
	}

	/**
	 * @since created at 18.01.2014.00:34:10
	 * @param rType
	 * @param row
	 *          void
	 */
	public static void parsSiedlung(Row rType, Row row) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		long id = -1;
		String berz = "";
		String besch = "";
		double bewertung = 0;

		for (int i = 2; i < rType.getLastCellNum(); i++) {
			Cell cell = rType.getCell(i);
			if (cell == null)
				continue;
			Cell cellData = row.getCell(i);
			String stringCellValue = cell.getStringCellValue();
			System.out.println(stringCellValue);
			if (stringCellValue.equalsIgnoreCase("ID")) {
				id = (long) cellData.getNumericCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Bezeichnung")) {
				berz = cellData.getStringCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Beschreibung")) {
				besch = cellData.getStringCellValue();
			} else if (stringCellValue.equalsIgnoreCase("Bewertung")) {
				bewertung = cellData.getNumericCellValue();
			}
		}
		Siedlung liegenschft = new Siedlung(id, berz, besch, bewertung);
		ds.getSiedlung().put(id, liegenschft);

	}

	/**
	 * @since created at 23.07.2014.22:52:21
	 * @param rType
	 * @param row
	 *          void
	 */
	public static void parsTilgungStrategie(Row rType, Row row) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		long lnvestitionID = -1;
		Investition investition = null;
		for (int i = 2; i < rType.getLastCellNum(); i++) {
			Cell cell = rType.getCell(i);
			if (cell == null)
				continue;
			Cell cellData = row.getCell(i);
			String stringCellValue = cell.getStringCellValue();
			System.out.println(stringCellValue);

			if (stringCellValue.equalsIgnoreCase("InvestitionID")) {
				lnvestitionID = (long) cellData.getNumericCellValue();
				investition = ds.getInvestition(lnvestitionID);

			} else {
				if (cellData == null)
					continue;

				String value = cellData.getStringCellValue();
				if (value == null || value.isEmpty()) {
					continue;
				}
				value = value.trim();
				if (stringCellValue.equalsIgnoreCase("Wohnung")) {
					if (value.equals(Tilgung.TilgungStrategie.Anzahl.name())) {
						investition.setTilgungStrategieWohung(Tilgung.TilgungStrategie.Anzahl);
					} else if (value.equals(Tilgung.TilgungStrategie.Bewertung.name())) {
						investition.setTilgungStrategieWohung(Tilgung.TilgungStrategie.Bewertung);
					} else if (value.equals(Tilgung.TilgungStrategie.Flaeche.name())) {
						investition.setTilgungStrategieWohung(Tilgung.TilgungStrategie.Flaeche);
					} else if (value.equals(Tilgung.TilgungStrategie.Individuell.name())) {
						investition.setTilgungStrategieWohung(Tilgung.TilgungStrategie.Individuell);
					}
				} else if (stringCellValue.equalsIgnoreCase("Liegenschaft")) {
					if (value.equals(Tilgung.TilgungStrategie.Anzahl.name())) {
						investition.setTilgungStrategieLiegenschaft(Tilgung.TilgungStrategie.Anzahl);
					} else if (value.equals(Tilgung.TilgungStrategie.Bewertung.name())) {
						investition.setTilgungStrategieLiegenschaft(Tilgung.TilgungStrategie.Bewertung);
					} else if (value.equals(Tilgung.TilgungStrategie.Individuell.name())) {
						investition.setTilgungStrategieLiegenschaft(Tilgung.TilgungStrategie.Individuell);
					}

				} else if (stringCellValue.equalsIgnoreCase("Siedlung")) {
					if (value.equals(Tilgung.TilgungStrategie.Anzahl.name())) {
						investition.setTilgungStrategieSiedlung(Tilgung.TilgungStrategie.Anzahl);
					} else if (value.equals(Tilgung.TilgungStrategie.Bewertung.name())) {
						investition.setTilgungStrategieSiedlung(Tilgung.TilgungStrategie.Bewertung);
					} else if (value.equals(Tilgung.TilgungStrategie.Individuell.name())) {
						investition.setTilgungStrategieSiedlung(Tilgung.TilgungStrategie.Individuell);
					}
				}
			}
		}

		// Tilgung.TilgungStrategie.Anzahl.name()
		//
		// Liegenschaft liegenschft = new Liegenschaft(id, berz, besch);
		// ds.getLiegenschaft().put(id, liegenschft);
		// ds.getSiedlung(siedlungID).getLiegenschaften().add(liegenschft);

	}

	/**
	 * @since created at 23.07.2014.22:53:48
	 * @param rType
	 * @param row
	 *          void
	 */
	public static void parsMietzinsbeitrag(Row rType, Row row) {
		// TODO Auto-generated method stub

	}

	public static void parsTilgungIndividuell(Row rType, Typ wohnung, Row row) {
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		long lnvestitionID = -1;
		Investition investition = null;

		for (int i = 2; i < rType.getLastCellNum(); i++) {
			Cell cell = rType.getCell(i);

			if (cell == null)
				continue;

			Cell cellData = row.getCell(i);
			String stringCellValue = cell.getStringCellValue();
			System.out.println(stringCellValue);

			if (stringCellValue.equalsIgnoreCase("InvestitionID")) {
				lnvestitionID = (long) cellData.getNumericCellValue();
				investition = ds.getInvestition(lnvestitionID);
			} else {
				if (cellData == null)
					continue;

				if (stringCellValue.equalsIgnoreCase("WohnungID")) {
					TilgungWert tw = investition.getTilgungsWertWohnung();
					if (tw == null) {
						tw = new TilgungWert();
						investition.setTilgungsWertWohnung(tw);
					}
					Cell cellDataW = row.getCell(i + 1);
					tw.addWert((long) cellData.getNumericCellValue(), cellDataW.getNumericCellValue());

				} else if (stringCellValue.equalsIgnoreCase("LiegenschaftID")) {
					TilgungWert tw = investition.getTilgungsWertLiegenschaft();
					if (tw == null) {
						tw = new TilgungWert();
						investition.setTilgungsWertLiegenschaft(tw);
					}
					Cell cellDataW = row.getCell(i + 1);
					tw.addWert((long) cellData.getNumericCellValue(), cellDataW.getNumericCellValue());

				} else if (stringCellValue.equalsIgnoreCase("SiedlungID")) {
					TilgungWert tw = investition.getTilgungsWertSiedlung();
					if (tw == null) {
						tw = new TilgungWert();
						investition.setTilgungsWertSiedlung(tw);
					}
					Cell cellDataW = row.getCell(i + 1);
					tw.addWert((long) cellData.getNumericCellValue(), cellDataW.getNumericCellValue());

				}
			}
		}
	}
}
