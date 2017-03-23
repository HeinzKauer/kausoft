package ch.basler.tool.dogenerator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import  ch.basler.tool.dogenerator.Constants.*;

public class CreatorTypeMgrClass {
	

	// TODO: Option Explicit ... Warning!!! not translated
	Workbook wbook;
	Sheet sheet;
	String classNameMgr;
	String packageMgr;
	String packageDOs;
	int indexMgr;
	String tb;
	String lf;
	int startrow;
	int rows;


	
	String [] DOClasses = new String[100];
	String [] BOClasses = new String[100];
	String [] KeyCreationTypes = new String[100];
	



	final void createCode(Workbook pWbook, String pClassNameMgr, String pPackageMgr, String pPackageDOs) {
	         int ii;
	         Sheet wsheet;
	         wbook = pWbook;
	         
	         wbook.getSheetAt(Constants.SHEET_TYPE_MGR)

	         classNameMgr = pClassNameMgr;
	         packageMgr = pPackageMgr;
	         packageDOs = pPackageDOs;
	         
	         
	         sheet.
	         indexMgr = sheet.Cells 	        		 [ Constants.ROW_CLASS_MGR, Constants. COL_INDEX_MGR];

	         //	         tb = (' ' + ' ');
//	         lf = ('\r' + '\n');
	         
	         startrow = Constants.ROW_START_SERVICES;
	         rows = sheet.UsedRange.rows.count;
	         //  collect do types
	         ii = 0;
	         for (wsheet : wbook.Sheet) {
	             if ((wsheet.index >= Constants.SHEET_START_DOS)) {
	                 ii = (ii + 1);
	                 DOClasses(ii) = wsheet.Cells[ROW_CLASS_NAME, COL_CLASS_VALUE];
	                 BOClasses(ii) = wsheet.Cells[ROW_CLASS_BO, COL_CLASS_VALUE];
	                 KeyCreationTypes(ii) = wsheet.Cells[ROW_CLASS_KEY_CREATION_TYPE, COL_CLASS_VALUE];
	             }
	             
	         }
	         
	         //  comment, package, imports
	         createCode = (createCode + createHeader);
	         //  class def
	         createCode = (createCode + createClass);
	         //  enumerator -> do types
	         createCode = (createCode + createEnumDOTypes);
	         //  enumerator -> read services
	         createCode = (createCode + createEnumReadServices);
	         //  descr types
	         createCode = (createCode + createDescrTypes);
	         //  descr read services
	         createCode = (createCode + createDescrReadServices);
	         //  constructor
	         createCode = (createCode + createConstructor);
	         //  end
	         return (createCode 
	                     + (lf + "}"));
	     }

	final void createHeader() {
		int ii;
		// comment
		createHeader = ("//*****************************************************************************" + (lf
				+ ("// GENERATED CODE!" + (lf + ("// This code has been generated via an excel file thru a vba macro."
						+ (lf + ("// Do not edit this code!"
								+ (lf + ("// To change the information represented by this code,"
										+ (lf + ("// edit the related excel file and regenerate the whole file from there."
												+ (lf + "//*****************************************************************************"))))))))))));
		createHeader = (createHeader + (lf + ("package " + (packageMgr + (";" + lf)))));
		// imports
		createHeader = (createHeader
				+ (lf + ("import java.util.ArrayList;" + (lf + ("import ch.basler.gwt.base.common.interfaces.IManager;"
						+ (lf + ("import ch.basler.gwt.base.common.interfaces.IDataObject;"
								+ (lf + ("import ch.basler.gwt.base.common.interfaces.IDOFactory;"
										+ (lf + ("import ch.basler.gwt.base.common.interfaces.IDescrType;"
												+ (lf + ("import ch.basler.gwt.base.common.interfaces.IDescrType.eKeyCreationType;"
														+ (lf + ("import ch.basler.gwt.base.common.interfaces.IDescrReadService;"
																+ (lf + ("import ch.basler.gwt.base.common.dataobject.TypeMgr;"
																		+ (lf + ("import ch.basler.gwt.base.common.dataobject.DescrType;"
																				+ (lf + "import ch.basler.gwt.base.common.dataobject.DescrReadService;"))))))))))))))))))));
		for (ii = 1; (ii <= UBound(DOClasses)); ii++) {
			if ((DOClasses(ii) == "")) {
				break;
			}

			createHeader = (createHeader + (lf + ("import " + (packageDOs + ("." + (DOClasses(ii) + ";"))))));
		}

	}

