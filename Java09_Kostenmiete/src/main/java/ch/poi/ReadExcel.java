package ch.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {

        public static void main(String[] args) {
                new ReadExcel().read();
        }

        private void read() {
                try {
                        // TODO Auto-generated method stub

                        String path = "C:\\eclipse2013\\Workspaces\\kausoft\\Java09_Kostenmiete\\daten\\";
                        InputStream inp = new FileInputStream(path + "workbook.xls");
                        // InputStream inp = new FileInputStream("workbook.xls");

                        Workbook wb = WorkbookFactory.create(inp);
                        
                       
                        //wb.gets
                        wb.setSheetName(1, "Schweizer2");
                        Sheet sheet = wb.getSheetAt(0);
                        //sheet.set
                        Row row = sheet.getRow(2);
                        
                        Cell cell = row.getCell(3);
                        if (cell == null)
                                cell = row.createCell(3);
                        System.out.println(cell.getCellStyle());
                        
//                        cell.setCellStyle(
//                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(4711);
                        wb.createSheet("sheetname");

                        // Write the output to a file
                        FileOutputStream fileOut = new FileOutputStream(path
                                        + "workbooko.xls");
                        wb.write(fileOut);
                        fileOut.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

}