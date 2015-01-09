/**
 * 
 */
package ch.kausoft.controller.useCases;

import java.util.List;

import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.context.SessionContext;
import ch.kausoft.controller.UseCase;
import ch.kausoft.wbg.daten.bo.InvestitionBO;
import ch.kausoft.wbg.daten.bo.TilgungBO;

/**
 * @author Heinz
 * 
 */
public class TilgungsplanRechnenUC extends UseCase {

   public static final String ID = "TilgungsplanRechnenUC";

   public static TilgungsplanRechnenUC getInstance() {
      return getInstance(ID);
   }

   public static TilgungsplanRechnenUC getInstance(String pID) {
      TilgungsplanRechnenUC uc = (TilgungsplanRechnenUC) SessionContext
            .getContext().getUseCase().get(pID);
      return (uc == null) ? new TilgungsplanRechnenUC(pID) : uc;
   }

   private TilgungsplanRechnenUC(String id) {
      super(id);
   }

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
