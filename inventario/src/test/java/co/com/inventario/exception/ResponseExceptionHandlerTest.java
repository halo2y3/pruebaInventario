package co.com.inventario.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.MethodParameter;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import co.com.inventario.exception.ResponseExceptionHandler.ExceptionResponse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ResponseExceptionHandlerTest {	
	
	@Autowired
	ResponseExceptionHandler responseExceptionHandler;
	
    @Mock
    WebRequest webRequest;
    @Mock
    MethodParameter parameter;
    @Mock
    BindingResult bindingResult;
    
	@Test
	public void handleMethodArgumentNotValid(){
		ResponseEntity<Object> respuesta=responseExceptionHandler.handleMethodArgumentNotValid(new MethodArgumentNotValidException(parameter, bindingResult), null, null, webRequest);
		assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
	}
	
	@Test
	public void invalidDataAccessApiUsageExceptionTest(){
		ResponseEntity<ExceptionResponse> respuesta=responseExceptionHandler.invalidDataAccessApiUsageException(new InvalidDataAccessApiUsageException("Concentrador"), webRequest);
		assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
	}
	
	@Test
	public void manejarTodasExcepcionesTest(){
		ResponseEntity<ExceptionResponse> respuesta=responseExceptionHandler.manejarTodasExcepciones(new Exception("Concentrador"), webRequest);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, respuesta.getStatusCode());
	}
	
	@Test
	public void manejarModeloExcepcionesTest(){
		ResponseEntity<ExceptionResponse> respuesta=responseExceptionHandler.manejarModeloExcepciones(new ExceptionManager("Concentrador"), webRequest);
		assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
	}
	
	@Test
	public void exceptionDataTest(){
		ResponseEntity<ExceptionResponse> respuesta=responseExceptionHandler.exceptionData(new ExceptionManager().new NullEntityExcepcion("Concentrador"), webRequest);
		assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
	}
	
	@Test
	public void exceptionNotFoundDataTest(){
		ResponseEntity<ExceptionResponse> respuesta=responseExceptionHandler.exceptionNotFoundData(new ExceptionManager().new NotFoundException("Concentrador"), webRequest);
		assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
	}
	
	@Test
	public void setExceptionResponseTest(){
		ExceptionResponse exception=responseExceptionHandler.new ExceptionResponse();
		exception.setDetalles("detalles");
		exception.setMensaje("mensaje");
		exception.setTimestamp(new Date());
		assertEquals("detalles", exception.getDetalles());
	}

}	
