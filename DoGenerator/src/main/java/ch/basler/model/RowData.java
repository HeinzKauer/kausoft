package ch.basler.model;

public class RowData {

	String string1;
	String string2;
	String string3;
	String string4;
	String string5;
	String string6;
	String string7;
	String string8;
	String string9;
	String string10;
	String string11;
	String string12;
	String string13;
	String string14;
	String string15;
	String string16;
	String string17;
	String string18;

	public RowData(String string1, String string2, String string3,
			String string4, String string5, String string6, String string7,
			String string8, String string9, String string10, String string11,
			String string12, String string13, String string14, String string15,
			String string16, String string17, String string18) {

		this.string1 = string1;
		this.string2 = string2;
		this.string3 = string3;
		this.string4 = string4;
		this.string5 = string5;
		this.string6 = string6;
		this.string7 = string7;
		this.string8 = string8;
		this.string9 = string9;
		this.string10 = string10;
		this.string11 = string11;
		this.string12 = string12;
		this.string13 = string13;
		this.string14 = string14;
		this.string15 = string15;
		this.string16 = string16;
		this.string17 = string17;
		this.string18 = string18;

	}

	public String getByIndex(int index) {
		switch (index) {
		case 1:
			return string1;
		case 2:
			return string2;
		case 3:
			return string3;
		case 4:
			return string4;
		case 5:
			return string5;
		case 6:
			return string6;
		case 7:
			return string7;
		case 8:
			return string8;
		case 9:
			return string9;
		case 10:
			return string10;
		case 11:
			return string11;
		case 12:
			return string12;
		case 13:
			return string13;
		case 14:
			return string14;
		case 15:
			return string15;
		case 16:
			return string16;
		case 17:
			return string17;
		case 18:
			return string18;
		default:
			return "";
		}

	}

	@Override
	public String toString() {
		return "\nRowData [string1=" + string1 + ", string2=" + string2
				+ ", string3=" + string3 + ", string4=" + string4 + ", string5="
				+ string5 + ", string6=" + string6 + ", string7=" + string7
				+ ", string8=" + string8 + ", string9=" + string9
				+ ", string10=" + string10 + ", string11=" + string11
				+ ", string12=" + string12 + ", string13=" + string13
				+ ", string14=" + string14 + ", string15=" + string15
				+ ", string16=" + string16 + ", string17=" + string17
				+ ", string18=" + string18 + "]";
	}

}
