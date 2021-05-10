package co.com.inventario.controller;

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

import co.com.inventario.dto.CargosDTO;
import co.com.inventario.exception.ExceptionManager;
import co.com.inventario.mapper.CargosMapper;
import co.com.inventario.modelo.Cargos;
import co.com.inventario.service.CargosService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/cargos")
@CrossOrigin(origins = "*")
public class CargosRestController {

	@Autowired
	private CargosService cargosService;
	@Autowired
	private CargosMapper cargosMapper;

	@GetMapping(value = "/findById/{idcargo}")
	public ResponseEntity<CargosDTO> findById(@PathVariable("idcargo") Integer idcargo) {
		log.debug("Request to findById() Cargos: {}", idcargo);

		Optional<Cargos> value = cargosService.findById(idcargo);
		Cargos cargos = null;

		if (value.isPresent()) {
			cargos = value.get();
		}else{
			throw new ExceptionManager().new NotFoundException("Cargos");
		}

		return ResponseEntity.ok().body(cargosMapper.cargosToCargosDTO(cargos));
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<List<CargosDTO>> findAll() {
		log.debug("Request to findAll() Cargos");
		return ResponseEntity.ok().body(cargosMapper.listCargosToListCargosDTO(cargosService.findAll()));
	}

	@PostMapping(value = "/save")
	public ResponseEntity<CargosDTO> save(@RequestBody CargosDTO cargosDTO) {
		log.debug("Request to save Cargos: {}", cargosDTO);

		Cargos cargos = cargosMapper.cargosDTOToCargos(cargosDTO);
		cargos = cargosService.save(cargos);

		return ResponseEntity.ok().body(cargosMapper.cargosToCargosDTO(cargos));
	}

	@PutMapping(value = "/update")
	public ResponseEntity<CargosDTO> update(@RequestBody CargosDTO cargosDTO) {
		log.debug("Request to update Cargos: {}", cargosDTO);

		Cargos cargos = cargosMapper.cargosDTOToCargos(cargosDTO);
		cargos = cargosService.update(cargos);

		return ResponseEntity.ok().body(cargosMapper.cargosToCargosDTO(cargos));
	}

	@DeleteMapping(value = "/delete/{idcargo}")
	public ResponseEntity<Object> delete( @PathVariable("idcargo") Integer idcargo) {
		log.debug("Request to delete Cargos: {}  ", idcargo);

		Optional<Cargos> value = cargosService.findById(idcargo);

		if (value.isPresent()) {
			cargosService.delete(value.get());
		}else{
			throw new ExceptionManager().new NotFoundException("Cargos");
		}
		return ResponseEntity.ok().build();
	}
}
