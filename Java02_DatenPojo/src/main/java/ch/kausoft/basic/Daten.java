/**
 * 
 */
package ch.kausoft.basic;

import java.lang.reflect.Field;



/**
 * @author Heinz
 * 
 */
public class Daten extends RecordID {

	/**
	 * @since created at 26.10.2007.22:11:27
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		try {
			Class clazz = this.getClass();
			// lesen alle Attribute
			Field fields[] = clazz.getDeclaredFields();
			StringBuffer sb = new StringBuffer();
			sb.append("\n  ");
			for (int i = 0; i < fields.length; i++) {
				// Zugriffsberechtigung für none private Methoden geben
				fields[i].setAccessible(true);
				Object obj = (Object) fields[i].get(this);
				// sb.append(obj.getClass().getName());
				fields[i].getClass().isPrimitive();
				sb.append(fields[i].getName()
						+ "("
						// TODO warum spricht hier isPrimitive() nicht an
						+ ((fields[i].getClass().isPrimitive()) ? ((obj == null) ? "null"
								: obj.getClass().getSimpleName().toLowerCase())
								: ((obj == null) ? "null" : obj.getClass().getSimpleName()))
						+ ")");
				sb.append(" = >");
				sb.append(obj);
				sb.append(" <\n  ");
			}
			return sb.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "has not Attributes";
	}
}
