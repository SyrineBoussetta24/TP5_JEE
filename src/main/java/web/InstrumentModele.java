package web;

import java.util.ArrayList;
import java.util.List;
import metier.entities.Instrument;

public class InstrumentModele {
	private String motCle;
	List<Instrument> instruments = new ArrayList<>();

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public List<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}
}