package ch.kausoft.wbg.daten.dao;

import java.util.HashMap;

import ch.kausoft.kostenmiete.InvestiertesKapital;
import ch.kausoft.kostenmiete.KapitalZinssatz;

public class KapitalBO {

	public InvestiertesKapital kapital;

	HashMap<Integer, KapitalZinssatz> zins = new HashMap<Integer, KapitalZinssatz>();

	public KapitalBO(String bezeichnung) {
		this(bezeichnung, "");
	}

	public KapitalBO(String bezeichnung, String beschreibung) {
//		kapital = new Kapital(bezeichnung, beschreibung);
//		addKapitalZins(new KapitalZinssatz(2000, .0d, 3.0d, 3.0d, 6.0d));
	}

	public KapitalZinssatz getKapitalZins(int jahr) {
		for (int i = jahr; i >= 2000; i--) {
			KapitalZinssatz x = zins.get(i);
			if (x != null) {
				return x;
			}
		}
		return null;
	}

	public void addKapitalZins(KapitalZinssatz kapitalZins) {
		zins.put(kapitalZins.abJahr, kapitalZins);
	}

}
