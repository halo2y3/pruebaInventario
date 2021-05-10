package  co.com.inventario.service;

import java.util.List;
import java.util.Optional;

/**
 * @author Edwin Gonzalez
 * 
 */
public interface GenericService <T,I>{

	public List<T> findAll();

	public T save(T entity);

	public T update(T entity);

	public void delete(T entity);

	public Optional<T> findById(I clieId);

	public void validate(T entity);

}
