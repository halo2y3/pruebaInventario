package co.com.inventario.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.inventario.dto.ParametrosDTO;
import co.com.inventario.dto.ResgistromercanciaDTO;
import co.com.inventario.exception.ExceptionManager;
import co.com.inventario.mapper.ResgistromercanciaMapper;
import co.com.inventario.modelo.Resgistromercancia;
import co.com.inventario.service.ResgistromercanciaService;
import co.com.inventario.utility.Utilities;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/resgistromercancia")
@CrossOrigin(origins = "*")
public class ResgistromercanciaRestController {
	
	@Autowired
	private ResgistromercanciaService resgistromercanciaService;
	@Autowired
	private ResgistromercanciaMapper resgistromercanciaMapper;

	@PostMapping(value = "/findByUsuarioFecha")
	public ResponseEntity<List<ResgistromercanciaDTO>> findByUsuarioFecha(@RequestBody ParametrosDTO parametros) {
		log.debug("Request to findById() findByUsuarioFecha Parametros: {}", parametros);

		List<ResgistromercanciaDTO> listaMercancia = resgistromercanciaService.consultarMercanciaPorFechaUsuario(parametros.getIdUsuario(), Utilities.parsearFecha(parametros.getFechaRegistro()));

		if (listaMercancia.isEmpty()) {
			throw new ExceptionManager().new NotFoundException("el sistema con la informacion ingresada");
		}

		return ResponseEntity.ok().body(listaMercancia);
	}
	
	@GetMapping(value = "/findById/{idregistro}")
	public ResponseEntity<ResgistromercanciaDTO> findById(@PathVariable("idregistro") Integer idregistro) {
		log.debug("Request to findById() Resgistromercancia: {}", idregistro);

		Optional<Resgistromercancia> value = resgistromercanciaService.findById(idregistro);
		Resgistromercancia resgistromercancia = null;

		if (value.isPresent()) {
			resgistromercancia = value.get();
		}else{
			throw new ExceptionManager().new NotFoundException("Resgistromercancia");
		}

		return ResponseEntity.ok().body(resgistromercanciaMapper.resgistromercanciaToResgistromercanciaDTO(resgistromercancia));
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<List<ResgistromercanciaDTO>> findAll() {
		log.debug("Request to findAll() Resgistromercancia");

		return ResponseEntity.ok().body(resgistromercanciaMapper.listResgistromercanciaToListResgistromercanciaDTO(resgistromercanciaService.findAll()));
	}

	@PostMapping(value = "/save")
	public ResponseEntity<ResgistromercanciaDTO> save(@RequestBody ResgistromercanciaDTO resgistromercanciaDTO) {
		log.debug("Request to save Resgistromercancia: {}", resgistromercanciaDTO);

		Resgistromercancia resgistromercancia = resgistromercanciaMapper.resgistromercanciaDTOToResgistromercancia(resgistromercanciaDTO);
		resgistromercancia.setFecharegistro(new Date());
		resgistromercancia = resgistromercanciaService.save(resgistromercancia);

		return ResponseEntity.ok().body(resgistromercanciaMapper.resgistromercanciaToResgistromercanciaDTO(resgistromercancia));
	}

	@PutMapping(value = "/update")
	public ResponseEntity<ResgistromercanciaDTO> update(@RequestBody ResgistromercanciaDTO resgistromercanciaDTO) {
		log.debug("Request to update Resgistromercancia: {}", resgistromercanciaDTO);

		Resgistromercancia resgistromercancia = resgistromercanciaMapper.resgistromercanciaDTOToResgistromercancia(resgistromercanciaDTO);
		resgistromercancia = resgistromercanciaService.update(resgistromercancia);

		return ResponseEntity.ok().body(resgistromercanciaMapper.resgistromercanciaToResgistromercanciaDTO(resgistromercancia));
	}

	@DeleteMapping(value = "/delete/{idregistro}/{idUsuario}")
	public ResponseEntity<Object> delete(@PathVariable("idregistro") Integer idregistro, @PathVariable("idUsuario") Integer idUsuario) {
		log.debug("Request to delete Resgistromercancia: {} {}", idregistro, idUsuario);

		Optional<Resgistromercancia> value = resgistromercanciaService.findById(idregistro);
		Resgistromercancia resgistromercancia = null;

		if (value.isPresent()) {
			resgistromercancia = value.get();
			if(!resgistromercancia.getUsuariosByUsuarioidregistrador().getIdusuario().equals(idUsuario)) {
				throw new ExceptionManager("El usuario no puede realizar esta accion, solo el usuario creador puede borrar este registro");
			}
			resgistromercanciaService.delete(resgistromercancia);
		}else{
			throw new ExceptionManager().new NotFoundException("Resgistromercancia");
		}

		return ResponseEntity.ok().build();
	}

}
