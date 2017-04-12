package ch.basler.tool.dogenerator;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

public class CreatorTypeMgrClass {

	// TODO: Option Explicit ... Warning!!! not translated

	HSSFWorkbook wbook;
	HSSFSheet sheet;

	String classNameMgr;
	String packageMgr;
	String packageDOs;
	int indexMgr;
	String tb;
	String lf;
	int startrow;
	int rows;

	String[] DOClasses = new String[100];
	String[] BOClasses = new String[100];
	String[] KeyCreationTypes = new String[100];

	// Function createCode(pWbook As Workbook, pClassNameMgr As String, _
	// pPackageMgr As String, pPackageDOs As String)
	final String createCode(HSSFWorkbook pWbook, String pClassNameMgr, String pPackageMgr, String pPackageDOs) {

		String createCode = "";

		// Dim ii As Integer
		int ii;

		// Dim wsheet As Worksheet
		HSSFSheet wsheet;

		// Set wbook = pWbook
		wbook = pWbook;

		// Set sheet = wbook.Worksheets(SHEET_TYPE_MGR)
		wbook.getSheetAt(Constants.SHEET_TYPE_MGR);

		// classNameMgr = pClassNameMgr
		classNameMgr = pClassNameMgr;

		// packageMgr = pPackageMgr
		packageMgr = pPackageMgr;

		// packageDOs = pPackageDOs
		packageDOs = pPackageDOs;

		// indexMgr = sheet.Cells(ROW_CLASS_MGR, COL_INDEX_MGR)

		indexMgr = Integer.parseInt(read(sheet, Constants.ROW_CLASS_MGR, Constants.COL_INDEX_MGR));

		// tb = Chr(32) + Chr(32)
		// lf = Chr(13) + Chr(10)

		// startrow = ROW_START_SERVICES
		// rows = sheet.UsedRange.rows.count
		startrow = Constants.ROW_START_SERVICES;
		// rows = sheet.UsedRange.rows.count;
		rows = 7;

		// collect do types
		// ii = 0
		ii = 0;

		// For Each wsheet In wbook.Worksheets
		for (Iterator iterator = wbook.iterator(); iterator.hasNext();) {
			wsheet = (HSSFSheet) iterator.next();

			// If wsheet.index >= SHEET_START_DOS Then
			// ii = ii + 1
			// DOClasses[ii] = wsheet.Cells(ROW_CLASS_NAME, COL_CLASS_VALUE)
			// BOClasses(ii) = wsheet.Cells(ROW_CLASS_BO, COL_CLASS_VALUE)
			// KeyCreationTypes(ii) = wsheet.Cells(ROW_CLASS_KEY_CREATION_TYPE,
			// COL_CLASS_VALUE)
			// End If

			if ((1 >= Constants.SHEET_START_DOS)) {
				ii = (ii + 1);
				DOClasses[ii] = read(wsheet, Constants.ROW_CLASS_NAME, Constants.COL_CLASS_VALUE);
				BOClasses[ii] = read(wsheet, Constants.ROW_CLASS_BO, Constants.COL_CLASS_VALUE);
				KeyCreationTypes[ii] = read(wsheet, Constants.ROW_CLASS_KEY_CREATION_TYPE, Constants.COL_CLASS_VALUE);
			}

		}

		// comment, package, imports
		createCode = (createCode + createHeader());
		// class def
		createCode = (createCode + createClass());
		// enumerator -> do types
		createCode = (createCode + createEnumDOTypes());
		// enumerator -> read services
		createCode = (createCode + createEnumReadServices());
		// descr types
		createCode = (createCode + createDescrTypes());
		// descr read services
		createCode = (createCode + createDescrReadServices());
		// constructor
		createCode = (createCode + createConstructor());
		// end
		return (createCode + (lf + "}"));
	}

