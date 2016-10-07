/**
 * @author Heinz Kauer 05.01.2014.17:22:04 <br/>
 * Copyright 2014 KauSoft by KauerInformatik. All rights reserved.
 */
package ch.kausoft.wbg.daten;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.kostenmiete.Investition;
import ch.kausoft.kostenmiete.TilgungWert;

/**
 * 
 */
public class DataIOExcel {

	DatenSpeicher ds;
	HashMap<String, Row> metaDatenTypenDefinitionen = new HashMap<String, Row>();

	public void save() {
		save("C:\\eclipse2013\\Workspaces\\kausoft\\Java09_Kostenmiete\\daten\\");
	}

	public void save(String path) {
		System.out.println("150320-2310: - save(..) - " + path);

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheetDaten = wb.createSheet("Daten");
		HSSFSheet sheetInvestitionen = wb.createSheet("Invest");
		HSSFSheet sheetWohnungen = wb.createSheet("Wohnungen");
		HSSFSheet s = wb.createSheet("s");

		HashMap<Long, Investition> investitionen = DatenSpeicher.getDatenSpeicher().getInvestitionen();

		HSSFCellStyle cs = wb.createCellStyle();
		HSSFCellStyle cs2 = wb.createCellStyle();
		HSSFDataFormat df = wb.createDataFormat();

		// create 2 fonts objects
		HSSFFont f = wb.createFont();
		HSSFFont f2 = wb.createFont();

		// Set font 1 to 12 point type, blue and bold
		f.setFontHeightInPoints((short) 12);
		f.setColor(HSSFColor.RED.index);
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// Set font 2 to 10 point type, red and bold
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(HSSFColor.RED.index);
		f2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// Set cell style and formatting
		cs.setFont(f);
		cs.setDataFormat(df.getFormat("#,##0.0"));

		// Set the other cell style and formatting
		cs2.setBorderBottom(cs2.BORDER_THIN);
		cs2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
		cs2.setFont(f2);

		// Define a few rows
		for (short rownum = (short) 0; rownum < 30; rownum++) {
			HSSFRow r = s.createRow(rownum);
			for (short cellnum = (short) 0; cellnum < 10; cellnum += 2) {
				HSSFCell c = r.createCell(cellnum);
				HSSFCell c2 = r.createCell(cellnum + 1);

				c.setCellValue((double) rownum + (cellnum / 10));
				c2.setCellValue(new HSSFRichTextString("Hello! " + cellnum));
			}
		}

		FileOutputStream out;
		try {
			out = new FileOutputStream(path + "sxssf.xls");
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void read() {
		read("C:\\eclipse2013\\Workspaces\\kausoft\\Java09_Kostenmiete\\daten\\");
	}

	public void read(String path) {
		read(path, "ExcelInputfuerApp.xls");
	}

	public void read(String path, String fileName) {
		System.out.println("15200:  - read(..) - " + path + "/" + fileName);

		try {
			InputStream inp = new FileInputStream(path + fileName);
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			System.out.println("re114: " + sheet.getSheetName());

			for (int i_row = 0; i_row < sheet.getLastRowNum(); i_row++) {
				Row row = sheet.getRow(i_row);

				if (row != null) {
					Cell cell = row.getCell(0);
					if (cell != null) {
						int typ = cell.getCellType();
						if (typ == HSSFCell.CELL_TYPE_STRING) {
							String value = cell.getStringCellValue().toUpperCase();
							System.out.println("03658: " + value);
							if (value.equals("TYP")) {
								parsTypDef(row);
							} else if (value.equals("DATA")) {
								selectParsData(row);
							} else if (value.equals("CMD")) {
								Cell cmdCell = row.getCell(1);
								String stringCellValue = cmdCell.getStringCellValue().toUpperCase();
								if (stringCellValue.equals("STOP")) {
									break;
								}
							}
						}
					}
				}
			}

			// wb.gets
			wb.setSheetName(1, "Schweizer2");
			// sheet.set
			Row row = sheet.getRow(2);

			Cell cell = row.getCell(3);
			if (cell == null)
				cell = row.createCell(3);
			System.out.println("050231-1422: " + cell.getCellStyle());

			// cell.setCellStyle(
			// cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(4711);
			wb.createSheet("sheetname");

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(path + "workbooko.xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @since created at 07.01.2014.22:45:29
	 * @param row
	 * @param stringCellValue
	 *          void
	 */
	private void selectParsData(Row row) {
		System.out.println("150320-2300: " + row.getRowNum() + " - selectParsData(..) - ");

		Cell cell = row.getCell(1);
		if (cell != null) {
			int type = cell.getCellType();
			if (type == HSSFCell.CELL_TYPE_STRING) {
				String value = cell.getStringCellValue();
				Row rowDataType = metaDatenTypenDefinitionen.get(value);
				if (rowDataType != null) {
					parsDatenRow(rowDataType, row);
				}
			}
		}
	}

	// private void read_Alt() {
	// try {
	// String path =
	// "C:\\eclipse2013\\Workspaces\\kausoft\\Java09_Kostenmiete\\daten\\";
	// InputStream inp = new FileInputStream(path + "workbook.xls");
	// Workbook wb = WorkbookFactory.create(inp);
	// Sheet sheet = wb.getSheetAt(0);
	// System.out.println(sheet.getSheetName());
	//
	// for (int i_row = 0; i_row < sheet.getLastRowNum(); i_row++) {
	// Row row = sheet.getRow(i_row);
	// if (row != null) {
	// for (int i_cell = 0; i_cell < row.getLastCellNum(); i_cell++) {
	// Cell cell = row.getCell(i_cell);
	// if (cell != null) {
	// int cellType = cell.getCellType();
	// if (cellType == HSSFCell.CELL_TYPE_STRING) {
	// String stringCellValue = cell.getStringCellValue();
	// if (i_cell == 0) {
	// if (stringCellValue.equals("Typ")) {
	// parsTypDef(row);
	// } else {
	// Row rType = metaDatenTypenDefinitionen.get(stringCellValue);
	// if (rType != null) {
	// parsDatenRow(rType, row);
	// }
	// System.out.println(stringCellValue);
	// }
	// }
	// System.out.println(stringCellValue);
	// } else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
	// double numericCellValue = cell.getNumericCellValue();
	// System.out.println(numericCellValue);
	// } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
	// } else if (cellType == HSSFCell.CELL_TYPE_BOOLEAN) {
	// boolean booleanCellValue = cell.getBooleanCellValue();
	// System.out.println(booleanCellValue);
	// } else if (cellType == HSSFCell.CELL_TYPE_FORMULA) {
	// int cachedFormulaResultType = cell.getCachedFormulaResultType();
	// System.out.println(cachedFormulaResultType);
	// }
	// }
	// }
	// }
	// }
	//
	// // wb.gets
	// wb.setSheetName(1, "Schweizer2");
	// // sheet.set
	// Row row = sheet.getRow(2);
	//
	// Cell cell = row.getCell(3);
	// if (cell == null)
	// cell = row.createCell(3);
	// System.out.println(cell.getCellStyle());
	//
	// // cell.setCellStyle(
	// // cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	// cell.setCellValue(4711);
	// wb.createSheet("sheetname");
	//
	// // Write the output to a file
	// FileOutputStream fileOut = new FileOutputStream(path +
	// "workbooko.xls");
	// wb.write(fileOut);
	// fileOut.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 
	 * @since created at 06.01.2014.21:15:39
	 * @param rType
	 * @param row
	 *          void
	 */
	private void parsDatenRow(Row rType, Row row) {
		System.out.println("pd001: row-" + row.getRowNum() + " parsDatenRow(..)");

		Cell cell = rType.getCell(1);
		String stringCellValue = cell.getStringCellValue();
		System.out.println("pd002: " + stringCellValue);
		if (stringCellValue.equalsIgnoreCase("InvestieresKapital")) {
			DataParser.parsInvestiertesKapital(rType, row);
		} else if (stringCellValue.equalsIgnoreCase("KapitalZinssatz")) {
			DataParser.parsKapitalZinssatz(rType, row);
		} else if (stringCellValue.equalsIgnoreCase("Investition")) {
			DataParser.parsInvestition(rType, row);
		} else if (stringCellValue.equalsIgnoreCase("Liegenschaft")) {
			DataParser.parsLiegenschaft(rType, row);
		} else if (stringCellValue.equalsIgnoreCase("Siedlung")) {
			DataParser.parsSiedlung(rType, row);
		} else if (stringCellValue.equalsIgnoreCase("Wohnung")) {
			DataParser.parsWohnung(rType, row);
		} else if (stringCellValue.equalsIgnoreCase("TilgungStrategie")) {
			DataParser.parsTilgungStrategie(rType, row);
		} else if (stringCellValue.equalsIgnoreCase("TilgungWohnung")) {
			DataParser.parsTilgungIndividuell(rType, TilgungWert.Typ.Wohnung, row);
		} else if (stringCellValue.equalsIgnoreCase("TilgungLiegenschaft")) {
			DataParser.parsTilgungIndividuell(rType, TilgungWert.Typ.Liegenschaft, row);
		} else if (stringCellValue.equalsIgnoreCase("TilgungSiedlung")) {
			DataParser.parsTilgungIndividuell(rType, TilgungWert.Typ.Siedlung, row);
		} else if (stringCellValue.equalsIgnoreCase("Mietzinsbeitrag")) {
			DataParser.parsMietzinsbeitrag(rType, row);
		}

	}

	/**
	 * Hier werden die Row's mit den Tabellen-Typen gespeichert.
	 * 
	 * @since created at 06.01.2014.20:26:23
	 * @param row
	 *          void
	 */
	private void parsTypDef(Row row) {
		System.out.println("typ01: parsTypDef(..)");
		Cell cell = row.getCell(1);
		String stringCellValue = cell.getStringCellValue();
		metaDatenTypenDefinitionen.put(stringCellValue, row);

		System.out.print("typ02: " + row.getRowNum() + " - ");
		for (int i_cell = 0; i_cell < row.getLastCellNum(); i_cell++) {
			cell = row.getCell(i_cell);
			if (cell != null) {
				int cellType = cell.getCellType();
				if (cellType == HSSFCell.CELL_TYPE_STRING) {
					stringCellValue = cell.getStringCellValue();
					System.out.println("typ03: , " + stringCellValue);
				}
			}
		}
	}

	public static void main(String[] args) {
		new DataIOExcel().read();
		DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
	}

	private static OutputExcel oExcel = null;

	public static OutputExcel getOutputExcel() {
		if (oExcel == null) {
			oExcel = new OutputExcel();
		}
		return oExcel;
	}

	/**
	 * @since created at 08.02.2014.23:00:48
	 * @param dataPath
	 * @return Excel
	 */
	public static OutputExcel getOutPutExcel(String dataPath) {
		OutputExcel excel = DataIOExcel.getOutputExcel();
		excel.setPath(dataPath);
		return excel;
	}

	/**
	 * @since created at 08.02.2014.23:43:34
	 * @param excel
	 *          void
	 */
	public void save(OutputExcel excel) {

		FileOutputStream out;
		try {
			out = new FileOutputStream(excel.getPath() + "sxssf" + new Date().getTime() + ".xls");
			excel.getWb().write(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
