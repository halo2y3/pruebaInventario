package co.com.inventario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import co.com.inventario.dto.UsuariosDTO;
import co.com.inventario.exception.ResponseExceptionHandler;
import lombok.extern.slf4j.Slf4j;


/**
* @author Edwin Gonzalez
*
*/
@Slf4j
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)	
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuariosControllerTest {
    @Autowired
    private UsuariosRestController usuariosRestController;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ResponseExceptionHandler responseExceptionHandler;
	
	public UsuariosDTO usuarioDto;
	
	@Order(1)
    @Test
    @DisplayName("findAll")
    public void findAll() {
		ResponseEntity<List<UsuariosDTO>> respuesta=usuariosRestController.findAll();
		assertEquals(200, respuesta.getStatusCodeValue());
    }
    
	@Order(2)
    @Test
    @DisplayName("save")
    public void save() throws Exception {
		usuarioDto=new UsuariosDTO();
		usuarioDto.setIdusuario(null);
		usuarioDto.setEdad(22);
		usuarioDto.setFechaingreso(new Date());
		usuarioDto.setNombreusuario("User pruebas");
		usuarioDto.setIdcargoCargos(1);
		
		ResponseEntity<UsuariosDTO> respuesta=usuariosRestController.save(usuarioDto);
		usuarioDto=respuesta.getBody();
		log.info("Usuario Creado: {}", usuarioDto.toString());
		assertEquals(200, respuesta.getStatusCodeValue());
    }

	@Order(3)
	@Test
    @DisplayName("update")
    public void update() throws Exception {
		ResponseEntity<UsuariosDTO> respuesta=usuariosRestController.update(usuarioDto);
		assertEquals(200, respuesta.getStatusCodeValue());
    }
	
	@Order(4)
    @Test
    @DisplayName("findById")
    public void findById() throws Exception {
		ResponseEntity<UsuariosDTO> respuesta=usuariosRestController.findById(usuarioDto.getIdusuario());
		assertEquals(200, respuesta.getStatusCodeValue());
    }
	
	@Order(5)
	@Test
	@DisplayName("findByIdError")
    public  void findByIdError() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(usuariosRestController)
	            .setControllerAdvice(responseExceptionHandler)
	            .build();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/usuarios/findById/-100"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
	@Order(6)
    @Test
    @DisplayName("delete")
    public void delete() throws Exception {
		ResponseEntity<Object> respuesta=usuariosRestController.delete(usuarioDto.getIdusuario());
		assertEquals(200, respuesta.getStatusCodeValue());
    }
	
	@Order(7)
	@Test
	@DisplayName("deleteError")
    public void deleteError() throws Exception  {
		mockMvc = MockMvcBuilders.standaloneSetup(usuariosRestController)
	            .setControllerAdvice(responseExceptionHandler)
	            .build();
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/usuarios/delete/"+usuarioDto.getIdusuario()))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
