/**
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 */

package ch.kausoft.attribut;

/**
 * Ein ShortAttribut kann eine Auspr�gung von MIN_VALUE -32768 bis
 * MAX_VALUE 32767 aufweisen.
 * 
 * @since created at 30.07.2008.00:44:48
 * @author Heinz Kauer by Kauer Informatik at 30.07.2008 Projekt = (
 *         Applikation KauSoft )
 */
public abstract class ShortAttributA extends AttributA {
  
  /**
   * @see AttributA#getString()
   */
  public final String getString() {
    short s = getShort();
    return Long.toString(s);
  }

  /**
   * (non-Javadoc)
   *
   * @see AttributA#setString(String)
   */
  public final void setString(String value) {
    try {
      setShort(Short.parseShort(value));
    } catch (NumberFormatException e) {}
  }
  
 
  /**
   * @since created at 30.07.2008.00:44:48
   * @return short
   */
  public abstract short getShort();
  
  /**
   * @since created at 30.07.2008.00:44:48
   * @param value
   */
  public abstract void setShort(short value);
}