	// Function createHeader()
	final String createHeader() {

		String createHeader = "";

		// Dim ii As Integer
		int ii;

		// ' comment
		// createHeader = _
		// "//*****************************************************************************"
		// + _
		// lf + "// GENERATED CODE!" + _
		// lf + "// This code has been generated via an excel file thru a vba
		// macro." +
		// _
		// lf + "// Do not edit this code!" + _
		// lf + "// To change the information represented by this code," + _
		// lf + "// edit the related excel file and regenerate the whole file
		// from
		// there." + _
		// lf +
		// "//*****************************************************************************"
		//
		createHeader = ("//*****************************************************************************" + (lf
				+ ("// GENERATED CODE!" + (lf + ("// This code has been generated via an excel file thru a vba macro."
						+ (lf + ("// Do not edit this code!"
								+ (lf + ("// To change the information represented by this code,"
										+ (lf + ("// edit the related excel file and regenerate the whole file from there."
												+ (lf + "//*****************************************************************************"))))))))))));

		// ' package
		// createHeader = createHeader + _
		// lf + "package " + packageMgr + ";" + _
		// lf
		createHeader = (createHeader + (lf + ("package " + (packageMgr + (";" + lf)))));

		// ' imports
		// createHeader = createHeader + _
		// lf + "import java.util.ArrayList;" + _
		// lf + "import ch.basler.gwt.base.common.interfaces.IManager;" + _
		// lf + "import ch.basler.gwt.base.common.interfaces.IDataObject;" + _
		// lf + "import ch.basler.gwt.base.common.interfaces.IDOFactory;" + _
		// lf + "import ch.basler.gwt.base.common.interfaces.IDescrType;" + _
		// lf + "import
		// ch.basler.gwt.base.common.interfaces.IDescrType.eKeyCreationType;" +
		// _
		// lf + "import ch.basler.gwt.base.common.interfaces.IDescrReadService;"
		// + _
		// lf + "import ch.basler.gwt.base.common.dataobject.TypeMgr;" + _
		// lf + "import ch.basler.gwt.base.common.dataobject.DescrType;" + _
		// lf + "import ch.basler.gwt.base.common.dataobject.DescrReadService;"
		// 'lf + "import ch.basler.gwt.base.common.interfaces.IBusinessObject;"
		// +
		// 'lf + "import ch.basler.gwt.base.common.interfaces.IBOFactory;" +
		//
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

		// For ii = 1 To UBound(DOClasses)
		for (ii = 1; (ii <= DOClasses.length); ii++) {

			// If DOClasses[ii] = "" Then
			// Exit For
			// End If
			if ((DOClasses[ii] == "")) {
				break;
			}

			// createHeader = createHeader + _
			// lf + "import " + packageDOs + "." + DOClasses[ii] + ";"
			// 'If Not BOClasses(ii) = "" Then
			// ' createHeader = createHeader + _
			// ' lf + "import " + packageDOs + "." + BOClasses(ii) + ";"
			// 'End If
			createHeader = (createHeader + (lf + ("import " + (packageDOs + ("." + (DOClasses[ii] + ";"))))));

			// Next
		}

		return createHeader;
		// End Function
	}

	// Function createClass()
	//
	// createClass = _
	// lf + _
	// lf + "//
	// ============================================================================="
	// + _
	// lf + "/**" + _
	// lf + " * The class <code>" + classNameMgr + "</code> creates and holds
	// meta
	// info for all DO types (DescrType) and " + _
	// lf + " * all read services (DescrReadService) used within the
	// application." +
	// _
	// lf + " */" + _
	// lf + "public class " + classNameMgr + " extends TypeMgr {"
	//
	// End Function
	final String createClass() {
		String createClass = "";

		createClass = (lf + (lf + ("// ============================================================================="
				+ (lf + ("/**" + (lf + (" * The class <code>"
						+ (classNameMgr + ("</code> creates and holds meta info for all DO types (DescrType) and " + (lf
								+ (" * all read services (DescrReadService) used within the application." + (lf + (" */"
										+ (lf + ("public class " + (classNameMgr + " extends TypeMgr {"))))))))))))))));
		return createClass;

	}

