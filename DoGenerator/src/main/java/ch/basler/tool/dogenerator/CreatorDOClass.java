package ch.basler.tool.dogenerator;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.usermodel.Sheet;

public class CreatorDOClass {

	String createImports;
	String createCode;
	String createHeader;


	Workbook wbook;
	String section;
	Sheet sheet;
	String classNameDO;
	String packageDOs;
	String classNameMgr;
	String packageMgr;
	String packageInts;


	String baseClassName;
	String baseClassImport;
	int hierarchyLevel;
	String interfaceinterface;

	String uid;
	String tb;
	String lf;
	String path;
	int startrow;

	int rows;
	CreatorDOAttribute attrCreator;



	String getCellString(int row, int col) {
		CellAddress cellAdr = new CellAddress(row, col);
		return sheet.getCellComment(cellAdr).toString();
	}

	
	
	/**
	 * 
	 * @param pWbook
	 * @param pSection
	 * @param pSheet
	 * @param pClassNameDO
	 * @param pPackageDOs
	 * @param pClassNameMgr
	 * @param pPackageMgr
	 * @param pPackageInts
	 * @param imports
	 * @return
	 */
	String  createCode(Workbook pWbook, String pSection, Sheet pSheet, String pClassNameDO, String pPackageDOs,
			String pClassNameMgr, String pPackageMgr, String pPackageInts, String[] imports) {

		wbook = pWbook;
		section = pSection;
		sheet = pSheet;
		classNameDO = pClassNameDO;
		packageDOs = pPackageDOs;
		classNameMgr = pClassNameMgr;
		packageMgr = pPackageMgr;
		packageInts = pPackageInts;



		baseClassName = getCellString(Constants.ROW_CLASS_BASECLASS_NAME, Constants.COL_CLASS_VALUE);
		baseClassImport = getCellString(Constants.ROW_CLASS_BASECLASS_IMPORT, Constants.COL_CLASS_VALUE);
		hierarchyLevel = Integer
				.parseInt(getCellString(Constants.ROW_CLASS_HIERARCHY_LEVEL, Constants.COL_CLASS_VALUE));
		interfaceinterface = getCellString(Constants.ROW_CLASS_INTERFACE, Constants.COL_CLASS_VALUE);
		uid = getCellString(Constants.ROW_CLASS_UID, Constants.COL_CLASS_VALUE);

		//
		// tb = Chr(32) + Chr(32)
		// lf = Chr(13) + Chr(10)
		// path = ""
		// startrow = ROW_START_ATTRS

		tb = "   ";
		lf = "\r\n";
		path = "";
		startrow = Constants.ROW_START_ATTRS;

		//
		// rows = sheet.UsedRange.rows.count
		rows = sheet.getLastRowNum();

		//
		// Set attrCreator = New CreatorDOAttribute
		attrCreator = new CreatorDOAttribute();

		// Call attrCreator.init(sheet, tb, lf, startrow, rows)
		attrCreator.init(sheet, tb, lf, startrow, rows);

		//
		// ' -----------------------------------------------------
		// ' section 1
		if (section.equals("0") || section.equals("1")) {
			createCode += createHeader;
		}

		//
		// ' -----------------------------------------------------
		// ' section import
		if (section.equals("0") || section.equals("import")) {
			createCode += createImports(imports);
		}

		// ' -----------------------------------------------------
		// ' section 2
		if (section.equals("0") || section.equals("2")) {
			createCode += createClass();
			createCode += createEnumerator();
			createCode += createFields();
			createCode += createDescriptions();
		}

		// ' -----------------------------------------------------
		// ' section 3
       if (section.equals("0") || section.equals("3")) {
			createCode += createConstructor();
			createCode += createInit();
			createCode += createGetTypeAndMgr();
			createCode += createGetSetFunctionsGen();
			createCode += createGetSetFunctions();
		}

		// ' -----------------------------------------------------
		// ' section 4
		if (section.equals("0") || section.equals("4")) {
		   createCode = createCode + //
		            tb + "// @@Begin-4" + //
		            lf + "}" + //
		            lf + "// @@End-4";
		}
		return  createCode;
	}


