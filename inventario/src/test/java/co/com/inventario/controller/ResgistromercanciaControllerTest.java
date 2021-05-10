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

import co.com.inventario.dto.ResgistromercanciaDTO;
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
public class ResgistromercanciaControllerTest {
    @Autowired
    private ResgistromercanciaRestController resgistromercanciaRestController;
    
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ResponseExceptionHandler responseExceptionHandler;
	
	public ResgistromercanciaDTO resgistromercanciaDto;
	
	@Order(1)
    @Test
    @DisplayName("findAll")
    public void findAll() {
		ResponseEntity<List<ResgistromercanciaDTO>> respuesta=resgistromercanciaRestController.findAll();
		assertEquals(200, respuesta.getStatusCodeValue());
    }
    
	@Order(2)
    @Test
    @DisplayName("save")
    public void save() throws Exception {
		resgistromercanciaDto=new ResgistromercanciaDTO();
		resgistromercanciaDto.setIdregistro(null);
		resgistromercanciaDto.setCantidad(10);
		resgistromercanciaDto.setFecharegistro(new Date());
		resgistromercanciaDto.setFechaingresoegreso(new Date());
		resgistromercanciaDto.setFechamodificacion(new Date());
		resgistromercanciaDto.setIdproductoProductos(1);
		resgistromercanciaDto.setIdusuarioModificador(null);
		resgistromercanciaDto.setIdusuarioRegistrador(1);
		resgistromercanciaDto.setTiporegistro("Prueba");
		
		ResponseEntity<ResgistromercanciaDTO> respuesta=resgistromercanciaRestController.save(resgistromercanciaDto);
		resgistromercanciaDto=respuesta.getBody();
		log.info("Registromercancia Creado: {}", resgistromercanciaDto.toString());
		assertEquals(200, respuesta.getStatusCodeValue());
    }

	@Order(3)
	@Test
    @DisplayName("update")
    public void update() throws Exception {
		ResponseEntity<ResgistromercanciaDTO> respuesta=resgistromercanciaRestController.update(resgistromercanciaDto);
		assertEquals(200, respuesta.getStatusCodeValue());
    }
	
	@Order(4)
    @Test
    @DisplayName("findById")
    public void findById() throws Exception {
		ResponseEntity<ResgistromercanciaDTO> respuesta=resgistromercanciaRestController.findById(resgistromercanciaDto.getIdregistro());
		assertEquals(200, respuesta.getStatusCodeValue());
    }
	
	@Order(5)
	@Test
	@DisplayName("findByIdError")
    public  void findByIdError() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(resgistromercanciaRestController)
	            .setControllerAdvice(responseExceptionHandler)
	            .build();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/resgistromercancia/findById/-100"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
	@Order(6)
    @Test
    @DisplayName("delete")
    public void delete() throws Exception {
		ResponseEntity<Object> respuesta=resgistromercanciaRestController.delete(resgistromercanciaDto.getIdregistro(), resgistromercanciaDto.getIdusuarioRegistrador());
		assertEquals(200, respuesta.getStatusCodeValue());
    }
	
	@Order(7)
	@Test
	@DisplayName("deleteError")
    public void deleteError() throws Exception  {
		mockMvc = MockMvcBuilders.standaloneSetup(resgistromercanciaRestController)
	            .setControllerAdvice(responseExceptionHandler)
	            .build();
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/resgistromercancia/delete/"+resgistromercanciaDto.getIdregistro()))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
}
