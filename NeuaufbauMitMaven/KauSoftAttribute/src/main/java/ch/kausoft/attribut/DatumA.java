/*
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 */

package ch.kausoft.attribut;

/**
 * Als Datum wird eine Attribut genannt wenn es sich um einen Datum
 * handelt.
 * 
 * @author Heinz
 */
public abstract class DatumA extends AttributA {
  
  /**
   * 
   * @see AttributA#getBundleName()
   */
  public String getBundleName() {
    return AttributCommon.bundleName;
  }

  /**
   * @created 27.07.2008.01:06:22
   * @see AttributA#getAttributID()
   */
  public String getAttributID() {
    return "Datum";
  }
}
