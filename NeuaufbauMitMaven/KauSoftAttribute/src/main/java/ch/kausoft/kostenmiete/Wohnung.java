package ch.kausoft.kostenmiete;

import ch.kausoft.basic.DatenBB;
import ch.kausoft.basic.DatenSpeicher;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@Data
@EqualsAndHashCode(callSuper = false)
public final class Wohnung extends DatenBB {

	/**
	 * Wohnungsnummer
	 */
	private short nummer;

	/**
	 * Hausnummer
	 */
	private short hausnummer;

	/**
	 * Wohnungsfläche
	 */
	private double flaeche;

	/**
	 * Bewertung der Wohnung
	 */
	private double bewertung;

	/**
	 * Gehärt zur Liegenschaft 
	 */
	private Liegenschaft liegenschaft = null;

	public Wohnung(Long pId, Liegenschaft liegenschaft,short pHausnummer, short pWohnungsnummer, String bezeichnung, String beschreibung,
			double pFlaeche, double bewertung) {
		super(pId, bezeichnung, beschreibung);
		setLiegenschaft(liegenschaft);
		setHausnummer(pHausnummer);
		setNummer(pWohnungsnummer);
		setFlaeche(pFlaeche);
		setBewertung(bewertung);
	}

	/**
	 * 
	 */
	public Wohnung() {
		super();
	}

	/**
	 * @since created at 09.02.2014.21:38:30
	 * @return HashMap<Long,Wohnung>
	 */
	public static HashMap<Long, Wohnung> getAll() {
		return DatenSpeicher.getDatenSpeicher().getWohnungen();
	}
	
	public String toString() {
		return "Wohnung " + getBezeichnung();
	}

}