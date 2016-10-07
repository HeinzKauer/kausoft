/**
 * 
 */
package ch.kausoft.controller.useCases;

import lombok.Getter;
import lombok.Setter;
import ch.kausoft.context.SessionContext;
import ch.kausoft.controller.UseCase;

/**
 * @author Heinz
 */
public class DataPathUC extends UseCase {

	/**
	 * 
	 */
	@Getter
	@Setter
	String dataPath = "/";

	/**
	 * 
	 */
	@Getter
	@Setter
	String dataName = "readme";

	/**
	 * 
	 */
	@Getter
	@Setter
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
	 * @since created at 30.01.2015.22:13:36
	 * @param args
	 *          void
	 */
	public static void main(String[] args) {

		DataPathUC uc = new DataPathUC("4711");
		SessionContext.getContext().add(uc);
		System.out.println(uc.toString());
	}

}
