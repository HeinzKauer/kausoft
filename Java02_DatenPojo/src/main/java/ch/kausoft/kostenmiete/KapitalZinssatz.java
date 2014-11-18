package ch.kausoft.kostenmiete;

/**
 * 
 * 
 * @author Heinz
 * 
 */
public final class KapitalZinssatz {

	/**
	 * Dieser Zins und Amortisationssatz ist gültig ab diesem Jahr
	 */
	public int abJahr;

	public int abMonat;

	/**
	 * Wir haben vier verschiedene Zinssätze
	 */
	public double zins[] = new double[4];

	/**
	 * 
	 * @param abJahr
	 * @param zinsOptimistisch
	 * @param zinsPessimistisch
	 * @param zinsIst
	 * @param zinsRendite
	 */
	public KapitalZinssatz(int abJahr, double zinsOptimistisch, double zinsPessimistisch, double zinsIst,
			double zinsRendite) {
		this(abJahr, 01, zinsOptimistisch, zinsPessimistisch, zinsIst, zinsRendite);
	}

	public KapitalZinssatz(int abJahr, int abMonat, double zinsOptimistisch, double zinsPessimistisch, double zinsIst,
			double zinsRendite) {
		this.abJahr = abJahr;
		this.abMonat = abMonat;
		this.zins[0] = zinsOptimistisch;
		this.zins[1] = zinsPessimistisch;
		this.zins[2] = zinsIst;
		this.zins[3] = zinsRendite;
	}

	public int getAbJahr() {
		return abJahr;
	}

	public void setAbJahr(int abJahr) {
		this.abJahr = abJahr;
	}

	public int getAbMonat() {
		return abMonat;
	}

	public void setAbMonat(int abMonat) {
		this.abMonat = abMonat;
	}

	public double[] getZins() {
		return zins;
	}

	public void setZins(double[] zins) {
		this.zins = zins;
	}
}
