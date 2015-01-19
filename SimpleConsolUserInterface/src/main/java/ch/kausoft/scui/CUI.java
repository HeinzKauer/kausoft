package ch.kausoft.scui;

/**
 * Console user interface.
 * 
 * @author Heinz kauer
 */
public interface CUI {

	/**
	 * @since created at 16.01.2015.23:15:56
	 * @return CUIMenu
	 */
	public CUIMenu getUIMenu();

	/**
	 * @since created at 16.01.2015.23:16:05
	 * @return CUITabelle
	 */
	public CUITabelle getContentTabelle();

}
