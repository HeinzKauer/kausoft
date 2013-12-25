package ch.kausoft.kostenmiete;

import ch.kausoft.basic.DatenRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public final class Wohnung extends DatenRecord {

	public Wohnung(String bezeichnung, String beschreibung) {
		// TODO entfernen
	}

	public Wohnung(Long pId, short pHausnummer, short pWohnungsnummer,
			String bezeichnung, String beschreibung, double pFlaeche,
			double bewertung) {

		super();
		setId(pId);
		setHausnummer(getHausnummer());
		setNummer(pWohnungsnummer);
		setBezeichnung(bezeichnung);
		setBeschreibung(beschreibung);
		setFlaeche(pFlaeche);
		setBewertung(bewertung);

	}

	@NonNull
	private String bezeichnung;
	@NonNull
	private String beschreibung;
	private short nummer;
	private short hausnummer;
	private double flaeche;
	private double bewertung;

}