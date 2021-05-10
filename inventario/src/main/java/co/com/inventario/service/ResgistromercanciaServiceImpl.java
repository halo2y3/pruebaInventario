package co.com.inventario.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.inventario.dto.ResgistromercanciaDTO;
import co.com.inventario.exception.ExceptionManager;
import co.com.inventario.modelo.Resgistromercancia;
import co.com.inventario.repository.ResgistromercanciaRepository;
import co.com.inventario.utility.Utilities;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Edwin Gonzalez
 *
 */
@Slf4j
@Scope("singleton")
@Service
public class ResgistromercanciaServiceImpl implements ResgistromercanciaService {
	@Autowired
	private ResgistromercanciaRepository resgistromercanciaRepository;
	@Autowired
	private Validator validator;

	@Override
	public void validate(Resgistromercancia resgistromercancia) {
		Utilities.validate(resgistromercancia, validator);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Resgistromercancia> findAll() {
		log.debug("finding all Resgistromercancia instances");
		return resgistromercanciaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Resgistromercancia save(Resgistromercancia entity) {
		log.debug("saving Resgistromercancia instance");

		Utilities.validationObjeto(entity, "Resgistromercancia ");
		validate(entity);
		
		if(entity.getCantidad()<=0) {
			throw new ExceptionManager("La catidad debe ser un numero positivo");
		}
		
		if(!Utilities.validarFechaMenor(entity.getFechaingresoegreso())) {
			throw new ExceptionManager("La fecha de ingreso debe ser menor o igual a la fecha actual");
		}
		
		return resgistromercanciaRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Resgistromercancia entity) {
		log.debug("deleting Resgistromercancia instance");

		Utilities.validationObjeto(entity, "Resgistromercancia");
		Utilities.validationObjeto(entity.getIdregistro(), "idregistro");
		
		resgistromercanciaRepository.deleteById(entity.getIdregistro());
		log.debug("delete Resgistromercancia successful");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Resgistromercancia update(Resgistromercancia entity) {
		log.debug("updating Resgistromercancia instance");

		Utilities.validationObjeto(entity, "Resgistromercancia");
		validate(entity);

		if(entity.getCantidad()<=0) {
			throw new ExceptionManager("La catidad debe ser un numero positivo");
		}
		
		if(!Utilities.validarFechaMenor(entity.getFechaingresoegreso())) {
			throw new ExceptionManager("La fecha de ingreso debe ser menor o igual a la fecha actual");
		}
		
		entity.setFechamodificacion(new Date());
		
		return resgistromercanciaRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Resgistromercancia> findById(Integer idregistro) {
		log.debug("getting Resgistromercancia instance");
		return resgistromercanciaRepository.findById(idregistro);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ResgistromercanciaDTO> consultarMercanciaPorFechaUsuario(Integer idUsuario, Date fechaRegistro) {
		
		if(idUsuario==null && fechaRegistro==null) {
			throw new ExceptionManager("Ingrese Usuario o Fecha para realizar consulta");
		}
		
		if(idUsuario!=null && fechaRegistro!=null) {
			return resgistromercanciaRepository.consultarMercanciaPorFechaUsuario(idUsuario, fechaRegistro);	
		}else if(fechaRegistro!=null){
			return resgistromercanciaRepository.consultarMercanciaPorFecha(fechaRegistro);
		}else {
			return resgistromercanciaRepository.consultarMercanciaPorUsuario(idUsuario);
		}
	}
}