	// Function createEnumDOTypes()
	final String createEnumDOTypes() {
		String createEnumDOTypes = "";

		// Dim ii As Integer
		// Dim sign As String
		int ii;
		String sign;

		// createEnumDOTypes = _
		// lf + tb + "public enum eType implements IEType {"
		createEnumDOTypes = (lf + (tb + "public enum eType implements IEType {"));

		// For ii = 1 To UBound(DOClasses)
		for (ii = 1; (ii <= DOClasses.length); ii++) {

			// If DOClasses[ii] = "" Then
			// Exit For
			// End If
			if ((DOClasses[ii] == "")) {
				break;
			}

			// If ii > 1 Then
			// createEnumDOTypes = createEnumDOTypes + ","
			// End If
			if ((ii > 1)) {
				createEnumDOTypes = (createEnumDOTypes + ",");
			}

			// createEnumDOTypes = createEnumDOTypes + _
			// lf + tb + tb + UCase(DOClasses[ii]) + _
			// " (" + Trim(Str(ii - 1)) + ")"
			createEnumDOTypes = (createEnumDOTypes
					+ (lf + (tb + (tb + (DOClasses[ii].toUpperCase() + (" (" + (Integer.toString(ii - 1) + ")")))))));

			// Next ii
		}

		// createEnumDOTypes = createEnumDOTypes + ";"
		createEnumDOTypes = (createEnumDOTypes + ";");

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
		return (createEnumDOTypes + (lf + (lf + (tb + (tb + ("private int index;" + (lf + (lf + (tb + (tb
				+ ("eType (int index) {" + (lf + (tb + (tb + (tb + ("this.index = index;" + (lf + (tb + (tb + ("}" + (lf
						+ (lf + (tb + (tb + ("public int index() {" + (lf + (tb + (tb + (tb + ("return index;" + (lf
								+ (tb + (tb + ("}" + (lf + (lf + (tb + (tb + ("public int mgr() {"
										+ (lf + (tb + (tb + (tb + ("return " + (Integer.toString(indexMgr)
												+ (";" + (lf + (tb + (tb + ("}" + (lf + (tb + ("}"
														+ lf)))))))))))))))))))))))))))))))))))))))))))))))))))));

		// End Function
	}

	// Function createEnumReadServices()
	final String createEnumReadServices() {

		String createEnumReadServices = "";

		// Dim ii As Integer
		// Dim sign As String
		int ii;
		String sign;

		// createEnumReadServices = _
		// lf + tb + "//
		// ---------------------------------------------------------------------------"
		// + _
		// lf + _
		// lf + tb + "public enum eReadService implements IEReadService {"
		//
		createEnumReadServices = (lf
				+ (tb + ("// ---------------------------------------------------------------------------"
						+ (lf + (lf + (tb + "public enum eReadService implements IEReadService {"))))));

		// For ii = startrow To rows
		for (ii = startrow; (ii <= rows); ii++) {

			// sign = ","
			// If ii = rows Then
			// sign = ";"
			sign = ",";
			if ((ii == rows)) {
				sign = ";";
				// End If
			}

			// createEnumReadServices = createEnumReadServices + _
			// lf + tb + tb + UCase(sheet.Cells(ii, COL_SERVICE_NAME)) + _
			// " (" + Trim(Str(ii - startrow)) + ")" + sign

			createEnumReadServices = (createEnumReadServices
					+ (lf + (tb + (tb + (read(sheet, ii, Constants.COL_SERVICE_NAME).toUpperCase()
							+ (" (" + (Integer.toString(ii - startrow) + (")" + sign))))))));

			// Next ii
		}

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
		return (createEnumReadServices + (lf + (lf + (tb + (tb + ("private int index;" + (lf + (lf + (tb + (tb
				+ ("eReadService (int index) {" + (lf + (tb + (tb + (tb + ("this.index = index;" + (lf + (tb + (tb
						+ ("}" + (lf + (lf + (tb + (tb + ("public int index() {" + (lf + (tb + (tb + (tb
								+ ("return index;" + (lf + (tb + (tb + ("}" + (lf + (lf + (tb + (tb
										+ ("public int mgr() {" + (lf + (tb + (tb + (tb + ("return " + (Integer
												.toString(indexMgr) + (";" + (lf + (tb + (tb + ("}" + (lf + (tb + ("}"
														+ lf)))))))))))))))))))))))))))))))))))))))))))))))))))));

		// End Function
	}

