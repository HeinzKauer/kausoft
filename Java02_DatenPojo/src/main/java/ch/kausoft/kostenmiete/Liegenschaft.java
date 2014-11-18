/**
 * @author Heinz Kauer 17.01.2014.22:56:12 <br/>
 * Copyright 2014 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.kostenmiete;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ch.kausoft.basic.DatenBB;

/**
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Liegenschaft extends DatenBB {

	/**
	 * Bewertung der Wohnung
	 */
	private double bewertung;

	private List<Wohnung> wohnungen = new ArrayList<Wohnung>();

	private Siedlung siedlung = null;

	/**
	 * 
	 */
	public Liegenschaft() {
		super();
	}

	/**
	 * @param id
	 * @param pBezeichnung
	 * @param pBeschreibung
	 */
	public Liegenschaft(long id, Siedlung siedlung, String pBezeichnung, String pBeschreibung, double bewertung) {
		super(id, pBezeichnung, pBeschreibung);
		setSiedlung(siedlung);
		setBewertung(bewertung);
	}

	public String toString() {
		return "Liegenschaft " + getBezeichnung();
	}

}
