package ch.kausoft.basic;

import lombok.Data;

/**
 * Dieses Jahr Monat repräsentiert einen bestimmten Monat wobei 01 = Januar ist
 * 
 * @author Heinz
 *
 */
@Data
public class JahrMonat {

	private int jahr;
	private int monat;

	/**
	 * @param jahr
	 * @param monat
	 */
	public JahrMonat(int jahr, int monat) {
		this.jahr = jahr;
		this.monat = monat;
	}

}
