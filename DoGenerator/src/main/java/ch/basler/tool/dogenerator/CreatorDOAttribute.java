package ch.basler.tool.dogenerator;

import org.apache.poi.ss.usermodel.Sheet;

public class CreatorDOAttribute {

	String section;
	Sheet sheet;
	String className;
	String tb;
	String lf;
	String path;
	int startrow;
	int rows;

	/**
	 * 
	 * @param pSheet
	 * @param pTb
	 * @param pLf
	 * @param pStartrow
	 * @param pRows
	 */
	void init(Sheet pSheet, String pTb, String pLf, int pStartrow, int pRows) {
		sheet = pSheet;
		tb = pTb;
		lf = pLf;
		startrow = pStartrow;
		rows = pRows;
	}

	/**
	 * 
	 * @param row
	 * @param index
	 * @return
	 */
	String createEnumeratorEntry(int row, int index) {

		return lf + tb + tb + Tool.read(sheet, row, Constants.COL_ATTR_NAME).toUpperCase() + //
				" (" + Integer.toString(index) + ")";
	}

	static String read(Sheet sheet, int row, int col) {
		return Tool.read(sheet, row, col);
	}

	/**
	 * 
	 * @param row
	 * @return
	 */
	String createField(int row) {
		boolean isFKey;
		String defval;
		String datatype;
		String createField = "";
		datatype = Tool.read(sheet, row, Constants.COL_ATTR_TYPE);
		isFKey = false;

		// If Mid(datatype, 1, 2) = "DO" Then
		// isFKey = True
		// End If
		if (datatype.substring(0, 2).equals("DO")) {
			isFKey = true;
		}

		// ' no field off ->
		// If (Not sheet.Cells(row, COL_ATTR_NOFIELD) = "true") And (Not isFKey)
		// Then
		if ((!(read(sheet, row, Constants.COL_ATTR_NOFIELD).equals("true"))) && !isFKey) {

			// If datatype = "Date" Then
			// datatype = "GwtBaseDate"
			// End If
			if ((datatype.equals("Date"))) {
				datatype = "GwtBaseDate";
			}

			// createField = lf + tb + "private " + datatype + " " + _
			// getAttrName(sheet.Cells(row, COL_ATTR_NAME))
			createField = lf + tb + "private " + datatype + " " + read(sheet, row, Constants.COL_ATTR_NAME);

			// If Not sheet.Cells(row, COL_ATTR_INIT) = "" Then
			// defval = sheet.Cells(row, COL_ATTR_INIT)
			// If sheet.Cells(row, COL_ATTR_TYPE) = "String" Then
			// defval = """" + defval + """"
			// End If
			// createField = createField + " = " + defval
			// End If
			if (!(read(sheet, row, Constants.COL_ATTR_INIT).equals(""))) {
				defval = read(sheet, row, Constants.COL_ATTR_INIT);
				if (read(sheet, row, Constants.COL_ATTR_TYPE).equals("String")) {
					defval = "\"\"" + defval + "\"\"";
				}
				createField = createField + " = " + defval;
			}

			// createField = createField + ";"
			createField = (createField + ";");

			// ' comment / description
			// If Not sheet.Cells(row, COL_ATTR_COMMENT) = "" Then
			// createField = createField + " // " + sheet.Cells(row,
			// COL_ATTR_COMMENT)
			// ElseIf Not sheet.Cells(row, COL_ATTR_DESCR) = "" Then
			// createField = createField + " // " + sheet.Cells(row,
			// COL_ATTR_DESCR)
			// End If
			if (!(read(sheet, row, Constants.COL_ATTR_COMMENT).equals(""))) {
				createField = createField + " // " + read(sheet, row, Constants.COL_ATTR_COMMENT);
			} else if (!(read(sheet, row, Constants.COL_ATTR_DESCR).equals(""))) {
				createField = (createField + " // " + read(sheet, row, Constants.COL_ATTR_DESCR));
			}
		}
		return createField;
	}

