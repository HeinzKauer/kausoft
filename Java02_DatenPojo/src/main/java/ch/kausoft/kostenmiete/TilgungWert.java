/**
 * @author Heinz Kauer 25.07.2014.17:35:23 <br/>
 * Copyright 2014 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.kostenmiete;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TilgungWert {

	public enum TilgungStrategie {
		Anzahl, Bewertung, Flaeche, Individuell
	}

	public enum Typ {
		Wohnung, Siedlung, Liegenschaft
	}

	private Typ typ;
	private double summe;
	private HashMap<Long, Double> wert = new HashMap<Long, Double>();

	public void addWert(Long id, double w) {
		if (wert.get(id) != null) {
			throw new IllegalArgumentException("140730-0020");
		}
		;
		wert.put(id, w);
		summe += w;
	}

	public Double getWertxxxx(Long id) {
		Double ddddddddd = wert.get(id);
		return (ddddddddd == null) ? 0 : ddddddddd;
	}
}
