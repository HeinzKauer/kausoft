package ch.kausoft.kostenmiete;

import java.util.ArrayList;
import java.util.List;

import ch.kausoft.basic.DatenBB;
import ch.kausoft.basic.DatenRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class InvestiertesKapital extends DatenBB {

	private List<KapitalZinssatz> zinssatz = new ArrayList<KapitalZinssatz>();

	public InvestiertesKapital() {
		super();
	}

	public InvestiertesKapital(Long pId, String pBezeichnung, String pBeschreibung) {
		super(pId, pBezeichnung, pBeschreibung);
	}

}