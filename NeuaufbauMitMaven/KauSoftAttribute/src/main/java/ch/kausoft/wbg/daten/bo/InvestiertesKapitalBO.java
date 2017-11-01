/**
 *
 */
package ch.kausoft.wbg.daten.bo;

import ch.kausoft.attribut.kostenmiete.InvestiertesKapitalBeschreibungA;
import ch.kausoft.basic.DatenSpeicher;
import ch.kausoft.kostenmiete.InvestiertesKapital;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Heinz
 */
public class InvestiertesKapitalBO {

  //@formatter:off
	private InvestiertesKapital dataRecord = null;
	@Getter private Beschreibung beschreibung = new Beschreibung();
	//@formatter:on

  public class Beschreibung extends InvestiertesKapitalBeschreibungA {

    public String getString() {
      return getDataRecord().getBeschreibung();
    }

    public void setString(String value) {
      getDataRecord().setBeschreibung(value);
    }
  }

  public InvestiertesKapital getDataRecord() {
    if (dataRecord == null) {
      // loadNewData();
    }
    return dataRecord;
  }

  public Long getId() {
    return dataRecord.getId();
  }

  public String getBezeichnung() {
    return dataRecord.getBezeichnung();
  }

  public void setBezeichnung(String bezeichnung) {
    this.setBezeichnung(bezeichnung);
  }

  // public String getBeschreibung() {
  // return dataRecord.getBeschreibung();
  // }
  //
  // public void setBeschreibung(String beschreibung) {
  // this.setBeschreibung(beschreibung);
  // }

  public InvestiertesKapitalBO(InvestiertesKapital pInvestiertesKapital) {
    this.dataRecord = pInvestiertesKapital;
  }

  public static List<InvestiertesKapitalBO> loadAll() {
    List<InvestiertesKapitalBO> w = new ArrayList<InvestiertesKapitalBO>();
    DatenSpeicher ds = DatenSpeicher.getDatenSpeicher();
    Collection<InvestiertesKapital> values = ds.getInvestiertesKapital()
            .values();
    for (InvestiertesKapital wohnung : values) {
      w.add(new InvestiertesKapitalBO(wohnung));
    }

    return w;
  }

}
