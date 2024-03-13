package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Instrument;
import util.JPAutil;

public class InstrumentDaoImpl implements IInstrumentDao {
	private EntityManager entityManager = JPAutil.getEntityManager("TP5");

	@Override
	public Instrument save(Instrument i) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(i);
		tx.commit();
		return i;
	}

	@Override
		public List<Instrument> instrumentsParMC(String mc) {
		List<Instrument> instrs =
		entityManager.createQuery("select i from Instrument i where i.nomInstrument like :mc").setParameter("mc", "%"+mc+"%").getResultList();

		return instrs;
		}

	@Override
	public Instrument getInstrument(Long id) {
		return entityManager.find(Instrument.class, id);
	}

	@Override
	public Instrument updateInstrument(Instrument i) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(i);
		tx.commit();
		return i;
	}

	@Override
	public void deleteInstrument(Long id) {
		Instrument instrument = entityManager.find(Instrument.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(instrument);
		entityManager.getTransaction().commit();
	}
}