/*
 * Copyright 2007 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.controller;
import java.util.Map;
import ch.kausoft.context.Context;
import ch.kausoft.context.SessionContext;
/**
 * Ein UseCase ist gleich ein Anwendungsfall.
 * 
 * @author Heinz
 */
public abstract class UseCase {
   public UseCase(String pUseCase_ID) {
      setUsseCase(pUseCase_ID);
   }
   // public static UseCase getInstance(){
   // SessionContext.getContext().setActionResult(actionResult);
   // }
   public Context getContext() {
      return SessionContext.getContext();
   }
   protected void setProperty(String pValue) {
      setProperty("", pValue);
   }
   protected void setProperty(String pName, String pValue) {
      getContext().getProperties().setProperty(
            this.getClass().getName() + pName, pValue);
   }
   protected String getProperty() {
      return getContext().getProperties().getProperty(
            this.getClass().getName());
   }
   protected String getProperty(String pName) {
      return getContext().getProperties().getProperty(
            this.getClass().getName() + pName);
   }
   private void setUsseCase(String pUseCase_ID) {
      Map<String, UseCase> actionResult = getContext()
            .getUseCase();
      actionResult.put(pUseCase_ID, this);
   }
}