	final void createClass() {
		return (lf + (lf + ("// ============================================================================="
				+ (lf + ("/**" + (lf + (" * The class <code>"
						+ (classNameMgr + ("</code> creates and holds meta info for all DO types (DescrType) and " + (lf
								+ (" * all read services (DescrReadService) used within the application." + (lf + (" */"
										+ (lf + ("public class " + (classNameMgr + " extends TypeMgr {"))))))))))))))));
	}

	final void createEnumDOTypes() {
		int ii;
		String sign;
		createEnumDOTypes = (lf + (tb + "public enum eType implements IEType {"));
		for (ii = 1; (ii <= UBound(DOClasses)); ii++) {
			if ((DOClasses(ii) == "")) {
				break;
			}

			if ((ii > 1)) {
				createEnumDOTypes = (createEnumDOTypes + ",");
			}

			createEnumDOTypes = (createEnumDOTypes
					+ (lf + (tb + (tb + (DOClasses(ii).ToUpper() + (" (" + (Str((ii - 1)).Trim() + ")")))))));
		}

		createEnumDOTypes = (createEnumDOTypes + ";");
		return (createEnumDOTypes + (lf + (lf + (tb + (tb + ("private int index;" + (lf + (lf + (tb + (tb
				+ ("eType (int index) {" + (lf + (tb + (tb + (tb + ("this.index = index;" + (lf + (tb + (tb + ("}" + (lf
						+ (lf + (tb + (tb + ("public int index() {" + (lf + (tb + (tb + (tb + ("return index;" + (lf
								+ (tb + (tb + ("}" + (lf + (lf + (tb + (tb + ("public int mgr() {" + (lf + (tb + (tb
										+ (tb + ("return " + (Str(indexMgr).Trim() + (";" + (lf + (tb + (tb + ("}" + (lf
												+ (tb + ("}" + lf)))))))))))))))))))))))))))))))))))))))))))))))))))));
	}

