/**
 * @author Heinz Kauer 18.01.2014.00:02:29 <br/>
 * Copyright 2014 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DatenBB extends DatenRecord {

	@NonNull
	private String bezeichnung;
	@NonNull
	private String beschreibung;

	/**
	 * 
	 */
	protected DatenBB() {
		super();
	}

	/**
	 * @param pId
	 * @param pBezeichnung
	 * @param pBeschreibung
	 */
	public DatenBB(long pId, String pBezeichnung, String pBeschreibung) {
		setId(pId);
		setBezeichnung(pBezeichnung);
		setBeschreibung(pBeschreibung);
	}

}
