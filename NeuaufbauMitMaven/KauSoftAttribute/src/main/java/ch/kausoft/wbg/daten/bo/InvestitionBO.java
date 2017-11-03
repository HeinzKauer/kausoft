package ch.kausoft.wbg.daten.bo;

import ch.kausoft.kostenmiete.Investition;
import ch.kausoft.kostenmiete.KapitalZinssatz;
import ch.kausoft.kostenmiete.Mietzinsbeitrag;
import ch.kausoft.kostenmiete.Tilgung;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <h1>Investitionen</h1> Eine Investition wird getötigt und dann zu einem bestimmten Zeitpunkt Aktiviert. Danach muss
 * diese Investition Amortisiert oder abgetragen werden. Eine werterhaltende Investition sollte durch angespartes
 * Kapital aus einem Erneuerungsfond getilt werden können. Der Wertvermehrende Anteil muss innerhalb einer Lebensdauer
 * Amortisiert werden.
 * 
 * <h2>Tilgung aus dem Erneuerungs Fond</h2> Werterhaltende Massnahmen sollten zum Teil aus dem Erneuerungfond und zum
 * Teil aus Eigenkapital Finanziert werden können.
 * 
 * <h2>Tilgung durch Amortiasation wöhren einer Lebensdauer</h2> Die Investition Wert vermehrender Massnahmen sollten
 * innerhalb der Lebensdauer Amortisiert und getilgt werden.
 * 
 * 
 * 
 * @author Heinz
 * 
 */
public class InvestitionBO {

	private Investition invetition;

	// public TilgungBO tilgung = new TilgungBO();

	public Investition getInvetition() {
		return invetition;
	}

	// public void setTilgung(TilgungBO tilgung) {
	// this.getInvetition().setTilgung(tilgung.get);
	// }

	public void setInvetition(Investition invetition) {
		this.invetition = invetition;
	}

	public InvestitionBO(Investition pInvestition) {
		invetition = pInvestition;
	}

