package ch.basler.tool.dogenerator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
 * 
 * 
 * https://poi.apache.org/apidocs/index.html?org/apache/poi/ss/
 * 
 * @author B026670
 *
 */
public class Module {

	HSSFWorkbook workbook;
	HSSFSheet sheet;

	// Sub createSelectedSheets()
	// Call createSheets(Application.ActiveWindow.SelectedSheets)
	// End Sub
	/**
	 * 
	 */
	final void createSelectedSheets() {
		// TODO Lesen ein einzelnes sheet
		// createSheets(new WorkSheet());
	}

	// Sub createAllSheets()
	// Call createSheets(Application.ActiveWorkbook.Worksheets)
	// End Sub
	/**
	 * 
	 */
	final void createAllSheets() {
		// TODO Lesen alle sheets
		// createSheets(Application.ActiveWorkbook.Worksheets);
	}

	// Sub createSheets(sheets As sheets)
	final void createSheets(HSSFSheet[] sheets) {

		// Dim sheet As Worksheet
		HSSFSheet sheet;

		// For Each sheet In sheets
		for (int i = 0; i < sheets.length; i++) {
			sheet = sheets[i];

			// If Not sheet.index = SHEET_DIR Then
			if (!(sheet.getSheetName().equals("DIRECTORY"))) {

				// If sheet.index = SHEET_TYPE_MGR Then
				if ((sheet.getSheetName().equals("TYPE MGR"))) {

					// Call writeTypeMgrCodeFile(Application.ActiveWorkbook)
					writeTypeMgrCodeFile(Application.ActiveWorkbook);
				} else {
					// Call writeDOCodeFile(sheet, Application.ActiveWorkbook)
					writeDOCodeFile(sheet, Application.ActiveWorkbook);
				}

			}

		}

		// Application.ActiveWorkbook.Save
		// TODO ----> Application.ActiveWorkbook.Save;
	}

	// Sub writeTypeMgrCodeFile(wbook As Workbook)
	final void writeTypeMgrCodeFile(Workbook wbook) {
		
		// Dim classNameMgr As String
		// 
		// Dim sheetdir As Worksheet
		// Dim workspace As String
		// Dim project As String
		// Dim packageDOs As String
		// Dim packageMgr As String
		// Dim packageInts As String
		// 
		// Dim dirMgr As String
		// Dim fileNameMgr As String
//		     
		// Dim mgrCreator As CreatorTypeMgrClass		
        String classNameMgr;
        Sheet sheetdir;
        String workspace;
        String project;
        String packageDOs;
        String packageMgr;
        String packageInts;
        String dirMgr;
        String fileNameMgr;
        CreatorTypeMgrClass mgrCreator;
        
        
        
     // classNameMgr = wbook.Worksheets(SHEET_TYPE_MGR).Cells(ROW_CLASS_MGR, COL_CLASS_NAME_MGR)
      // TODO   
      //  classNameMgr = wbook.Worksheets(Constants.SHEET_TYPE_MGR)    .Cells[ROW_CLASS_MGR, COL_CLASS_NAME_MGR];
        
        
        // If classNameMgr = "" Then
//          Exit Sub
     // End If
        if ((classNameMgr == "")) {
            return;
        }
        
        
        
        
        // Set sheetdir = wbook.Worksheets(SHEET_DIR)
        sheetdir = wbook.Worksheets  (SHEET_DIR);
        
        // workspace = sheetdir.Cells(ROW_DIR_WORKSPACE, 2)
        workspace =  read(sheetdir,Constants.ROW_DIR_WORKSPACE,2);

        
        
        // project = sheetdir.Cells(ROW_DIR_PROJECT, 2)
        project = read(sheetdir,Constants.ROW_DIR_PROJECT, 2);

        // packageDOs = sheetdir.Cells(ROW_DIR_DOS, 2)
        packageDOs = read(sheetdir,Constants.ROW_DIR_DOS, 2);

        // packageMgr = sheetdir.Cells(ROW_DIR_MGR, 2)
        packageMgr = read(sheetdir,Constants.ROW_DIR_MGR, 2);

        // packageInts = sheetdir.Cells(ROW_DIR_INTERFACES, 2)
        packageInts = read(sheetdir,Constants.ROW_DIR_INTERFACES, 2);

       
        // dirMgr = getDirectory(workspace, project, packageMgr)
        dirMgr = getDirectory(workspace, project, packageMgr);

        // fileNameMgr = dirMgr + "\" + classNameMgr + ".java"
        fileNameMgr = (dirMgr + ("\\" + (classNameMgr + ".java")));
        
     // Set mgrCreator = New CreatorTypeMgrClass
        mgrCreator = new CreatorTypeMgrClass();
        
     // Open fileNameMgr For Output As #1
        Open;
        fileNameMgr;
        
     // Close #1
        //
        //End Sub

        
     // Print #1, mgrCreator.createCode(wbook, classNameMgr, packageMgr, packageDOs)
//        
//        for (object Output; ; Output++) {
//            // TODO: # ... Warning!!! not translated
//            1;
//            Print;
//            // TODO: # ... Warning!!! not translated
//            1;
//            mgrCreator.createCode(wbook, classNameMgr, packageMgr, packageDOs);
//            Close;
//            // TODO: # ... Warning!!! not translated
//            1;
//            
	}

