package ch.kausoft.attribut.kostenmiete;

import ch.kausoft.attribut.BezeichnungA;

public abstract class InvestiertesKapitalBezeichnungA extends BezeichnungA {
	public String getBundleName() {
		return Constant.bundleName;
	}

	public String getAttributID() {
		return "issue.InvestiertesKapitalBezeichnung";
	}
}