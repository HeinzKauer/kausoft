/**
 * @author Heinz Kauer 08.02.2014.23:04:30 <br/>
 * Copyright 2014 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.wbg.daten;

import lombok.Data;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 */
@Data
public class OutputExcel {
	private HSSFWorkbook wb = new HSSFWorkbook();
	private HSSFSheet datenSheet = wb.createSheet("Daten");
	private HSSFSheet investitionenSheet = wb.createSheet("Invest");
	private HSSFSheet wohnungenSheet = wb.createSheet("Wohnungen");
	private String path = "C:\\eclipse2013\\Workspaces\\kausoft\\Java09_Kostenmiete\\daten\\";
}
