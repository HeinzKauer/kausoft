/*
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.controller;

import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ch.kausoft.context.Context;
import ch.kausoft.context.SessionContext;

/**
 * Ein UseCase ist gleich wie ein Anwendungsfall.
 * 
 * Ein UseCase wird immer an einen Context geh�ngt. Sollen von
 * derselben Klasse mehrer UseCases mit unterschiedlichen
 * Identifikationen gespeichert werden, k�nnen diese mit einer
 * UseCase-ID unterschieden werden.
 * 
 * @author Heinz
 */
public abstract class UseCase {

	/**
	 * Unter diesem Namen wurde dieses UseCase im Contecht gespeichert.
	 */
	@Getter
	private String useCase_ID;

	/**
	 * In diesen Conext wurde das UseCase registriert.
	 */
	@Getter
	@Setter
	private Context context;

	/**
	 * @param id
	 */
	protected UseCase(String id) {
		useCase_ID = id;
	}



//	/**
//	 * @since created at 31.01.2015.00:45:42
//	 * @param context
//	 * @param id
//	 * @return UseCase
//	 */
//	public static UseCase loadInstance(Context context, String id) {
//		return (UseCase) context.getUseCase().get(id);
//	}
//
//	/**
//	 * @since created at 31.01.2015.00:45:45
//	 * @param pUseCase_ID
//	 *          void
//	 */
//	private void setUsseCase(String pUseCase_ID) {
//		Map<String, UseCase> actionResult = getContext().getUseCase();
//		actionResult.put(pUseCase_ID, this);
//	}

}
