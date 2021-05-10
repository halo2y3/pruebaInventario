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

import co.com.inventario.dto.UsuariosDTO;
import co.com.inventario.exception.ExceptionManager;
import co.com.inventario.mapper.UsuariosMapper;
import co.com.inventario.modelo.Usuarios;
import co.com.inventario.service.UsuariosService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosRestController {

	@Autowired
	private UsuariosService usuariosService;
	@Autowired
	private UsuariosMapper usuariosMapper;

	@GetMapping(value = "/findById/{idusuario}")
	public ResponseEntity<UsuariosDTO> findById(@PathVariable("idusuario") Integer idusuario) {
		log.debug("Request to findById() Usuarios: {}", idusuario);

		Optional<Usuarios> value = usuariosService.findById(idusuario);
		Usuarios usuarios = null;

		if (value.isPresent()) {
			usuarios = value.get();
		}else{
			throw new ExceptionManager().new NotFoundException("Usuarios");
		}

		return ResponseEntity.ok().body(usuariosMapper.usuariosToUsuariosDTO(usuarios));
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<List<UsuariosDTO>> findAll() {
		log.debug("Request to findAll() Usuarios");
		return ResponseEntity.ok().body(usuariosMapper.listUsuariosToListUsuariosDTO(usuariosService.findAll()));
	}

	@PostMapping(value = "/save")
	public ResponseEntity<UsuariosDTO> save(@RequestBody UsuariosDTO usuariosDTO) {
		log.debug("Request to save Usuarios: {}", usuariosDTO);

		Usuarios usuarios = usuariosMapper.usuariosDTOToUsuarios(usuariosDTO);
		usuarios = usuariosService.save(usuarios);

		return ResponseEntity.ok().body(usuariosMapper.usuariosToUsuariosDTO(usuarios));
	}

	@PutMapping(value = "/update")
	public ResponseEntity<UsuariosDTO> update(@RequestBody UsuariosDTO usuariosDTO) {
		log.debug("Request to update Usuarios: {}", usuariosDTO);

		Usuarios usuarios = usuariosMapper.usuariosDTOToUsuarios(usuariosDTO);
		usuarios = usuariosService.update(usuarios);

		return ResponseEntity.ok().body(usuariosMapper.usuariosToUsuariosDTO(usuarios));
	}

	@DeleteMapping(value = "/delete/{idusuario}")
	public ResponseEntity<Object> delete(@PathVariable("idusuario") Integer idusuario) {
		log.debug("Request to delete Usuarios: {}", idusuario);

		Optional<Usuarios> value = usuariosService.findById(idusuario);
		Usuarios usuarios = null;

		if (value.isPresent()) {
			usuarios = value.get();
			usuariosService.delete(usuarios);
		}else{
			throw new ExceptionManager().new NotFoundException("Usuarios");
		}

		return ResponseEntity.ok().build();
	}
}
