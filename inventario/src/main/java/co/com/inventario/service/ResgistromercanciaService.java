package co.com.inventario.service;

import java.util.Date;
import java.util.List;

import co.com.inventario.dto.ResgistromercanciaDTO;
import co.com.inventario.modelo.Resgistromercancia;


/**
* @author Edwin Gonzalez
*
*/
public interface ResgistromercanciaService extends GenericService<Resgistromercancia, Integer> {
	
	public List<ResgistromercanciaDTO> consultarMercanciaPorFechaUsuario(Integer idUsuario, Date fechaRegistro);
	
}
