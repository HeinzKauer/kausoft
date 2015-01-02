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
	



	public IOElement(String command, String hilfe, IMenueAction pCall) {
		setCommand(command);
		setHilfe(hilfe);
		setCall(new MenueAction(pCall));
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getHilfe() {
		return hilfe;
	}

	public void setHilfe(String hilfe) {
		this.hilfe = hilfe;
	}

	public String display() {
		return command + "   --> " + hilfe;
	}

	public String getRueckmeldung() {
		return rueckmeldung;
	}

	public void setRueckmeldung(String rueckmeldung) {
		this.rueckmeldung = rueckmeldung;
	}

}