	// Function createDescrTypes()
	final String createDescrTypes() {

		String createDescrTypes = "";

		// Dim ii As Integer
		// Dim jj As Integer
		// Dim isFirst As Boolean
		// Dim sign As String
		// Dim kct As String
		int ii;
		int jj;
		boolean isFirst;
		String sign;
		String kct;

		// createDescrTypes = _
		// lf + tb + "//
		// ---------------------------------------------------------------------------"
		// + _
		// lf + tb + "// descr types" + _
		// lf
		createDescrTypes = (lf + (tb + ("// ---------------------------------------------------------------------------"
				+ (lf + (tb + ("// descr types" + lf))))));

		// For ii = 1 To UBound(DOClasses)
		// If DOClasses[ii] = "" Then
		// Exit For
		// End If
		for (ii = 1; (ii <= DOClasses.length); ii++) {
			if ((DOClasses[ii] == "")) {
				break;
			}

			// kct = KeyCreationTypes(ii)
			// If kct = "" Then
			// kct = "KeyOnPersist"
			// End If
			kct = KeyCreationTypes[ii];
			if ((kct == "")) {
				kct = "KeyOnPersist";
			}

			// ' attr mgr, do class, do creation
			// createDescrTypes = createDescrTypes + _
			// lf + tb + "public static final DescrType d" +
			// UCase(DOClasses[ii]) + " = new
			// DescrType(" + _
			// lf + tb + tb + "eType." + UCase(DOClasses[ii]) + "," + _
			// lf + tb + tb + DOClasses[ii] + ".ATTRMGR," + _
			// lf + tb + tb + "eKeyCreationType." + kct + "," + _
			// lf + tb + tb + DOClasses[ii] + ".class," + _
			// lf + tb + tb + "new IDOFactory() {" + _
			// lf + tb + tb + tb + "public IDataObject createDO(IManager mgr) {"
			// + _
			// lf + tb + tb + tb + tb + "return new " + DOClasses[ii] + "(mgr);"
			// + _
			// lf + tb + tb + tb + "}" + _
			// lf + tb + tb + "},"
			createDescrTypes = (createDescrTypes + (lf + (tb + ("public static final DescrType d" + (DOClasses[ii]
					.toUpperCase() + (" = new DescrType(" + (lf + (tb + (tb + ("eType." + (DOClasses[ii].toUpperCase()
							+ ("," + (lf + (tb + (tb + (DOClasses[ii] + (".ATTRMGR," + (lf + (tb + (tb
									+ ("eKeyCreationType." + (kct + ("," + (lf + (tb + (tb + (DOClasses[ii] + (".class,"
											+ (lf + (tb + (tb + ("new IDOFactory() {" + (lf + (tb + (tb + (tb
													+ ("public IDataObject createDO(IManager mgr) {" + (lf + (tb + (tb
															+ (tb + (tb + ("return new " + (DOClasses[ii] + ("(mgr);"
																	+ (lf + (tb + (tb + (tb + ("}" + (lf + (tb + (tb
																			+ "},")))))))))))))))))))))))))))))))))))))))))))))))))))));

			//
			// ' bo -> bo creation
			// 'If Not BOClasses(ii) = "" Then
			// ' createDescrTypes = createDescrTypes + _
			// ' lf + tb + tb + "new IBOFactory()" + _
			// ' lf + tb + tb + "{" + _
			// ' lf + tb + tb + tb + "public IBusinessObject createBO(IManager
			// mgr) { return
			// new " + BOClasses(ii) + "(mgr); }" + _
			// ' lf + tb + tb + "},"
			// 'Else

			// createDescrTypes = createDescrTypes + _
			// lf + tb + tb + "null,"
			// 'End If
			createDescrTypes = (createDescrTypes + (lf + (tb + (tb + "null,"))));

			// ' read service(s)
			// isFirst = True
			// sign = ""
			isFirst = true;
			sign = "";

			// For jj = startrow To rows
			for (jj = startrow; (jj <= rows); jj++) {

				// If sheet.Cells(jj, COL_START_DOS) = DOClasses[ii] Then
				if ((read(sheet, jj, Constants.COL_START_DOS) == DOClasses[ii])) {

					// If isFirst Then
					// isFirst = False
					// createDescrTypes = createDescrTypes + _
					// lf + tb + tb + "new eReadService[] { "
					// End If
					if (isFirst) {
						isFirst = false;
						createDescrTypes = (createDescrTypes + (lf + (tb + (tb + "new eReadService[] { "))));
					}

					// createDescrTypes = createDescrTypes + sign +
					// "eReadService." +
					// UCase(sheet.Cells(jj, COL_SERVICE_NAME))
					// sign = ", "
					createDescrTypes = (createDescrTypes
							+ (sign + ("eReadService." + read(sheet, jj, Constants.COL_SERVICE_NAME).toUpperCase())));
					sign = ", ";

					// End If
				}

				// Next jj
			}

			// If isFirst Then
			// createDescrTypes = createDescrTypes + _
			// lf + tb + tb + "null);"
			// Else
			// createDescrTypes = createDescrTypes + " });"
			// End If
			if (isFirst) {
				createDescrTypes = (createDescrTypes + (lf + (tb + (tb + "null);"))));
			} else {
				createDescrTypes = (createDescrTypes + " });");
			}

			// createDescrTypes = createDescrTypes + lf
			createDescrTypes = (createDescrTypes + lf);

			// Next ii
		}

		// End Function
		return createDescrTypes;
	}