	/**
	 * Function createDescription(row As Integer)
	 * 
	 * @param row
	 * @return
	 */
	String createDescription(int row) {

		// Dim isFKey As Boolean
		// Dim classNameDO As String
		// Dim attr As String
		// Dim key As String
		// Dim datatypeDO As String
		// Dim datatype As String
		// Dim length As String
		// Dim prec As String
		// Dim mand As String
		// Dim read As String
		// Dim info As String
		// Dim trans As String
		// Dim code As String
		// Dim attrGetSetCaller As String
		// Dim javaDoc As String

		boolean isFKey;
		String classNameDO;
		String attr;
		String key;
		String datatypeDO;
		String datatype;
		String length;
		String prec;
		String mand;
		String read;
		String info;
		String trans;
		String code;
		String attrGetSetCaller;
		String javaDoc;

		// ' data object
		// classNameDO = sheet.Cells(ROW_CLASS_NAME, COL_CLASS_VALUE)
		classNameDO = read(sheet, Constants.ROW_CLASS_NAME, Constants.COL_CLASS_VALUE);

		// ' attribute
		// attr = sheet.Cells(row, COL_ATTR_NAME)
		attr = read(sheet, row, Constants.COL_ATTR_NAME);

		// ' key
		// key = "NONE"
		// If sheet.Cells(row, COL_ATTR_KEY) = "true" Then
		// key = "PRIMARY"
		// End If
		key = "NONE";
		if ((read(sheet, row, Constants.COL_ATTR_KEY).equals("true"))) {
			key = "PRIMARY";
		}

		// format
		// datatype = sheet.Cells(row, COL_ATTR_TYPE)
		// isFKey = False
		// If Mid(datatype, 1, 2) = "DO" Then
		// isFKey = True
		// End If
		datatype = read(sheet, row, Constants.COL_ATTR_TYPE);
		isFKey = false;
		if ((datatype.substring(0, 2).equals("DO"))) {
			isFKey = true;
		}

		// mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm

		// datatype = UCase(datatype)
		// datatypeDO = datatype
		datatype = datatype.toUpperCase();
		datatypeDO = datatype;

		// If Not (datatype = "STRING" Or _
		// datatype = "BYTE" Or datatype = "SHORT" Or _
		// datatype = "INT" Or datatype = "LONG" Or datatype = "FLOAT" Or
		// datatype =
		// "DOUBLE" Or _
		// datatype = "BOOLEAN" Or datatype = "DATE") Then
		// datatype = "OBJECT"
		// End If
		if (!(datatype.equals("STRING") || datatype.equals("BYTE") //
				|| datatype.equals("SHORT") || datatype.equals("INT") //
				|| datatype.equals("LONG") || datatype.equals("FLOAT") //
				|| datatype.equals("DOUBLE") || datatype.equals("BOOLEAN") //
				|| datatype.equals("DATE"))) {

			datatype = "OBJECT";
		}

		// length = "-1"
		// prec = "0"
		length = "-1";
		prec = "0";

		// If Not sheet.Cells(row, COL_ATTR_LEN) = "" Then
		// length = sheet.Cells(row, COL_ATTR_LEN)
		// End If
		if (!(read(sheet, row, Constants.COL_ATTR_LEN).equals(""))) {
			length = read(sheet, row, Constants.COL_ATTR_LEN);
		}

		// If Not sheet.Cells(row, COL_ATTR_PREC) = "" Then
		// prec = sheet.Cells(row, COL_ATTR_PREC)
		// End If
		if (!(read(sheet, row, Constants.COL_ATTR_PREC).equals(""))) {
			prec = read(sheet, row, Constants.COL_ATTR_PREC);
		}

		// flags
		// mand = "false"
		mand = "false";
		// If sheet.Cells(row, COL_ATTR_MANDATORY) = "true" Then
		// mand = "true"
		// End If
		if ((read(sheet, row, Constants.COL_ATTR_MANDATORY).equals("true"))) {
			mand = "true";
		}

		// read = "false"
		read = "false";
		// If sheet.Cells(row, COL_ATTR_READONLY) = "true" Then
		// read = "true"
		// End If
		if ((read(sheet, row, Constants.COL_ATTR_READONLY).equals("true"))) {
			read = "true";
		}

		// info = sheet.Cells(row, COL_ATTR_INFO)
		// trans = "false"
		info = read(sheet, row, Constants.COL_ATTR_INFO);
		trans = "false";

		// If sheet.Cells(row, COL_ATTR_TRANSIENT) = "true" Then
		// trans = "true"
		// End If
		if ((read(sheet, row, Constants.COL_ATTR_TRANSIENT).equals("true"))) {
			trans = "true";
		}

		// code = sheet.Cells(row, COL_ATTR_CODE)
		code = read(sheet, row, Constants.COL_ATTR_CODE);

		// If isFKey Then
		if (isFKey) {

			// If trans = "false" Then
			if (trans.equals("false")) {

				// attrGetSetCaller = _
				// lf + tb + tb + tb + "new IAttrGetSetCaller() {" + _
				// lf + tb + tb + tb + tb + "@Override" + _
				// lf + tb + tb + tb + tb + "public Object get(IDataObject aObj)
				// {" + _
				// lf + tb + tb + tb + tb + tb + "return ((" + classNameDO +
				// ")aObj).getRelated(" + classNameDO + ".d" + UCase(attr) +
				// ");" + _
				// lf + tb + tb + tb + tb + "}" + _
				// lf + _
				// lf + tb + tb + tb + tb + "@Override" + _
				// lf + tb + tb + tb + tb + "public void set(IDataObject aObj,
				// Object aValue) {"
				// + _
				// lf + tb + tb + tb + tb + tb + "((" + classNameDO +
				// ")aObj).setRelated(" +
				// classNameDO + ".d" + UCase(attr) + ", (IDataObject) aValue);"
				// + _
				// lf + tb + tb + tb + tb + "}" + _
				// lf + tb + tb + tb + "}"

				attrGetSetCaller = //
						lf + tb + tb + tb + "new IAttrGetSetCaller() {" + //
								lf + tb + tb + tb + tb + "@Override" + //
								lf + tb + tb + tb + tb + "public Object get(IDataObject aObj) {" + //
								lf + tb + tb + tb + tb + tb + "return ((" + classNameDO + ")aObj).getRelated("
								+ classNameDO + ".d" + attr.toUpperCase() + ");" + //
								lf + tb + tb + tb + tb + "}" + //
								lf + //
								lf + tb + tb + tb + tb + "@Override" + //
								lf + tb + tb + tb + tb + "public void set(IDataObject aObj, Object aValue) {" + //
								lf + tb + tb + tb + tb + tb + "((" + classNameDO + ")aObj).setRelated(" + classNameDO
								+ ".d" + attr.toUpperCase() + ", (IDataObject) aValue);" + //
								lf + tb + tb + tb + tb + "}" + //
								lf + tb + tb + tb + "}";

				// Else
			} else {

				// attrGetSetCaller = _
				// lf + tb + tb + tb + "new IAttrGetSetCaller() {" + _
				// lf + tb + tb + tb + tb + "@Override" + _
				// lf + tb + tb + tb + tb + "public Object get(IDataObject aObj)
				// {" + _
				// lf + tb + tb + tb + tb + tb + "return ((" + classNameDO +
				// ")aObj).getRelated(" + classNameDO + ".d" + UCase(attr) +
				// ");" + _
				// lf + tb + tb + tb + tb + "}" + _
				// lf + _
				// lf + tb + tb + tb + tb + "@Override" + _
				// lf + tb + tb + tb + tb + "public void set(IDataObject aObj,
				// Object aValue) {"
				// + _
				// lf + tb + tb + tb + tb + tb + "// empty implementation" + _
				// lf + tb + tb + tb + tb + "}" + _
				// lf + tb + tb + tb + "}"

				attrGetSetCaller = //
						lf + tb + tb + tb + "new IAttrGetSetCaller() {" + //
								lf + tb + tb + tb + tb + "@Override" + //
								lf + tb + tb + tb + tb + "public Object get(IDataObject aObj) {" + //
								lf + tb + tb + tb + tb + tb + "return ((" + classNameDO + ")aObj).getRelated("
								+ classNameDO + ".d" + attr.toUpperCase() + ");" + //
								lf + tb + tb + tb + tb + "}" + //
								lf + //
								lf + tb + tb + tb + tb + "@Override" + //
								lf + tb + tb + tb + tb + "public void set(IDataObject aObj, Object aValue) {" + //
								lf + tb + tb + tb + tb + tb + "// empty implementation" + //
								lf + tb + tb + tb + tb + "}" + //
								lf + tb + tb + tb + "}";

			}

			// Else
			// If trans = "false" Then
		} else if (trans.equals("false")) {

			// attrGetSetCaller = _
			// lf + tb + tb + tb + "new IAttrGetSetCaller() {" + _
			// lf + tb + tb + tb + tb + "@Override" + _
			// lf + tb + tb + tb + tb + "public Object get(IDataObject aObj) {"
			// + _
			// lf + tb + tb + tb + tb + tb + "return ((" + classNameDO +
			// ")aObj).get" + attr
			// + "();" + _
			// lf + tb + tb + tb + tb + "}" + _
			// lf + _
			// lf + tb + tb + tb + tb + "@Override" + _
			// lf + tb + tb + tb + tb + "public void set(IDataObject aObj,
			// Object aValue) {"
			// + _
			// lf + tb + tb + tb + tb + tb + "((" + classNameDO + ")aObj).set" +
			// attr + "(("
			// + getJavaDataType(row) + ")aValue);" + _
			// lf + tb + tb + tb + tb + "}" + _
			// lf + tb + tb + tb + "}"

			attrGetSetCaller = //
					lf + tb + tb + tb + "new IAttrGetSetCaller() {" + //
							lf + tb + tb + tb + tb + "@Override" + //
							lf + tb + tb + tb + tb + "public Object get(IDataObject aObj) {" + //
							lf + tb + tb + tb + tb + tb + "return ((" + classNameDO + ")aObj).get" + attr + "();" + //
							lf + tb + tb + tb + tb + "}" + //
							lf + //
							lf + tb + tb + tb + tb + "@Override" + //
							lf + tb + tb + tb + tb + "public void set(IDataObject aObj, Object aValue) {" + //
							lf + tb + tb + tb + tb + tb + "((" + classNameDO + ")aObj).set" + attr + "(("
							+ getJavaDataType(row) + ")aValue);" + //
							lf + tb + tb + tb + tb + "}" + //
							lf + tb + tb + tb + "}";

			// Else
		} else {

			// attrGetSetCaller = _
			// lf + tb + tb + tb + "new IAttrGetSetCaller() {" + _
			// lf + tb + tb + tb + tb + "@Override" + _
			// lf + tb + tb + tb + tb + "public Object get(IDataObject aObj) {"
			// + _
			// lf + tb + tb + tb + tb + tb + "return ((" + classNameDO +
			// ")aObj).get" + attr
			// + "();" + _
			// lf + tb + tb + tb + tb + "}" + _
			// lf + _
			// lf + tb + tb + tb + tb + "@Override" + _
			// lf + tb + tb + tb + tb + "public void set(IDataObject aObj,
			// Object aValue) {"
			// + _
			// lf + tb + tb + tb + tb + tb + "// empty implementation" + _
			// lf + tb + tb + tb + tb + "}" + _
			// lf + tb + tb + tb + "}"
			// End If
			// End If

			attrGetSetCaller = //
					lf + tb + tb + tb + "new IAttrGetSetCaller() {" + //
							lf + tb + tb + tb + tb + "@Override" + //
							lf + tb + tb + tb + tb + "public Object get(IDataObject aObj) {" + //
							lf + tb + tb + tb + tb + tb + "return ((" + classNameDO + ")aObj).get" + attr + "();" + //
							lf + tb + tb + tb + tb + "}" + //
							lf + //
							lf + tb + tb + tb + tb + "@Override" + //
							lf + tb + tb + tb + tb + "public void set(IDataObject aObj, Object aValue) {" + //
							lf + tb + tb + tb + tb + tb + "// empty implementation" + //
							lf + tb + tb + tb + tb + "}" + //
							lf + tb + tb + tb + "}";

		}

		// javaDoc = ""
		javaDoc = "";

		// If Not sheet.Cells(row, COL_ATTR_COMMENT) = "" Then
		// javaDoc = lf + tb + "/**" + lf + tb + " * " + sheet.Cells(row,
		// COL_ATTR_COMMENT) + lf + tb + " */"
		// ElseIf Not sheet.Cells(row, COL_ATTR_DESCR) = "" Then
		// javaDoc = lf + tb + "/**" + lf + tb + " * " + sheet.Cells(row,
		// COL_ATTR_DESCR) + lf + tb + " */"
		// End If

		if (!(read(sheet, row, Constants.COL_ATTR_COMMENT).equals(""))) {
			javaDoc = lf + tb + "/**" + lf + tb + " * " + read(sheet, row, Constants.COL_ATTR_COMMENT) + lf + tb
					+ " */";
		} else if (!(read(sheet, row, Constants.COL_ATTR_DESCR).equals(""))) {
			javaDoc = lf + tb + "/**" + lf + tb + " * " + read(sheet, row, Constants.COL_ATTR_DESCR) + lf + tb + " */";
		}

		String createDescription;
		// If isFKey Then
		if (isFKey) {

			// createDescription = lf + javaDoc + _
			// lf + tb + "static public final DescrAttr d" + UCase(attr) + " =
			// new
			// DescrAttrFK(ATTRMGR, " + _
			// "eAttr." + UCase(attr) + ", " + _
			// lf + tb + tb + tb + """" + attr + """, " + _
			// "eType." + datatypeDO + ", " + _
			// mand + ", " + read + ", " + """" + info + """" + ", " + trans +
			// ", " + _
			// """" + sheet.Cells(row, COL_ATTR_DESCR) + """, " + """" +
			// sheet.Cells(row,
			// COL_ATTR_ML) + """, " + _
			// attrGetSetCaller + ");"

			createDescription = lf + javaDoc + //
					lf + tb + "static public final DescrAttr d" + attr.toUpperCase() + " = new  DescrAttrFK(ATTRMGR, " + //
					"eAttr." + attr.toUpperCase() + ", " + //
					lf + tb + tb + tb + "\"\"" + attr + "\"\", " + //
					"eType." + datatypeDO + ", " + //
					mand + ", " + read + ", " + "\"\"" + info + "\"\"" + ", " + trans + ", " + //
					"\"\"" + read(sheet, row, Constants.COL_ATTR_DESCR) + "\"\", " + "\"\"" + //
					read(sheet, row, Constants.COL_ATTR_ML) + "\"\", " + //
					attrGetSetCaller + ");";

		} else {

			// createDescription = lf + javaDoc + _
			// lf + tb + "static public final DescrAttr d" + UCase(attr) + " =
			// new
			// DescrAttr(ATTRMGR, " + _
			// "eAttr." + UCase(attr) + ", " + _
			// lf + tb + tb + tb + """" + attr + """, " + "eKeyType." + key + ",
			// " +
			// "eDataType." + datatype + ", " + _
			// length + ", " + prec + ", " + _
			// mand + ", " + read + ", " + """" + info + """" + ", " + trans +
			// ", " + _
			// """" + sheet.Cells(row, COL_ATTR_DESCR) + """, " + """" +
			// sheet.Cells(row,
			// COL_ATTR_ML) + """, " + _
			// """" + sheet.Cells(row, COL_ATTR_PRINT_TAG) + """, " + """" +
			// code + """," +
			// _
			// attrGetSetCaller + ");"
			createDescription = lf + javaDoc + lf + tb + "static public final DescrAttr d" + attr.toUpperCase()
					+ " = new DescrAttr(ATTRMGR, " + "eAttr." + attr.toUpperCase() + ", " + lf + tb + tb + tb + "\""
					+ attr + "\", " + "eKeyType." + key + ", " + "eDataType." + datatype + ", " + length + ", " + prec
					+ ", " + mand + ", " + read + ", " + "\"" + info + "\"" + ", " + trans + ", " + "\""
					+ read(sheet, row, Constants.COL_ATTR_DESCR) + "\", " + "\""
					+ read(sheet, row, Constants.COL_ATTR_ML) + "\", " + "\""
					+ read(sheet, row, Constants.COL_ATTR_PRINT_TAG) + "\", " + "\"" + code + "\"," + attrGetSetCaller
					+ ");";
		}

		return createDescription;
	}

