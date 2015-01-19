package ch.kausoft.scui;

import lombok.Data;

/**
 * IO Element.
 * 
 * @author Heinz kauer
 */
@Data
public class IOElement {
	String command;
	String hilfe;
	String rueckmeldung;
	MenueAction call;

	/**
	 * @param command
	 * @param hilfe
	 * @param pCall
	 */
	public IOElement(String command, String hilfe, IMenueAction pCall) {
		System.out.println("150119-2253 \r" + command + "\r" + hilfe
				+ "\r" + pCall);
		setCommand((command == null) ? "" : command);
		setHilfe((hilfe == null) ? "" : hilfe);
		setCall(new MenueAction(pCall));
	}

	/**
	 * @since created at 19.01.2015.22:49:11
	 * @return String
	 */
	public String display() {
		return command + "   --> " + hilfe;
	}

}
