package ch.kausoft.basic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import lombok.Data;

@Data
public class RecordID {

	/**
	 * 
	 */
	private long id = -1;

	/**
	 * Die zuletzt vergebene ID-Nummer wird festgehalten. Dies ist notwendig weil innerhalb sehr kurzer Zeit mehrere ID's
	 * angefordert werden können. In der Methode
	 * 
	 * @see #getNextNewID()
	 */
	private static long lastNewID = Long.MIN_VALUE;

	/**
	 * 
	 */
	private final long x = 100;

	/**
	 * Hier wird die nexte eindeutige ID generiert. Mit diesem System ist jeder Record ein-eindeutig. Wurde einmal eine ID
	 * vergeben, bekommt niemand anders diese ID.
	 * 
	 * @return long
	 */
	public synchronized long getNextNewID() {
		long dateTime = (new Date().getTime()) * x;
		if (dateTime > lastNewID) {
			lastNewID = dateTime;
		} else {
			lastNewID++;
		}
		return lastNewID;
	}

	/**
	 * Darstellung der DataRecordID. <br>
	 * Die DataRecordID wird folgendermassen dargestellt. ( 2007.11.09 01:28:03 22 / 11945.68081.30222 ). Dabei ist die
	 * Darstellungsform 11945.68081.30222 die wirklich eindeutige ID. Die Darstellung in der Datumsform kann ungenau sein.
	 * Dies ist der Fall weil die Zeit in Millisekunden gespeichert wird.<br>
	 * <BR>
	 * 
	 * @since created at 30.07.2008.00:16:42
	 * @return String
	 */
	public String getString() {
		String s = Long.toString(id);
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		long d = (id / x);
		date.setTime(d);
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy.MM.dd HH:mm:ss");
		return sdf.format(date) + " " + s.substring(10) + " / " //
				+ s.substring(0, 5) + "." //
				+ s.substring(5, 10) + "." //
				+ s.substring(10);
	}



}