	// Function createInitValue(row As Integer, isEForm As Boolean)
	/**
	 * 
	 * @param row
	 * @param isEForm
	 * @return
	 */
	String createInitValue(int row, boolean isEForm) {

		// Dim defval As String
		// createInitValue = ""
		String defval;
		String createInitValue = "";

		// ' no field off ->
		// If Not sheet.Cells(row, COL_ATTR_NOFIELD) = "true" Then
		if (!(read(sheet, row, Constants.COL_ATTR_NOFIELD).equals("true"))) {

			// If isEForm Then
			// defval = sheet.Cells(row, COL_ATTR_INIT_EFORM)
			// Else
			// defval = sheet.Cells(row, COL_ATTR_INIT)
			// End If
			if (isEForm) {
				defval = read(sheet, row, Constants.COL_ATTR_INIT_EFORM);
			} else {
				defval = read(sheet, row, Constants.COL_ATTR_INIT);
			}

			// (in case of empty form all strings are initialized with an empty
			// string)
			// If Not defval = "" Or (isEForm And sheet.Cells(row,
			// COL_ATTR_TYPE) =
			// "String") Then
			// If sheet.Cells(row, COL_ATTR_TYPE) = "String" Then
			// defval = """" + defval + """"
			// End If
			// createInitValue = lf + tb + tb + getAttrName(sheet.Cells(row,
			// COL_ATTR_NAME))
			// + " = " + defval + ";"
			// End If
			if (!(defval.equals("") || (isEForm && (read(sheet, row, Constants.COL_ATTR_TYPE).equals("String"))))) {
				if ((read(sheet, row, Constants.COL_ATTR_TYPE).equals("String"))) {
					defval = ("\"" + defval + "\"");
				}

				createInitValue = lf + tb + tb //
						+ getAttrName(read(sheet, row, Constants.COL_ATTR_NAME)) + " = " + defval + ";";
			}

		}
		return createInitValue;

	}

