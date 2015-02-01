/*
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.controller;

import lombok.Getter;
import lombok.Setter;
import ch.kausoft.context.Context;

/**
 * Ein UseCase ist gleich wie ein Anwendungsfall.
 * 
 * Ein UseCase wird immer an einen Context gehängt. Sollen von
 * derselben Klasse mehrer UseCases mit unterschiedlichen
 * Identifikationen gespeichert werden, können diese mit einer
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
	 * In diesen Context wurde das zulezt UseCase registriert. Wird ein
	 * UseCase in einen Context gespeichert, registriert sich der
	 * Context hier im UseCase
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

}
