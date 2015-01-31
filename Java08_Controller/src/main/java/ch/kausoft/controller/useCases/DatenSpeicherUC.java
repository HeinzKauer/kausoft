/**
 * 
 */
package ch.kausoft.controller.useCases;

import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.context.Context;
import ch.kausoft.context.SessionContext;
import ch.kausoft.context.SystemContext;
import ch.kausoft.controller.UseCase;
import ch.kausoft.wbg.daten.DataIOExcel;
import ch.kausoft.wbg.daten.DataImporter;
import ch.kausoft.wbg.daten.OutputExcel;

/**
 * @author Heinz
 * 
 */
public class DatenSpeicherUC extends UseCase {

   DatenSpeicher datenSpeicher;

   public static final String ID = "DatenSpeicherUC";

   private DatenSpeicherUC(String id) {
      super(SessionContext.getContext(),id);
   }

   public static DatenSpeicherUC getInstance() {
      return getInstance(ID);
   }

   public static DatenSpeicherUC getInstance(String pID) {
      DatenSpeicherUC uc = (DatenSpeicherUC) SessionContext.getContext()
            .getUseCase().get(pID);
      return (uc == null) ? new DatenSpeicherUC(pID) : uc;
   }

//   private DatenSpeicherUC(String id) {
//      super(id);
//   }

   public Context getContext() {
      return SystemContext.getContext();
   }

   /**
	 * 
	 */
   public void loadCSV(String path) {
      // getContext().
      DataImporter di = new DataImporter();
      datenSpeicher = di.load(path);
      // setUsseCase();
   }

   public void loadFromExcel(String path) {
      // getContext().
      DataIOExcel di = new DataIOExcel();
      di.read(path);
      datenSpeicher = DatenSpeicher.getDatenSpeicher();
      // setUsseCase();
   }

   public void saveToExcel(String path) {
      // getContext().
      DataIOExcel di = new DataIOExcel();
      di.save(path);
      // setUsseCase();
   }

   /**
    * @since created at 08.02.2014.23:42:12
    * @param excel
    *           void
    */
   public void save(OutputExcel excel) {
      // TODO Auto-generated method stub
      DataIOExcel di = new DataIOExcel();
      di.save(excel);
      // setUsseCase();

   }

}
