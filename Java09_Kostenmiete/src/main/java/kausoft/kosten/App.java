package kausoft.kosten;

import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.controller.useCases.DataPathUC;
import ch.kausoft.controller.useCases.DatenSpeicherUC;
import ch.kausoft.controller.useCases.TilgungsplanRechnenUC;
import ch.kausoft.wbg.daten.DataIOExcel;
import ch.kausoft.wbg.daten.OutputExcel;
import ch.kausoft.wbg.daten.bo.InvestitionBO;
import ch.kausoft.wbg.daten.bo.WohnungBO;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		// UseCase DatenPath
		DataPathUC dataPath = new DataPathUC();
		System.out.println(dataPath.getDataPath());
		dataPath
				.setDataPath("C:\\eclipse2013\\Workspaces\\kausoft\\Java09_Kostenmiete\\daten\\InputDaten.csv");
		System.out.println(dataPath.getDataPath());

		// UseCase DatenSpeicher laden 
		DatenSpeicherUC datenSpeicher = new  DatenSpeicherUC();
   //		daten.loadCSV(dataPath.getDataPath());

		// UseCase DatenSpeicher laden
		dataPath.setDataPath("C:\\eclipse2013\\Workspaces\\kausoft\\Java09_Kostenmiete\\daten\\");
		System.out.println(dataPath.getDataPath());
		datenSpeicher.loadFromExcel(dataPath.getDataPath());
		
		// UseCase Tilgungsplane Rechnen
		TilgungsplanRechnenUC tpr = new TilgungsplanRechnenUC();
		tpr.calculate(DatenSpeicher.getDatenSpeicher()); 
		
		// UseCase DatenSpeicher laden
		OutputExcel excel = DataIOExcel.getOutPutExcel(dataPath.getDataPath());
		InvestitionBO.fillSheet(excel.getInvestitionenSheet());
		WohnungBO.fillSheet(excel.getWohnungenSheet());
		datenSpeicher.save(excel);
		
		datenSpeicher.saveToExcel(dataPath.getDataPath());
		
	}
}
