/**
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 * @author Heinz
 */

package ch.kausoft.attribut;

/**
 * 
 * @since created at 30.07.2008.00:44:23
 * @author Heinz Kauer by Kauer Informatik at 30.07.2008 Projekt = (
 * Applikation KauSoft )
 */
public abstract class LongAttributA extends AttributA {
  
  /**
   * @see ch.kausoft.attribut.AttributA#getString()
   */
  public final String getString() {
    long l = getLong();
    return Long.toString(l);
  }
  
  /**
   * @see ch.kausoft.attribut.AttributA#setString(java.lang.String)
   */
  public final void setString(String value) {
    setLong(Long.parseLong(value));
    
  }
  
  /**
   * @return long
   */
  public abstract long getLong();
  
  /**
   * @param value
   */
  public abstract void setLong(long value);
  
}
