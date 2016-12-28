/**
 * @author Heinz Kauer 01.08.2014.13:33:34 <br/>
 * Copyright 2014 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.wbg.daten.bo;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.kostenmiete.Siedlung;

/**
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SiedlungBO  {
	
	
	private Siedlung siedlung;

	public SiedlungBO(Siedlung pSiedlung) {
		this.siedlung = pSiedlung;
	}
	
	public static List<SiedlungBO> loadAll() {
		List<SiedlungBO> w = new ArrayList<SiedlungBO>();
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
		Collection<Siedlung > values = ds.getSiedlung().values();
		for (Siedlung siedlung : values) {
			w.add(new SiedlungBO(siedlung));
		}
		return w;
	}

	
}
