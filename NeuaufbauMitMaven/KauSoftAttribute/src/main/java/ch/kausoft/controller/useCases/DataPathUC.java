/**
 *
 */
package ch.kausoft.controller.useCases;

import ch.kausoft.context.SessionContext;
import ch.kausoft.controller.UseCase;
import lombok.Data;
import lombok.ToString;

/**
 * @author Heinz
 */
@ToString
@Data
public class DataPathUC extends UseCase {

    /**
     *
     */
    String dataPath = "/";

    /**
     *
     */
    String dataName = "readme";

    /**
     *
     */
    String dataType = "txt";

    /**
     * @param id
     */
    public DataPathUC(String id) {
        super(id);
    }

    /**
     * Test Main
     *
     * @param args void
     * @since created at 30.01.2015.22:13:36
     */
    public static void main(String[] args) {
        DataPathUC uc = new DataPathUC("4711");
        SessionContext.getContext().add(uc);
        System.out.println("uc = " + uc.toString());
        uc.setDataPath("sss\\ssssssssssss");
        System.out.println("uc = " + uc.toString() +" path" +uc.getClass().getPackage());

    }

}