	//
	// createGetFunctionGen = _
	// lf + tb + tb2 + ifelse + "if (attrib == eAttr." + UCase(sheet.Cells(row,
	// COL_ATTR_NAME)) + ") {" + _
	// lf + tb + tb + tb2 + "return get" + sheet.Cells(row, COL_ATTR_NAME) +
	// "();" +
	// _
	// lf + tb + tb2 + "}"
	//
	// End Function
	/**
	 * 
	 * @param row
	 * @param isFirst
	 * @param tb2
	 */
	String createGetFunctionGen(int row, boolean isFirst, String tb2) {
		// Function createGetFunctionGen(row As Integer, isFirst As Boolean, tb2
		// As String)

		// Dim ifelse As String
		// ifelse = "else "
		String ifelse;
		ifelse = "else ";

		// If isFirst Then
		// ifelse = ""
		// End If
		if (isFirst) {
			ifelse = "";
		}

		return lf + tb + tb2 + ifelse + "if (attrib.equals(eAttr."
				+ read(sheet, row, Constants.COL_ATTR_NAME).toUpperCase() + ") {" + lf + tb + tb + tb2 + "return get"
				+ read(sheet, row, Constants.COL_ATTR_NAME) + "();" + lf + tb + tb2 + "}";
	}

	// Function getJavaDataType(row As Integer) As String
	String getJavaDataType(int row) {

		// primitive data types

		// getJavaDataType = sheet.Cells(row, COL_ATTR_TYPE)
		String getJavaDataType = read(sheet, row, Constants.COL_ATTR_TYPE);

		// If sheet.Cells(row, COL_ATTR_TYPE) = "Date" Then
		// getJavaDataType = "GwtBaseDate"
		// Exit Function
		// End If
		if (read(sheet, row, Constants.COL_ATTR_TYPE).equals("Date")) {
			return "GwtBaseDate";

		}

		// If sheet.Cells(row, COL_ATTR_TYPE) = "byte" Then
		// getJavaDataType = "Byte"
		// Exit Function
		// End If
		if (read(sheet, row, Constants.COL_ATTR_TYPE).equals("byte")) {
			return "Byte";

		}

		// If sheet.Cells(row, COL_ATTR_TYPE) = "short" Then
		// getJavaDataType = "Short"
		// Exit Function
		// End If
		if (read(sheet, row, Constants.COL_ATTR_TYPE).equals("short")) {
			return "Short";

		}

		// If sheet.Cells(row, COL_ATTR_TYPE) = "int" Then
		// getJavaDataType = "Integer"
		// Exit Function
		// End If
		if (read(sheet, row, Constants.COL_ATTR_TYPE).equals("int")) {
			return "Integer";

		}

		// If sheet.Cells(row, COL_ATTR_TYPE) = "long" Then
		// getJavaDataType = "Long"
		// Exit Function
		// End If
		if (read(sheet, row, Constants.COL_ATTR_TYPE).equals("long")) {
			return "Long";

		}

		// If sheet.Cells(row, COL_ATTR_TYPE) = "float" Then
		// getJavaDataType = "Float"
		// Exit Function
		// End If
		if (read(sheet, row, Constants.COL_ATTR_TYPE).equals("float")) {
			return "Float";

		}

		// If sheet.Cells(row, COL_ATTR_TYPE) = "double" Then
		// getJavaDataType = "Double"
		// Exit Function
		// End If
		if (read(sheet, row, Constants.COL_ATTR_TYPE).equals("double")) {
			return "Double";

		}

		// If sheet.Cells(row, COL_ATTR_TYPE) = "boolean" Then
		// getJavaDataType = "Boolean"
		// Exit Function
		// End If
		if (read(sheet, row, Constants.COL_ATTR_TYPE).equals("boolean")) {
			return "Boolean";

		}

		return getJavaDataType;
	}

