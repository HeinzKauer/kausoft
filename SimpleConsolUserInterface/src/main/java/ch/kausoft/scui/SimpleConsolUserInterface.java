package ch.kausoft.scui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Simple Console User Interface
 * 
 * @author Heinz Kauer
 * 
 */
public class SimpleConsolUserInterface {

	private CUIMenu menue;

	private CUITabelle menuTab = new CUITabelle();

	private CUITabelle content = new CUITabelle();

	private String[] autoRun = new String[0];

	private int autoRunCounter = 0;

	private InputStreamReader isr = new InputStreamReader(System.in);

	private BufferedReader br = new BufferedReader(isr);

	public SimpleConsolUserInterface() {

	}

	public SimpleConsolUserInterface(String[] autoRunStringArray) {
		setAutoRun(autoRunStringArray);
	}

	public String[] getAutoRun() {
		return autoRun;
	}

	public void setAutoRun(String[] autorun) {
		this.autoRun = autorun;
	}

	public void work(CUI cui) {
		io(cui);
	}

	private void io(CUI cui) {

		menue = cui.getUIMenu();
		// content = cui.getContentTabelle();
		IOElement[] ioa = menue.getIOElements();

		for (int i = 0; i < ioa.length; i++) {
			ioa[i].getActionID();
			menuTab.addZeile(new String[] { ioa[i].getActionID(), ioa[i].getHilfe() });
		}

		boolean loop = true;
		while (loop) {
			content = cui.getContentTabelle();
			menuTab.out(content);
			System.out.print("120404-0810: Code : ");

			String str = lesenConsole(br);

			String upperStr = str.toUpperCase();
			for (int i = 0; i < ioa.length; i++) {
				if (ioa[i].getActionID().equals(upperStr)) {
					loop = ioa[i].getCall() == null ? false 
					: ioa[i].getCall().getAction() == null ? false 
					: ioa[i].getCall().getAction().runMenueAction() ? false : true;
					if (!loop) {
						System.out.println("120404-0815: " + ioa[i].getRueckmeldung());
					}
				}
			}
		}

	}

	private String lesenConsole(BufferedReader br) {
		String str;

		if (hasAutoRun()) {
			str = getNextAutoRun();
			System.out.println();
			System.out.println("120403-1200: AutoRun Input = " + str);
		} else {
			// Hier wird eine Zeile eingelesen
			try {
				str = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				str = "";
			}
		}
		return str;
	}

	private boolean hasAutoRun() {
		return (autoRunCounter < autoRun.length) ? true : false;
	}

	private String getNextAutoRun() {
		return autoRun[autoRunCounter++];
	}

	public CUITabelle getMenuTab() {
		return menuTab;
	}

	public void setMenuTab(CUITabelle menuTab) {
		this.menuTab = menuTab;
	}

	public CUITabelle getContent() {
		return content;
	}

	public void setContent(CUITabelle content) {
		this.content = content;
	}

	public CUIMenu getMenue() {
		return menue;
	}

	public void setMenue(CUIMenu menue) {
		this.menue = menue;
	}

	public String inputText(String string) {

		// InputStreamReader isr = new InputStreamReader(System.in);
		// BufferedReader br = new BufferedReader(isr);

		System.out.print("120403-1200: " + string + " : ");
		return lesenConsole(br);

	}

}
