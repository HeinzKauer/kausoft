package ch.kausoft.scui;

/**
 * Callback Anschluss 
 * 
 * @author Heinz kauer
 */
public interface IMenueAction {
	
	/**
	 * 
	 * @return true Aufgabe erledigt und zur�ck zu Vor-Menue; 
	 *         false Aufgabe nicht erledigt erneut Menue ausgeben.
	 */
	 public boolean runMenueAction();

}
