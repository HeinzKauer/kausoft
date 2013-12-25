package ch.kausoft.kostenmiete;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ch.kausoft.basic.DatenRecord;
import ch.kausoft.basic.JahrMonat;

/**
 * für die Tilgung einer Investition wird ein jährlicher Mietzinsbeitrag
 * verwendet. Von diesem Beitrag werden die Zinskosten abgezogen und der Rest
 * wird zur Schuldamortisation verwendet. Dieser Mietzinsbeitrag ist der in
 * einem effektiven Mietzins der für diese Investition verwendet wird. 
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
	 * Dieser Zins und Amortisationssatz ist gültig ab diesem Jahr
	 */
	public JahrMonat gueltigAb;

	/**
	 * Ab dem definierten Jahr wird jährlich dieser Betrag verwendet um die
	 * auflauffenden Zinsen und die Amortisation zu finanzieren.
	 */
	public double jaehrlicherMietzinsbeitrag;

}