	/**
	 * 
	 * @return
	 */
	String createHeader() {
		String createHeader = "//@@Begin-1" + lf
				+ "//*****************************************************************************" + //
				lf + "// GENERATED CODE!" + //
				lf + "// All code that lies within a section marked with @@Begin-. and @@End-." + //
				lf + "// has been generated." + //
				lf + "// Do not edit this code!" + //
				lf + "// To change the information represented by this code," + //
				lf + "// edit the excel file and regenerate the whole file from there." + //
				lf + "//" + //
				lf + "// INDIVIDUAL CODE!" + //
				lf + "// After marker @@End-1 import statements can be added" + //
				lf + "//  Remark: Because of the checkstyle behaviour (alphabetical sort)" + //
				lf + "//  the generated import statements lie also outside of a marked section!" + //
				lf + "//  However, they will be regenerated together with your individual ones." + //
				lf + "// After marker @@End-2 class fields can be added" + //
				lf + "// After marker @@End-3 class functions can be added" + //
				lf + "//*****************************************************************************";

		createHeader = createHeader + //
				lf + "package " + packageDOs + ";" + //
				lf;

		createHeader = createHeader + //
				lf + "//@@End-1";

		if (section == "0") {
			createHeader = createHeader + lf;
		}
		
		return createHeader;
	}
	
    /**
     * 
     * @param imports
     * @return
     */
	String createImports(String[] imports) {

		String[] importsgen = new String[50];
		Integer ii;
		Integer jj;
		Boolean isFKey;
		Boolean isDOList;
		Boolean isLang;
		Boolean isDate;
		Boolean isList;
		Boolean isCollection;
		String matched;

		// ' -----------------------------------------------------
		// ' generated imports:
		importsgen[1] = "import ch.basler.gwt.base.common.interfaces.IAttrMgr;";
		importsgen[2] = "import ch.basler.gwt.base.common.interfaces.IDescrAttr;";
		importsgen[3] = "import Ch.basler.gwt.base.common.interfaces.IDescrAttr.eDataType;";
		importsgen[4] = "import ch.basler.gwt.base.common.interfaces.IDescrAttr.eKeyType;";
		importsgen[5] = "import ch.basler.gwt.base.common.interfaces.IManager;";
		importsgen[6] = "import ch.basler.gwt.base.common.interfaces.ITypeMgr.IEType;";
		importsgen[7] = "import ch.basler.gwt.base.common.interfaces.IAttrGetSetCaller;";
		importsgen[8] = "import ch.basler.gwt.base.common.interfaces.IDataObject;";
		importsgen[9] = "import ch.basler.gwt.base.common.dataobject.AttrMgr;";
		importsgen[10] = "import ch.basler.gwt.base.common.dataobject.DescrAttr;";

		if (classNameMgr.equals("")) {
			importsgen[11] = "import ch.basler.gwt.base.common.dataobject.TypeMgr.eType;";
		} else {
			importsgen[11] = "import " + packageMgr + "." + classNameMgr + ".eType;";
		}
		jj = 12;

		// ' explicit base class
		if (!baseClassImport.equals("")) {
			importsgen[jj] = baseClassImport;
			jj = jj + 1;
		}

		// ' base class -> AbstractDataObject
		if (baseClassName.equals("")) {
			importsgen[jj] = "import ch.basler.gwt.base.common.dataobject.AbstractDataObject;";
			jj = jj + 1;
		}

		// ' interface ->
		if (!interfaceinterface.equals("")) {
			importsgen[jj] = "import " + packageInts + "." + interfaceinterface + ";";
			jj = jj + 1;
		}

		// ' attributes of type: DOxxx, IDOList, Language, Date, List,
		// Collection
		isFKey = false;
		isDOList = false;
		isLang = false;
		isDate = false;
		isList = false;
		isCollection = false;

		// For ii = startrow To rows
		for (int i = startrow; i <= rows; i++) {
			if (sheet.getCellComment(new CellAddress(i, Constants.COL_ATTR_TYPE)).toString().toUpperCase()
					.startsWith("DO")) {
				isFKey = true;
			}

			if (sheet.getCellComment(new CellAddress(i, Constants.COL_ATTR_TYPE)).toString().toUpperCase()
					.equals("IDOLIST")) {
				isDOList = true;
			}

			if (sheet.getCellComment(new CellAddress(i, Constants.COL_ATTR_TYPE)).toString().toUpperCase()
					.equals("LANGUAGE")) {
				isLang = true;
			}

			if (sheet.getCellComment(new CellAddress(i, Constants.COL_ATTR_TYPE)).toString().toUpperCase()
					.equals("DATE")) {
				isDate = true;
			}

			if (sheet.getCellComment(new CellAddress(i, Constants.COL_ATTR_TYPE)).toString().toUpperCase()
					.startsWith("LIST")) {
				isList = true;
			}

			if (sheet.getCellComment(new CellAddress(i, Constants.COL_ATTR_TYPE)).toString().toUpperCase()
					.equals("COLLECTION")) {
				isCollection = true;
			}
		}

		if (isFKey) {
			importsgen[jj] = "import ch.basler.gwt.base.common.dataobject.DescrAttrFK;";
			jj = jj + 1;
		}

		if (isDOList) {
			importsgen[jj] = "import ch.basler.gwt.base.common.interfaces.IDOList;";
			jj = jj + 1;
		}

		if (isLang) {
			importsgen[jj] = "import ch.basler.gwt.base.common.Language;";
			jj = jj + 1;
		}

		if (isDate) {
			importsgen[jj] = "import ch.basler.gwt.base.common.datatype.GwtBaseDate;";
			jj = jj + 1;
		}

		if (isList) {
			importsgen[jj] = "import java.util.List;";
			jj = jj + 1;
		}

		if (isCollection) {
			importsgen[jj] = "import java.util.Collection;";
			jj = jj + 1;
		}

		for (int i = 0; i < importsgen.length; i++) {
			if (importsgen[i] == null) {
				break;
			}

			if (i > 1) {
				createImports = createImports + lf;
			}
			createImports = createImports + importsgen[i];
		}

		//
		// ' -----------------------------------------------------
		// ' individual imports:
		for (int i = 0; i < imports.length; i++) {
			if (imports[i].equals("")) {
				break;
			}
			matched = null;

			// TODO Application.WorksheetFunction.Match(imports[ii], importsgen,
			// 0);
			// matched = Application.WorksheetFunction.Match(imports[ii],
			// importsgen, 0);

			if ((matched == null)) {
				createImports = createImports + lf + imports[i];
			}
		}

		// ' -----------------------------------------------------
		if (section.equals("0")) {
			createImports = createImports + lf;
		}
		
		return createImports;
	}


