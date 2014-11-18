/**
 * 
 */
package ch.kausoft.controller.useCases;

import ch.kausoft.controller.UseCase;

/**
 * @author Heinz
 * 
 */
public class DataPathUC extends UseCase {

	public String getDataPath() {
		String path = getContext().getProperties().getProperty(
				this.getClass().getName());
		return (path == null) ? "/" : path;
	}

	public void setDataPath(String pPath) {
		setProperty(this.getClass().getName(), pPath);
	}


}
