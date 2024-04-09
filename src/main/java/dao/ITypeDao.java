package dao;

import java.util.List;
import metier.entities.Type;

public interface ITypeDao {
	public Type save(Type typ);

	public Type getType(Long id);

	public Type updateType(Type typ);

	public void deleteType(Long id);

	public List<Type> getAllTypes();
}