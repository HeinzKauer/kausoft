/**
 * 
 */
package ch.kausoft.context;



/**
 * @author Heinz
 *
 */

public class SessionContext extends Context {
	
	private static SessionContext session = new SessionContext();
	  
	public static SessionContext getContext(){	
		return SessionContext.session;
	}


	
}
