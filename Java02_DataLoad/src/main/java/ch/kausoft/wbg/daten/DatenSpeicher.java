package ch.kausoft.wbg.daten;

import java.util.HashMap;

import lombok.Data;
import ch.kausoft.kostenmiete.Investition;
import ch.kausoft.kostenmiete.InvestiertesKapital;
import ch.kausoft.kostenmiete.Wohnung;

@Data
public class DatenSpeicher {

	private static DatenSpeicher datenSpeicher;

	private HashMap<Long, InvestiertesKapital> investiertesKapital = new HashMap<Long, InvestiertesKapital>();
	private HashMap<Long, Wohnung> wohnungen = new HashMap<Long, Wohnung>();
	private HashMap<Long, Investition> investitionen = new HashMap<Long, Investition>();
	
	private  DatenSpeicher(){}
	
	public static DatenSpeicher getDatenSpeicher(){
		if (datenSpeicher == null) {
			datenSpeicher = new DatenSpeicher();
		}
		return datenSpeicher;
	}
	
	
//	public double wohnungsanteilFlaeche[];
//	public double wohnungsanteilBewertet[];

//	public InvestiertesKapital getKapital(Long id) {
//		InvestiertesKapital k = investiertesKapital.get(id);
//		return (k != null) ? k : investiertesKapital.put(id, new InvestiertesKapital());
//	}
//
//	public Investition getInvestition(Long id) {
//		Investition iv = investitionen.get(id);
//		return (iv != null) ? iv : investitionen.put(id, new Investition());
//	}



	// public InvestitionBO[] getAllInvestition() {
	// InvestitionBO[] inv = new InvestitionBO[invest.size()];
	// int i = 0;
	// // for (InvestitionBO iv : invest.values())
	// // inv[i++] = iv;
	// // return inv;
	// }

///		WohnungBO[] w = new WohnungBO[wohnungen.length];
//		for (int i = 0; i < wohnungen.length; i++) {
//			w[i] = WohnungBO.create(wohnungen[i]);
//		}
//		return w;
//	}

}
