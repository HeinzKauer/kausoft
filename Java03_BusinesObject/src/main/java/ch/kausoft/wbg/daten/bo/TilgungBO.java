package ch.kausoft.wbg.daten.bo;

import java.util.ArrayList;
import java.util.List;

import ch.kausoft.kostenmiete.InvestiertesKapital;
import ch.kausoft.kostenmiete.Investition;
import ch.kausoft.kostenmiete.Tilgung;
import ch.kausoft.kostenmiete.KapitalZinssatz;
import ch.kausoft.kostenmiete.ZinsCase;

public class TilgungBO {

	public static void rechnenTilgungsplan(InvestitionBO invest) {
		@SuppressWarnings("unchecked")
		List<Tilgung>[] workList = new List[ZinsCase.values().length];

		for (ZinsCase day : ZinsCase.values()) {
			int i = day.ordinal();
			workList[i] = createTilgungTabelle(invest, i);
			System.out.println(day.name() + " " + day.ordinal());
			rechnen(workList[i], invest.getInvetition().getTilgungDurchAmortisation());
		}
//		list(workList);
		invest.getInvetition().setTilgungList(workList);
	}

	/**
	 * @param workList
	 */
	private static void list(List<Tilgung>[] workList) {
		for (List<Tilgung> list : workList) {
			for (Tilgung tilgung : list) {
				System.out.println(tilgung);
			}
		}
	}

	/**
	 * 
	 * @param invest
	 * @param zinsCase
	 * @return createTilgungTabelle
	 */
	private static List<Tilgung> createTilgungTabelle(InvestitionBO invest, int zinsCase) {
		int aktivierungsJahr = invest.getInvetition().getAktivierungsJahr();
		int aktivierungsMonat = invest.getInvetition().getAktivierungsMonat();
		int lebensdauerInJahre = invest.getInvetition().getLebensdauerInJahre();

		int tilgAbJahr = aktivierungsJahr;
		KapitalZinssatz beforZinssatz = null;

		List<Tilgung> workList = new ArrayList<Tilgung>();
		Tilgung tilgung = new Tilgung(aktivierungsJahr, aktivierungsMonat);

		Investition invetition1 = invest.getInvetition();
		InvestiertesKapital investiertesKapital1 = invetition1.getInvestiertesKapital();
		List<KapitalZinssatz> zinssatz = investiertesKapital1.getZinssatz();

		for (KapitalZinssatz kapitalZinssatz : zinssatz) {
			int zinsAbJahr = kapitalZinssatz.getAbJahr();
			int zinsAbMonat = kapitalZinssatz.getAbMonat();
			do {
				if (zinsAbJahr > tilgAbJahr) {
					tilgung.kapitalZinssatz = beforZinssatz;
					double[] z = beforZinssatz.getZins();
					tilgung.setZins(z[zinsCase]);
					workList.add(tilgung);
					tilgAbJahr += 1;
					tilgung = new Tilgung(tilgAbJahr, 01);
					continue;
				}
				if (zinsAbJahr == tilgAbJahr) {
					if (zinsAbMonat > tilgung.getAbMonat()) {
						tilgung.setBisUndMitMonat(zinsAbMonat - 1);
						tilgung.kapitalZinssatz = beforZinssatz;
						double[] z = beforZinssatz.getZins();
						tilgung.setZins(z[zinsCase]);
						workList.add(tilgung);
						tilgung = new Tilgung(zinsAbJahr, zinsAbMonat);
						continue;
					} else {
						beforZinssatz = kapitalZinssatz;
						break;
					}
				}
				beforZinssatz = kapitalZinssatz;
				break;
			} while (tilgAbJahr <= aktivierungsJahr + lebensdauerInJahre);
		}
		return workList;
	}

