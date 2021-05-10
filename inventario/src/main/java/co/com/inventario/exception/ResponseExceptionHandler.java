package co.com.inventario.exception;

import java.util.Date;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.inventario.exception.ExceptionManager.NotFoundException;
import co.com.inventario.exception.ExceptionManager.NullEntityExcepcion;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
		
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("ERROR InvalidDataAccessApiUsageException:", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validacion fallida", request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public final ResponseEntity<ExceptionResponse> invalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex, WebRequest request){
		log.error("ERROR InvalidDataAccessApiUsageException:", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(Exception ex, WebRequest request){
		log.error("ERROR Exception:", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Ocurrio un error desconocido", request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(ExceptionManager.class)
	public final ResponseEntity<ExceptionResponse> manejarModeloExcepciones(ExceptionManager ex, WebRequest request) {
		log.debug("ERROR ExceptionManager:", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullEntityExcepcion.class)
	public final ResponseEntity<ExceptionResponse> exceptionData(NullEntityExcepcion ex, WebRequest request) {
		log.debug("ERROR NullEntityExcepcion:", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ExceptionResponse> exceptionNotFoundData(NotFoundException ex, WebRequest request) {
		log.debug("ERROR NotFoundException:", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@Getter
	@Setter
	public class ExceptionResponse {

		private Date timestamp;
		private String mensaje;
		private String detalles;

		public ExceptionResponse() {
		}
		
		public ExceptionResponse(Date timestamp, String mensaje, String detalles) {
			this.timestamp = timestamp;
			this.mensaje = mensaje;
			this.detalles = detalles;
		}

	}
	
}
