/*
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 */

package ch.kausoft.attribut;

/**
 * Als Bezeichner wird eine Attribut genannt wenn es sich um einen
 * kurzen Text handelt der ein Objekt oder Subjekt bezeichnet.
 * 
 * @author Heinz
 */
public abstract class BeschreibungA extends AttributA {
  
  /**
   * @see ch.kausoft.attribut.AttributA#getBundleName()
   */
  public String getBundleName() {
    return AttributCommon.bundleName;
  }
  
  /**
   * @since created at 04.01.2014.22:57:46
   * @see ch.kausoft.attribut.AttributA#getAttributID()
   */
  public String getAttributID() {
    return "Bezeichnung";
  }
}