	/**
	 * 
	 * 
	 * Function createSetFunctionGen(row As Integer, isFirst As Boolean, tb2 As
	 * String)
	 * 
	 * @param row
	 * @param isFirst
	 * @param tb2
	 * @return
	 */
	String createSetFunctionGen(int row, boolean isFirst, String tb2) {

		// Dim ifelse As String
		// createSetFunctionGen = ""
		String ifelse;
		String createSetFunctionGen = "";

		// ifelse = "else "
		// If isFirst Then
		// ifelse = ""
		// End If
		ifelse = "else ";
		if (isFirst) {
			ifelse = "";
		}

		// createSetFunctionGen = _
		// lf + tb + tb2 + ifelse + "if (attrib == eAttr." +
		// UCase(sheet.Cells(row,
		// COL_ATTR_NAME)) + ") {" + _
		// lf + tb + tb + tb2 + "set" + sheet.Cells(row, COL_ATTR_NAME) + "((" +
		// getJavaDataType(row) + ")value);" + _
		// lf + tb + tb2 + "}"
		return lf + tb + tb2 + ifelse + "if (attrib.equals(eAttr."
				+ read(sheet, row, Constants.COL_ATTR_NAME).toUpperCase() + ") {" + lf + tb + tb + tb2 + "set"
				+ read(sheet, row, Constants.COL_ATTR_NAME) + "((" + getJavaDataType(row) + ")value);" + lf + tb + tb2
				+ "}";
	}

