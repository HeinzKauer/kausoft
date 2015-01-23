package ch.kausoft.scui;

import lombok.Data;

/**
 * IO Element.
 * 
 * @author Heinz kauer
 */
@Data
public class IOElement {

	/**
	 * Mit der Action-ID kann dann via Console eine Aktion die im Call
	 * gespeichert ist, ausgeführt wern. 
	 */
	String actionID;

	/**
	 * Ein erklärender Text zur Action-ID 
	 */
	String hilfe;

	/**
	 * 
	 */
	String rueckmeldung;

	/**
	 * 
	 */
	MenueAction call;

	/**
	 * Bereritstellen eines IO Elementes.
	 * 
	 * 
	 * @param command
	 * @param hilfe
	 * @param pCall
	 */
	public IOElement(String command, String hilfe, IMenueAction pCall) {
		System.out.println("150119-2253 \r" + command + "\r" + hilfe
				+ "\r" + pCall);
		setActionID((command == null) ? "" : command);
		setHilfe((hilfe == null) ? "" : hilfe);
		setCall((pCall == null) ? new MenueAction(new IMenueAction() {
			public boolean runMenueAction() {
				// TODO Auto-generated method stub
				return false;
			}
		}) : new MenueAction(pCall));
//		pCall.
//		setRueckmeldung("");
	}

	/**
	 * @since created at 19.01.2015.22:49:11
	 * @return String
	 */
	public String display() {
		return actionID + "   --> " + hilfe;
	}

}
