/*
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.attribut;

import ch.kausoft.KauSoft;
import ch.kausoft.attribut.CODE.Sprache;
import ch.kausoft.attribut.META.AttributMetaData;

/**
 * Jedes Daten Attribut leitet von diesem Attribut ab. In dieser Klasse
 * werden fundametale Anforderungen umgesetzt. So zum Beispiel die
 * Sprachsteuerung für die Repräsentation. <BR>
 * <BR>
 * <b>Wiki:</b><br>
 * <A HREF="http://notizbuch2014.heinzkauer.ch/mediawiki/index.php?search=Attribut" >Attribut
 * </A> Das ist ein<br>
 * 
 * @see <A HREF="http://notizbuch2014.heinzkauer.ch/mediawiki/index.php?search=Attribut"
 *      >Attribut</A>
 * @since created at 29.07.2008.23:19:18
 * @author Heinz Kauer by Kauer Informatik at 29.07.2008 Projekt = (
 *         Applikation KauSoft )
 */
public abstract class AttributA extends KauSoft {
  
  /**
   * Hier muss die Attribut ID zurück gegeben werden. Diese ID bezieht
   * sich auf das eigentliche Attribut und wird verwendet um aus dem
   * messages.propperties Texte aus zu lesen. Ein messages.propperties
   * Eintrag kann folgendermassen aussehen.
   * "Attr.<b>person.Strasse</b>.Label.D=Strasse: " In diesem Fall
   * entspricht <b>person.Strasse</b> der AttributID <BR>
   * <BR>
   * 
   * @since created at 29.07.2008.23:23:25
   * @return String z.B. <b>person.Strasse</b>
   */
  public abstract String getAttributID();
  
  /**
   * Für jedes Package gibt es ein messages.propperties file. Wird im
   * Package ch.kausoft.attribut.person ein Attribut Strasse deklariert
   * so soll die Methode getBundleName() den String
   * "ch.kausoft.attribut.person.messages" zurückgeben.
   * 
   * @since created at 29.07.2008.23:25:20
   * @return String z.B. "ch.kausoft.attribut.person.messages"
   */
  public abstract String getBundleName();
  
  /**
   * Jedes Attribut hat seine Berechtigung oder seine Begründung darin
   * dass es einen Grund gibt durch die eine Anforderung gefordert ist.
   * Im messages.propperties wird für jedes Attribut die
   * Dokumentations-ID der Anforderung gespeichert. <BR>
   * <BR>
   * 
   * @since created at 29.07.2008.23:25:56
   * @return liefert die Dokumentations-ID der Anforderung
   */
  public final String getAnforderung() {
    return AttributMetaData.getAnforderung(getBundleName(),
        getAttributID());
  }
  
  /**
   * liefert den Label-Text der in einem User-Interface vor einem
   * ausgegebenen Attribut angezeigt wird. <BR>
   * <BR>
   * 
   * @TODO hier ist die Sprache noch fix. Aber eigentlich sollte hier
   *       die Sprache aus einem bestimmten Kontext genommen werden.
   *       Z.B. aus dem Page-Kontext. Die Lables werden ja in einem UI
   *       verwendet.
   * @since created at 29.07.2008.23:26:15
   * @return Sring Label-Text
   */
  public final String getLabelName() {
    return AttributMetaData.getAttributLabelText(getBundleName(),
        getAttributID(), Sprache.SPRACHE_DEUTSCH);
  }
  
  /**
   * liefert den Label-Text der in einem User-Interface vor einem
   * ausgegebenen Attribut angezeigt wird. In einer bestinnten
   * verlangten Sprache TODO ddd
   * 
   * @param sprach_code
   * @since created at 29.07.2008.23:28:09
   * @return String
   */
  public final String getLabelName(char sprach_code) {
    return AttributMetaData.getAttributLabelText(getBundleName(),
        getAttributID(), sprach_code);
  }
  
  /**
   * Hier muss die String Repräsentation vom Attribut zurück gegebenen
   * werden. Dabei handelt es sich vorwiegend um die String Darstellung
   * in einem UI. Für die DB kann es unterschiedlich sein.
   * 
   * @since created at 29.07.2008.23:16:36
   * @return String
   */
  public abstract String getString();
  
  /**
   * Hier wird das Attribut aus einem String erstellt. Das heist der
   * String der übergeben wird, wird so umgewandelt dass er korrekt in
   * das Attribut gespeichert wird.
   * 
   * @since created at 29.07.2008.23:18:04
   * @param value
   */
  public abstract void setString(String value);
}
