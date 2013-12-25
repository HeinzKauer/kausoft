package ch.kausoft.kostenmiete;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import ch.kausoft.basic.DatenRecord;
import ch.kausoft.basic.JahrMonat;

public final class Investition extends DatenRecord {

	public Investition() {
	}

	public Investition(Long pId, InvestiertesKapital pKapital,
			String pBezeichnung, String pBeschreibung, int pAktivierungsJahr,
			int pAktivierungsMonat, int pInvestitionsBetrag, int pLebensdauerInJahre,
			int pTilgungAusErneuerungsFond) {
		super();
		setId(pId);
		setInvestiertesKapital(pKapital);
		setBezeichnung(pBezeichnung);
		setBeschreibung(pBeschreibung);
		setAktivierungsJahr(pAktivierungsJahr);
		setAktivierungsMonat(pAktivierungsMonat);
		setInvestitionsBetrag(pInvestitionsBetrag);
		setLebensdauerInJahre(pLebensdauerInJahre);
		setTilgungAusErneuerungsFond(pTilgungAusErneuerungsFond);
		setTilgungDurchAmortisation(pInvestitionsBetrag - pTilgungAusErneuerungsFond);
	}

	/**
	 * Wird von Investiertem Kapital Amortisiert
	 */
	private InvestiertesKapital investiertesKapital;

	private Tilgung tilgung;

	private HashMap<JahrMonat, Mietzinsbeitrag> mietzinsbeitrag = new HashMap<JahrMonat, Mietzinsbeitrag>();

	@NonNull
	private String bezeichnung;

	@NonNull
	private String beschreibung;

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

	/**
     * 
     */
	private double tilgungProWohnung[] = new double[14];

	public InvestiertesKapital getInvestiertesKapital() {
		return investiertesKapital;
	}

	public void setInvestiertesKapital(InvestiertesKapital investiertesKapital) {
		this.investiertesKapital = investiertesKapital;
	}

	public Tilgung getTilgung() {
		return tilgung;
	}

	public void setTilgung(Tilgung tilgung) {
		this.tilgung = tilgung;
	}

	public HashMap<JahrMonat, Mietzinsbeitrag> getMietzinsbeitrag() {
		return mietzinsbeitrag;
	}

	public void setMietzinsbeitrag(
			HashMap<JahrMonat, Mietzinsbeitrag> mietzinsbeitrag) {
		this.mietzinsbeitrag = mietzinsbeitrag;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public int getInvestitionsBetrag() {
		return investitionsBetrag;
	}

	public void setInvestitionsBetrag(int investitionsBetrag) {
		this.investitionsBetrag = investitionsBetrag;
	}

	public int getTilgungAusErneuerungsFond() {
		return tilgungAusErneuerungsFond;
	}

	public void setTilgungAusErneuerungsFond(int tilgungAusErneuerungsFond) {
		this.tilgungAusErneuerungsFond = tilgungAusErneuerungsFond;		
	}

	public int getTilgungDurchAmortisation() {
		return tilgungDurchAmortisation;
	}

	public void setTilgungDurchAmortisation(int tilgungDurchAmortisation) {
		this.tilgungDurchAmortisation = tilgungDurchAmortisation;
	}

	public int getAktivierungsJahr() {
		return aktivierungsJahr;
	}

	public void setAktivierungsJahr(int aktivierungsJahr) {
		this.aktivierungsJahr = aktivierungsJahr;
	}

	public int getAktivierungsMonat() {
		return aktivierungsMonat;
	}

	public void setAktivierungsMonat(int aktivierungsMonat) {
		this.aktivierungsMonat = aktivierungsMonat;
	}

	public int getLebensdauerInJahre() {
		return lebensdauerInJahre;
	}

	public void setLebensdauerInJahre(int lebensdauerInJahre) {
		this.lebensdauerInJahre = lebensdauerInJahre;
	}

	public double[] getTilgungProWohnung() {
		return tilgungProWohnung;
	}

	public void setTilgungProWohnung(double[] tilgungProWohnung) {
		this.tilgungProWohnung = tilgungProWohnung;
	}
	
	
	

}