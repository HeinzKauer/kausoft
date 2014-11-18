package ch.kausoft.wbg.daten.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.kostenmiete.Investition;
import ch.kausoft.kostenmiete.Liegenschaft;
import ch.kausoft.kostenmiete.Siedlung;
import ch.kausoft.kostenmiete.Tilgung;
import ch.kausoft.kostenmiete.Tilgung.TilgungStrategie;
import ch.kausoft.kostenmiete.TilgungWert;
import ch.kausoft.kostenmiete.Wohnung;

public class WohnungBO {

	private Wohnung wohnung;

	public WohnungBO(Wohnung pWohnung) {
		this.wohnung = pWohnung;
	}

	public Long getId() {
		return wohnung.getId();
	}

	public String getBezeichnung() {
		return wohnung.getBezeichnung();
	}

	public void setBezeichnung(String bezeichnung) {
		this.wohnung.setBezeichnung(bezeichnung);
	}

	public String getBeschreibung() {
		return wohnung.getBeschreibung();
	}

	public void setBeschreibung(String beschreibung) {
		this.setBeschreibung(beschreibung);
	}

	public short getNummer() {
		return wohnung.getNummer();
	}

	public void setNummer(short nummer) {
		this.setNummer(nummer);
	}

	public short getHausnummer() {
		return wohnung.getHausnummer();
	}

	public void setHausnummer(short hausnummer) {
		this.setHausnummer(hausnummer);
	}

	public double getFlaeche() {
		return wohnung.getFlaeche();
	}

	public void setFlaeche(double flaeche) {
		this.setFlaeche(flaeche);
	}

	public double getBewertung() {
		return wohnung.getBewertung();
	}

	public void setBewertung(double bewertung) {
		this.setBewertung(bewertung);
	}

	public void setWohnung(Wohnung wohnung) {
		this.wohnung = wohnung;
	}

	public static WohnungBO create(short pNummer) {
		WohnungBO bo = new WohnungBO();
		bo.wohnung.setNummer(pNummer);
		bo.wohnung.setHausnummer((pNummer <= 7) ? (short) 23 : (short) 25);
		return bo;
	}

	public static List<WohnungBO> loadAll() {
		List<WohnungBO> w = new ArrayList<WohnungBO>();
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		Collection<Wohnung> values = ds.getWohnungen().values();
		for (Wohnung wohnung : values) {
			w.add(new WohnungBO(wohnung));
		}
		return w;
	}

	public static WohnungBO create(Wohnung pWohnung) {
		if (pWohnung == null) {
			pWohnung = new Wohnung();
		}
		WohnungBO bo = new WohnungBO();
		bo.wohnung = pWohnung;
		return bo;
	}

	public WohnungBO() {

	}

	public void list() {
		System.out.println("Wohnung " + wohnung.getNummer() + " Haus " + wohnung.getHausnummer() + wohnung.getBezeichnung()
				+ " // " + wohnung.getBeschreibung() + " Flaeche " + wohnung.getFlaeche() + " Bewertet "
				+ wohnung.getBewertung());
	}

	public Wohnung getWohnung() {
		return wohnung;
	}

	public static List<WohnungBO> getWohnungen() {
		List<WohnungBO> allBo = new ArrayList<WohnungBO>();
		for (Wohnung pojo : Wohnung.getAll().values()) {
			System.out.println(pojo);
			WohnungBO bo = new WohnungBO(pojo);
			allBo.add(bo);
		}
		return allBo;
	}

