/*
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 */

package ch.kausoft.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Ein DataRecord Objekt ist ein einfaches POJO Daten-Objekt. Es hat im wesentlichen nur Datendefinitionen in der Form
 * von primitiven Datentypen. Ein DataRecord darf keine Business-Logik enthalten.<br>
 * <br>
 * Siehe in Package ch.kausoft.PER.Adresse.java
 * 
 * @author Heinz
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DatenRecord extends Daten {
	
	protected DatenRecord() {		
		setId(getNextNewID());
	}
	
	
	protected DatenRecord(long id) {		
		setId(id);
	}

	

}
