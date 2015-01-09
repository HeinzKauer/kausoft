/**
 * 
 */
package ch.kausoft.controller.useCases;

import ch.kausoft.context.SessionContext;
import ch.kausoft.controller.UseCase;

/**
 * @author Heinz
 * 
 */
public class DataPathUC extends UseCase {

   public static final String ID = "DataPathUC";

   private DataPathUC(String id) {
      super(id);
   }
   public static DataPathUC getInstance() 
   {
      return getInstance(ID);
   }

   public static DataPathUC getInstance(String pID) {
      DataPathUC uc = (DataPathUC) SessionContext.getContext().getUseCase()
            .get(pID);
      return (uc == null) ? new DataPathUC(pID) : uc;
   }

   public String getDataPath() {
      String path = getContext().getProperties().getProperty(
            this.getClass().getName());
      return (path == null) ? "/" : path;
   }

   public void setDataPath(String pPath) {
      setProperty(pPath);
   }

}
