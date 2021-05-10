package co.com.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.inventario.modelo.Cargos;


/**
* Interface for   CargosRepository.
*
*/
public interface CargosRepository extends JpaRepository<Cargos, Integer> {
}