	private static void rechnen(List<Tilgung> tilgungWork, double investition) {
		double jaehrlicherMietzinsbeitrag = 0d;
		// double investition = invest.getInvetition().getInvestitionsBetrag();
		double resrschuld = 0d;
		do {
			jaehrlicherMietzinsbeitrag += 1000d;
			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag, tilgungWork);
		} while (resrschuld > 1000d);
		jaehrlicherMietzinsbeitrag -= 1000d;
		do {
			jaehrlicherMietzinsbeitrag += 100d;
			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag, tilgungWork);
		} while (resrschuld > 100d);
		jaehrlicherMietzinsbeitrag -= 100d;
		do {
			jaehrlicherMietzinsbeitrag += 10d;
			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag, tilgungWork);
		} while (resrschuld > 10d);
		jaehrlicherMietzinsbeitrag -= 10d;
		do {
			jaehrlicherMietzinsbeitrag += 1d;
			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag, tilgungWork);
		} while (resrschuld > 1d);
		jaehrlicherMietzinsbeitrag -= 1d;
		do {
			jaehrlicherMietzinsbeitrag += 0.1d;
			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag, tilgungWork);
		} while (resrschuld > 0.1d);
		jaehrlicherMietzinsbeitrag -= 0.1d;
		do {
			jaehrlicherMietzinsbeitrag += 0.10d;
			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag, tilgungWork);
		} while (resrschuld > 0.01d);
	}

	private static double rechnenIntern(double investition, double jaehrlicherMietzinsbeitrag, List<Tilgung> pTilgungWork) {
		List<Tilgung> tilgungWork = pTilgungWork;
		double resrschuld = investition;
		for (Tilgung work : tilgungWork) {
			work.setBetrag(resrschuld);
			resrschuld = rechnenJaehrlicheBeitraege(jaehrlicherMietzinsbeitrag, work);
		}
		return resrschuld;
	}

	/**
	 * 
	 * @param jaehrlicherMietzinsbeitrag
	 * @return restschuld
	 */
	public static double rechnenJaehrlicheBeitraege(double jaehrlicherMietzinsbeitrag, Tilgung work) {

		double zbj = work.getBetrag() * work.getZins() / 100;
		double abj = jaehrlicherMietzinsbeitrag - zbj;
		work.setZinsBetragJahr(zbj);
		work.setAmortisationsBetragJahr(abj);
		int anzahlMonate = work.getBisUndMitMonat() - work.getAbMonat() + 1;
		if (anzahlMonate == 12) {
			work.setZinsBetragPeriode(zbj);
			work.setAmortisationsBetragPeriode(abj);
		} else {
			work.setZinsBetragPeriode(zbj * anzahlMonate / 12);
			work.setAmortisationsBetragPeriode(abj * anzahlMonate / 12);
		}
		double rest = work.getBetrag() - work.getAmortisationsBetragPeriode();
		return rest;
	}

	// Tilgung tilgung = new Tilgung();
	// Investition investition;
	//
	// InvestitionBO invest;
	//
	// TilgungBO tilgungBefore;
	// TilgungBO tilgungNext;

	// public TilgungBO(InvestitionBO invest, int zinsCase) {
	// this.invest = invest;
	// investition = invest.getInvetition();
	// investition.setTilgung(this.tilgung);
	// tilgung.setZinsCase(zinsCase);
	// tilgung.setBetrag(invest.getInvetition().getTilgungDurchAmortisation());
	// tilgung.setJahr(invest.getInvetition().getAktivierungsJahr());
	// }

	// private TilgungBO(TilgungBO tparent) {
	// tilgungBefore = tparent;
	// tilgungNext = this;
	// this.invest = new InvestitionBO(
	// tparent.tilgungBefore.tilgung.getInvestition());
	// this.tilgung.setZinsCase(tilgungBefore.tilgung.getZinsCase());
	// this.tilgung.setBetrag(tilgungBefore.tilgung.getBetrag()
	// - tilgungBefore.tilgung.getAmortisationsBetrag());
	// this.tilgung.setJahr(tilgungBefore.tilgung.getJahr() + 1);
	// }

	// public void rechnenMietzinsbeitragVorgegeben() {
	// KapitalZinssatz kz = invest.getKapitalZins(tilgung.getJahr());
	// tilgung.setZinsBetrag(tilgung.getBetrag() * kz.zins[tilgung.getZinsCase()]
	// / 100);
	// // TODO Mietzinsbeitrag mb = invest.getMietzinsbeitrag(this.jahr);
	// // TODO amortisationsBetrag = mb.jaehrlicherMietzinsbeitrag - zinsBetrag;
	// }

	// /**
	// *
	// * @param jaehrlicherMietzinsbeitrag
	// * @return restschuld
	// */
	// public double rechnenJaehrlicheBeitraege(double jaehrlicherMietzinsbeitrag) {
	// KapitalZinssatz kz = invest.getKapitalZins(tilgung.getJahr());
	// double zb = tilgung.getBetrag() * kz.zins[tilgung.getZinsCase()] / 100;
	// double ab = jaehrlicherMietzinsbeitrag - zb;
	// tilgung.setZinsBetrag(zb);
	// tilgung.setAmortisationsBetrag(ab);
	// // return betrag;
	// double b = tilgung.getBetrag();
	// double ab2 = tilgung.getAmortisationsBetrag();
	// double rest = tilgung.getBetrag() - tilgung.getAmortisationsBetrag();
	// return rest;
	// }

	// public void consolOut() {
	// System.out
	// .println("131004-2210 "
	// + tilgung.getJahr()
	// + " "
	// + (int) tilgung.getBetrag()
	// + " "
	// + (int) tilgung.getZinsBetrag()
	// + " "
	// + (int) tilgung.getAmortisationsBetrag()
	// + " "
	// + (int) ((tilgung.getZinsBetrag() + tilgung
	// .getAmortisationsBetrag()) / 12)
	// + " "
	// + ((investition.getLebensdauerInJahre()
	// - (this.tilgung.getJahr() - investition.getAktivierungsJahr()) > 0) ? this.tilgung
	// .getJahr() - investition.getAktivierungsJahr() + 1
	// : "Ueberschreitung Amortisierungsdauer"));
	// }

	// public void consolOutDetail(double zins, Investition invest2) {
	// System.out.println("131004-2217 " //
	// + invest2.getId()
	// + " "
	// + invest2.getBezeichnung() //
	// + " Betrag "
	// + tilgung.getBetrag() //
	// + " Zinskosten " //
	// + zins //
	// + " Miete / Jahr "
	// + ((tilgung.getZinsBetrag() + tilgung.getAmortisationsBetrag()))
	// + " Miete / Monat "
	// + ((tilgung.getZinsBetrag() + tilgung.getAmortisationsBetrag()) / 12));
	// }

	// public void consolOutDetail(Investition invest2, double wohnungsanteil) {
	// boolean gerundet = true;
	// if (gerundet) {
	// System.out
	// .println("131004-1212 "
	// + invest2.getId()
	// + " "
	// + invest2.getBezeichnung() //
	// + " Betrag "
	// + (int) (tilgung.getBetrag() * wohnungsanteil / 100)
	// + " ("
	// + (int) (tilgung.getZinsBetrag() * wohnungsanteil / 100)
	// + ","
	// + (int) (tilgung.getAmortisationsBetrag() * wohnungsanteil / 100)
	// + ") Miete / Jahr "
	// + ((int) ((tilgung.getZinsBetrag() + tilgung
	// .getAmortisationsBetrag()) * wohnungsanteil) / 100)
	// + " Monat "
	// + ((int) ((tilgung.getZinsBetrag() + tilgung
	// .getAmortisationsBetrag()) / 12 * wohnungsanteil) / 100));
	// } else {
	// System.out.println("131004-1213 "
	// + invest2.getId()
	// + " "
	// + invest2.getBezeichnung() //
	// + " Betrag " + tilgung.getBetrag() * wohnungsanteil / 100 + " ("
	// + (int) (tilgung.getZinsBetrag() * wohnungsanteil / 100) + ","
	// + (int) (tilgung.getAmortisationsBetrag() * wohnungsanteil / 100)
	// + ") Miete / Jahr "
	// + (tilgung.getZinsBetrag() + tilgung.getAmortisationsBetrag())
	// * wohnungsanteil / 100 + " Monat "
	// + (tilgung.getZinsBetrag() + tilgung.getAmortisationsBetrag()) / 12
	// * wohnungsanteil / 100);
	// }
	// }

	// private int amortisationsjahr() {
	// return (tilgung.getJahr() - invest.getInvetition().getAktivierungsJahr() + 1);
	// }

	// public boolean fertig() {
	// return tilgung.getBetrag() <= 0;
	// }

	// public TilgungBO TilgungRechnen() {
	// TilgungBO t = this;
	// do {
	// t.rechnenMietzinsbeitragVorgegeben();
	// t = new TilgungBO(t);
	// } while (!t.fertig() && t.amortisationsjahr() < 200);
	// return this;
	// }

	// public TilgungBO tilgungsPlanRechnen() {
	// double beitrag = 0d;
	// double resrschuld;
	// do {
	// beitrag += 1000d;
	// resrschuld = tilgungsPlanRechnenIntern(beitrag);
	// } while (resrschuld > 1000d);
	// beitrag -= 1000d;
	// do {
	// beitrag += 100d;
	// resrschuld = tilgungsPlanRechnenIntern(beitrag);
	// } while (resrschuld > 100d);
	// beitrag -= 100d;
	// do {
	// beitrag += 10d;
	// resrschuld = tilgungsPlanRechnenIntern(beitrag);
	// } while (resrschuld > 10d);
	// beitrag -= 10d;
	// do {
	// beitrag += 1d;
	// resrschuld = tilgungsPlanRechnenIntern(beitrag);
	// } while (resrschuld > 1d);
	// beitrag -= 1d;
	// do {
	// beitrag += 0.1d;
	// resrschuld = tilgungsPlanRechnenIntern(beitrag);
	// } while (resrschuld > 0.1d);
	// beitrag -= 0.1d;
	// do {
	// beitrag += 0.10d;
	// resrschuld = tilgungsPlanRechnenIntern(beitrag);
	// } while (resrschuld > 0.01d);
	// return this;
	// }

	// private double tilgungsPlanRechnenIntern(double beitrag) {
	// TilgungBO t = this;
	// double resrschuld;
	// do {
	// resrschuld = t.rechnenJaehrlicheBeitraege(beitrag);
	// t = new TilgungBO(t);
	// if (t.tilgung.getBetrag() < t.tilgung.getAmortisationsBetrag()) {
	//
	// }
	// } while (t.amortisationsjahr() < (this.investition.getLebensdauerInJahre() + 1));
	// // } while (t.betrag > t.amortisationsBetrag);
	// return resrschuld;
	// }

	// public void listConsoloutput() {
	// System.out.println("131001-2209 --------------------------");
	// TilgungBO t = this;
	// double zins = 0d;
	// do {
	// // t.consolOut();
	// zins += t.tilgung.getZinsBetrag();
	// t.tilgung = t.tilgung.getTilgungNext();
	// } while (t != null);
	// this.consolOutDetail(zins, this.invest.getInvetition());
	// }

}