	// Function createDescrReadServices()
	final String createDescrReadServices() {

		String createDescrReadServices = "";

		// Dim ii As Integer
		// Dim jj As Integer
		// Dim isFirst As Boolean
		// Dim sign As String
		int ii;
		int jj;
		boolean isFirst;
		String sign;

		// createDescrReadServices = _
		// lf + tb + "//
		// ---------------------------------------------------------------------------"
		// + _
		// lf + tb + "// descr read services" + _
		// lf
		createDescrReadServices = (lf
				+ (tb + ("// ---------------------------------------------------------------------------"
						+ (lf + (tb + ("// descr read services" + lf))))));

		// For ii = startrow To rows
		for (ii = startrow; (ii <= rows); ii++) {

			// If Not sheet.Cells(ii, COL_SERVICE_NAME) = "" Then
			if (!(read(sheet, ii, Constants.COL_SERVICE_NAME).equals(""))) {

				// createDescrReadServices = createDescrReadServices + _
				// lf + tb + "public static final DescrReadService d" +
				// UCase(sheet.Cells(ii,
				// COL_SERVICE_NAME)) + " = new DescrReadService(" + _
				// "eReadService." + UCase(sheet.Cells(ii, COL_SERVICE_NAME)) +
				// ","

				createDescrReadServices = (createDescrReadServices + (lf + (tb
						+ ("public static final DescrReadService d" + (read(sheet, ii, Constants.COL_SERVICE_NAME)
								.toUpperCase() + (" = new DescrReadService(" + ("eReadService."
										+ (read(sheet, ii, Constants.COL_SERVICE_NAME).toUpperCase() + ","))))))));

				// ' do type(s)
				// isFirst = True
				// sign = ""
				isFirst = true;
				sign = "";

				// For jj = COL_START_DOS To 100
				// If sheet.Cells(ii, jj) = "" Then
				// Exit For
				// End If
				// If isFirst Then
				// isFirst = False
				// createDescrReadServices = createDescrReadServices + _
				// lf + tb + tb + "new eType[] {"
				// End If
				// createDescrReadServices = createDescrReadServices + sign +
				// "eType." +
				// UCase(sheet.Cells(ii, jj))
				// sign = ", "
				// Next jj
				for (jj = Constants.COL_START_DOS; (jj <= 100); jj++) {
					if ((read(sheet, ii, jj) == "")) {
						break;
					}

					if (isFirst) {
						isFirst = false;
						createDescrReadServices = (createDescrReadServices + (lf + (tb + (tb + "new eType[] {"))));
					}

					createDescrReadServices = (createDescrReadServices
							+ (sign + ("eType." + read(sheet, ii, jj).toUpperCase())));
					sign = ", ";
				}

				// If isFirst Then
				// createDescrReadServices = createDescrReadServices + "null);"
				// Else
				// createDescrReadServices = createDescrReadServices + "});"
				// End If
				if (isFirst) {
					createDescrReadServices = (createDescrReadServices + "null);");
				} else {
					createDescrReadServices = (createDescrReadServices + "});");
				}

				// End If
			}

			// createDescrReadServices = createDescrReadServices + lf
			createDescrReadServices = (createDescrReadServices + lf);

			// Next ii
		}

		//

		return createDescrReadServices;
		// End Function

	}

