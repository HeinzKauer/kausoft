/**
 * 
 */
package ch.kausoft.controller.useCases;

import java.util.List;

import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.controller.UseCase;
import ch.kausoft.wbg.daten.bo.InvestitionBO;
import ch.kausoft.wbg.daten.bo.TilgungBO;

/**
 * @author Heinz
 * 
 */
public class TilgungsplanRechnenUC extends UseCase {

	/**
	 * @param datenSpeicher
	 * 
	 */
	public void calculate() {
		DatenSpeicher datenSpeicher = DatenSpeicher.getDatenSpeicher();
		calculate(datenSpeicher);
		// System.out.println( datenSpeicher);
	}

	public void calculate(DatenSpeicher datenSpeicher) {
		System.out.println("tilgungsplanRechnen() --------------------------");
		List<InvestitionBO> investitionen = InvestitionBO.getInvestitionen();
		for (InvestitionBO invBo : investitionen) {
			TilgungBO.rechnenTilgungsplan(invBo);
		}
	}

}
