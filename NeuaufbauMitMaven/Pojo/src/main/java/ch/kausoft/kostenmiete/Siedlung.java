/**
 * @author Heinz Kauer 17.01.2014.22:55:19 <br/>
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
public class Siedlung extends DatenBB {

	/**
	 * Bewertung der Wohnung
	 */
	private double bewertung;

	private List<Liegenschaft> liegenschaften = new ArrayList<Liegenschaft>();

	/**
	 * 
	 */
	public Siedlung() {
		super();
	}

	public Siedlung(long id, String pBezeichnung, String pBeschreibung, double bewertung) {
		super(id, pBezeichnung, pBeschreibung);
		setBewertung(bewertung);
	}

	public String toString() {
		return "Siedlung " + getBezeichnung();
	}

}
