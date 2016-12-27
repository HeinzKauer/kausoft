/**
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 */

package ch.kausoft.attribut.CODE;

/**
 * @author Heinz Kauer by 06.11.2007 Applikation KauSoft
 */
public abstract class Status extends Code {
  

  /**
   * 
   */
  private short test;
  
  /**
   * Getter of the property <tt>test</tt>
   * @return Returns the test.
   */
  
  public short getTest() {
    return test;
  }
  
  /**
   * Setter of the property <tt>test</tt>
   * @param test The test to set.
   */
  public void setTest(short test) {
    this.test = test;
  }
  
}
