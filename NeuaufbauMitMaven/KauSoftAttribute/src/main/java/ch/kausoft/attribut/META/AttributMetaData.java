/*
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 */

package ch.kausoft.attribut.META;

import ch.kausoft.attribut.CODE.Sprache;

import java.util.Hashtable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * AttributMetaData hält alle Beschreibungsdaten über die Attribute.<br>
 * <br>
 * Beschreibungsdaten sind Informationen die ein Attribut als Typ
 * beschreiben. <br>
 * Beispiele:
 * <ul>
 * <li><b>Label</b> das ist die Bezeichnung des Attributes das z.B. auf
 * einer Bildschirmdarstellung verendet wird. Dieser Text ist
 * mehrsprachig</li>
 * <li><b>Anforderung</b> jedes Attribut ist auf Grund einer bestimmten
 * Anforderung zustande gekommen. Es kann die ID der Anforderung
 * abgefragt werden.</li>
 * </ul>
 * Die Beschreibungsdaten werden in ResourceBundle gespeichert. Die
 * Beschreibungsdaten werden in Textfiles mit der Bezeichnung
 * <b>messages.properties</b> gespeichert. In der Regel hat jedes Java
 * Package ein messages.properties File. <br>
 * <br>
 * Damit die Textfiles nicht jedes mal eingelsen werden wenn eine
 * Anfrage gestellt wird, werden die gelesenen properties File in einem
 * Buffer gespeichert. <BR>
 * <BR>
 * <b>Wiki=</b><A
 * HREF="http://notizbuch2014.heinzkauer.ch/mediawiki/index.php?search=ResourceBundle"
 * >ResourceBundle </A>,<A
 * HREF="http://notizbuch2014.heinzkauer.ch/mediawiki/index.php?search=0807.2923.4500">
 * 0807.2923.4500 </A>
 * 
 * @since created at 29.07.2008.23:44:05
 * @author Heinz Kauer by Kauer Informatik at 29.07.2008 Projekt = (
 *         Applikation KauSoft )
 */
public class AttributMetaData {
  
  /**
   * Der MessageContainer ist ein Container in dem die eingelesenen
   * messages.properties Files gebuffert werden.<br>
   * 
   * @since created at 13.10.2007 00:26:27
   * @author Heinz
   */
  class MessageContainer {
    
    /**
     * Der MessageContainer wird durch eine Hashtable abgebildet.
     */
    private Hashtable<String, ResourceBundle> messages = new Hashtable<String, ResourceBundle>();
    
    /**
     * (non-Javadoc)
     * 
     * @param key
     * @return ResourceBundle
     * @see java.util.Hashtable#get(java.lang.Object)
     */
    public synchronized ResourceBundle get(String key) {
      return messages.get(key);
    }
    
    /**
     * (non-Javadoc)
     * 
     * @param arg0
     * @param arg1
     * @return ResourceBundle
     * @see java.util.Hashtable#put(java.lang.Object, java.lang.Object)
     */
    public synchronized ResourceBundle put(String arg0,
        ResourceBundle arg1) {
      return messages.put(arg0, arg1);
    }
  }
  
  /**
   * Singelton implementierung<br>
   * Die MataDaten müssen nur einmal im System vorhanden sein.
   */
  private static AttributMetaData metaData = null;
  
  /**
   * Diese Methode gibt abhängig von den Parametern die Anforderung-ID
   * zurück.
   * 
   * @param bundleName
   *          "ch.kausoft.attribut.person.messages"
   * @param attributID
   *          = z.B. person.GeburtsDatum
   * @return String der gefundene Wert aus dem
   *         ch.kausoft.attribut.person.messages.properties File
   */
  public static String getAnforderung(String bundleName,
      String attributID) {
    AttributMetaData ctrl = getInstance();
    ResourceBundle bundle = ctrl.messageContainer.get(bundleName);
    if (bundle == null) {
      bundle = ResourceBundle.getBundle(bundleName);
      if (bundle == null) { throw new MissingResourceException(
          "ResourceBundle konnte nicht geladen werden.", bundleName,
          attributID); }
      ctrl.messageContainer.put(bundleName, bundle);
    }
    // Attr.person.GeburtsDatum.Anforderung=0707.1501.0900
    try {
      return bundle.getString("Attr." + attributID + ".Anforderung");
    } catch (MissingResourceException e) {
      e.printStackTrace();
    }
    return "-->" + attributID;
  }
  
  /**
   * Diese Methode gibt abhängig von den Parametern den Attribut-Label
   * Text zurück. Je nach Sprache_code in der entsprechenden Sprache.
   * Ist der Sprach_code = ? wird der technische Name =-> die attributID
   * zusammen mit der Anforderungs-Id zurück gegeben.
   * 
   * @param bundleName
   *          "ch.kausoft.attribut.person.messages"
   * @param attributID
   * @param sprach_code
   * @return String
   */
  public static String getAttributLabelText(String bundleName,
      String attributID, char sprach_code) {
    if (sprach_code == Sprache.SPRACHE_ATTRIBUT_ID) { return attributID
        + "-"
        + getAnforderung(bundleName, attributID); }
    AttributMetaData ctrl = getInstance();
    // System.out.println("getAttributLabelName()
    // bundleName="+bundleName+"
    // attributID="+attributID+" sprache="+sprache);
    ResourceBundle bundle = ctrl.messageContainer.get(bundleName);
    if (bundle == null) {
      bundle = ResourceBundle.getBundle(bundleName);
      if (bundle == null) { throw new MissingResourceException(
          "ResourceBundle konnte nicht geladen werden.", bundleName,
          attributID); }
      ctrl.messageContainer.put(bundleName, bundle);
    }
    // Attr.person.Strasse.Label.D=Strasse D
    try {
      return bundle.getString("Attr."
          + attributID
          + ".Label."
          + sprach_code);
    } catch (MissingResourceException e) {
      e.printStackTrace();
    }
    return "-->" + attributID;
  }
  
  /**
   * Singelton implementierung von AttributMetaData<br>
   * Es genügt wenn die AttributMetaData pro Applikation nur einmal
   * vorhanden sind. Dieses Singelton ist nicht gesichert. Das heist es
   * gibt keine Referenzen die dafür sorgen dass dieses Singelton vom
   * Garbage Collector nicht etwa gelöscht würde. Dies schadet
   * allerdings nicht, weil es nur transiente Daten enthält.
   * 
   * @since created at 14.06.2008.22:46:05
   * @return AttributMetaData
   */
  private static synchronized AttributMetaData getInstance() {
    if (metaData == null) {
      metaData = new AttributMetaData();
      System.out
          .println("14.06.2008.22:46:05 AttributMetaData neu instantiiert"
              + metaData);
    }
    return metaData;
  }
  
  /**
   * Der vorhanden Buffer wird gelöscht, das führt dazu dass bei Bedarf
   * die message.properties neu geladen werden.
   */
  public void reload() {
    messageContainer = new MessageContainer();
  }
  
  /**
   * Buffer in dem die message.properties gespeichert werden.
   */
  private MessageContainer messageContainer = new MessageContainer();
}
