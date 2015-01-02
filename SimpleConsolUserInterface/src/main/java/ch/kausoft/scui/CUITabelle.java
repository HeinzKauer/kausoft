package ch.kausoft.scui;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Console user interface Content Tabelle.
 * 
 * @author Heinz kauer
 */
public class CUITabelle {

	private int[] spaltenBreite = new int[100];

	private int anzahlSpalten = 0;

	private Vector<String[]> zeilen = new Vector<String[]>();

	public void addZeile(String zellen[]) {
		for (int i = 0; i < zellen.length; i++) {
			if (zellen[i] == null) {
				zellen[i] = "";
			}
			if (zellen[i].length() > spaltenBreite[i]) {
				spaltenBreite[i] = zellen[i].length();
			}
		}

		this.zeilen.add(zellen);
		if (zellen.length > anzahlSpalten) {
			anzahlSpalten = zellen.length;
		}
	}

	private String prepareZeile(String[] s) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < s.length; i++) {
			sb.append(s[i]);
			for (int j = s[i].length(); j < spaltenBreite[i]; j++) {
				sb.append(" ");
			}
			sb.append(" ");
		}

		if (anzahlSpalten > s.length) {
			for (int i = s.length; i < anzahlSpalten; i++) {
				for (int j = 0; j < spaltenBreite[i]; j++) {
					sb.append(" ");
				}
				sb.append(" ");

			}
		}

		return sb.toString();
	}

	private int getTabellenBreite() {
		int x = 1;
		for (int i = 0; i < anzahlSpalten; i++) {
			x += spaltenBreite[i] + 1;
		}
		return x;
	}

	public void out() {

		for (Enumeration<String[]> e = zeilen.elements(); e.hasMoreElements();) {
			String s[] = (String[]) e.nextElement();

			System.out.println("120403-0945: " + " " + prepareZeile(s));

		}

	}

	public void out(CUITabelle mitContent) {
		StringBuffer line = new StringBuffer();
		line.append("-");
		int ii = getTabellenBreite() + mitContent.getTabellenBreite();
		for (int i = 0; i < ii; i++) {
			line.append("-");
		}
		System.out.println("120403-1005: " + line);

		if (this.zeilen.size() >= mitContent.zeilen.size()) {
			int i = 0;
			for (Enumeration<String[]> e = zeilen.elements(); e.hasMoreElements();) {
				String s[] = (String[]) e.nextElement();

				String x = " " + prepareZeile(s);

				x += "| ";
				if (i >= mitContent.zeilen.size()) {
					x += mitContent.prepareZeile(new String[0]);
				} else {
					x += mitContent.prepareZeile(mitContent.getZeile(i));
				}
				i++;
				System.out.println("120403-1115: " + x);
			}
		} else {
			int i = 0;
			for (Enumeration e = mitContent.zeilen.elements(); e.hasMoreElements();) {
				String s[] = (String[]) e.nextElement();

				String x = "";

				if (i >= zeilen.size()) {
					x = " " + prepareZeile(new String[0]);
				} else {
					x = " " + prepareZeile(this.getZeile(i));
				}

				// x += "| ";
				x += mitContent.prepareZeile(mitContent.getZeile(i));
				i++;

				System.out.println("120403-1125: " + x);
			}

		}
		System.out.println("120403-1133: " + line);

	}

	private String[] getZeile(int i) {
		return (String[]) zeilen.elementAt(i);
	}

}
