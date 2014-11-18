package ch.kausoft.basic;

import java.util.HashMap;

import lombok.Data;
import ch.kausoft.kostenmiete.Investition;
import ch.kausoft.kostenmiete.InvestiertesKapital;
import ch.kausoft.kostenmiete.Liegenschaft;
import ch.kausoft.kostenmiete.Siedlung;
import ch.kausoft.kostenmiete.Wohnung;

@Data
public class DatenSpeicher {

	private static DatenSpeicher datenSpeicher;

	private HashMap<Long, DatenBB> daten = new HashMap<Long, DatenBB>();
	
	private HashMap<Long, InvestiertesKapital> investiertesKapital = new HashMap<Long, InvestiertesKapital>(){
		private static final long serialVersionUID = -3480639456813358639L;
		public InvestiertesKapital put(Long key, InvestiertesKapital value) {
			daten.put(key, value);
			return super.put(key,value);
		};
	};
	
	private HashMap<Long, Liegenschaft> liegenschaft = new HashMap<Long, Liegenschaft>(){
		private static final long serialVersionUID = -6579506898235358037L;
		public Liegenschaft put(Long key, Liegenschaft value) {
			daten.put(key, value);
			return super.put(key,value);
		};
	};
	
	private HashMap<Long, Siedlung> siedlung = new HashMap<Long, Siedlung>(){
		private static final long serialVersionUID = 3556013476170495673L;
		public Siedlung put(Long key, Siedlung value) {
			daten.put(key, value);
			return super.put(key,value);
		};
	};
	
	private HashMap<Long, Wohnung> wohnungen = new HashMap<Long, Wohnung>(){
		private static final long serialVersionUID = 4128962709248091350L;
		public Wohnung put(Long key, Wohnung value) {
			daten.put(key, value);
			return super.put(key,value);
		};
	};

	private HashMap<Long, Investition> investitionen = new HashMap<Long, Investition>() {
		private static final long serialVersionUID = -434146157932516654L;
		public Investition put(Long key, Investition value) {
			daten.put(key, value);
			return super.put(key,value);
		};
	};

	private DatenSpeicher() {
	}

	public static DatenSpeicher getDatenSpeicher() {
		if (datenSpeicher == null) {
			datenSpeicher = new DatenSpeicher();
		}
		return datenSpeicher;
	}

	public double wohnungsanteilFlaeche[];
	public double wohnungsanteilBewertet[];

	public InvestiertesKapital getKapital(Long id) {
		InvestiertesKapital k = investiertesKapital.get(id);
		return (k != null) ? k : investiertesKapital.put(id, new InvestiertesKapital());
	}

	public Investition getInvestition(Long id) {
		Investition iv = investitionen.get(id);
		return (iv != null) ? iv : investitionen.put(id, new Investition());
	}

	public Liegenschaft getLiegenschaft(Long id) {
		Liegenschaft iv = liegenschaft.get(id);
		return (iv != null) ? iv : liegenschaft.put(id, new Liegenschaft());
	}

	public Siedlung getSiedlung(Long id) {
		Siedlung iv = siedlung.get(id);
		return (iv != null) ? iv : siedlung.put(id, new Siedlung());
	}

	public Wohnung getWohnung(Long id) {
		Wohnung iv = wohnungen.get(id);
		return (iv != null) ? iv : wohnungen.put(id, new Wohnung());
	}

	// public InvestitionBO[] getAllInvestition() {
	// InvestitionBO[] inv = new InvestitionBO[invest.size()];
	// int i = 0;
	// for (InvestitionBO iv : invest.values())
	// inv[i++] = iv;
	// return inv;
	// }

	// public WohnungBO[] getAllWohnung() {
	// WohnungBO[] w = new WohnungBO[wohnungen.length];
	// for (int i = 0; i < wohnungen.length; i++) {
	// w[i] = WohnungBO.create(wohnungen[i]);
	// }
	// return w;
	// }

}
