/**
 * 
 */
package ch.kausoft.scui;

import lombok.Data;

/**
 * @author Heinz
 *
 */
@Data
public class MenueAction {
	private IMenueAction action;
	
	public MenueAction(IMenueAction pAction) {
		action = pAction;
	}

}
