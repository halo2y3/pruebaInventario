package  co.com.inventario.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.inventario.modelo.Usuarios;
import co.com.inventario.repository.UsuariosRepository;
import co.com.inventario.utility.Utilities;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Edwin Gonzalez
 * 
 */
@Slf4j
@Scope("singleton")
@Service
public class UsuariosServiceImpl implements UsuariosService{

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
	private Validator validator;

	@Override        		
	public void validate(Usuarios usuarios){		
		Utilities.validate(usuarios, validator);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuarios> findAll(){
		log.debug("finding all Usuarios instances");
		return usuariosRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
	public Usuarios save(Usuarios entity){
		log.debug("saving Usuarios instance");

		Utilities.validationObjeto(entity, "Usuarios");
		validate(entity);

		return usuariosRepository.save(entity);
	}

	@Override
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public void delete(Usuarios entity) {
		log.debug("deleting Usuarios instance");
		Utilities.validationObjeto(entity, "Usuarios ");
		Utilities.validationObjeto(entity.getIdusuario(), "idusuario");

		usuariosRepository.deleteById(entity.getIdusuario());
		log.debug("delete Usuarios successful");
	}

	@Override
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public Usuarios update(Usuarios entity) {
		log.debug("updating Usuarios instance");

		Utilities.validationObjeto(entity, "Usuarios");
		validate(entity);
		return usuariosRepository.save(entity);		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Usuarios> findById(Integer idusuario) {            
		log.debug("getting Usuarios instance");
		return usuariosRepository.findById(idusuario);
	}

}