	final void createEnumReadServices() {
	         int ii;
	         String sign;
	         createEnumReadServices = (lf 
	                     + (tb + ("// ---------------------------------------------------------------------------" 
	                     + (lf 
	                     + (lf 
	                     + (tb + "public enum eReadService implements IEReadService {"))))));
	         for (ii = startrow; (ii <= rows); ii++) {
	             sign = ",";
	             if ((ii == rows)) {
	                 sign = ";";
	             }
	             
	             createEnumReadServices = (createEnumReadServices 
	                         + (lf 
	                         + (tb 
	                         + (tb 
	                         + (sheet.Cells[ii, COL_SERVICE_NAME].ToUpper() + (" (" 
	                         + (Str((ii - startrow)).Trim() + (")" + sign))))))));
	         }
	         
	         return (createEnumReadServices 
	                     + (lf 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("private int index;" 
	                     + (lf 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("eReadService (int index) {" 
	                     + (lf 
	                     + (tb 
	                     + (tb 
	                     + (tb + ("this.index = index;" 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("}" 
	                     + (lf 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("public int index() {" 
	                     + (lf 
	                     + (tb 
	                     + (tb 
	                     + (tb + ("return index;" 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("}" 
	                     + (lf 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("public int mgr() {" 
	                     + (lf 
	                     + (tb 
	                     + (tb 
	                     + (tb + ("return " 
	                     + (Str(indexMgr).Trim() + (";" 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("}" 
	                     + (lf 
	                     + (tb + ("}" + lf)))))))))))))))))))))))))))))))))))))))))))))))))))));
	     }

	final void createDescrTypes() {
	         int ii;
	         int jj;
	         boolean isFirst;
	         String sign;
	         String kct;
	         createDescrTypes = (lf 
	                     + (tb + ("// ---------------------------------------------------------------------------" 
	                     + (lf 
	                     + (tb + ("// descr types" + lf))))));
	         for (ii = 1; (ii <= UBound(DOClasses)); ii++) {
	             if ((DOClasses(ii) == "")) {
	                 break;
	             }
	             
	             kct = KeyCreationTypes(ii);
	             if ((kct == "")) {
	                 kct = "KeyOnPersist";
	             }
	             
	             //  attr mgr, do class, do creation
	             createDescrTypes = (createDescrTypes 
	                         + (lf 
	                         + (tb + ("public static final DescrType d" 
	                         + (DOClasses(ii).ToUpper() + (" = new DescrType(" 
	                         + (lf 
	                         + (tb 
	                         + (tb + ("eType." 
	                         + (DOClasses(ii).ToUpper() + ("," 
	                         + (lf 
	                         + (tb 
	                         + (tb 
	                         + (DOClasses(ii) + (".ATTRMGR," 
	                         + (lf 
	                         + (tb 
	                         + (tb + ("eKeyCreationType." 
	                         + (kct + ("," 
	                         + (lf 
	                         + (tb 
	                         + (tb 
	                         + (DOClasses(ii) + (".class," 
	                         + (lf 
	                         + (tb 
	                         + (tb + ("new IDOFactory() {" 
	                         + (lf 
	                         + (tb 
	                         + (tb 
	                         + (tb + ("public IDataObject createDO(IManager mgr) {" 
	                         + (lf 
	                         + (tb 
	                         + (tb 
	                         + (tb 
	                         + (tb + ("return new " 
	                         + (DOClasses(ii) + ("(mgr);" 
	                         + (lf 
	                         + (tb 
	                         + (tb 
	                         + (tb + ("}" 
	                         + (lf 
	                         + (tb 
	                         + (tb + "},")))))))))))))))))))))))))))))))))))))))))))))))))))));
	             createDescrTypes = (createDescrTypes 
	                         + (lf 
	                         + (tb 
	                         + (tb + "null,"))));
	             isFirst = true;
	             sign = "";
	             for (jj = startrow; (jj <= rows); jj++) {
	                 if ((sheet.Cells[jj, COL_START_DOS] == DOClasses(ii))) {
	                     if (isFirst) {
	                         isFirst = false;
	                         createDescrTypes = (createDescrTypes 
	                                     + (lf 
	                                     + (tb 
	                                     + (tb + "new eReadService[] { "))));
	                     }
	                     
	                     createDescrTypes = (createDescrTypes 
	                                 + (sign + ("eReadService." + sheet.Cells[jj, COL_SERVICE_NAME].ToUpper())));
	                     sign = ", ";
	                 }
	                 
	             }
	             
	             if (isFirst) {
	                 createDescrTypes = (createDescrTypes 
	                             + (lf 
	                             + (tb 
	                             + (tb + "null);"))));
	             }
	             else {
	                 createDescrTypes = (createDescrTypes + " });");
	             }
	             
	             createDescrTypes = (createDescrTypes + lf);
	         }
	         
	     }s

	final void createDescrReadServices() {
	         int ii;
	         int jj;
	         boolean isFirst;
	         String sign;
	         createDescrReadServices = (lf 
	                     + (tb + ("// ---------------------------------------------------------------------------" 
	                     + (lf 
	                     + (tb + ("// descr read services" + lf))))));
	         for (ii = startrow; (ii <= rows); ii++) {
	             if (!(sheet.Cells[ii, COL_SERVICE_NAME] == "")) {
	                 createDescrReadServices = (createDescrReadServices 
	                             + (lf 
	                             + (tb + ("public static final DescrReadService d" 
	                             + (sheet.Cells[ii, COL_SERVICE_NAME].ToUpper() + (" = new DescrReadService(" + ("eReadService." 
	                             + (sheet.Cells[ii, COL_SERVICE_NAME].ToUpper() + ","))))))));
	                 isFirst = true;
	                 sign = "";
	                 for (jj = COL_START_DOS; (jj <= 100); jj++) {
	                     if ((sheet.Cells[ii, jj] == "")) {
	                         break;
	                     }
	                     
	                     if (isFirst) {
	                         isFirst = false;
	                         createDescrReadServices = (createDescrReadServices 
	                                     + (lf 
	                                     + (tb 
	                                     + (tb + "new eType[] {"))));
	                     }
	                     
	                     createDescrReadServices = (createDescrReadServices 
	                                 + (sign + ("eType." + sheet.Cells[ii, jj].ToUpper())));
	                     sign = ", ";
	                 }
	                 
	                 if (isFirst) {
	                     createDescrReadServices = (createDescrReadServices + "null);");
	                 }
	                 else {
	                     createDescrReadServices = (createDescrReadServices + "});");
	                 }
	                 
	             }
	             
	             createDescrReadServices = (createDescrReadServices + lf);
	         }
	         
	     }

	final void createConstructor() {
	         int ii;
	         createConstructor = (lf 
	                     + (tb + ("// ---------------------------------------------------------------------------" 
	                     + (lf 
	                     + (lf 
	                     + (tb + ("public " 
	                     + (classNameMgr + "() {"))))))));
	         createConstructor = (createConstructor 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("// add descr types" 
	                     + (lf 
	                     + (tb 
	                     + (tb + "ArrayList<IDescrType> mgrtypes = new ArrayList<IDescrType>();"))))))));
	         for (ii = 1; (ii <= UBound(DOClasses)); ii++) {
	             if ((DOClasses(ii) == "")) {
	                 break;
	             }
	             
	             createConstructor = (createConstructor 
	                         + (lf 
	                         + (tb 
	                         + (tb + ("mgrtypes.add(d" 
	                         + (DOClasses(ii).ToUpper() + ");"))))));
	         }
	         
	         createConstructor = (createConstructor 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("addDescrTypes(" 
	                     + (Str(indexMgr).Trim() + ", mgrtypes);"))))));
	         createConstructor = (createConstructor 
	                     + (lf 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("// add descr read services" 
	                     + (lf 
	                     + (tb 
	                     + (tb + "ArrayList<IDescrReadService> mgrservices = new ArrayList<IDescrReadService>();")))))))));
	         for (ii = startrow; (ii <= rows); ii++) {
	             if (!(sheet.Cells[ii, COL_SERVICE_NAME] == "")) {
	                 createConstructor = (createConstructor 
	                             + (lf 
	                             + (tb 
	                             + (tb + ("mgrservices.add(d" 
	                             + (sheet.Cells[ii, COL_SERVICE_NAME].ToUpper() + ");"))))));
	             }
	             
	         }
	         
	         createConstructor = (createConstructor 
	                     + (lf 
	                     + (tb 
	                     + (tb + ("addDescrReadServices(" 
	                     + (Str(indexMgr).Trim() + ", mgrservices);"))))));
	         return (createConstructor 
	                     + (lf 
	                     + (tb + "}")));
	     }

}


