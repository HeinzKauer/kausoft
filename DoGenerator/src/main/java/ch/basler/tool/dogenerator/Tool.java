package ch.basler.tool.dogenerator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.*;

public class Tool {

static	String read(Sheet sheet, int row, int col) {
		return sheet.getCellComment(new CellAddress(row, col)).toString();
	}
}