	// Sub writeDOCodeFile(sheet As Worksheet, wbook As Workbook)
	void writeDOCodeFile(HSSFSheet sheet, HSSFWorkbook wbook ) {
    	  
		// 
		// 
		// 

		// Dim classNameMgr As String
            String classNameMgr;
            
            
            // Dim sheetdir As Worksheet
            // Dim workspace As String
            // Dim project As String
            // Dim packageDOs As String
            // Dim packageMgr As String
            // Dim packageInts As String
            HSSFSheet sheetdir;
            String workspace;
            String project;
            String packageDOs;
            String packageMgr;
            String packageInts;

            
            
            // Dim dirDOs As String
            // Dim classNameDO As String
            // Dim fileNameDO As String
            String dirDOs;
            String classNameDO;
            String fileNameDO;

            
            
            // Dim isFileExists As Boolean
            // Dim file As Object
            // Dim DOCreator As CreatorDOClass
            // Dim rec As String
            boolean isFileExists;
            Object file;
            CreatorDOClass DOCreator;
            String rec;

            
            // Dim pos As Long
            // Dim pos1 As Long
            // Dim pos2 As Long
            long pos;
            long pos1;
            long pos2;

            
            // Dim section As String
            // Dim isSection As Boolean
            // Dim isImport As Boolean
            // Dim imps As Integer
            String section;
            boolean isSection;
            boolean isImport;
            int imps;
            
            
            
            // Dim imports(50) As String
            String[] imports = new String [50];
            
            // classNameMgr = wbook.Worksheets(SHEET_TYPE_MGR).Cells(ROW_CLASS_MGR, COL_CLASS_NAME_MGR)
            classNameMgr = wbook.getSheetAt(Constants.SHEET_TYPE_MGR).getCellComment(Constants.ROW_CLASS_MGR, Constants.COL_CLASS_NAME_MGR).toString();
            
            
         // 
         // Set sheetdir = wbook.Worksheets(SHEET_DIR)
         // workspace = sheetdir.Cells(ROW_DIR_WORKSPACE, 2)
         // project = sheetdir.Cells(ROW_DIR_PROJECT, 2)
         // packageDOs = sheetdir.Cells(ROW_DIR_DOS, 2)
         // packageMgr = sheetdir.Cells(ROW_DIR_MGR, 2)
         // packageInts = sheetdir.Cells(ROW_DIR_INTERFACES, 2)
            
            sheetdir = wbook.Worksheets(SHEET_DIR);
            workspace = sheetdir.Cells[ROW_DIR_WORKSPACE, 2];
            project = sheetdir.Cells[ROW_DIR_PROJECT, 2];
            packageDOs = sheetdir.Cells[ROW_DIR_DOS, 2];
            packageMgr = sheetdir.Cells[ROW_DIR_MGR, 2];
            packageInts = sheetdir.Cells[ROW_DIR_INTERFACES, 2];
            classNameDO = sheet.Cells[ROW_CLASS_NAME, COL_CLASS_VALUE];
            dirDOs = getDirectory(workspace, project, packageDOs);
            fileNameDO = (dirDOs + ("\\" 
                        + (classNameDO + ".java")));
            file = CreateObject("Scripting.FileSystemObject");
            //  save file under .javaold
            // If Not dir(fileNameDO) = "" Then
            //     file.copyfile fileNameDO, fileNameDO + "old", True
            // End If
            //  --------------------------------------------------
           //  file already exists -> copy it under .javax and then delete it
            isFileExists = false;
            if (!(dir(fileNameDO) == "")) {
                isFileExists = true;
                file.copyfile;
                fileNameDO;
                (fileNameDO + "x");
                true;
                Kill;
                fileNameDO;
            }
            
            //  --------------------------------------------------
           DOCreator = new CreatorDOClass();
            Open;
            fileNameDO;
            for (object Output; ; Output++) {
                // TODO: # ... Warning!!! not translated
                1;
                //  file already exists ->
                if (isFileExists) {
                    Open;
                    (fileNameDO + "x");
                    for (object Input; ; Input++) {
                        // TODO: # ... Warning!!! not translated
                        2;
                        isSection = false;
                        isImport = false;
                        imps = 0;
                        while (!EOF(2)) {
                            //  read record
                            Line;
                            Input;
                            // TODO: # ... Warning!!! not translated
                            2;
                            rec;
                            //  -------------------------------------
                           //  IMPORTS:
                            section = rec.Substring(0, 6);
                            //  collect imports
                            if ((section == "import")) {
                                isImport = true;
                                if (!(rec == "")) {
                                    imps = (imps + 1);
                                    imps = rec;
                                }
                                
                            }
                            
                            //  generate imports and write them to output file
                            pos1 = (rec.IndexOf("//@@Begin-2") + 1);
                            pos2 = (rec.IndexOf("// @@Begin-2") + 1);
                            if (((pos1 > 0) 
                                        || (pos2 > 0))) {
                                isImport = false;
                                Print;
                                // TODO: # ... Warning!!! not translated
                                1;
                                DOCreator.createCode(wbook, "import", sheet, classNameDO, packageDOs, classNameMgr, packageMgr, packageInts, imports);
                            }
                            
                            //  -------------------------------------
                           //  GENERATED CODE:
                            //  begin of section -> generate whole section and write it to output file
                            pos1 = (rec.IndexOf("//@@Begin-") + 1);
                            pos2 = (rec.IndexOf("// @@Begin-") + 1);
                            if (((pos1 > 0) 
                                        || (pos2 > 0))) {
                                if ((pos1 > 0)) {
                                    pos = (pos1 + 10);
                                }
                                
                                if ((pos2 > 0)) {
                                    pos = (pos2 + 11);
                                }
                                
                                isSection = true;
                                section = rec.Substring((pos - 1), 1);
                                Print;
                                // TODO: # ... Warning!!! not translated
                                1;
                                DOCreator.createCode(wbook, section, sheet, classNameDO, packageDOs, classNameMgr, packageMgr, packageInts, imports);
                            }
                            
                            //  -------------------------------------
                           //  INDIVIDUAL CODE:
                            //  record outside of section and import -> write it to output file
                            if ((!isSection 
                                        && !isImport)) {
                                Print;
                                // TODO: # ... Warning!!! not translated
                                1;
                                rec;
                            }
                            
                            //  -------------------------------------
                           //  end of section -> clear flag
                            pos1 = (rec.IndexOf("//@@End-") + 1);
                            pos2 = (rec.IndexOf("// @@End-") + 1);
                            if (((pos1 > 0) 
                                        || (pos2 > 0))) {
                                isSection = false;
                            }
                            
                        }
                        
                        //  file doesn't exist -> generate whole file in one part
                        Print;
                        // TODO: # ... Warning!!! not translated
                        1;
                        DOCreator.createCode(wbook, 0, sheet, classNameDO, packageDOs, classNameMgr, packageMgr, packageInts, imports);
                        if (Close) {
                            // TODO: # ... Warning!!! not translated
                            1;
                            //  file already exists -> close and delete .javax
                            if (isFileExists) {
                                Close;
                                // TODO: # ... Warning!!! not translated
                                2;
                                Kill;
                                (fileNameDO + "x");
                            }
                            
                        }
                        
                        ((string)(getDirectory(((string)(workspace)), ((string)(project)), ((string)(package)))));
                        String dir;
                        dir = package.Replace(".", "\\");
                        getDirectory = (workspace + ("\\" 
                                    + (project + ("\\src\\main\\java\\" + dir))));
                    }
                    
                }
                
            }
            
        }

	}

	static String read(Sheet sheet, int row, int col) {
		return Tool.read(sheet, row, col);
	}

}
