/*
 * Copyright 2007 KauSoft by Kauer-Informatik. All rights reserved.
 */

package ch.kausoft.attribut;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Jeder Datenrecord wird durch eine eindeutige ID die DataRecordID
 * identifiziert. Die RecordID wird im Prinzip durch die Zeit in
 * Milisekunden definiert. Somit zeigt die ID auch die Zeit an zu der
 * der Record erzeugt wurde. <BR>
 * <BR>
 * 
 * @since created at 29.07.2008.23:46:28
 * @author Heinz Kauer by Kauer Informatik at 29.07.2008 Projekt = (
 *         Applikation KauSoft )
 */
public abstract class DataRecordIDA extends LongAttributA {
  
  /**
   * Die zuletzt vergebene ID-Nummer wird festgehalten. Dies ist
   * notwendig weil innerhalb sehr kurzer Zeit mehrere ID's angefordert
   * werden können. In der Methode
   * 
   * @see #getNextNewID()
   */
  private static long lastNewID = Long.MIN_VALUE;
  
  /**
   * @return String
   * @see ch.kausoft.attribut.AttributA#getAttributID()
   */
  public String getAttributID() {
    return "DataRecordID"; // 
  }
  
  /**
   * @see ch.kausoft.attribut.AttributA#getBundleName()
   * @return String
   */
  public String getBundleName() {
    return AttributCommon.bundleName;
  }
  
  /**
   * Die initialisierungsID ist der Wert der gegeben wird wenn die
   * wirkliche ID noch nicht bekannt ist. Anhand diesre Initialisierung
   * kann festgestellt werden dass ein Datensart noch nicht wirklich
   * vorhanden ist.
   * 
   * @return long
   */
  public long getInitialID() {
    return Long.MIN_VALUE;
  }
  
  /**
   * Hier wird die nexte eindeutige ID generiert. Mit diesem System ist
   * jeder Record ein-eindeutig. Wurde einmal eine ID vergeben, bekommt
   * niemand anders diese ID.
   * 
   * @return long
   */
  public synchronized long getNextNewID() {
    long dateTime = (new Date().getTime()) * 100;
    if (dateTime > lastNewID) {
      lastNewID = dateTime;
    } else {
      lastNewID++;
    }
    return lastNewID;
  }
  
  /**
   * Darstellung der DataRecordID. <br>
   * Die DataRecordID wird folgendermassen dargestellt. ( 2007.11.09
   * 01:28:03 22 / 11945.68081.30222 ). Dabei ist die Darstellungsform
   * 11945.68081.30222 die wirklich eindeutige ID. Die Darstellung in
   * der Datumsform kann ungenau sein. Dies ist der Fall weil die Zeit
   * in Millisekunden gespeichert wird.<br>
   * <BR>
   * 
   * @since created at 30.07.2008.00:16:42
   * @param id
   * @return String
   */
  public String getString(long id) {
    String s = Long.toString(id);
    Calendar calendar = new GregorianCalendar();
    Date date = new Date();
    date.setTime((id / 100));
    calendar.setTime(date);
    SimpleDateFormat sdf = new SimpleDateFormat();
    sdf.applyPattern("yyyy.MM.dd HH:mm:ss");
    return sdf.format(date) + " / " //
        + s.substring(0, 5)
        + "." //
        + s.substring(5, 10)
        + "." //
        + s.substring(10);
  }
}