	/**
	 * 
	 * 
	 * Function createGetSetFunction(row As Integer)
	 * 
	 * @param row
	 */
	String createGetSetFunction(int row) {

		// Dim attr As String
		// Dim isFKey As Boolean
		// Dim datatype As String
		String attr;
		boolean isFKey;
		String datatype;

		// attr = sheet.Cells(row, COL_ATTR_NAME)
		// datatype = sheet.Cells(row, COL_ATTR_TYPE)
		attr = read(sheet, row, Constants.COL_ATTR_NAME);
		datatype = read(sheet, row, Constants.COL_ATTR_TYPE);

		// isFKey = False
		// If Mid(datatype, 1, 2) = "DO" Then
		// isFKey = True
		// End If
		isFKey = false;
		if (datatype.substring(0, 2).equals("DO")) {
			isFKey = true;
		}

		// createGetSetFunction = ""
		String createGetSetFunction = "";

		// ' no get/set off ->
		// If Not sheet.Cells(row, COL_ATTR_NOGETSET) = "true" Then
		if (!(read(sheet, row, Constants.COL_ATTR_NOGETSET).equals("true"))) {
			// javaDoc
			// If Not sheet.Cells(row, COL_ATTR_COMMENT) = "" Then
			if (!(read(sheet, row, Constants.COL_ATTR_COMMENT).equals(""))) {

				// createGetSetFunction = _
				// lf + tb + "/**" + _
				// lf + tb + " * " + sheet.Cells(row, COL_ATTR_COMMENT) + _
				// lf + tb + " */"
				createGetSetFunction = (lf + tb + "/**" + lf + tb + " * " + read(sheet, row, Constants.COL_ATTR_COMMENT)
						+ lf + tb + " */");

			} else if (!(read(sheet, row, Constants.COL_ATTR_DESCR).equals("")))
			// ElseIf Not sheet.Cells(row, COL_ATTR_DESCR) = "" Then
			{

				// createGetSetFunction = _
				// lf + tb + "/**" + _
				// lf + tb + " * " + sheet.Cells(row, COL_ATTR_DESCR) + _
				// lf + tb + " */"
				// End If

				createGetSetFunction = (lf + tb + "/**" + lf + tb + " * " + read(sheet, row, Constants.COL_ATTR_DESCR)
						+ lf + tb + " */");
			}

			// get
			// If datatype = "Date" Then
			// datatype = "GwtBaseDate"
			// End If
			if (datatype.equals("Date")) {
				datatype = "GwtBaseDate";
			}

			// createGetSetFunction = createGetSetFunction + _
			// lf + tb + "public " + datatype + " get" + attr + "() {"
			createGetSetFunction = (createGetSetFunction + lf + tb + "public " + datatype + " get" + attr + "() {");

			// If isFKey Then ' temporary different implemetation
			// createGetSetFunction = createGetSetFunction + _
			// lf + tb + tb + "return (" + datatype + ")this.get(d" +
			// UCase(attr) + ");"
			if (isFKey) {
				// temporary different implemetation
				createGetSetFunction = (createGetSetFunction + lf + tb + tb + "return (" + datatype + ")this.get(d"
						+ attr.toUpperCase() + ");");
			} else {
				// Else
				// createGetSetFunction = createGetSetFunction + _
				// lf + tb + tb + "return this." + getAttrName(attr) + ";"
				// End If
				createGetSetFunction = (createGetSetFunction + lf + tb + tb + "return this." + getAttrName(attr) + ";");
			}

			// createGetSetFunction = createGetSetFunction + _
			// lf + tb + "}" + _
			// lf
			createGetSetFunction = createGetSetFunction + lf + tb + "}" + lf;

			// transient off ->
			// If Not sheet.Cells(row, COL_ATTR_TRANSIENT) = "true" Then
			if (!(read(sheet, row, Constants.COL_ATTR_TRANSIENT).equals("true"))) {
				// set
				// createGetSetFunction = createGetSetFunction + _
				// lf + tb + "private void set" + attr + "(" + datatype + "
				// value) {"
				createGetSetFunction = (createGetSetFunction + lf + tb + "private void set" + attr + "(" + datatype
						+ " value) {");
				if (isFKey) {

					// temporary different implemetation
					// If isFKey Then ' temporary different implemetation
					// createGetSetFunction = createGetSetFunction + _
					// lf + tb + tb + "this.set(d" + UCase(attr) + ", value);"
					createGetSetFunction = (createGetSetFunction + lf + tb + tb + "this.set(d" + attr.toUpperCase()
							+ ", value);");
				} else {
					// Else
					// createGetSetFunction = createGetSetFunction + _
					// lf + tb + tb + "this." + getAttrName(attr) + " = value;"
					// End If
					createGetSetFunction = (createGetSetFunction + lf + tb + tb + "this." + getAttrName(attr)
							+ " = value;");
				}

				// createGetSetFunction = createGetSetFunction + lf + tb + "}" +
				// lf
				createGetSetFunction = createGetSetFunction + lf + tb + "}" + lf;
			}

		}
		return createGetSetFunction;

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