	public KapitalZinssatz getKapitalZins(int jahr) {
		try {
			List<KapitalZinssatz> kz = invetition.getInvestiertesKapital().getZinssatz();
			for (KapitalZinssatz kapitalZinssatz : kz) {
				if (jahr <= kapitalZinssatz.abJahr) // ???????????????????
				{
					return kapitalZinssatz;
				}
			}

		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Mietzinsbeitrag getMietzinsbeitrag(int jahr) {
		for (int i = jahr; i >= 2000; i--) {
			Mietzinsbeitrag x = invetition.getMietzinsbeitrag().get(i);
			if (x != null) {
				return x;
			}
		}
		return null;
	}

	public void addMietzinsbeitrag(Mietzinsbeitrag mietzinsbeitrag) {
		invetition.getMietzinsbeitrag().put(mietzinsbeitrag.getGueltigAb(), mietzinsbeitrag);
	}

	public static List<InvestitionBO> getInvestitionen() {
		List<InvestitionBO> bo = new ArrayList<InvestitionBO>();
		for (Investition inv : Investition.getAll().values()) {
			System.out.println(inv);
			InvestitionBO invBo = new InvestitionBO(inv);
			bo.add(invBo);
		}
		return bo;

	}

	/**
	 * 
	 */
	public int getLebensdauerInJahre() {
		return getInvetition().getLebensdauerInJahre();
	}

	/**
	 * @since created at 08.02.2014.23:28:02
	 * @param investitionenSheet
	 *          void
	 */
	public static void fillSheet(HSSFSheet investitionenSheet) {
		List<InvestitionBO> investitionen = getInvestitionen();
		int rownum = 1;
		int cc = 1;
		HSSFRow row = investitionenSheet.createRow(rownum++);
		row.createCell(cc++).setCellValue("Bezeichnung");
		row.createCell(cc++).setCellValue("Beschreibung");
		row.createCell(cc++).setCellValue("InvestitionsBetrag");
		row.createCell(cc++).setCellValue("AktivierungsJahr");
		row.createCell(cc++).setCellValue("AktivierungsMonat");
		row.createCell(cc++).setCellValue("LebensdauerInJahre");
		row.createCell(cc++).setCellValue("TilgungAusErneuerungsFond");
		row.createCell(cc++).setCellValue("TilgungDurchAmortisation");
		cc++;
		cc++;
		cc++;
		row.createCell(cc++).setCellValue("optimistisch / Jahr");
		row.createCell(cc++).setCellValue("optimistisch / Monat");
		row.createCell(cc++).setCellValue("	pessimistisch / Jahr");
		row.createCell(cc++).setCellValue("	pessimistisch / Monat");
		row.createCell(cc++).setCellValue("	Real is / Jahr");
		row.createCell(cc++).setCellValue("	Real is / Monat");
		row.createCell(cc++).setCellValue("	Rendite / Jahr");
		row.createCell(cc++).setCellValue("	Rendite / Monat");
		
		


		for (InvestitionBO investitionBO : investitionen) {
			cc = 1;
			row = investitionenSheet.createRow(rownum++);
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getBezeichnung());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getBeschreibung());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getInvestitionsBetrag());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getAktivierungsJahr());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getAktivierungsMonat());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getLebensdauerInJahre());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getTilgungAusErneuerungsFond());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getTilgungDurchAmortisation());

			List<Tilgung>[] tilgungList = investitionBO.getInvetition().getTilgungList();
			cc++;
			cc++;
			cc++;
			for (List<Tilgung> list : tilgungList) {
				for (Tilgung tilgung : list) {
					row.createCell(cc++).setCellValue(tilgung.getAmortisationsBetragJahr() + tilgung.getZinsBetragJahr());
					row.createCell(cc++).setCellValue((tilgung.getAmortisationsBetragJahr() + tilgung.getZinsBetragJahr()) / 12);
					break;
				}
			}
		}

		for (InvestitionBO investitionBO : investitionen) {
			cc = 1;
			row = investitionenSheet.createRow(rownum++);
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getBezeichnung());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getBeschreibung());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getInvestitionsBetrag());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getAktivierungsJahr());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getAktivierungsMonat());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getLebensdauerInJahre());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getTilgungAusErneuerungsFond());
			row.createCell(cc++).setCellValue(investitionBO.getInvetition().getTilgungDurchAmortisation());

			List<Tilgung>[] tilgungList = investitionBO.getInvetition().getTilgungList();
			for (List<Tilgung> list : tilgungList) {
				HSSFRow rowt = investitionenSheet.createRow(rownum++);
				cc = 3;
				rowt.createCell(cc++).setCellValue("AbJahr");
				rowt.createCell(cc++).setCellValue("AbMonat");
				rowt.createCell(cc++).setCellValue("BisUndMitMonat");
				rowt.createCell(cc++).setCellValue("Betrag");
				rowt.createCell(cc++).setCellValue("Zins");
				rowt.createCell(cc++).setCellValue("ZinsBetragJahr");
				rowt.createCell(cc++).setCellValue("ZinsBetragPeriode");
				rowt.createCell(cc++).setCellValue("AmortisationsBetragJahr");
				rowt.createCell(cc++).setCellValue("AmortisationsBetragPeriode");
//				rowt.createCell(cc++).setCellValue("AmortisationsBetragJahr + ZinsBetragJahr");
//				rowt.createCell(cc++).setCellValue("AmortisationsBetragPeriode + ZinsBetragPeriode");

				rowt = investitionenSheet.createRow(rownum++);
				for (Tilgung tilgung : list) {
					rowt = investitionenSheet.createRow(rownum++);
					cc = 3;
					rowt.createCell(cc++).setCellValue(tilgung.getAbJahr());
					rowt.createCell(cc++).setCellValue(tilgung.getAbMonat());
					rowt.createCell(cc++).setCellValue(tilgung.getBisUndMitMonat());
					rowt.createCell(cc++).setCellValue(tilgung.getBetrag());
					rowt.createCell(cc++).setCellValue(tilgung.getZins());
					rowt.createCell(cc++).setCellValue(tilgung.getZinsBetragJahr());
					rowt.createCell(cc++).setCellValue(tilgung.getZinsBetragPeriode());
					rowt.createCell(cc++).setCellValue(tilgung.getAmortisationsBetragJahr());
					rowt.createCell(cc++).setCellValue(tilgung.getAmortisationsBetragPeriode());
//					rowt.createCell(cc++).setCellValue(tilgung.getAmortisationsBetragJahr() + tilgung.getZinsBetragJahr());
//					rowt.createCell(cc++).setCellValue(tilgung.getAmortisationsBetragPeriode() + tilgung.getZinsBetragPeriode());
				}
			}
		}
	}

}
