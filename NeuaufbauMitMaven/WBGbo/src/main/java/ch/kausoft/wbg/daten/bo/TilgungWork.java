/**
 * 
 */
package ch.kausoft.wbg.daten.bo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import ch.kausoft.kostenmiete.KapitalZinssatz;
import ch.kausoft.kostenmiete.Tilgung;
import ch.kausoft.kostenmiete.ZinsCase;

/**
 * @author Heinz
 * 
 */
public class TilgungWork {
//	
//	InvestitionBO invest;
//
//	public TilgungWork(InvestitionBO pInvest) {
//		invest = pInvest;
//	}
//
//	public void rechnenTilgungsplan() {
//		List<Tilgung>[] workList = new List[ZinsCase.values().length];
//
//		for (ZinsCase day : ZinsCase.values()) {
//			int i = day.ordinal();
//			workList[i] = createTilgungTabelle(i);
//			System.out.println(day.name() + " " + day.ordinal());
//			rechnen(workList[i]);
//		}
//		System.out.println();
//	}
//
//	/**
//	 * 
//	 * @param invest
//	 * @param zinsCase
//	 * @return
//	 */
//	private List<Tilgung> createTilgungTabelle(int zinsCase) {
//		int aktivierungsJahr = invest.getInvetition().getAktivierungsJahr();
//		int aktivierungsMonat = invest.getInvetition().getAktivierungsMonat();
//		int lebensdauerInJahre = invest.getInvetition().getLebensdauerInJahre();
//
//		int tilgAbJahr = aktivierungsJahr;
//		KapitalZinssatz beforZinssatz = null;
//
//		List<Tilgung> workList = new ArrayList<Tilgung>();
//		Tilgung tilgung = new Tilgung(aktivierungsJahr, aktivierungsMonat);
//
//		List<KapitalZinssatz> zinssatz = invest.getInvetition()
//				.getInvestiertesKapital().getZinssatz();
//
//		for (KapitalZinssatz kapitalZinssatz : zinssatz) {
//			int zinsAbJahr = kapitalZinssatz.getAbJahr();
//			int zinsAbMonat = kapitalZinssatz.getAbMonat();
//			do {
//				if (zinsAbJahr > tilgAbJahr) {
//					tilgung.kapitalZinssatz = beforZinssatz;
//					double[] z = beforZinssatz.getZins();
//					tilgung.setZins(z[zinsCase]);
//					workList.add(tilgung);
//					tilgAbJahr += 1;
//					tilgung = new Tilgung(tilgAbJahr, 01);
//					continue;
//				}
//				if (zinsAbJahr == tilgAbJahr) {
//					if (zinsAbMonat > tilgung.getAbMonat()) {
//						tilgung.setBisUndMitMonat(zinsAbMonat - 1);
//						tilgung.kapitalZinssatz = beforZinssatz;
//						double[] z = beforZinssatz.getZins();
//						tilgung.setZins(z[zinsCase]);
//						workList.add(tilgung);
//						tilgung = new Tilgung(zinsAbJahr, zinsAbMonat);
//						continue;
//					} else {
//						beforZinssatz = kapitalZinssatz;
//						break;
//					}
//				}
//				beforZinssatz = kapitalZinssatz;
//				break;
//			} while (tilgAbJahr <= aktivierungsJahr + lebensdauerInJahre);
//		}
//		return workList;
//	}
//
//	private void rechnen(List<Tilgung> tilgungWork) {
//		double jaehrlicherMietzinsbeitrag = 0d;
//		double investition = invest.getInvetition().getInvestitionsBetrag();
//		double resrschuld = 0d;
//		do {
//			jaehrlicherMietzinsbeitrag += 1000d;
//			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag,
//					tilgungWork);
//		} while (resrschuld > 1000d);
//		jaehrlicherMietzinsbeitrag -= 1000d;
//		do {
//			jaehrlicherMietzinsbeitrag += 100d;
//			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag,
//					tilgungWork);
//		} while (resrschuld > 100d);
//		jaehrlicherMietzinsbeitrag -= 100d;
//		do {
//			jaehrlicherMietzinsbeitrag += 10d;
//			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag,
//					tilgungWork);
//		} while (resrschuld > 10d);
//		jaehrlicherMietzinsbeitrag -= 10d;
//		do {
//			jaehrlicherMietzinsbeitrag += 1d;
//			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag,
//					tilgungWork);
//		} while (resrschuld > 1d);
//		jaehrlicherMietzinsbeitrag -= 1d;
//		do {
//			jaehrlicherMietzinsbeitrag += 0.1d;
//			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag,
//					tilgungWork);
//		} while (resrschuld > 0.1d);
//		jaehrlicherMietzinsbeitrag -= 0.1d;
//		do {
//			jaehrlicherMietzinsbeitrag += 0.10d;
//			resrschuld = rechnenIntern(investition, jaehrlicherMietzinsbeitrag,
//					tilgungWork);
//		} while (resrschuld > 0.01d);
//	}
//
//	private double rechnenIntern(double investition,
//			double jaehrlicherMietzinsbeitrag, List<Tilgung> pTilgungWork) {
//		List<Tilgung> tilgungWork = pTilgungWork;
//		double resrschuld = investition;
//		for (Tilgung work : tilgungWork) {
//			work.setBetrag(resrschuld);
//			resrschuld = rechnenJaehrlicheBeitraege(jaehrlicherMietzinsbeitrag, work);
//		}
//		return resrschuld;
//	}
//
//	/**
//	 * 
//	 * @param jaehrlicherMietzinsbeitrag
//	 * @return restschuld
//	 */
//	public double rechnenJaehrlicheBeitraege(double jaehrlicherMietzinsbeitrag,
//			Tilgung work) {
//
//		double zbj = work.getBetrag() * work.getZins() / 100;
//		double abj = jaehrlicherMietzinsbeitrag - zbj;
//		work.setZinsBetragJahr(zbj);
//		work.setAmortisationsBetragJahr(abj);
//		int anzahlMonate = work.getBisUndMitMonat() - work.getAbMonat() + 1;
//		if (anzahlMonate == 12) {
//			work.setZinsBetragPeriode(zbj);
//			work.setAmortisationsBetragPeriode(abj);
//		} else {
//			work.setZinsBetragPeriode(zbj * anzahlMonate / 12);
//			work.setAmortisationsBetragPeriode(abj * anzahlMonate / 12);
//		}
//		double rest = work.getBetrag() - work.getAmortisationsBetragPeriode();
//		return rest;
//	}

}