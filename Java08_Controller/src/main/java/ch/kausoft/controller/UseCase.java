/**
 * 
 */
package ch.kausoft.controller;

import java.util.Map;

import ch.kausoft.context.Context;
import ch.kausoft.context.SessionContext;

/**
 * @author Heinz
 * 
 */
public abstract class UseCase {

	public Context getContext() {
		return SessionContext.getContext();
	}

	protected void setProperty(String pValue) {
		setProperty(this.getClass().getName(), pValue);
	}

	protected void setProperty(String name, String pValue) {
		getContext().getProperties().setProperty(this.getClass().getName(), pValue);
	}

	protected String getProperty() {
		return getContext().getProperties().getProperty(this.getClass().getName());
	}

	protected void setUsseCase() {
		Map<String, UseCase> actionResult = getContext().getActionResult();
		actionResult.put(this.getClass().getName(), this);
	}

	protected UseCase getUseCase() {
		return getContext().getActionResult().get(this.getClass().getName());
	}

}
