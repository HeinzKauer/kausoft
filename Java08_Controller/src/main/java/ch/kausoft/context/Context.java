/**
 * 
 */
package ch.kausoft.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;
import ch.kausoft.controller.UseCase;

/**
 * @author Heinz
 *
 */
public class Context {
	//@formatter:off
//   @Getter @Setter private Properties properties = new Properties();  
   @Getter @Setter private Map<String, UseCase>  useCase = new HashMap<String, UseCase>();
   //@formatter:on

	/**
	 * @since created at 31.01.2015.23:58:44
	 * @param uc
	 *          void
	 */
	public void add(UseCase uc) {
		uc.setContext(this);
		uc.getUseCase_ID();
		if (useCase.get(uc.getUseCase_ID()) != null) {
			throw new RuntimeException();
		}
		useCase.put(uc.getUseCase_ID(), uc);
	};

}