	/**
	 * @since created at 09.02.2014.21:35:29
	 * @param wohnungenSheet
	 *          void
	 */
	public static void fillSheet(HSSFSheet wohnungenSheet) {
		List<WohnungBO> wohnungen = getWohnungen();
		HSSFRow rowBez = wohnungenSheet.createRow(3);
		HSSFRow rowBesch = wohnungenSheet.createRow(4);
		HSSFRow rowFlaeche = wohnungenSheet.createRow(5);
		HSSFRow rowBewert = wohnungenSheet.createRow(6);
		HSSFRow rowHausnummer = wohnungenSheet.createRow(7);
		HSSFRow rowNummer = wohnungenSheet.createRow(8);

		int colCount = 1;
		rowBez.createCell(colCount).setCellValue("Wohnungsbezeichnung");
		rowBesch.createCell(colCount).setCellValue("Beschreibung / Name");
		rowFlaeche.createCell(colCount).setCellValue("Fläche der Wohnung");
		rowBewert.createCell(colCount).setCellValue("Bewertung der Wohnung");
		rowHausnummer.createCell(colCount).setCellValue("Hausnummer");
		rowNummer.createCell(colCount).setCellValue("Wohnungsnummer");

		colCount = 10;
		for (WohnungBO wBo : wohnungen) {
			rowBez.createCell(colCount).setCellValue(wBo.getWohnung().getBezeichnung());
			rowBesch.createCell(colCount).setCellValue(wBo.getWohnung().getBeschreibung());
			rowFlaeche.createCell(colCount).setCellValue(wBo.getWohnung().getFlaeche());
			rowBewert.createCell(colCount).setCellValue(wBo.getWohnung().getBewertung());
			rowHausnummer.createCell(colCount).setCellValue(wBo.getWohnung().getHausnummer());
			rowNummer.createCell(colCount).setCellValue(wBo.getWohnung().getNummer());
			colCount++;
		}

		try {
			fillMietzinsanteil(wohnungenSheet, wohnungen);
		} catch (Exception e) {
			System.out.println("141117-2329 ---------------> " + e);
		}

	}

	/**
	 * @since created at 01.08.2014.22:06:17
	 * @param wohnungenSheet
	 * @param wohnungen
	 *          void
	 */
	private static void fillMietzinsanteil(HSSFSheet wohnungenSheet, List<WohnungBO> wohnungen) {
		int colCount;
		List<InvestitionBO> investitionen = InvestitionBO.getInvestitionen();

		int rowCountInvestition = 10;
		for (InvestitionBO investitionBO : investitionen) {

			HSSFRow rowInvestition = wohnungenSheet.createRow(rowCountInvestition++);

			colCount = 0;
			Investition iv = investitionBO.getInvetition();
			rowInvestition.createCell(colCount++).setCellValue(iv.getBezeichnung());
			rowInvestition.createCell(colCount++).setCellValue(iv.getBeschreibung());
			rowInvestition.createCell(colCount++).setCellValue(iv.getInvestitionsBetrag());
			rowInvestition.createCell(colCount++).setCellValue(iv.getTilgungAusErneuerungsFond());
			rowInvestition.createCell(colCount++).setCellValue(iv.getTilgungDurchAmortisation());
			rowInvestition.createCell(colCount++).setCellValue(iv.getTilgungStrategieSiedlung().name() + //
					"," + iv.getTilgungStrategieLiegenschaft().name() + //
					"," + iv.getTilgungStrategieWohung().name());

			List<Tilgung>[] tilgungList2 = iv.getTilgungList();
			List<Tilgung> tl = tilgungList2[0];
			Tilgung tilgung = tl.get(0);

			rowInvestition.createCell(colCount++).setCellValue(tilgung.getZins());
			rowInvestition.createCell(colCount++).setCellValue(tilgung.getZinsBetragJahr());
			rowInvestition.createCell(colCount++).setCellValue(tilgung.getAmortisationsBetragJahr());

			boolean isWohnungsID = true;
			boolean isWert = true;
			boolean isSumme = true;
			boolean isMietzins = true;
			boolean isTilgung = true;

			int colCw = colCount;

			if (isWohnungsID) {
				colCw = colCount;
				rowInvestition.createCell(colCw++).setCellValue("Wohnung ID");
				for (WohnungBO wBo : wohnungen) {
					rowInvestition.createCell(colCw++).setCellValue(wBo.getId());
				}
				rowInvestition = wohnungenSheet.createRow(rowCountInvestition++);
			}

			if (isWert) {
				colCw = colCount;
				rowInvestition.createCell(colCw++).setCellValue("Wert");
				for (WohnungBO wBo : wohnungen) {
					Long id = wBo.getId();
					TilgungWert tw = iv.getTilgungsWertWohnung();
					rowInvestition.createCell(colCw++).setCellValue((tw == null) ? 0 : tw.getWertxxxx(id));
				}
				rowInvestition = wohnungenSheet.createRow(rowCountInvestition++);
			}

			if (isSumme) {
				colCw = colCount;
				rowInvestition.createCell(colCw++).setCellValue("Summe");
				for (WohnungBO wBo : wohnungen) {
					Long id = wBo.getId();
					TilgungWert tw = iv.getTilgungsWertWohnung();
					rowInvestition.createCell(colCw++).setCellValue((tw == null) ? 0 : tw.getSumme());
				}
				rowInvestition = wohnungenSheet.createRow(rowCountInvestition++);
			}

			if (isMietzins) {
				colCw = colCount;
				rowInvestition.createCell(colCw++).setCellValue("Calc");
				for (WohnungBO wBo : wohnungen) {
					rowInvestition.createCell(colCw++).setCellValue(calcMietzinsAnteil(investitionBO, wBo));
				}
			}

			if (isTilgung) {
				rowInvestition = wohnungenSheet.createRow(rowCountInvestition++);
				colCw = colCount;
				rowInvestition.createCell(colCw++).setCellValue("Tigung");

				double tdw[] = iv.getTilgungProWohnung();
				for (double d : tdw) {
					rowInvestition.createCell(colCw++).setCellValue(d);
				}

				double[] tilgungProWohnung = iv.getTilgungProWohnung();
				List<Tilgung>[] tilgungList = tilgungList2;
			}

		}
	}

