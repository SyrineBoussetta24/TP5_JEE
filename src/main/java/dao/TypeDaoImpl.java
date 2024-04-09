package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Type;
import util.JPAutil;

public class TypeDaoImpl implements ITypeDao {
// TP6_JEE à replacer par votre persistence unit, consultez votre
//fichier persistence.xml <persistence-unit name="TP6_JEE">
	private EntityManager entityManager = JPAutil.getEntityManager("TP5");

	@Override
	public Type save(Type typ) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(typ);
		tx.commit();
		return typ;
	}

	@Override
	public Type getType(Long id) {
		return entityManager.find(Type.class, id);
	}

	@Override
	public Type updateType(Type typ) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(typ);
		tx.commit();
		return typ;
	}

	@Override
	public void deleteType(Long id) {
		Type type = entityManager.find(Type.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(type);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Type> getAllTypes() {
		List<Type> typs =

				entityManager.createQuery("select t from Type t").getResultList();
		return typs;
	}
}