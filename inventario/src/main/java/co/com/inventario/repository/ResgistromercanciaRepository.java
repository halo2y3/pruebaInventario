package co.com.inventario.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.inventario.dto.ResgistromercanciaDTO;
import co.com.inventario.modelo.Resgistromercancia;


/**
* Interface for   ResgistromercanciaRepository.
*
*/
public interface ResgistromercanciaRepository extends JpaRepository<Resgistromercancia, Integer> {
	
	@Query(value = "SELECT NEW co.com.inventario.dto.ResgistromercanciaDTO(regm.cantidad, regm.fechaingresoegreso, regm.fechamodificacion, regm.fecharegistro, regm.idregistro, regm.tiporegistro, regm.productos.idproducto, regm.usuariosByUsuarioidregistrador.idusuario, regm.usuariosByUsuarioidmodificador.idusuario, pro.nombreproducto) "
			+ "FROM co.com.inventario.modelo.Resgistromercancia regm "
			+ "INNER JOIN co.com.inventario.modelo.Productos pro ON pro.idproducto=regm.productos.idproducto "
			+ "WHERE (regm.usuariosByUsuarioidregistrador.idusuario=?1 OR regm.usuariosByUsuarioidmodificador.idusuario=?1) AND regm.fechaingresoegreso=?2 "
			+ "ORDER BY regm.idregistro DESC")
	public List<ResgistromercanciaDTO> consultarMercanciaPorFechaUsuario(Integer idUsuario, Date fechaRegistro);
	
	@Query(value = "SELECT NEW co.com.inventario.dto.ResgistromercanciaDTO(regm.cantidad, regm.fechaingresoegreso, regm.fechamodificacion, regm.fecharegistro, regm.idregistro, regm.tiporegistro, regm.productos.idproducto, regm.usuariosByUsuarioidregistrador.idusuario, regm.usuariosByUsuarioidmodificador.idusuario, pro.nombreproducto) "
			+ "FROM co.com.inventario.modelo.Resgistromercancia regm "
			+ "INNER JOIN co.com.inventario.modelo.Productos pro ON pro.idproducto=regm.productos.idproducto "
			+ "WHERE (regm.usuariosByUsuarioidregistrador.idusuario=?1 OR regm.usuariosByUsuarioidmodificador.idusuario=?1) "
			+ "ORDER BY regm.idregistro DESC")
	public List<ResgistromercanciaDTO> consultarMercanciaPorUsuario(Integer idUsuario);
	
	@Query(value = "SELECT NEW co.com.inventario.dto.ResgistromercanciaDTO(regm.cantidad, regm.fechaingresoegreso, regm.fechamodificacion, regm.fecharegistro, regm.idregistro, regm.tiporegistro, regm.productos.idproducto, regm.usuariosByUsuarioidregistrador.idusuario, regm.usuariosByUsuarioidmodificador.idusuario, pro.nombreproducto) "
			+ "FROM co.com.inventario.modelo.Resgistromercancia regm "
			+ "INNER JOIN co.com.inventario.modelo.Productos pro ON pro.idproducto=regm.productos.idproducto "
			+ "WHERE regm.fechaingresoegreso=?1 "
			+ "ORDER BY regm.idregistro DESC")
	public List<ResgistromercanciaDTO> consultarMercanciaPorFecha(Date fechaRegistro);
	
}
