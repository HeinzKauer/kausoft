package ch.kausoft.wbg.daten.bo;

import ch.kausoft.kostenmiete.InvestiertesKapital;
import ch.kausoft.kostenmiete.Investition;
import ch.kausoft.kostenmiete.KapitalZinssatz;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TilgungBOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public  void testTilgungRechnen () {

		Investition invest = new Investition();
		invest.setLebensdauerInJahre(20);
		invest.setAktivierungsJahr(2012);
		invest.setAktivierungsMonat(8);
		invest.setInvestitionsBetrag(20000);

		InvestiertesKapital investiertesKapital = new InvestiertesKapital();
		invest.setInvestiertesKapital(investiertesKapital);

		List<KapitalZinssatz> kapitalZinssatz = new ArrayList<KapitalZinssatz>();
		kapitalZinssatz.add(new KapitalZinssatz(2000, 1, 1, 3, 2, 5));
		kapitalZinssatz.add(new KapitalZinssatz(2012, 10, 1, 3, 2, 5));
		kapitalZinssatz.add(new KapitalZinssatz(2012, 11, 1, 3, 2, 5));
		kapitalZinssatz.add(new KapitalZinssatz(2013, 1, 1, 3, 2, 5));
		kapitalZinssatz.add(new KapitalZinssatz(2014, 6, 1, 3, 2, 5));
		kapitalZinssatz.add(new KapitalZinssatz(2018, 8, 1, 3, 2, 5));
		kapitalZinssatz.add(new KapitalZinssatz(2999, 8, 1, 3, 2, 5));
		investiertesKapital.setZinssatz(kapitalZinssatz);
		InvestitionBO investBo = new InvestitionBO(invest);
		TilgungBO.rechnenTilgungsplan(investBo);
	}

	
}
