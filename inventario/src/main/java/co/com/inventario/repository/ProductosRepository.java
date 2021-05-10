package co.com.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.inventario.modelo.Productos;


/**
 * Interface for   ProductosRepository.
 *
 */
public interface ProductosRepository extends JpaRepository<Productos, Integer> {

	@Query(value = "SELECT count(*) FROM productos WHERE nombreproducto =?1 ", nativeQuery = true)
	public Long existeNombre(String nombre);

	@Query(value = "SELECT count(*) FROM productos WHERE nombreproducto =?1 AND idproducto!=?2", nativeQuery = true)
	public Long existeNombreDiferenteActual(String nombre, Integer idProducto);
}
