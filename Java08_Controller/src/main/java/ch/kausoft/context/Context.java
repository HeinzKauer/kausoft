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
 * Im Context werden UseCase'is gespreichert
 * 
 * @author Heinz
 */
public class Context {

	/**
	 * 
	 */
	@Getter
	@Setter
	private Map<String, UseCase> useCase = new HashMap<String, UseCase>();

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