// Function createCode(pWbook As Workbook, pClassNameMgr As String, _
// pPackageMgr As String, pPackageDOs As String)
//
// Dim ii As Integer
// Dim wsheet As Worksheet
//
// Set wbook = pWbook
// Set sheet = wbook.Worksheets(SHEET_TYPE_MGR)
// classNameMgr = pClassNameMgr
// packageMgr = pPackageMgr
// packageDOs = pPackageDOs
//
// indexMgr = sheet.Cells(ROW_CLASS_MGR, COL_INDEX_MGR)
//
// tb = Chr(32) + Chr(32)
// lf = Chr(13) + Chr(10)
// startrow = ROW_START_SERVICES
// rows = sheet.UsedRange.rows.count
//
// ' collect do types
// ii = 0
// For Each wsheet In wbook.Worksheets
// If wsheet.index >= SHEET_START_DOS Then
// ii = ii + 1
// DOClasses(ii) = wsheet.Cells(ROW_CLASS_NAME, COL_CLASS_VALUE)
// BOClasses(ii) = wsheet.Cells(ROW_CLASS_BO, COL_CLASS_VALUE)
// KeyCreationTypes(ii) = wsheet.Cells(ROW_CLASS_KEY_CREATION_TYPE,
// COL_CLASS_VALUE)
// End If
// Next
//
// ' comment, package, imports
// createCode = createCode + createHeader
//
// ' class def
// createCode = createCode + createClass
//
// ' enumerator -> do types
// createCode = createCode + createEnumDOTypes
//
// ' enumerator -> read services
// createCode = createCode + createEnumReadServices
//
// ' descr types
// createCode = createCode + createDescrTypes
//
// ' descr read services
// createCode = createCode + createDescrReadServices
//
// ' constructor
// createCode = createCode + createConstructor
//
// ' end
// createCode = createCode + _
// lf + "}"
//
// End Function
//
// Function createHeader()
//
// Dim ii As Integer
//
// ' comment
// createHeader = _
// "//*****************************************************************************"
// + _
// lf + "// GENERATED CODE!" + _
// lf + "// This code has been generated via an excel file thru a vba macro." +
// _
// lf + "// Do not edit this code!" + _
// lf + "// To change the information represented by this code," + _
// lf + "// edit the related excel file and regenerate the whole file from
// there." + _
// lf +
// "//*****************************************************************************"
//
// ' package
// createHeader = createHeader + _
// lf + "package " + packageMgr + ";" + _
// lf
//
// ' imports
// createHeader = createHeader + _
// lf + "import java.util.ArrayList;" + _
// lf + "import ch.basler.gwt.base.common.interfaces.IManager;" + _
// lf + "import ch.basler.gwt.base.common.interfaces.IDataObject;" + _
// lf + "import ch.basler.gwt.base.common.interfaces.IDOFactory;" + _
// lf + "import ch.basler.gwt.base.common.interfaces.IDescrType;" + _
// lf + "import
// ch.basler.gwt.base.common.interfaces.IDescrType.eKeyCreationType;" + _
// lf + "import ch.basler.gwt.base.common.interfaces.IDescrReadService;" + _
// lf + "import ch.basler.gwt.base.common.dataobject.TypeMgr;" + _
// lf + "import ch.basler.gwt.base.common.dataobject.DescrType;" + _
// lf + "import ch.basler.gwt.base.common.dataobject.DescrReadService;"
// 'lf + "import ch.basler.gwt.base.common.interfaces.IBusinessObject;" +
// 'lf + "import ch.basler.gwt.base.common.interfaces.IBOFactory;" +
//
// For ii = 1 To UBound(DOClasses)
// If DOClasses(ii) = "" Then
// Exit For
// End If
// createHeader = createHeader + _
// lf + "import " + packageDOs + "." + DOClasses(ii) + ";"
// 'If Not BOClasses(ii) = "" Then
// ' createHeader = createHeader + _
// ' lf + "import " + packageDOs + "." + BOClasses(ii) + ";"
// 'End If
// Next
//
// End Function
//
// Function createClass()
//
// createClass = _
// lf + _
// lf + "//
// ============================================================================="
// + _
// lf + "/**" + _
// lf + " * The class <code>" + classNameMgr + "</code> creates and holds meta
// info for all DO types (DescrType) and " + _
// lf + " * all read services (DescrReadService) used within the application." +
// _
// lf + " */" + _
// lf + "public class " + classNameMgr + " extends TypeMgr {"
//
// End Function
//
// Function createEnumDOTypes()
//
// Dim ii As Integer
// Dim sign As String
//
// createEnumDOTypes = _
// lf + tb + "public enum eType implements IEType {"
//
// For ii = 1 To UBound(DOClasses)
// If DOClasses(ii) = "" Then
// Exit For
// End If
// If ii > 1 Then
// createEnumDOTypes = createEnumDOTypes + ","
// End If
// createEnumDOTypes = createEnumDOTypes + _
// lf + tb + tb + UCase(DOClasses(ii)) + _
// " (" + Trim(Str(ii - 1)) + ")"
// Next ii
//
// createEnumDOTypes = createEnumDOTypes + ";"
//
// createEnumDOTypes = createEnumDOTypes + _
// lf + _
// lf + tb + tb + "private int index;" + _
// lf + _
// lf + tb + tb + "eType (int index) {" + _
// lf + tb + tb + tb + "this.index = index;" + _
// lf + tb + tb + "}" + _
// lf + _
// lf + tb + tb + "public int index() {" + _
// lf + tb + tb + tb + "return index;" + _
// lf + tb + tb + "}" + _
// lf + _
// lf + tb + tb + "public int mgr() {" + _
// lf + tb + tb + tb + "return " + Trim(Str(indexMgr)) + ";" + _
// lf + tb + tb + "}" + _
// lf + tb + "}" + _
// lf
//
// End Function
//
// Function createEnumReadServices()
//
// Dim ii As Integer
// Dim sign As String
//
// createEnumReadServices = _
// lf + tb + "//
// ---------------------------------------------------------------------------"
// + _
// lf + _
// lf + tb + "public enum eReadService implements IEReadService {"
//
// For ii = startrow To rows
// sign = ","
// If ii = rows Then
// sign = ";"
// End If
// createEnumReadServices = createEnumReadServices + _
// lf + tb + tb + UCase(sheet.Cells(ii, COL_SERVICE_NAME)) + _
// " (" + Trim(Str(ii - startrow)) + ")" + sign
// Next ii
//
// createEnumReadServices = createEnumReadServices + _
// lf + _
// lf + tb + tb + "private int index;" + _
// lf + _
// lf + tb + tb + "eReadService (int index) {" + _
// lf + tb + tb + tb + "this.index = index;" + _
// lf + tb + tb + "}" + _
// lf + _
// lf + tb + tb + "public int index() {" + _
// lf + tb + tb + tb + "return index;" + _
// lf + tb + tb + "}" + _
// lf + _
// lf + tb + tb + "public int mgr() {" + _
// lf + tb + tb + tb + "return " + Trim(Str(indexMgr)) + ";" + _
// lf + tb + tb + "}" + _
// lf + tb + "}" + _
// lf
//
// End Function
//
// Function createDescrTypes()
//
// Dim ii As Integer
// Dim jj As Integer
// Dim isFirst As Boolean
// Dim sign As String
// Dim kct As String
//
// createDescrTypes = _
// lf + tb + "//
// ---------------------------------------------------------------------------"
// + _
// lf + tb + "// descr types" + _
// lf
//
// For ii = 1 To UBound(DOClasses)
// If DOClasses(ii) = "" Then
// Exit For
// End If
//
// kct = KeyCreationTypes(ii)
// If kct = "" Then
// kct = "KeyOnPersist"
// End If
//
// ' attr mgr, do class, do creation
// createDescrTypes = createDescrTypes + _
// lf + tb + "public static final DescrType d" + UCase(DOClasses(ii)) + " = new
// DescrType(" + _
// lf + tb + tb + "eType." + UCase(DOClasses(ii)) + "," + _
// lf + tb + tb + DOClasses(ii) + ".ATTRMGR," + _
// lf + tb + tb + "eKeyCreationType." + kct + "," + _
// lf + tb + tb + DOClasses(ii) + ".class," + _
// lf + tb + tb + "new IDOFactory() {" + _
// lf + tb + tb + tb + "public IDataObject createDO(IManager mgr) {" + _
// lf + tb + tb + tb + tb + "return new " + DOClasses(ii) + "(mgr);" + _
// lf + tb + tb + tb + "}" + _
// lf + tb + tb + "},"
//
// ' bo -> bo creation
// 'If Not BOClasses(ii) = "" Then
// ' createDescrTypes = createDescrTypes + _
// ' lf + tb + tb + "new IBOFactory()" + _
// ' lf + tb + tb + "{" + _
// ' lf + tb + tb + tb + "public IBusinessObject createBO(IManager mgr) { return
// new " + BOClasses(ii) + "(mgr); }" + _
// ' lf + tb + tb + "},"
// 'Else
// createDescrTypes = createDescrTypes + _
// lf + tb + tb + "null,"
// 'End If
//
// ' read service(s)
// isFirst = True
// sign = ""
// For jj = startrow To rows
// If sheet.Cells(jj, COL_START_DOS) = DOClasses(ii) Then
// If isFirst Then
// isFirst = False
// createDescrTypes = createDescrTypes + _
// lf + tb + tb + "new eReadService[] { "
// End If
// createDescrTypes = createDescrTypes + sign + "eReadService." +
// UCase(sheet.Cells(jj, COL_SERVICE_NAME))
// sign = ", "
// End If
// Next jj
// If isFirst Then
// createDescrTypes = createDescrTypes + _
// lf + tb + tb + "null);"
// Else
// createDescrTypes = createDescrTypes + " });"
// End If
//
// createDescrTypes = createDescrTypes + lf
// Next ii
//
// End Function
//
// Function createDescrReadServices()
//
// Dim ii As Integer
// Dim jj As Integer
// Dim isFirst As Boolean
// Dim sign As String
//
// createDescrReadServices = _
// lf + tb + "//
// ---------------------------------------------------------------------------"
// + _
// lf + tb + "// descr read services" + _
// lf
//
// For ii = startrow To rows
// If Not sheet.Cells(ii, COL_SERVICE_NAME) = "" Then
//
// createDescrReadServices = createDescrReadServices + _
// lf + tb + "public static final DescrReadService d" + UCase(sheet.Cells(ii,
// COL_SERVICE_NAME)) + " = new DescrReadService(" + _
// "eReadService." + UCase(sheet.Cells(ii, COL_SERVICE_NAME)) + ","
//
// ' do type(s)
// isFirst = True
// sign = ""
// For jj = COL_START_DOS To 100
// If sheet.Cells(ii, jj) = "" Then
// Exit For
// End If
// If isFirst Then
// isFirst = False
// createDescrReadServices = createDescrReadServices + _
// lf + tb + tb + "new eType[] {"
// End If
// createDescrReadServices = createDescrReadServices + sign + "eType." +
// UCase(sheet.Cells(ii, jj))
// sign = ", "
// Next jj
//
// If isFirst Then
// createDescrReadServices = createDescrReadServices + "null);"
// Else
// createDescrReadServices = createDescrReadServices + "});"
// End If
//
// End If
//
// createDescrReadServices = createDescrReadServices + lf
// Next ii
//
// End Function
//
// Function createConstructor()
//
// Dim ii As Integer
//
// createConstructor = _
// lf + tb + "//
// ---------------------------------------------------------------------------"
// + _
// lf + _
// lf + tb + "public " + classNameMgr + "() {"
//
// ' do types
// createConstructor = createConstructor + _
// lf + tb + tb + "// add descr types" + _
// lf + tb + tb + "ArrayList<IDescrType> mgrtypes = new
// ArrayList<IDescrType>();"
//
// For ii = 1 To UBound(DOClasses)
// If DOClasses(ii) = "" Then
// Exit For
// End If
// createConstructor = createConstructor + _
// lf + tb + tb + "mgrtypes.add(d" + UCase(DOClasses(ii)) + ");"
// Next ii
//
// createConstructor = createConstructor + _
// lf + tb + tb + "addDescrTypes(" + Trim(Str(indexMgr)) + ", mgrtypes);"
//
// ' read services
// createConstructor = createConstructor + _
// lf + _
// lf + tb + tb + "// add descr read services" + _
// lf + tb + tb + "ArrayList<IDescrReadService> mgrservices = new
// ArrayList<IDescrReadService>();"
//
// For ii = startrow To rows
// If Not sheet.Cells(ii, COL_SERVICE_NAME) = "" Then
// createConstructor = createConstructor + _
// lf + tb + tb + "mgrservices.add(d" + UCase(sheet.Cells(ii, COL_SERVICE_NAME))
// + ");"
// End If
// Next ii
//
// createConstructor = createConstructor + _
// lf + tb + tb + "addDescrReadServices(" + Trim(Str(indexMgr)) + ",
// mgrservices);"
//
// createConstructor = createConstructor + _
// lf + tb + "}"
//
// End Function
