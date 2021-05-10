package co.com.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.inventario.modelo.Usuarios;


/**
* Interface for   UsuariosRepository.
*
*/
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
}
