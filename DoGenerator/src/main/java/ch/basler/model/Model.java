package ch.basler.model;

import java.util.HashMap;

// ------------------------------------------------
//  replace // , jkgdfkjsdlökfjgsljfg   
//  alles Kommentieren // erste Zeile 
//  repalce // , data.add(new  Attribut ("
//  am Schluss ")); anhängen 
//------------------------------------------------

public class Model {

	private HashMap<String, DoModel> model = new HashMap<String, DoModel>();
	
	public DoModel getDoModel(String doModel ) {
		return model.get(doModel);
	}

	public void load() {
		//@formatter:off

		DoModel doModel = new DoModel("DODiensteintritt");
		model.put("DODiensteintritt", doModel);
		doModel.add(new RowData("Meta Info for DO (DescrType)", "", "", "", "", "Code Generation", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("Class Name", "DODiensteintritt", "", "", "", "Auftrag: Diensteintritt", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("Base class Name", "DOAuftrag", "", "", "", "Database table: TB15_BLDDE", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("Base class Import", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("Hierarchy Level", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("UID", "2593040872547771871L", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("BO", "BODiensteintritt", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("Interface", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("KeyCreationType", "KeyOnPersist", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("Meta Info for DO-Attributes (DescrAttr)", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "Code Generation", "", ""));
		doModel.add(new RowData("Attribute", "", "Format", "", "", "Flags", "", "", "", "Description", "", "", "Value", "", "", "", "", ""));
		doModel.add(new RowData("Name", "Key", "Type", "Length", "Precision", "Mandatory", "Readonly", "Info", "Transient", "Description", "ML Key", "PrintTag", "Init", "Init EForm", "Code", "NoField", "NoGetSet", "Comment"));
		doModel.add(new RowData("AHVNr1", "", "String", "14", "", "", "", "", "", "AHV-Nr.", "LBL_AHV_Nummer", "", "", "", "", "", "", ""));
		doModel.add(new RowData("AHVNr1Neu", "", "String", "12", "", "", "", "", "", "AHV-Nr.", "LBL_AHV_Nummer_Neu", "", "", "", "", "", "", "neue AHV-Nr 1"));
		doModel.add(new RowData("AHVNr2", "", "String", "14", "", "", "", "", "", "AHV-Nr.", "LBL_AHV_Nummer", "", "", "", "", "", "", ""));
		doModel.add(new RowData("AHVNr2Neu", "", "String", "4", "", "", "", "", "", "AHV-Nr.", "LBL_AHV_Nummer_Neu", "", "", "", "", "", "", "neue AHV-Nr 2"));
		doModel.add(new RowData("AHVNr3", "", "String", "14", "", "", "", "", "", "AHV-Nr.", "LBL_AHV_Nummer", "", "", "", "", "", "", ""));
		doModel.add(new RowData("AHVNr3Neu", "", "String", "4", "", "", "", "", "", "AHV-Nr.", "LBL_AHV_Nummer_Neu", "", "", "", "", "", "", "neue AHV-Nr 3"));
		doModel.add(new RowData("AHVNr4", "", "String", "14", "", "", "", "", "", "AHV-Nr.", "LBL_AHV_Nummer", "", "", "", "", "", "", ""));
		doModel.add(new RowData("AHVNr4Neu", "", "String", "2", "", "", "", "", "", "AHV-Nr.", "LBL_AHV_Nummer_Neu", "", "", "", "", "", "", "neue AHV-Nr 4"));
		doModel.add(new RowData("AHVNrNeu", "", "String", "16", "", "", "true", "", "true", "AHV-Nummer", "LBL_AHV_Nummer_Neu", "neuahvnr", "", "", "", "true", "true", "neue AHV-Nr gesamt"));
		doModel.add(new RowData("Personalnummer", "", "String", "20", "", "", "", "", "", "Personalnummer", "LBL_Personalnummer", "vppersnr", "", "", "", "", "", "Personalnummer der Versicherten Personen des Versicherungsnehmers."));
		doModel.add(new RowData("Arbeitsfaehig", "", "short", "", "", "true", "", "", "", "Arbeitsfaehigkeit", "LBL_Ist_die_Person", "aktarbf", "", "2", "-243", "", "", "2=Ja, 1=Nein"));
		doModel.add(new RowData("ArbeitsfaehigGrund", "", "String", "100", "", "", "", "", "", "Bemerkung", "LBL_Falls_nein_Grund", "eugrund", "", "", "", "", "", ""));
		doModel.add(new RowData("Austrittleistung", "", "short", "", "", "true", "", "http//www.baloise.ch/$$/application-content/businesslifedirect/austrittsleistung", "", "Ist eine Austrittsleistung aus einem frueheren Arbeitsverhaeltnis zu erwarten?", "LBL_Austrittsleistung", "swleisag", "", "2", "243", "", "", "1=Ja, 2=Nein, 3=Unbekannt"));
		doModel.add(new RowData("Beschgrad", "", "float", "5", "2", "", "", "", "", "Beschaeftigungsgrad", "LBL_Beschaeftigungsgrad", "aktbgrad", "", "100", "", "", "", ""));
		doModel.add(new RowData("BeziehtLeistungen", "", "short", "", "", "true", "", "", "", "Bezieht sie Leistungen der Invalidenversicherung (IV), Militaerversicherung (MV) oder der Unfallversicherung (UVG) und/oder aus einer Personalvorsorge-Einrichtung oder sind Ansprueche haengig?", "LBL_Bezieht_sie_Leistungen", "swleistv", "", "1", "-243", "", "", "2=Ja, 1=Nein"));
		doModel.add(new RowData("Dedatum", "", "Date", "10", "", "true", "", "http//www.baloise.ch/$$/application-content/businesslifedirect/diensteintritt", "", "Diensteintritt", "LBL_Diensteintrittsdatum", "eintdt", "", "", "", "", "", ""));
		doModel.add(new RowData("Dokumentsprache", "", "short", "", "", "true", "", "", "", "Sprache fuer Versicherungsdokumente", "LBL_Sprache_Versicherungsdokumente", "vpsprac", "", "", "252", "", "", "1=deutsch, 2=franzoesisch, 3=italienisch, 4=englisch"));
		doModel.add(new RowData("Firmenname", "", "String", "35", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("Geburtsdatum", "", "Date", "10", "", "true", "", "", "", "Geburtsdatum", "LBL_Geburtsdatum", "vpgebdat", "", "", "", "", "", ""));
		doModel.add(new RowData("Geschlecht", "", "short", "", "", "true", "", "", "", "Geschlecht", "LBL_Geschlecht", "vpgeschl", "", "", "196", "", "", "1= maennlich, 2=weiblich"));
		doModel.add(new RowData("Heiratsdatum", "", "Date", "10", "", "", "", "", "", "Datum der Eheschliessung", "LBL_Datum_Eheschliessung", "vpehedt", "", "", "", "", "", ""));
		doModel.add(new RowData("Jahreslohn", "", "double", "13", "2", "true", "", "http//www.baloise.ch/$$/application-content/businesslifedirect/jahreslohn", "", "Jahreslohn", "LBL_Zu_meldender_Jahreslohn", "aktjlohn", "", "", "", "", "", ""));
		doModel.add(new RowData("Kategorie", "", "int", "", "", "true", "", "http//www.baloise.ch/$$/application-content/businesslifedirect/kategorie", "", "Personenkategorie", "LBL_Personenkategorie", "aktkateg", "", "", "", "", "", ""));
		doModel.add(new RowData("Organisationseinheit", "", "int", "", "", "true", "", "", "", "Organisationseinheit", "LBL_Organisationseinheit", "aktoe", "", "", "", "", "", ""));
		doModel.add(new RowData("Ort", "", "String", "30", "", "", "", "", "", "Ort", "LBL_Ort", "vport", "", "", "", "", "", ""));
		doModel.add(new RowData("Stellung", "", "short", "", "", "true", "", "", "", "Stellung in der Firma", "LBL_Stellung_Firma", "stellung", "", "1", "144", "", "", "1=Arbeitnehmer, 2=Arbeitgeber"));
		doModel.add(new RowData("Unterstuetzungspflicht", "", "short", "", "", "", "", "http//www.baloise.ch/$$/application-content/businesslifedirect/unterstuetzungspflicht", "", "Unterstuetzungspflicht", "LBL_Unterstuetzungspflicht", "aktupfli", "", "3", "1030", "", "", "1=Ja, 2=Nein, 3=Unbekannt"));
		doModel.add(new RowData("Versbeginndatum", "", "Date", "10", "", "", "", "", "", "Versicherungsbeginn", "LBL_Versicherungsbeginn", "versbdt", "", "", "", "", "", ""));
		doModel.add(new RowData("VersNr", "", "String", "15", "", "", "", "", "", "Vers.-Nr.", "LBL_VersNr", "vppono", "", "", "", "", "", "Versicherungsnummer"));
		doModel.add(new RowData("Zivilstand", "", "short", "", "", "true", "", "", "", "Zivilstand", "LBL_Zivilstand", "vpzivils", "", "", "189", "", "", "1 = ledig ,2 = verheiratet ,3 = geschieden ,4 = verwitwet ,5 = in eingetragener Partnerschaft ,6 = aufgelöste Partnerschaft ,7 = getrennt"));
		doModel.add(new RowData("Adr_Adresszusatz", "", "String", "35", "", "", "", "", "", "Adresszusatz", "LBL_Adresszusatz", "vpzusadr", "", "", "", "", "", ""));
		doModel.add(new RowData("Adr_Hausnummer", "", "String", "8", "", "", "", "", "", "Hausnummer", "", "", "", "", "", "", "", ""));
		doModel.add(new RowData("Adr_Landcode", "", "short", "", "", "true", "", "", "", "Land", "LBL_Land", "vpintkfz", "", "19", "1", "", "", ""));
		doModel.add(new RowData("Adr_Ort", "", "String", "40", "", "true", "", "", "", "Ort", "LBL_Wohnort", "vport", "", "", "", "", "", ""));
		doModel.add(new RowData("Adr_Plz", "", "String", "6", "", "true", "", "", "", "Plz", "LBL_PLZ", "vpplz", "", "", "", "", "", ""));
		doModel.add(new RowData("Adr_Postfach", "", "String", "10", "", "", "", "", "", "Postfach", "LBL_Postfach", "vpzusstr", "", "", "", "", "", ""));
		doModel.add(new RowData("Adr_Strasse", "", "String", "35", "", "true", "", "", "", "Strasse", "LBL_Strasse", "", "", "", "", "", "", ""));
		doModel.add(new RowData("Adr_StrasseNr", "", "String", "", "", "", "true", "", "true", "Strasse, Nr.", "LBL_Strasse", "vpstrass", "", "", "", "true", "true", "Strasse + Hausnummer"));
	}
}