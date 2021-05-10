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

import co.com.inventario.dto.ProductosDTO;
import co.com.inventario.exception.ExceptionManager;
import co.com.inventario.mapper.ProductosMapper;
import co.com.inventario.modelo.Productos;
import co.com.inventario.service.ProductosService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductosRestController {

	@Autowired
	private ProductosService productosService;
	@Autowired
	private ProductosMapper productosMapper;

	@GetMapping(value = "/findById/{idproducto}")
	public ResponseEntity<ProductosDTO> findById(@PathVariable("idproducto") Integer idproducto) {
		log.debug("Request to findById() Productos: {}", idproducto);

		Optional<Productos> value = productosService.findById(idproducto);
		Productos productos = null;

		if (value.isPresent()) {
			productos = value.get();
		}else{
			throw new ExceptionManager().new NotFoundException("Productos");
		}

		return ResponseEntity.ok().body(productosMapper.productosToProductosDTO(productos));
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<List<ProductosDTO>> findAll() {
		log.debug("Request to findAll() Productos");

		return ResponseEntity.ok().body(productosMapper.listProductosToListProductosDTO(productosService.findAll()));
	}

	@PostMapping(value = "/save")
	public ResponseEntity<ProductosDTO> save(@RequestBody ProductosDTO productosDTO) {
		log.debug("Request to save Productos: {}", productosDTO);

		Productos productos = productosMapper.productosDTOToProductos(productosDTO);
		productos = productosService.save(productos);

		return ResponseEntity.ok().body(productosMapper.productosToProductosDTO(productos));
	}

	@PutMapping(value = "/update")
	public ResponseEntity<ProductosDTO> update(@RequestBody ProductosDTO productosDTO) {
		log.debug("Request to update Productos: {}", productosDTO);

		Productos productos = productosMapper.productosDTOToProductos(productosDTO);
		productos = productosService.update(productos);

		return ResponseEntity.ok().body(productosMapper.productosToProductosDTO(productos));
	}

	@DeleteMapping(value = "/delete/{idproducto}")
	public ResponseEntity<Object> delete(@PathVariable("idproducto") Integer idproducto) {
		log.debug("Request to delete Productos: {}", idproducto);

		Optional<Productos> value = productosService.findById(idproducto);
		Productos productos = null;

		if (value.isPresent()) {
			productos = value.get();
			productosService.delete(productos);
		}else{
			throw new ExceptionManager().new NotFoundException("Productos");
		}

		return ResponseEntity.ok().build();
	}

}
