package  co.com.inventario.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.inventario.modelo.Cargos;
import co.com.inventario.repository.CargosRepository;
import co.com.inventario.utility.Utilities;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Edwin Gonzalez
 * 
 */
@Slf4j
@Scope("singleton")
@Service
public class CargosServiceImpl implements CargosService{

	@Autowired
	private CargosRepository cargosRepository;

	@Autowired
	private Validator validator;

	@Override        		
	public void validate(Cargos cargos){		
		Utilities.validate(cargos, validator);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cargos> findAll(){
		log.debug("finding all Cargos instances");
		return cargosRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
	public Cargos save(Cargos entity){
		log.debug("saving Cargos instance");

		Utilities.validationObjeto(entity, "Cargos");
		validate(entity);
		return cargosRepository.save(entity);
	}

	@Override
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public void delete(Cargos entity) {
		log.debug("deleting Cargos instance");

		Utilities.validationObjeto(entity, "Cargos ");
		Utilities.validationObjeto(entity.getIdcargo(), "idcargo");

		cargosRepository.deleteById(entity.getIdcargo());
		log.debug("delete Cargos successful");
	}

	@Override
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public Cargos update(Cargos entity) {
		log.debug("updating Cargos instance");

		Utilities.validationObjeto(entity, "Cargos");
		validate(entity);
		return cargosRepository.save(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Cargos> findById(Integer idcargo) {            
		log.debug("getting Cargos instance");
		return cargosRepository.findById(idcargo);
	}

}