package ch.kausoft.kostenmiete;

import java.util.ArrayList;
import java.util.List;

import ch.kausoft.basic.DatenRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

public  class InvestiertesKapital extends DatenRecord {

	public InvestiertesKapital() {
	}

	public InvestiertesKapital(Long pId,String pBezeichnung,String pBeschreibung) {
		super();
		setId(pId);
		setBezeichnung(pBezeichnung);
		setBeschreibung(pBeschreibung);

	}

	private String bezeichnung;
	
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public List<KapitalZinssatz> getZinssatz() {
		return zinssatz;
	}

	public void setZinssatz(List<KapitalZinssatz> zinssatz) {
		this.zinssatz = zinssatz;
	}

	private String beschreibung;
	
	private List<KapitalZinssatz> zinssatz = new ArrayList<KapitalZinssatz>();



}