	/**
	 * 
	 * 
	 * 
	 * 
	 * @return
	 */
	String createClass() {
		String createClass;
		String base;

		createClass = lf + //
				"// @@Begin-2" //
				+ lf //
				+ lf + "/**" //
				+ lf + " * " //
				+ sheet.getCellComment(new CellAddress(Constants.ROW_CLASS_NAME, Constants.COL_CLASS_DESCR));

		if (!(sheet.getCellComment(new CellAddress(Constants.ROW_CLASS_BASECLASS_NAME, Constants.COL_CLASS_DESCR))
				.toString().equals(""))) {
			createClass = createClass + " <br>" + lf + " * " //
					+ sheet.getCellComment(
							new CellAddress(Constants.ROW_CLASS_BASECLASS_NAME, Constants.COL_CLASS_DESCR));
		}

		createClass = createClass + lf + " */";

		base = "AbstractDataObject";

		if (!(baseClassName.equals(""))) {
			base = baseClassName;
		}

		createClass = createClass + lf + "public class " + classNameDO + " extends " + base;

		if (!(interfaceinterface.equals(""))) {
			createClass = createClass + " implements " + interfaceinterface;
		}

		createClass = createClass + //
				" {" + //
				lf + tb + "private static final long serialVersionUID = " + uid + ";" + //
				lf;

		return createClass;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return
	 */
	String createEnumerator() {
		String createEnumerator;
		int ii;
		int jj;
		boolean isFirst;
		int level;
		String baseClass;

		createEnumerator = (lf + (tb + "public enum eAttr implements IEAttr {"));

		isFirst = true;
		jj = 0;

		for (ii = startrow; (ii <= rows); ii++) {
			if (!(sheet.getCellComment(new CellAddress(ii, Constants.COL_ATTR_NAME)).toString().equals(""))) {
				if (isFirst) {
					isFirst = false;
				} else {
					createEnumerator = (createEnumerator + ",");
				}

				createEnumerator = (createEnumerator + attrCreator.createEnumeratorEntry(ii, jj));
				jj = (jj + 1);
			}

		}

		createEnumerator = (createEnumerator + ";");

		createEnumerator = createEnumerator + //
				lf + //
				lf + tb + tb + "private int index;" + //
				lf + //
				lf + tb + tb + "eAttr(int index) {" + //
				lf + tb + tb + tb + "this.index = index;" + //
				lf + tb + tb + "}" + //
				lf + //
				lf + tb + tb + "public int index() {" + //
				lf + tb + tb + tb + "return index;" + //
				lf + tb + tb + "}" + //
				lf;

		baseClass = baseClassName;
		if (hierarchyLevel > 0) {
			level = hierarchyLevel;
		} else {
			level = getLevel(baseClass, 0);
		}

		createEnumerator = createEnumerator + //
				lf + tb + tb + "public int level() {" + //
				lf + tb + tb + tb + "return " + Integer.toString(level) + ";" + //
				lf + tb + tb + "}" + //
				lf + tb + "}" + //
				lf;

		return createEnumerator;

	}

	/**
	 * 
	 * @param baseClass
	 * @param level
	 * @return
	 */
	int getLevel(String baseClass, int level) {

		Sheet wsheet = null;

		int getLevel = level;

		if (baseClass.equals("")) {
			getLevel = level;
			// TODO: Exit Function: Warning!!! Need to return the value
			return getLevel;
		}

		// get sheet for base class

		int sheetCount = 0;
		for (Sheet sheet : wbook) {
			sheetCount++;
			wsheet = sheet;

			if (sheetCount >= Constants.SHEET_START_DOS) {
				if (wsheet.getCellComment(new CellAddress(Constants.ROW_CLASS_NAME, Constants.COL_CLASS_VALUE))
						.toString().equals(baseClass)) {
					break;
				}
			}
		}

		String baseClass2 = (wsheet
				.getCellComment(new CellAddress(Constants.ROW_CLASS_BASECLASS_NAME, Constants.COL_CLASS_VALUE))
				.toString());
		getLevel = getLevel + 1;

		// Rekursiver Aufruff ????????????????????
		getLevel = getLevel(baseClass2, level);
		return getLevel;

	}

	/**
	 * 
	 * @return
	 */
	String createFields() {
		String createFields;
		int ii;
		createFields = "";
		for (ii = startrow; (ii <= rows); ii++) {
			if (!(sheet.getCellComment(new CellAddress(ii, Constants.COL_ATTR_NAME)).toString().equals(""))) {
				createFields = (createFields + attrCreator.createField(ii));
			}
		}
		createFields = (createFields + lf);
		return createFields;
	}

	/**
	 * 
	 * @return
	 */
	String createDescriptions() {
		String createDescriptions;
		int ii;
		String baseMgr = "null";

		if (!(baseClassName.equals(""))) {
			baseMgr = (baseClassName + ".ATTRMGR");
		}

		// type + attr mgr
		createDescriptions = //
				lf + tb + "// ------------------------------------------------------------------------" + //
						lf + //
						lf + tb + "@SuppressWarnings(\"hiding\")" + //
						lf + tb + "static public final eType TYPE = eType." + classNameDO.toUpperCase() + ";" + //
						lf + tb + "@SuppressWarnings(\"hiding\")" + //
						lf + tb + "static public final AttrMgr ATTRMGR = new AttrMgr(TYPE, " + baseMgr + ");" + //
						lf + //
						lf + tb + "// ------------------------------------------------------------------------" + //
						lf;

		// attr descriptions
		for (ii = startrow; ii <= rows; ii++) {
			if (!(sheet.getCellComment(new CellAddress(ii, Constants.COL_ATTR_NAME)).toString().equals(""))) {
				createDescriptions = (createDescriptions + attrCreator.createDescription(ii));
			}
		}

		createDescriptions = createDescriptions + lf + tb + "// @@End-2";

		if (section.equals("0")) {
			createDescriptions = (createDescriptions + lf);
		}

		return createDescriptions;
	}

	/**
	 * 
	 * @return
	 */
	String createConstructor() {
		String createConstructor = //
				lf + tb + "// @@Begin-3" + //
						lf + tb + "// -------------------------------------------------------------------------" + //
						lf + tb + "/**" + //
						lf + tb + " * constructor" + //
						lf + tb + " */" + //
						lf + tb + "public " + classNameDO + "() {" + //
						lf + tb + tb + "this(null);" + //
						lf + tb + "}" + //
						lf + //
						lf + tb + "/**" + //
						lf + tb + " * constructor with manager" + //
						lf + tb + " */" + //
						lf + tb + "public " + classNameDO + "(IManager mgr) {" + //
						lf + tb + tb + "super(mgr);" + //
						lf + tb + tb + "initAttrs();" + //
						lf + tb + "}" + //
						lf;
		return createConstructor;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	String createInit() {
		String createInit;
		int ii;
		createInit = //
				lf + tb + "/**" + //
						lf + tb + " * Initial values." + //
						lf + tb + " */" + //
						lf + tb + "private void initAttrs() {" + //
						lf + tb + tb + "// attributes to be initialized";

		for (ii = startrow; ii <= rows; ii++) {
			if (!(sheet.getCellComment(new CellAddress(ii, Constants.COL_ATTR_NAME)).toString().equals(""))) {
				createInit = (createInit + attrCreator.createInitValue(ii, false));
			}
		}

		createInit = createInit + //
				lf + tb + "}" + //
				lf;

		// initial values for empty form
		createInit = createInit + //
				lf + tb + "/**" + //
				lf + tb + " * Initial values for empty form." + //
				lf + tb + " */" + //
				lf + tb + "@Override" + //
				lf + tb + "public void initAttrsEForm() {";

		if (!(baseClassName == "")) {
			createInit = createInit + //
					lf + tb + tb + "super.initAttrsEForm();";
		}

		for (ii = startrow; (ii <= rows); ii++) {
			if (!(sheet.getCellComment(new CellAddress(ii, Constants.COL_ATTR_NAME)).toString().equals(""))) {
				createInit = (createInit + attrCreator.createInitValue(ii, true));
			}

		}

		createInit = createInit + //
				lf + tb + "}" + //
				lf;

		return createInit;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	String createGetTypeAndMgr() {
		String createGetTypeAndMgr;

		int ii;
		createGetTypeAndMgr = //
				lf + tb + "// -------------------------------------------------------------------------" + //
						lf + tb + "// type / attr mgr" + //
						lf + tb + "// -------------------------------------------------------------------------" + //
						lf + //
						lf + tb + "/**" + //
						lf + tb + " * Returns DO TYPE." + //
						lf + tb + " */" + //
						lf + tb + "@Override" + //
						lf + tb + "public IEType getType() {" + //
						lf + tb + tb + "return TYPE;" + //
						lf + tb + "}" + //
						lf + //
						lf + tb + "/**" + //
						lf + tb + " * Returns Attribute manager -> allows access to all DescrAttr objects of DO." + //
						lf + tb + " */" + //
						lf + tb + "@Override" + //
						lf + tb + "public IAttrMgr getAttrMgr() {" + //
						lf + tb + tb + "return ATTRMGR;" + //
						lf + tb + "}" + //
						lf;
		return createGetTypeAndMgr;
	}

	/**
	 * 
	 * @return
	 */
	String createGetSetFunctionsGen() {

		String createGetSetFunctionsGen;
		int ii;
		String tb2;
		boolean isFirst;
		tb2 = tb;

		if (!(baseClassName.equals(""))) {
			tb2 = (tb + tb);
		}

		createGetSetFunctionsGen = //
				lf + tb + "// -------------------------------------------------------------------------" //
						+ lf + tb + "// get/set (generic)" + lf + tb
						+ "// -------------------------------------------------------------------------";

		createGetSetFunctionsGen = createGetSetFunctionsGen + //
				lf + tb + "/**" + //
				lf + tb + " * Allows to get DO attribute value via related DescrAttr object." + //
				lf + tb + " */" + //
				lf + tb + "@Override" + //
				lf + tb + "public Object get(IDescrAttr attr) {";

		if (!(baseClassName.equals(""))) {
			createGetSetFunctionsGen = createGetSetFunctionsGen + lf + tb + tb + "if (attr.getId() instanceof eAttr) {";
		}

		createGetSetFunctionsGen = createGetSetFunctionsGen + lf + tb + tb2 + "eAttr attrib = (eAttr)attr.getId();";

		isFirst = true;

		for (ii = startrow; ii <= rows; ii++) {
			if (!(sheet.getCellComment(new CellAddress(ii, Constants.COL_ATTR_NAME)).toString().equals(""))) {
				createGetSetFunctionsGen = (createGetSetFunctionsGen
						+ attrCreator.createGetFunctionGen(ii, isFirst, tb2));
				isFirst = false;
			}

		}

		if (!(baseClassName == "")) {
			createGetSetFunctionsGen = (createGetSetFunctionsGen + lf + tb + tb + "}" + lf + tb + tb + "else" + lf + tb
					+ tb + "{" + lf + tb + tb + tb + "return super.get(attr);" + lf + tb + tb + "}");
		}

		createGetSetFunctionsGen = createGetSetFunctionsGen + lf + tb + tb + "return null;" + lf + tb + "}";

		createGetSetFunctionsGen = createGetSetFunctionsGen + //
				lf + tb + "/**" + //
				lf + tb + " * Allows to set DO attribute value via related DescrAttr object." + //
				lf + tb + " */" + //
				lf + tb + "@Override" + //
				lf + tb + "public void set(IDescrAttr attr, Object value) {";

		createGetSetFunctionsGen = createGetSetFunctionsGen + //
				lf + tb + tb + "super.set(attr, value);" + lf;

		createGetSetFunctionsGen = createGetSetFunctionsGen + //
				lf + tb + tb + "if (attr.getId() instanceof eAttr) {";

		createGetSetFunctionsGen = createGetSetFunctionsGen + //
				lf + tb + tb + tb + "eAttr attrib = (eAttr)attr.getId();";

		isFirst = true;

		for (ii = startrow; ii <= rows; ii++) {

			if (!(sheet.getCellComment(new CellAddress(ii, Constants.COL_ATTR_NAME)).toString().equals(""))) {
				// transient off ->
				if (!(sheet.getCellComment(new CellAddress(ii, Constants.COL_ATTR_TRANSIENT)).toString()
						.equals("true"))) {
					createGetSetFunctionsGen = (createGetSetFunctionsGen
							+ attrCreator.createSetFunctionGen(ii, isFirst, (tb + tb)));
					isFirst = false;
				}

			}

		}

		createGetSetFunctionsGen = (createGetSetFunctionsGen + (lf + (tb + (tb + "}"))));
		createGetSetFunctionsGen = (createGetSetFunctionsGen + (lf + (tb + ("}" + lf))));

		return createGetSetFunctionsGen;
	}

	/**
	 * 
	 * @return
	 */
	String createGetSetFunctions() {
		String createGetSetFunctions;
		int ii;
		createGetSetFunctions = lf + tb + "// -------------------------------------------------------------------------"
				+ lf + tb + "// get/set" + lf + tb
				+ "// -------------------------------------------------------------------------";
		for (ii = startrow; (ii <= rows); ii++) {
			if (!(sheet.getCellComment(new CellAddress(ii, Constants.COL_ATTR_NAME)).toString().equals(""))) {
				createGetSetFunctions = createGetSetFunctions + attrCreator.createGetSetFunction(ii);
			}
		}
		createGetSetFunctions = createGetSetFunctions + lf + tb + "// @@End-3";
		if ((section == "0")) {
			createGetSetFunctions = (createGetSetFunctions + lf);
		}
		return createGetSetFunctions;
	}

	/**
	 * 
	 * @param attr
	 * @return
	 */
	String getAttrName(String attr) {
		return (attr.substring(0, 1).toLowerCase() + attr.substring(1));
	}

}
