package test;

import java.util.List;

import dao.InstrumentDaoImpl;
import metier.entities.Instrument;

public class TestDao {
	public static void main(String[] args) {
		InstrumentDaoImpl pdao = new InstrumentDaoImpl();
		Instrument instr = pdao.save(new Instrument("batterie", 2800));
		System.out.println(instr);
		List<Instrument> instrs = pdao.instrumentsParMC("batterie1");
		for (Instrument i : instrs)
			System.out.println(i);
	}
}