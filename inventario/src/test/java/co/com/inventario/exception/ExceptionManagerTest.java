package co.com.inventario.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import co.com.inventario.exception.ExceptionManager.DeletingException;
import co.com.inventario.exception.ExceptionManager.EmptyFieldException;
import co.com.inventario.exception.ExceptionManager.NotFoundException;
import co.com.inventario.exception.ExceptionManager.NullEntityExcepcion;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExceptionManagerTest {

	@Test
	public void nullEntityExcepcionTest(){
		Assertions.assertThrows(NullEntityExcepcion.class, () -> {
			throw new ExceptionManager().new NullEntityExcepcion("Entidad Vacia");
		});
	}
	
	@Test
	public void emptyFieldExceptionTest(){
		Assertions.assertThrows(EmptyFieldException.class, () -> {
			throw new ExceptionManager().new EmptyFieldException("Campo Vacio");
		});
	}
	
	@Test
	public void deletingExceptionTest(){
		Assertions.assertThrows(DeletingException.class, () -> {
			throw new ExceptionManager().new DeletingException("Borrando Entidad");
		});
	}
	
	@Test
	public void notFoundExceptionTest(){
		Assertions.assertThrows(NotFoundException.class, () -> {
			throw new ExceptionManager().new NotFoundException ("Concentrador");
		});
	}

}	
