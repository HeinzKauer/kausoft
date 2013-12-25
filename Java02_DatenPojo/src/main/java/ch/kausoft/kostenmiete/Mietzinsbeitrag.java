package ch.kausoft.kostenmiete;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ch.kausoft.basic.DatenRecord;
import ch.kausoft.basic.JahrMonat;

/**
 * f�r die Tilgung einer Investition wird ein j�hrlicher Mietzinsbeitrag
 * verwendet. Von diesem Beitrag werden die Zinskosten abgezogen und der Rest
 * wird zur Schuldamortisation verwendet. Dieser Mietzinsbeitrag ist der in
 * einem effektiven Mietzins der f�r diese Investition verwendet wird. 
 * 
 * Jeder Investition hat pro jahr und Monat die Mietzinsbeitrag definiert
 * 
 * @author Heinz
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public final class Mietzinsbeitrag extends DatenRecord {

	public Mietzinsbeitrag() {
	}

	public Mietzinsbeitrag(int abJahr, double jaehrlicherMietzinsbeitrag) {
		this(abJahr, 01, jaehrlicherMietzinsbeitrag);
	}

	public Mietzinsbeitrag(int abJahr, int abMonat,
			double jaehrlicherMietzinsbeitrag) {
		this.gueltigAb = new JahrMonat(abJahr, abMonat);
		this.jaehrlicherMietzinsbeitrag = jaehrlicherMietzinsbeitrag;
	}

	/**
	 * Dieser Zins und Amortisationssatz ist g�ltig ab diesem Jahr
	 */
	public JahrMonat gueltigAb;

	/**
	 * Ab dem definierten Jahr wird j�hrlich dieser Betrag verwendet um die
	 * auflauffenden Zinsen und die Amortisation zu finanzieren.
	 */
	public double jaehrlicherMietzinsbeitrag;

}
