package ch.kausoft.ui;

import ch.kausoft.controller.useCases.DataPathUC;
import ch.kausoft.controller.useCases.DatenSpeicherUC;
import ch.kausoft.controller.useCases.TilgungsplanRechnenUC;
import ch.kausoft.scui.CUI;
import ch.kausoft.scui.CUIMenu;
import ch.kausoft.scui.CUITabelle;
import ch.kausoft.scui.IMenueAction;
import ch.kausoft.scui.IOElement;
import ch.kausoft.scui.SimpleConsolUserInterface;

public class Menu implements CUI {

	CUITabelle t = new CUITabelle();

	public CUIMenu getUIMenu() {
		CUIMenu menu = new CUIMenu();
		menu.addIOElement(new IOElement("LC", "Load from CSV", new LoadCSV()));
		menu.addIOElement(new IOElement("LE", "Load from Excel", new LoadExcel()));
		menu.addIOElement(new IOElement("SE", "Save Excel", new SaveExcel()));
		menu.addIOElement(new IOElement("E", "Ende", new E()));
		return menu;
	}

	public CUITabelle getContentTabelle() {
		return t;
	}

	
	class LoadCSV implements IMenueAction {
		public boolean runMenueAction() {

			// UseCase DatenPath
         DataPathUC dataPath =  DataPathUC.getInstance();
         dataPath =  DataPathUC.getInstance("PATH");
			System.out.println(dataPath.getDataPath());
			dataPath.setDataPath("C:\\Development\\gitHub\\kausoftFromGithub\\Daten\\InputDaten.csv");
			System.out.println(dataPath.getDataPath());

			// UseCase DatenSpeicher laden
			DatenSpeicherUC daten =  DatenSpeicherUC.getInstance();
			daten.loadCSV(dataPath.getDataPath());

			dataPath.setDataPath("C:\\Development\\gitHub\\kausoftFromGithub\\Daten\\");
			System.out.println(dataPath.getDataPath());

			// UseCase DatenSpeicher laden
			daten.loadFromExcel(dataPath.getDataPath());

			SimpleConsolUserInterface scui = new SimpleConsolUserInterface();
			scui.work(new M01Auswahl());
			return false;
		}
	}

	class LoadExcel implements IMenueAction {
		public boolean runMenueAction() {

			// UseCase DatenPath
			DataPathUC dataPath = DataPathUC.getInstance();
			System.out.println(dataPath.getDataPath());

			dataPath.setDataPath("C:\\Development\\gitHub\\kausoftFromGithub\\Daten\\");
			System.out.println(dataPath.getDataPath());

			// UseCase DatenSpeicher laden
			DatenSpeicherUC daten =  DatenSpeicherUC.getInstance();
			daten.loadFromExcel(dataPath.getDataPath());

			// Rechnen 
			TilgungsplanRechnenUC uc =   TilgungsplanRechnenUC.getInstance();
			uc.calculate();
			
			
			SimpleConsolUserInterface scui = new SimpleConsolUserInterface();
			scui.work(new M01Auswahl());
			return false;
		}
	}

	class SaveExcel implements IMenueAction {
		public boolean runMenueAction() {

			// UseCase DatenPath
			DataPathUC dataPath = DataPathUC.getInstance();
			System.out.println(dataPath.getDataPath());
			


			dataPath.setDataPath("C:\\Development\\gitHub\\kausoftFromGithub\\Daten\\");
			System.out.println(dataPath.getDataPath());

			// UseCase DatenSpeicher laden
			DatenSpeicherUC daten =  DatenSpeicherUC.getInstance();
			daten.saveToExcel(dataPath.getDataPath());

			SimpleConsolUserInterface scui = new SimpleConsolUserInterface();
			scui.work(new M01Auswahl());

			return false;
		}
	}

	
	class E implements IMenueAction {
		public boolean runMenueAction() {
			return true;
		}
	}

}
