package dao;

import java.util.List;

import metier.entities.Instrument;

public interface IInstrumentDao {
	public Instrument save(Instrument i);

	public List<Instrument> instrumentsParMC(String mc);

	public Instrument getInstrument(Long id);

	public Instrument updateInstrument(Instrument i);

	public void deleteInstrument(Long id);
}
