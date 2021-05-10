package  co.com.inventario.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.inventario.exception.ExceptionManager;
import co.com.inventario.modelo.Productos;
import co.com.inventario.repository.ProductosRepository;
import co.com.inventario.utility.Utilities;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Edwin Gonzalez
 * 
 */
@Slf4j
@Scope("singleton")
@Service
public class ProductosServiceImpl implements ProductosService{

	@Autowired
	private ProductosRepository productosRepository;

	@Autowired
	private Validator validator;

	@Override        		
	public void validate(Productos productos){
		Utilities.validate(productos, validator);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Productos> findAll(){
		log.debug("finding all Productos instances");
		return productosRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
	public Productos save(Productos entity){
		log.debug("saving Productos instance");

		entity.setFechacreacion(new Date());
		Utilities.validationObjeto(entity, "Productos");
		validate(entity);
		
		entity.setNombreproducto(entity.getNombreproducto().toUpperCase());

		Long count =productosRepository.existeNombre(entity.getNombreproducto());
		if(count>0) {
			throw new ExceptionManager("El nombre ingresado ya se encuentra en uso");
		}
		
		return productosRepository.save(entity);
	}

	@Override
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public void delete(Productos entity) {
		log.debug("deleting Productos instance");

		Utilities.validationObjeto(entity, "Productos ");
		Utilities.validationObjeto(entity.getIdproducto(), "idproducto");

		productosRepository.deleteById(entity.getIdproducto());
		log.debug("delete Productos successful");
	}

	@Override
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public Productos update(Productos entity) {
		log.debug("updating Productos instance");

		Utilities.validationObjeto(entity, "Productos");
		validate(entity);
		
		entity.setNombreproducto(entity.getNombreproducto().toUpperCase());
		entity.setFechamodificacion(new Date());
		Long count =productosRepository.existeNombreDiferenteActual(entity.getNombreproducto(), entity.getIdproducto());
		if(count>0) {
			throw new ExceptionManager("El nombre ingresado ya se encuentra en uso");
		}
		
		return productosRepository.save(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Productos> findById(Integer idproducto) {            
		log.debug("getting Productos instance");
		return productosRepository.findById(idproducto);
	}

}