	/**
	 * @since created at 31.07.2014.23:26:41
	 * @param iv
	 * @param wBo
	 * @return Date
	 */
	private static Double calcMietzinsAnteil(InvestitionBO iv, WohnungBO wBo) {
		
		List<Tilgung>[] tilgungList2 = iv.getInvetition().getTilgungList();
		List<Tilgung> tl = tilgungList2[0];
		Tilgung tilgung = tl.get(0);
		
		Long id = wBo.getId();
		TilgungWert twWohnung = iv.getInvetition().getTilgungsWertWohnung();
		double result = tilgung.getZinsBetragJahr() + tilgung.getAmortisationsBetragJahr();
		double bewertungWohnung = twWohnung.getWertxxxx(id);
		boolean berechnet = false;

		if (bewertungWohnung != 0) {
			result = result * bewertungWohnung / twWohnung.getSumme();
			berechnet = true;
			// Liegenschaft
			TilgungWert twLiegenschaft = iv.getInvetition().getTilgungsWertLiegenschaft();
			if (twLiegenschaft == null) {
				TilgungStrategie tilgungStrategieLiegenschaft = iv.getInvetition().getTilgungStrategieLiegenschaft();
				List<Liegenschaft> liegenschaften = wBo.getWohnung().getLiegenschaft().getSiedlung().getLiegenschaften();
				twLiegenschaft = new TilgungWert();
				for (Liegenschaft liegenschaft : liegenschaften) {
					if (tilgungStrategieLiegenschaft == TilgungStrategie.Anzahl) {
						twLiegenschaft.addWert(liegenschaft.getId(), 1.0);
					} else if (tilgungStrategieLiegenschaft == TilgungStrategie.Bewertung) {
						twLiegenschaft.addWert(liegenschaft.getId(), liegenschaft.getBewertung());
					} else {
						twLiegenschaft.addWert(liegenschaft.getId(), 1.0);
					}
				}
				iv.getInvetition().setTilgungsWertLiegenschaft(twLiegenschaft);
			}

			Liegenschaft liegenschaft = wBo.getWohnung().getLiegenschaft();
			double bewertungLiegenschaft = twLiegenschaft.getWertxxxx(liegenschaft.getId());
			if (bewertungLiegenschaft != 0) {
				result = result * bewertungLiegenschaft / twLiegenschaft.getSumme();
				berechnet = true;
				TilgungWert twSiedlung = iv.getInvetition().getTilgungsWertSiedlung();
				Siedlung siedlung = liegenschaft.getSiedlung();

				if (twSiedlung == null) {
					TilgungStrategie tilgungStrategieSiedlung = iv.getInvetition().getTilgungStrategieSiedlung();
					twSiedlung = new TilgungWert();

					List<SiedlungBO> loadAll = SiedlungBO.loadAll();
					for (SiedlungBO siedlungBO : loadAll) {
						Siedlung siedlung2 = siedlungBO.getSiedlung();

						if (tilgungStrategieSiedlung == TilgungStrategie.Anzahl) {
							twSiedlung.addWert(siedlung2.getId(), 1.0);
						} else if (tilgungStrategieSiedlung == TilgungStrategie.Bewertung) {
							twSiedlung.addWert(siedlung2.getId(), siedlung2.getBewertung());
						} else {
							twSiedlung.addWert(siedlung2.getId(), 1.0);
						}
					}
					iv.getInvetition().setTilgungsWertSiedlung(twSiedlung);

				}

				double bewertungSiedlung = twSiedlung.getWertxxxx(siedlung.getId());
				if (bewertungSiedlung != 0) {
					result = result * bewertungSiedlung / twSiedlung.getSumme();
					berechnet = true;
				}
			}
		}
		return (berechnet) ? result : 0.0;
	}

}
