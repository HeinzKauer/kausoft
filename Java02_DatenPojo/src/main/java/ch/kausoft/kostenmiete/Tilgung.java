package ch.kausoft.kostenmiete;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ch.kausoft.basic.DatenRecord;

@Data
@EqualsAndHashCode(callSuper = false)
public class Tilgung extends DatenRecord {

	private int jahr;
	private int abMonat;
	private int bisUndMitMonat;

	private double zins = 1d;
	private double betrag;
	private double zinsBetragJahr;
	private double amortisationsBetragJahr;
	private double zinsBetragPeriode;
	private double amortisationsBetragPeriode;


	public KapitalZinssatz kapitalZinssatz;

	public Tilgung(int pJahr) {
		this(pJahr, 01);
	}

	public Tilgung(int pJahr, int pAbMonat) {
		this(pJahr, pAbMonat, 12);
	}

	public Tilgung(int pJahr, int pAbMonat, int pBisMonat) {
		jahr = pJahr;
		abMonat = pAbMonat;
		bisUndMitMonat = pBisMonat;
	}

}
