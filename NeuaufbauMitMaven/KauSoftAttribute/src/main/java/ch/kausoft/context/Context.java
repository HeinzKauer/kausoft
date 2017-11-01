/**
 * Copyright 2015 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.context;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import ch.kausoft.controller.UseCase;

/**
 * In einem Context werden die UseCases regristriert. Ein UseCase hat
 * immer genau einen Context, kann jedoch in mehreren Contexten
 * gespeichert sein.
 * <br>
 * <br>
 * Es gibt unterschiedliche Contexte 
 * <li> Session 
 * <li> BusinesObject
 * <br><br>
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