	// Function createConstructor()
	//
	// Dim ii As Integer
	//
	final String createConstructor() {

		String createConstructor = "";

		int ii;

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

		createConstructor = (lf
				+ (tb + ("// ---------------------------------------------------------------------------"
						+ (lf + (lf + (tb + ("public " + (classNameMgr + "() {"))))))));
		createConstructor = (createConstructor + (lf + (tb + (tb + ("// add descr types"
				+ (lf + (tb + (tb + "ArrayList<IDescrType> mgrtypes = new ArrayList<IDescrType>();"))))))));

		// For ii = 1 To UBound(DOClasses)
		// If DOClasses[ii] = "" Then
		// Exit For
		// End If
		// createConstructor = createConstructor + _
		// lf + tb + tb + "mgrtypes.add(d" + UCase(DOClasses[ii]) + ");"
		// Next ii
		//
		for (ii = 1; (ii <= DOClasses.length); ii++) {
			if ((DOClasses[ii] == "")) {
				break;
			}

			createConstructor = (createConstructor
					+ (lf + (tb + (tb + ("mgrtypes.add(d" + (DOClasses[ii].toUpperCase() + ");"))))));
		}

		// createConstructor = createConstructor + _
		// lf + tb + tb + "addDescrTypes(" + Trim(Str(indexMgr)) + ",
		// mgrtypes);"
		createConstructor = (createConstructor
				+ (lf + (tb + (tb + ("addDescrTypes(" + (Integer.toString(indexMgr) + ", mgrtypes);"))))));

		//
		// ' read services
		// createConstructor = createConstructor + _
		// lf + _
		// lf + tb + tb + "// add descr read services" + _
		// lf + tb + tb + "ArrayList<IDescrReadService> mgrservices = new
		// ArrayList<IDescrReadService>();"
		createConstructor = (createConstructor + (lf + (lf + (tb + (tb + ("// add descr read services" + (lf
				+ (tb + (tb + "ArrayList<IDescrReadService> mgrservices = new ArrayList<IDescrReadService>();")))))))));

		// For ii = startrow To rows
		// If Not sheet.Cells(ii, COL_SERVICE_NAME) = "" Then
		// createConstructor = createConstructor + _
		// lf + tb + tb + "mgrservices.add(d" + UCase(sheet.Cells(ii,
		// COL_SERVICE_NAME))
		// + ");"
		// End If
		// Next ii
		for (ii = startrow; (ii <= rows); ii++) {
			if (!(read(sheet, ii, Constants.COL_SERVICE_NAME) == "")) {
				createConstructor = (createConstructor + (lf + (tb + (tb + ("mgrservices.add(d"
						+ (read(sheet, ii, Constants.COL_SERVICE_NAME).toUpperCase() + ");"))))));
			}

		}

		// createConstructor = createConstructor + _
		// lf + tb + tb + "addDescrReadServices(" + Trim(Str(indexMgr)) + ",
		// mgrservices);"
		createConstructor = (createConstructor
				+ (lf + (tb + (tb + ("addDescrReadServices(" + (Integer.toString(indexMgr) + ", mgrservices);"))))));

		// createConstructor = createConstructor + _
		// lf + tb + "}"
		return (createConstructor + (lf + (tb + "}")));

		// End Function
	}

	static String read(Sheet sheet, int row, int col) {
		return Tool.read(sheet, row, col);
	}
}
