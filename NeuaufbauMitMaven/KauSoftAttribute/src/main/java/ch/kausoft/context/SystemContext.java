/**
 * 
 */
package ch.kausoft.context;



/**
 * @author Heinz
 *
 */
public class SystemContext extends Context {
	
	private static SystemContext system = new SystemContext();
  
	public static SystemContext getContext(){	
		return SystemContext.system;
	};	

	
}
