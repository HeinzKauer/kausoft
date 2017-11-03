package ch.kausoft.kostenmiete;

import ch.kausoft.basic.DatenBB;
import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.basic.JahrMonat;
import ch.kausoft.kostenmiete.Tilgung.TilgungStrategie;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public final class Investition extends DatenBB {

	/**
	 * Wird von Investiertem Kapital Amortisiert
	 */
	private InvestiertesKapital investiertesKapital;

	private List<Tilgung>[] tilgungList;

	private HashMap<JahrMonat, Mietzinsbeitrag> mietzinsbeitrag = new HashMap<JahrMonat, Mietzinsbeitrag>();


	/**
	 * Ein investierter Betrag der während einer bestimmten Dauer amortisiert und getilgt werden soll
	 */
	private int investitionsBetrag;

	/**
	 * Ein Teil der Investition kann aus dem Erneuerungsfond getilgt werden.
	 */
	private int tilgungAusErneuerungsFond;

	/**
	 * Differenz investitionsBetrag - tilgungAusErneuerungsFond
	 * 
	 * Transient
	 */
	private int tilgungDurchAmortisation;

	/**
	 * Aktiviwreungsjahr
	 */
	private int aktivierungsJahr;

	/**
	 * Aktivierungs Monat / Default = Januar
	 */
	private int aktivierungsMonat = 01;

	/**
	 * Die Lebensdauer in Jahren
	 */
	private int lebensdauerInJahre;
	
	
	private TilgungStrategie tilgungStrategieWohung = TilgungStrategie.automatisch;
	private TilgungStrategie tilgungStrategieLiegenschaft = TilgungStrategie.automatisch;
	private TilgungStrategie tilgungStrategieSiedlung = TilgungStrategie.automatisch;	
	
	private TilgungWert tilgungsWertWohnung;
	private TilgungWert tilgungsWertLiegenschaft;
	private TilgungWert tilgungsWertSiedlung;
	


//	private HashMap<Long, TilgungWert> tilgungWerte = new HashMap<Long, TilgungWert>();

	/**
     * 
     */
	private double tilgungProWohnung[] = new double[14];

	public Investition() {
	}

	public Investition(Long pId, InvestiertesKapital pKapital, String pBezeichnung, String pBeschreibung,
			int pAktivierungsJahr, int pAktivierungsMonat, int pInvestitionsBetrag, int pLebensdauerInJahre,
			int pTilgungAusErneuerungsFond) {
		super(pId, pBezeichnung, pBeschreibung);
		setInvestiertesKapital(pKapital);
		setAktivierungsJahr(pAktivierungsJahr);
		setAktivierungsMonat(pAktivierungsMonat);
		setInvestitionsBetrag(pInvestitionsBetrag);
		setLebensdauerInJahre(pLebensdauerInJahre);
		setTilgungAusErneuerungsFond(pTilgungAusErneuerungsFond);
		setTilgungDurchAmortisation(pInvestitionsBetrag - pTilgungAusErneuerungsFond);
	}

	public static HashMap<Long, Investition> getAll() {
		return DatenSpeicher.getDatenSpeicher().getInvestitionen();
	}


	/**
	 * @since created at 24.07.2014.23:13:51
	 * @param anzahl void
	 */
//	public void setTilgungsStrategierWohung(TilgungStrategie  anzahl) {
//		// TODO Auto-generated method stub
//		
//	}

}